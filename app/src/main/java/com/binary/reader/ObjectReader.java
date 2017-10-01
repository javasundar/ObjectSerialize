package com.binary.reader;

import com.binary.utils.BinaryBuffer;
import com.binary.exception.PackageException;
import com.binary.utils.ByteUtil;
import com.binary.utils.ReflectionUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 二进制数据解包成对象
 * @author sundarchen
 */

public class ObjectReader implements IByteReader<Object> {

    private static Map<String, IByteReader<?>> writerList = new HashMap<>();

    static {
        writerList.put(CUSTOM_OBJECT, new ObjectReader());
        writerList.put(STRING, new UTF8StringReader());
        writerList.put(CHAR, new CharReader());
        writerList.put(CHAR_OBJECT, new CharReader());
        writerList.put(INTEGER, new IntegerReader());
        writerList.put(INTEGER_OBJECT, new IntegerReader());
        writerList.put(SHORT, new ShortReader());
        writerList.put(SHORT_OBJECT, new ShortReader());
        writerList.put(LONG, new LongReader());
        writerList.put(LONG_OBJECT, new LongReader());
        writerList.put(LIST, new ListReader());
    }


    @Override
    public Object read(Type type, BinaryBuffer binaryBuffer) throws PackageException {
        if (type == null || binaryBuffer == null) {
            return null;
        }

        if (ByteUtil.NULL_BYTE.length > 0) {
            byte isNull = binaryBuffer.readByte();
            if (isNull == 0) {
                return null;
            }
        }

        Object object = null;
        try {
            object = ((Class)(type)).newInstance();
        } catch (Exception e) {
            throw new PackageException(e);
        }

        Field currentField;
        IByteReader iByteReader;
        String fielTypeName = null;

        List<Field> fieldList = ReflectionUtil.obtainField(object);
        for (int index = 0; index < fieldList.size(); index ++) {
            currentField = fieldList.get(index);
            currentField.setAccessible(true);
            fielTypeName = currentField.getType().getName();
            // 当前field的类型不存在,就尝试使用自定义对象类型进行解包
            if (!writerList.containsKey(fielTypeName)) {
                fielTypeName = CUSTOM_OBJECT;
            }
            iByteReader = writerList.get(fielTypeName);
            try {
                // 自定义model类型
                if (fielTypeName.equals(CUSTOM_OBJECT)) {
                    currentField.set(object, iByteReader.read(currentField.getGenericType(), binaryBuffer));
                }
                // List类型
                else if(fielTypeName.equals(LIST)) {
                    currentField.set(object, iByteReader.read(currentField.getGenericType(), binaryBuffer));
                }
                // 基础数据类型
                else {
                    currentField.set(object, iByteReader.read(ReflectionUtil.getGenericTypeByObject(iByteReader), binaryBuffer));
                }
            } catch (Exception e) {
                throw new PackageException(e);
            }
        }
        return object;
    }
    /**
     * 添加或者覆盖byteReader
     * @param name
     * @param byteReader
     */
    public static void addOrReplayWriter(String name, IByteReader<?> byteReader) {
        writerList.put(name, byteReader);
    }

    /**
     * 删除Reader
     * @param name
     */
    public static void removeWirter(String name) {
        if (!writerList.containsKey(name)) {
            return;
        }
        writerList.remove(name);
    }
    /**
     * 只能用于把arraylist里面的无素序列化
     * 如果是基础数据类型: 基础数据类型的二进制数据包
     * 如果自定义对象:数据包=前两位(0代表对象为空,1代表对象不为空)
     * @param o
     * @return
     * @throws PackageException
     */
    public static Object staticRead(Class type, BinaryBuffer binaryBuffer) throws PackageException {
        // 获取类型名字
        String name = type.getName();
        if (!writerList.containsKey(name)) {
            name = CUSTOM_OBJECT;
        }
        IByteReader iByteWriter = writerList.get(name);
        return iByteWriter.read(type, binaryBuffer);
    }
}
