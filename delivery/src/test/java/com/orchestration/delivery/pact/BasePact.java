package com.orchestration.delivery.pact;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;

public class BasePact {

    protected static  <T extends Enum<T>> String enumToRegexString(Class<T> enumValues) {
        Object[] enumConstants = enumValues.getEnumConstants();
        return StringUtils.join(enumConstants, '|');
    }

    protected static Field[] getDeclaredFields(Class tClass){
        return tClass.getDeclaredFields();
    }

}
