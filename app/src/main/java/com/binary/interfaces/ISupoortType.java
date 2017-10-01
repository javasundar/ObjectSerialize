package com.binary.interfaces;

import java.util.List;

/**
 * 基础类
 */

public interface ISupoortType {
    /**
     * 自定义对象
     */
    String CUSTOM_OBJECT = "CUSTOM_OBJECT";

    /**
     * 整型
     */
    String INTEGER = int.class.getSimpleName();
    String INTEGER_OBJECT = Integer.class.getName();

    /**
     * 字符串
     */
    String STRING = String.class.getName();

    /**
     * short类型
     */
    String SHORT = short.class.getSimpleName();
    String SHORT_OBJECT = Short.class.getName();

    /**
     * 长整型
     */
    String LONG = long.class.getSimpleName();
    String LONG_OBJECT = Long.class.getName();

    /**
     * char类型
     */
    String CHAR = char.class.getSimpleName();
    String CHAR_OBJECT = Character.class.getName();

    /**
     * 列表类型
     */
    String LIST = List.class.getName();
}
