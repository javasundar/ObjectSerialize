package com.binary.writer;

import com.binary.utils.ByteUtil;
import com.binary.exception.PackageException;
import com.binary.utils.ReflectionUtil;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 对象转成二进制socket数据
 * @author sundarchen
 */
public class ObjectWriter implements IByteWriter<Object> {

    private static Map<String, IByteWriter<?>> writerList = new HashMap<>();

    static {
        writerList.put(CUSTOM_OBJECT, new ObjectWriter());
        writerList.put(STRING, new UTF8StringWriter());
        writerList.put(CHAR, new CharWriter());
        writerList.put(CHAR_OBJECT, new CharWriter());
        writerList.put(INTEGER, new IntegerWriter());
        writerList.put(INTEGER_OBJECT, new IntegerWriter());
        writerList.put(SHORT, new ShortWriter());
        writerList.put(SHORT_OBJECT, new ShortWriter());
        writerList.put(LONG, new LongWriter());
        writerList.put(LONG_OBJECT, new LongWriter());
        writerList.put(LIST, new ListWriter());
    }

    public ObjectWriter() {
    }

    @Override
    public byte[] write(Object o, Class tClass) throws PackageException {
        // 对象为空的标志
        if (o == null) {
            return ByteUtil.NULL_BYTE;
        }

        // 对象不为空的标志
        // flag + byte of data
        byte[] results = ByteUtil.NOT_NULL_BYTE;

        Field currentField;
        String currentFieldTypeName;
        IByteWriter byteWriter;
        List<Field> fieldList = ReflectionUtil.obtainField(o);
        for (int index = 0; index < fieldList.size(); index ++) {
            currentField = fieldList.get(index);
            currentFieldTypeName = currentField.getType().getName();
            if (!writerList.containsKey(currentFieldTypeName)) {
                currentFieldTypeName = CUSTOM_OBJECT;
            }
            byteWriter = writerList.get(currentFieldTypeName);
            currentField.setAccessible(true);
            try {
                results = ByteUtil.contractByte(results, byteWriter.write(currentField.get(o), currentField.getType()));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                // TODO throw not support exception
                throw new PackageException(e);
            }
        }
        return results;
    }


    /**
     * add or replay byteWriter
     * @param name
     * @param byteWriter
     */
    public static void addOrReplayWriter(String name, IByteWriter<?> byteWriter) {
        writerList.put(name, byteWriter);
    }

    /**
     * remove writer
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
    public static byte[] staticWrite(Object o, Class tClass ) throws PackageException {
        // 获取类型名字
        String name = o.getClass().getName();
        if (!writerList.containsKey(name)) {
            name = CUSTOM_OBJECT;
        }
        IByteWriter iByteWriter = writerList.get(name);
        return iByteWriter.write(o, tClass);
    }
}
