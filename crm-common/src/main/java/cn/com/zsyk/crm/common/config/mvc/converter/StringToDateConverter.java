package cn.com.zsyk.crm.common.config.mvc.converter;

import java.text.SimpleDateFormat;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;

import cn.com.zsyk.crm.common.constant.ConfigConsts;

/**
 * String->Date的数据类型转换器
 * @author
 */
public class StringToDateConverter implements Converter<String, Date> {

    
    @Override
    public Date convert(String source) {
        if (StringUtils.isBlank(source)) {
            return null;
        }
        source = source.trim();
        try {
            SimpleDateFormat formatter;
            if (source.contains("-")) {
                if (source.contains(":")) {
                    formatter = new SimpleDateFormat(ConfigConsts.DATA_STYLE1);
                } else {
                    formatter = new SimpleDateFormat(ConfigConsts.SHORT_DATE_STYLE1);
                }
                Date dtDate = formatter.parse(source);
                return dtDate;
            } else if (source.contains("/")) {
                if (source.contains(":")) {
                    formatter = new SimpleDateFormat(ConfigConsts.DATA_STYLE2);
                } else {
                    formatter = new SimpleDateFormat(ConfigConsts.SHORT_DATE_STYLE2);
                }
                Date dtDate = formatter.parse(source);
                return dtDate;
            }
        } catch (Exception e) {
            throw new RuntimeException(String.format("parser %s to Date fail", source));
        }

        throw new RuntimeException(String.format("parser %s to Date fail", source));

    }
}