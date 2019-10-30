package com.demo.webboard.util;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

@Deprecated
public class CommonUtil {


    /**
     * request에서 params을 찾아낸다. 가급적 사용을 권장하지는 않음.
     *
     * @param request
     * @return
     * @throws Exception
     */
    @Deprecated
    public static HashMap getParamsMap(HttpServletRequest request) throws Exception {
        HashMap resultMap = new HashMap<String, Object>();
        Map paramMap = new TreeMap(request.getParameterMap());
        Iterator it = paramMap.keySet().iterator();
        String key = null;
        String[] value = null;
        while (it.hasNext()) {
            key = (String) it.next();
            value = (String[]) paramMap.get(key);
            if (1 == value.length) {
                resultMap.put(key, value[0]);
            } else {
                resultMap.put(key, value);
            }
        }
        return resultMap;
    }


}
