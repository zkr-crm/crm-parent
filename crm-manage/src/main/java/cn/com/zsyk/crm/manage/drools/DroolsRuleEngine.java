package cn.com.zsyk.crm.manage.drools;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.drools.compiler.compiler.DroolsParserException;
import org.kie.api.io.ResourceType;
import org.kie.internal.KnowledgeBase;
import org.kie.internal.KnowledgeBaseFactory;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.definition.KnowledgePackage;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.runtime.StatefulKnowledgeSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 *
 */

public class DroolsRuleEngine implements IRuleEngine{


    Logger logger = LoggerFactory.getLogger(this.getClass());

    //key:场景(渠道+交易类型),value:KnowledgeBase
    private static Map<String,KnowledgeBase> ruleMap = new ConcurrentHashMap<String, KnowledgeBase>();
    //规则内容
    private String rule = "";

    public DroolsRuleEngine() {
    }

    @Override
    public String getRule() {
        return rule;
    }

    @Override
    public Map<String, Object> excute(String scene, Object factor, Map<String, Object> referMap) throws Exception {
        StatefulKnowledgeSession session = null;
        try{
            Map<String,Object> resultRule = new ConcurrentHashMap<String, Object>();

            session = getDrlSession(scene);
            if(session == null){
                return resultRule;
            }
            //存入因子对象
            session.insert(factor);

            //设置结果全局变量，和drools进行交互
            session.setGlobal("$result", resultRule);
            //设置参考数据全局变量，和drools进行交互
            session.setGlobal("$referMap",referMap);

            //执行规则
            session.fireAllRules();

            return  resultRule;
        }catch(Exception ex){
        	logger.error("",ex);
        	throw new RuntimeException(ex);
        }finally {
            if(session != null){
                session.dispose();
            }
        }
    }

    /**
     * 将所有规则插入到规则引擎内部，初始化
     * @param sceneRules
     */
    @Override
    public void initRules(Map<String,String> sceneRules,String rule) throws Exception{
        if(sceneRules == null || sceneRules.size()<1){
            logger.warn("传送的规则为空！");
            ruleMap.clear();
            return;
        }

        for (Map.Entry<String, String> entry : sceneRules.entrySet()) {
            try {
                putSceneRule(entry.getKey(),entry.getValue());
            }catch (DroolsParserException ex){
                logger.error(entry.getValue(),ex);
                throw ex;
            }
        }

        this.rule = rule;
    }

    /**
     * @param scene 场景
     * @return
     */
    public StatefulKnowledgeSession getDrlSession(String scene) throws DroolsParserException {
        KnowledgeBase kbase =  ruleMap.get(scene);
        if(kbase == null){
        	logger.warn("没有"+scene+"对应场景的规则");
        	return null;
        }
        return kbase.newStatefulKnowledgeSession();
    }

    /**
     * 将规则字符串加载到drools中
     * @param scene：场景
     * @param rule：规则字符串
     * @throws DroolsParserException
     */
    private void putSceneRule(String scene,String rule)
            throws DroolsParserException {

        Reader strReader = null;
        try{
            //构建kbuilder
            final KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
            strReader = new StringReader(rule);
            kbuilder.add(ResourceFactory.newReaderResource(strReader), ResourceType.DRL);

            if (kbuilder.hasErrors()) {
                throw new RuntimeException(kbuilder.getErrors()+"Unable to compile drool rules.");
            }

            //获取kbase
            Collection<KnowledgePackage> pkgs = kbuilder.getKnowledgePackages();
            KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
            kbase.addKnowledgePackages(pkgs);

            //将场景和对应的kbase放入内存中
            ruleMap.put(scene, kbase);
        }finally {
            if(strReader != null){
                try {
                    strReader.close();
                }catch (IOException ex){
                    logger.error("",ex);
                }
            }
        }

    }

    @Override
    public void removeRuleMap(String key){
        ruleMap.remove(key);
    }

    @Override
    public void removeRuleMap(){
        ruleMap.clear();
    }
}
