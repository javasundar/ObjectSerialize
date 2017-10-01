package com.binary.reader;

import com.binary.TestModel;
import com.binary.utils.BinaryBuffer;
import com.binary.writer.ListWriter;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Belle on 2017/10/1.
 */
public class ListReaderTest {

    public List<TestModel> testModelList = new ArrayList<>();

    List<Integer> testIntegerList = new ArrayList<>();

    List<Character> testcharList = new ArrayList<>();


    List<String> teststringList = new ArrayList<>();

    List<Long> testlongs = new ArrayList<>();

    @Test
    public void read() throws Exception {

        ListReader listReader = new ListReader();

        ListWriter listWriter = new ListWriter();

        TestModel testModel = new TestModel();
        testModel.setNoSerialize("111");
        testModel.setTestChar('1');
        testModel.setTestInteger(100);
        testModel.setTestLong(100l);
        testModel.setTestShort((short)12);
        testModel.setTestString("aaaaaa");

        testModel.getIntegerList().add(1);
        testModel.getIntegerList().add(2);
        testModel.getIntegerList().add(3);

        // custom model
        List<TestModel> testModels = new ArrayList<>();
        testModels.add(testModel);
        testModels.add(testModel);
        testModels.add(testModel);
        byte[] results = listWriter.write(testModels, null);
        List<TestModel> testModelList2 = (List<TestModel>) listReader.read(ListReaderTest.class.getDeclaredField("testModelList").getGenericType(), new BinaryBuffer(results));



        // integer
        List<Integer> integerList = new ArrayList<>();
        integerList.add(1);
        integerList.add(2);
        integerList.add(3);
        results = listWriter.write(integerList, null);
        List<Integer> integerList1 = (List<Integer>) listReader.read(ListReaderTest.class.getDeclaredField("testIntegerList").getGenericType(), new BinaryBuffer(results));

        // char
        List<Character> charList = new ArrayList<>();
        charList.add('1');
        charList.add('a');
        charList.add('b');
        results = listWriter.write(charList, null);
        List<Character> charList1 = (List<Character>) listReader.read(ListReaderTest.class.getDeclaredField("testcharList").getGenericType(), new BinaryBuffer(results));


        // string
        List<String> stringList = new ArrayList<>();
        stringList.add("aa");
        stringList.add("bb");
        stringList.add("cc");
        results = listWriter.write(stringList, null);
        List<String> stringList1 = (List<String>) listReader.read(ListReaderTest.class.getDeclaredField("teststringList").getGenericType(), new BinaryBuffer(results));

        // long
        List<Long> longs = new ArrayList<>();
        longs.add(1l);
        longs.add(2l);
        longs.add(3l);
        results = listWriter.write(longs, null);
        List<Long> longs1 = (List<Long>) listReader.read(ListReaderTest.class.getDeclaredField("testlongs").getGenericType(), new BinaryBuffer(results));

        longs.size();
    }

}