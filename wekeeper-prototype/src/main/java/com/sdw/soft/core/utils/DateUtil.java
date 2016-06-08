package com.sdw.soft.core.utils;

import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by shangyindong on 2016/6/8.
 */
public class DateUtil {

    private static final String DATE_FORMAT_DEFAULT = "yyyy-mm-dd HH:mm:ss";
    private static final ThreadLocal<Map<String,DateFormat>> tl = new ThreadLocal<Map<String, DateFormat>>();

    public static DateFormat getDateFormat(String dateFormat){
        Map<String,DateFormat> map = tl.get();
        if(null == map){
            map = new HashMap<String, DateFormat>();
            tl.set(map);
        }
        if(StringUtils.isBlank(dateFormat)){
            dateFormat = DATE_FORMAT_DEFAULT;
        }
        DateFormat df = map.get(dateFormat);
        if(null == df){
            df = new SimpleDateFormat(dateFormat);
            map.put(dateFormat,df);
        }

        return df;
    }

    public static DateFormat getDefaultDateFormat() {
        return getDateFormat(null);
    }
}
