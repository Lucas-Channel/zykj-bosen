package com.bosen.common.util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 提取省市区工具类
 * @author Lucas
 * @version 2.0.0
 * @date 2023/3/20
 */
public class AddressResolutionUtil {

    public static List<Map<String, String>> addressResolution(String address) {
        String regex = "(?<province>[^省]+自治区|.*?省|.*?行政区|.*?市)(?<city>[^市]+自治州|.*?地区|.*?行政单位|.+盟|市辖区|.*?市|.*?县)(?<district>[^县]+县|.+区|.+市|.+旗|.+海域|.+岛)?(?<town>[^区]+区|.+镇)?(?<detail>.*)";
        Matcher m = Pattern.compile(regex).matcher(address);
        String province = null, city = null, district = null, town = null, detail = null;
        List<Map<String, String>> table = new ArrayList<Map<String, String>>();
        Map<String, String> row = null;
        while (m.find()) {
            row = new LinkedHashMap<String, String>();
            province = m.group("province");
            row.put("province", province == null ? "" : province.trim());
            city = m.group("city");
            row.put("city", city == null ? "" : city.trim());
            district = m.group("district");
            row.put("district", district == null ? "" : district.trim());
            town = m.group("town");
            row.put("town", town == null ? "" : town.trim());
            detail = m.group("detail");
            row.put("detail", detail == null ? "" : detail.trim());
            table.add(row);
        }
        return table;
    }
}
