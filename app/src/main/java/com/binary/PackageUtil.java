package com.binary;

import com.binary.exception.PackageException;
import com.binary.reader.ObjectReader;
import com.binary.utils.BinaryBuffer;
import com.binary.writer.ObjectWriter;

import java.lang.reflect.Type;

/**
 * 对象二进制打包工具
 */

public class PackageUtil {

    /**
     * 对象序列化成二进制数据
     * @param object
     * @param <T>
     * @return
     * @throws PackageException
     */
    public static <T> byte[] object2Byte(Object object) throws PackageException {
        return new ObjectWriter().write(object, object.getClass());
    }

    /**
     * 二进制数据转化成对象
     * @param tClass        对象类型
     * @param byteBuffer    byteB缓存数据
     * @param <T>
     * @return
     * @throws PackageException
     */
    public static <T> T byte2Object(Type tClass, BinaryBuffer byteBuffer) throws PackageException {
        return (T) new ObjectReader().read(tClass, byteBuffer);
    }

    /**
     * 二进制数据转化成对象
     * @param tClass        对象类型
     * @param byteBuffer    byteB缓存数据
     * @param <T>
     * @return
     * @throws PackageException
     */
    public static <T> T byte2Object(Type tClass, byte[] byteBuffer) throws PackageException {
        return byte2Object(tClass, new BinaryBuffer(byteBuffer));
    }
}
