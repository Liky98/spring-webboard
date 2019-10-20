package com.demo.webboard.util;

import org.apache.commons.collections.map.ListOrderedMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.support.JdbcUtils;

public class ParamMap extends ListOrderedMap {
    private static final long serialVersionUID = 4793434790501845972L;

    public ParamMap() { /* compiled code */ }

    /**
     * key에 대하여 소문자로 변환하여 super.put(ListOrderedMap)을 호출한다.
     * 전자정부프레임워크의 EgovMap을 살펴보면 ListOrderedMap을 상속받고 camelCase로 key를 셋팅하도록 되어있다.
     * @param key
     *        - '_'가 포함된 변수명
     * @param value
     *        - 명시된 key에 대한 값 (변경없음)
     * @return
     */
    public Object put(Object key, Object value) {
        // StringUtils.lowerCase 로 key값을 소문자로 변경 (USER_NAME => user_name)
        // JdbcUtils.convertUnderscoreNameToPropertyName 로 key값을 camelCase로 변경 (user_name => userName)
        return super.put(JdbcUtils.convertUnderscoreNameToPropertyName(StringUtils.lowerCase((String) key)), value);
    }

}
