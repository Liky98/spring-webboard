package com.demo.webboard.config.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

@Deprecated
public class HTMLTagFilterRequestWrapper extends HttpServletRequestWrapper {

    public HTMLTagFilterRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    public String[] getParameterValues(String parameter) {
        String[] values = super.getParameterValues(parameter);

        if (null == values) {
            return null;
        }

        for (int i = 0; i < values.length; i++) {
            if (values[i] != null) {
                StringBuffer strBuff = new StringBuffer();
                for (int j = 0; j < values[i].length(); j++) {
                    char c = values[i].charAt(j);
                    switch (c) {
                        case '<':
                            strBuff.append("&lt;");
                            break;
                        case '>':
                            strBuff.append("&gt;");
                            break;
                        case '&':
                            strBuff.append("&amp;");
                            break;
                        case '"':
                            strBuff.append("&quot;");
                            break;
                        case '\'':
                            strBuff.append("&apos;");
                            break;
                        default:
                            strBuff.append(c);
                            break;
                    }
                }
                values[i] = strBuff.toString();
            } else {
                values[i] = null;
            }
        }

        return values;
    }

    public String getParameter(String parameter) {
        String value = super.getParameter(parameter);

        if(null == value){
            return null;
        }

        StringBuffer strBuff = new StringBuffer();

        for (int i = 0; i < value.length(); i++) {
            char c = value.charAt(i);
            switch (c) {
                case '<':
                    strBuff.append("&lt;");
                    break;
                case '>':
                    strBuff.append("&gt;");
                    break;
                case '&':
                    strBuff.append("&amp;");
                    break;
                case '"':
                    strBuff.append("&quot;");
                    break;
                case '\'':
                    strBuff.append("&apos;");
                    break;
                default:
                    strBuff.append(c);
                    break;
            }
        }

        value = strBuff.toString();

        return value;
    }
}
