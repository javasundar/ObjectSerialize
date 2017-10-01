package com.binary.writer;

import com.binary.TestModel;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Belle on 2017/9/30.
 */
public class ListWriterTest {
    @Test
    public void write() throws Exception {
        ListWriter listWriter = new ListWriter();

        TestModel testModel = new TestModel();
        testModel.setNoSerialize("111");
        testModel.setTestChar('1');
        testModel.setTestInteger(100);
        testModel.setTestLong(100l);
        testModel.setTestShort((short)12);
        testModel.setTestString("aaaaaa");

        // custom model
        List<TestModel> testModels = new ArrayList<>();
        testModels.add(testModel);
        testModels.add(testModel);
        testModels.add(testModel);
        listWriter.write(testModels, null);

        // integer
        List<Integer> integerList = new ArrayList<>();
        integerList.add(1);
        integerList.add(2);
        integerList.add(3);
        listWriter.write(integerList, null);

        // char
        List<Character> charList = new ArrayList<>();
        charList.add('1');
        charList.add('a');
        charList.add('b');
        listWriter.write(charList, null);

        // string
        List<String> stringList = new ArrayList<>();
        stringList.add("aa");
        stringList.add("bb");
        stringList.add("cc");
        listWriter.write(stringList, null);

        // long
        List<Long> longs = new ArrayList<>();
        longs.add(1l);
        longs.add(2l);
        longs.add(3l);
        listWriter.write(longs, null);
    }

}