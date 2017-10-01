只需要简单的代码就可以把对象转化成二进制数据，并把相应的二进制数据转化成对象

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


// 二进制数据结果
byte[] resultbytes = PackageUtil.object2Byte(testModel);

// 二进制数据反序列化成对象
TestModel serializeObject = PackageUtil.byte2Object(TestModel.class, new BinaryBuffer(resultbytes));
Assert.assertEquals(testModel.getTestChar(), serializeObject.getTestChar());
Assert.assertEquals(testModel.getTestInteger(), serializeObject.getTestInteger());
Assert.assertEquals(testModel.getTestLong(), serializeObject.getTestLong());
Assert.assertEquals(testModel.getTestString(), serializeObject.getTestString());
Assert.assertEquals(testModel.getTestShort(), serializeObject.getTestShort());
