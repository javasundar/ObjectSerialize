package com.binary.utils;

import com.binary.generics.FieldInfo;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author sundarchen
 */

public class ReflectionUtil {

    /**
     * the cache of the object field order list
     */
    private static Map<String, List<Field>> orderFieldMap = new HashMap<>();

    /**
     * 获取对象的所有字段，并保留在缓存里面
     * @param target
     * @return
     */
    public static List<Field> obtainField(Object target) {
        if (target == null) {
            return null;
        }

        Class targetClass = target.getClass();
        if (targetClass == null) {
            return null;
        }

        if (orderFieldMap.containsKey(targetClass.getName())) {
            return orderFieldMap.get(targetClass.getName());
        }
        List<Field> results = forceObtainField(target);
        orderFieldMap.put(targetClass.getName(), results);
        return results;
    }

    /**
     * 获取对象的所有的字段
     * @param target
     * @return
     */
    public static List<Field> forceObtainField(Object target) {
        if (target == null) {
            return null;
        }

        Class targetClass = target.getClass();
        if (targetClass == null) {
            return null;
        }

        Field[] declaredFields = targetClass.getDeclaredFields();
        if (declaredFields == null) {
            return null;
        }
        // 获取FieldInfo
        Field currentField;
        FieldInfo fieldInfo;
        List<Field> results = new ArrayList<>(declaredFields.length);
        for (int index = 0; index < declaredFields.length; index ++) {
            currentField = declaredFields[index];
            fieldInfo = currentField.getAnnotation(FieldInfo.class);
            if (fieldInfo == null) {
                continue;
            }
            results.add(results.size(), currentField);
        }
        // 排序
        Collections.sort(results, new Comparator<Field>() {
            @Override
            public int compare(Field field, Field t1) {
                return field.getAnnotation(FieldInfo.class).order() - t1.getAnnotation(FieldInfo.class).order();
            }
        });
        return results;
    }

    /**
     * 获取对象泛型
     * @param input
     * @return
     */
    public static Type getGenericTypeByObject(Object input) {
        return  getGenericTypeByClass(input.getClass());
    }

    /**
     * 获取Class对象泛型
     * @param input
     * @return
     */
    public static Type getGenericTypeByClass(Class input) {
        Type genericType = input.getGenericSuperclass();
        // 此类没有泛型
        if (genericType instanceof ParameterizedType) {
            return ((ParameterizedType)input.getGenericSuperclass()).getActualTypeArguments()[0];
        }

        Type[] types = input.getGenericInterfaces();
        if (types == null || types.length <= 0) {
            return null;
        }

        genericType = types[0];
        if (genericType instanceof ParameterizedType) {
            return ((ParameterizedType)(genericType)).getActualTypeArguments()[0];
        }
        return null;
    }
}
