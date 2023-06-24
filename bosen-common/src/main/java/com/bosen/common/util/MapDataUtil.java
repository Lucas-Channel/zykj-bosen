package com.bosen.common.util;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * map数据转换
 * @author Lucas
 * @version 2.0.0
 * @date 2023/6/23
 */
public class MapDataUtil {
    public static Map<String,String>  getParameterMap(HttpServletRequest request){
        Map<String,String> resultMap = new HashMap<>();
        Map<String, String[]> params = request.getParameterMap();
        String value = "";
        for(Map.Entry<String, String[]> entry : params.entrySet()){
            value = Arrays.toString(entry.getValue());
            resultMap.put(entry.getKey(), value.substring(1, value.length()-1));
        }
        return resultMap;
    }
}
