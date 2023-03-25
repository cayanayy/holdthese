package com.cayanay.holdthese.core.utilities;

import org.apache.commons.codec.digest.DigestUtils;

public class Utils {
    public static String md5Hash(String input) {
        String md5Hex = DigestUtils.md5Hex(input).toUpperCase();

        return md5Hex;
    }
}