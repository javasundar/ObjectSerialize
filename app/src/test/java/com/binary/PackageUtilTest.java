package com.binary;

import com.binary.utils.BinaryBuffer;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Belle on 2017/9/30.
 */
public class PackageUtilTest {
    @Test
    public void object2Byte() throws Exception {
        TestModel testModel = new TestModel();
        testModel.setNoSerialize("111");
        testModel.setTestChar('1');
        testModel.setTestInteger(100);
        testModel.setTestLong(100l);
        testModel.setTestShort((short)12);
        testModel.setTestString("aaaaaa");


        testModel.getIntegerList().add(1);
        testModel.getIntegerList().add(2);

        testModel.getStringList().add("123");
        testModel.getStringList().add("456");

        TestModel2 testModel2 = new TestModel2();
        testModel2.setValue(100);
        testModel.getTestModel2List().add(testModel2);
        testModel.getTestModel2List().add(testModel2);

        byte[] resultbytes = PackageUtil.object2Byte(testModel);

        TestModel serializeObject = PackageUtil.byte2Object(TestModel.class, new BinaryBuffer(resultbytes));
        Assert.assertEquals(testModel.getTestChar(), serializeObject.getTestChar());
        Assert.assertEquals(testModel.getTestInteger(), serializeObject.getTestInteger());
        Assert.assertEquals(testModel.getTestLong(), serializeObject.getTestLong());
        Assert.assertEquals(testModel.getTestString(), serializeObject.getTestString());
        Assert.assertEquals(testModel.getTestShort(), serializeObject.getTestShort());


        Assert.assertNotEquals(testModel.getNoSerialize(), serializeObject.getNoSerialize());
    }

    @Test
    public void byte2Object() throws Exception {
        TestModel testModel = new TestModel();
        testModel.setNoSerialize("111");
        testModel.setTestChar('1');
        testModel.setTestInteger(100);
        testModel.setTestLong(100l);
        testModel.setTestShort((short)12);
        testModel.setTestString("aaaaaa");


        testModel.getIntegerList().add(1);
        testModel.getIntegerList().add(2);

        testModel.getStringList().add("123");
        testModel.getStringList().add("456");

        TestModel2 testModel2 = new TestModel2();
        testModel2.setValue(100);
        testModel.getTestModel2List().add(testModel2);
        testModel.getTestModel2List().add(testModel2);

        byte[] resultbytes = PackageUtil.object2Byte(testModel);

        TestModel serializeObject = PackageUtil.byte2Object(TestModel.class, new BinaryBuffer(resultbytes));
        Assert.assertEquals(testModel.getTestChar(), serializeObject.getTestChar());
        Assert.assertEquals(testModel.getTestInteger(), serializeObject.getTestInteger());
        Assert.assertEquals(testModel.getTestLong(), serializeObject.getTestLong());
        Assert.assertEquals(testModel.getTestString(), serializeObject.getTestString());
        Assert.assertEquals(testModel.getTestShort(), serializeObject.getTestShort());


        Assert.assertNotEquals(testModel.getNoSerialize(), serializeObject.getNoSerialize());
    }

}