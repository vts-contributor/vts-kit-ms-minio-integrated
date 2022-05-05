package com.viettel.vtskit.minio.utils;

import java.util.UUID;

public class StringUtils {

    public static boolean isNullOrEmpty(String str){
        return str == null || str.isEmpty();
    }

    public static String randomString(){
        return UUID.randomUUID().toString();
    }

}
