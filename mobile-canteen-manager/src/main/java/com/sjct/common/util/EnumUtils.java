package com.sjct.common.util;

import java.util.ArrayList;
import java.util.List;

import com.sjct.common.enumerated.DescriptionID;

public class EnumUtils {

    public static <I extends DescriptionID> I getEnum(Class<I> type, int index) {
        I[] types = type.getEnumConstants();
        for (I t : types) {
            if (t.getIndex() == index) {
                return t;
            }
        }
        throw new AssertionError("不能够映射:" + index + "到枚举" + type.getSimpleName());
    }

    public static <I extends DescriptionID> I getEnum(Class<I> type, String description) {
        I[] types = type.getEnumConstants();
        for (I t : types) {
            if (t.getDescription().equals(description)) {
                return t;
            }
        }
        throw new AssertionError("不能够映射:" + description + "到枚举" + type.getSimpleName());
    }

    public static <I extends DescriptionID> List<String> getDescriptions(Class<I> type) {
        I[] types = type.getEnumConstants();
        List<String> result = new ArrayList<String>();
        for (I t : types) {
            result.add(t.getDescription());
        }
        return result;
    }

}
