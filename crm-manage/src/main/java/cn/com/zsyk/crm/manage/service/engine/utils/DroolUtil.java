package cn.com.zsyk.crm.manage.service.engine.utils;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import org.kie.api.io.ResourceType;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;
import org.springframework.core.io.ClassPathResource;

public class DroolUtil {

    /**
     * compileRule
     * @param exp
     * @return
     */
    public static boolean compileRule(String exp){
        Reader strReader = null;
        final KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        try{
            String ruleStr = getRuleStr(exp);
            strReader = new StringReader(ruleStr);
            kbuilder.add(ResourceFactory.newReaderResource(strReader), ResourceType.DRL);
        }catch (Exception e) {
            try {
                strReader.close();//关闭流
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            return false;
        }

        if (kbuilder.hasErrors()) {
            System.out.println(kbuilder.getErrors().toString());
            return false;
        }
        return true;
    }


    /**
     * 获取头部声明
     * @return
     */
    private static String getFileHeader()  throws IOException {
        StringBuffer ruleStr = new StringBuffer();
        ruleStr.append("global java.util.Map $result;\r\n");
        ruleStr.append("global java.util.Map $referMap;\r\n");
        ruleStr.append(getFileFunctions());
        return ruleStr.toString();
    }

    /**
     * 获取函数定义
     * @return
     */
    private static String getFileFunctions() throws IOException {
        org.springframework.core.io.Resource fileRource = new ClassPathResource("rule/functions.txt");
        File file = fileRource.getFile();
        byte[] buffer =new byte[(int) file.length()];
        FileInputStream is =new FileInputStream(file);
        is.read(buffer, 0, buffer.length);
        is.close();
        String str = new String(buffer);
        return str;
    }

    /**
     * 获取一条规则内容
     *
     * @param exp
     * @return
     */
    private static String getRuleStr(String exp) throws IOException{
        if(exp.indexOf("memberOf")>0){
            exp=exp.replaceAll("\"","\'");
        }
        StringBuffer ruleStr = new StringBuffer();
        ruleStr.append(getFileHeader());
        String lineSeparator = System.getProperty("line.separator", "\n");
        ruleStr.append("rule \"exp_test\"" + lineSeparator);
        ruleStr.append("when " + lineSeparator);
        ruleStr.append("$factMap:Map(" + exp + ")" + lineSeparator);
        ruleStr.append("then " + lineSeparator);
        ruleStr.append("System.out.println(\"rule "+exp+" test success\");");
        ruleStr.append("end" + lineSeparator);

        return ruleStr.toString();
    }
}
