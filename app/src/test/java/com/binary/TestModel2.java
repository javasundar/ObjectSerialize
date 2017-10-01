package com.binary;

import com.binary.generics.FieldInfo;

/**
 * Created by Belle on 2017/10/1.
 */

public class TestModel2 {

    @FieldInfo(order = 1)
    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
