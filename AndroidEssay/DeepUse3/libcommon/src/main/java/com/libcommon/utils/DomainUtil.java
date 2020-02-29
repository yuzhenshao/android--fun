package com.libcommon.utils;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class DomainUtil {

    public static Map<String, Object> object2Map(Object obj) {
        Map<String, Object> map = new HashMap<>();
        try {
            Class<?> clazz = obj.getClass();
            for (Field field : clazz.getDeclaredFields()) {
                if (Modifier.isStatic(field.getModifiers())) {
                    continue;
                }
                field.setAccessible(true);
                String fieldName = field.getName();
                Object value = field.get(obj);
                if (!fieldName.startsWith("$") && !fieldName.equals("serialVersionUID") && value != null) {
                    if (value.getClass().isArray() || value instanceof Collection) {
                        convertList(clazz, value, map, fieldName);
                    } else {
                        map.put(fieldName, value);
                    }

                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return map;
    }

    private static void convertList(Class<?> clazz, Object obj, Map<String, Object> map, String fieldName) {
        String key = null;
        StringBuilder sb = new StringBuilder();
        if (obj.getClass().isArray()) {
            key = fieldName + "[]";
            int length = Array.getLength(obj);
            for (int i = 0; i < length; i++) {
                sb.append(Array.get(obj, i))
                        .append(",");
            }
        }
        if (obj instanceof Collection) {
            key = fieldName + "[]";
            Collection collection = (Collection) obj;
            for (Object o : collection) {
                sb.append(o).append(",");
            }
        }

        if (sb.length() != 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        if (key != null) {
            map.put(key, sb.toString());
        }
    }

    public static Integer getSafeInteger(Integer integer) {
        if (integer == null) return 0;
        return integer;
    }

    public static Integer getSafeInteger(Integer integer, int defaultValue) {
        if (integer == null) {
            return defaultValue;
        }
        return integer;
    }

    public static Double getSafeDouble(Double doubleValue) {
        if (doubleValue == null) {
            return 0d;
        }
        return doubleValue;
    }

    public static Long getSafeLong(Long longValue) {
        if (longValue == null) {
            return 0L;
        }
        return longValue;
    }

    public static Boolean getSafeBoolean(Boolean booleanValue) {
        if (booleanValue == null) {
            return false;
        }
        return booleanValue;
    }

    public static Boolean getSafeBoolean(Boolean booleanValue, boolean defaultValue) {
        if (booleanValue == null) {
            return defaultValue;
        }
        return booleanValue;
    }
}
