package com.epam.suleimenov.util;

import org.apache.commons.codec.digest.DigestUtils;

public final class Utils {

    private  static final String salt = "3<u4~UkR6f1>e?8DS|2l2;eA`d*Zy#+04w\"K4WzcocUXaW\"#VmeeOv8#p&Zu3448ZWh=kbO5v]E45bP}2Xha])#r1L";
    public final static String encoding = "UTF-8";

    private Utils() {

    }

    public static String md5Apache(String encoding) {
        String md5Hex = DigestUtils.md5Hex(encoding + salt);
                return md5Hex;
    }
}
