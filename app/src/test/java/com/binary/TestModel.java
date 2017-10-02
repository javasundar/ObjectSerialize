package com.binary;

import com.binary.generics.FieldInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试用例
 */
public class TestModel {

    @FieldInfo(order = 101)
    private int testInteger;

    @FieldInfo(order = 10)
    private String testString;

    @FieldInfo(order = 20)
    private short testShort;

    @FieldInfo(order = 30)
    private char testChar;

    @FieldInfo(order = 50)
    private long testLong;

    public String noSerialize;

    @FieldInfo(order = 71)
    private List<Integer> integerList = new ArrayList<>();

    @FieldInfo(order = 81)
    private List<String> stringList = new ArrayList<>();

    @FieldInfo(order = 91)
    private List<Long> longArrayList = new ArrayList<>();

    @FieldInfo(order = 101)
    private List<TestModel2> testModel2List = new ArrayList<>();

    @FieldInfo(order = 1)
    private TestModel2 testModel2 = new TestModel2();

    public int getTestInteger() {
        return testInteger;
    }

    public void setTestInteger(int testInteger) {
        this.testInteger = testInteger;
    }

    public String getTestString() {
        return testString;
    }

    public void setTestString(String testString) {
        this.testString = testString;
    }

    public short getTestShort() {
        return testShort;
    }

    public void setTestShort(short testShort) {
        this.testShort = testShort;
    }

    public char getTestChar() {
        return testChar;
    }

    public void setTestChar(char testChar) {
        this.testChar = testChar;
    }

    public long getTestLong() {
        return testLong;
    }

    public void setTestLong(long testLong) {
        this.testLong = testLong;
    }

    public String getNoSerialize() {
        return noSerialize;
    }

    public void setNoSerialize(String noSerialize) {
        this.noSerialize = noSerialize;
    }

    public List<Integer> getIntegerList() {
        return integerList;
    }

    public void setIntegerList(List<Integer> integerList) {
        this.integerList = integerList;
    }

    public List<String> getStringList() {
        return stringList;
    }

    public void setStringList(List<String> stringList) {
        this.stringList = stringList;
    }

    public List<Long> getLongArrayList() {
        return longArrayList;
    }

    public void setLongArrayList(List<Long> longArrayList) {
        this.longArrayList = longArrayList;
    }

    public List<TestModel2> getTestModel2List() {
        return testModel2List;
    }

    public void setTestModel2List(List<TestModel2> testModel2List) {
        this.testModel2List = testModel2List;
    }

    public TestModel2 getTestModel2() {
        return testModel2;
    }

    public void setTestModel2(TestModel2 testModel2) {
        this.testModel2 = testModel2;
    }
}
