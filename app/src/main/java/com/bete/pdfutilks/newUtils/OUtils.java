package com.bete.pdfutilks.newUtils;

import com.bete.pdfutilks.utils.StringUtils;

public class OUtils {
    public static boolean isEmpty(String value) {
        if (StringUtils.isEmpty(value))
            return true;
        else
            return false;
    }
}
