package cn.mju.admintle.utils;

import java.util.HashMap;
import java.util.Map;

public class AJAXUtil {
    public static Map<String,Object> getReturn(boolean flag){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        if (flag) {
            resultMap.put("result", "true");
        } else {
            resultMap.put("result", "false");
        }
        return resultMap;
    }
}
