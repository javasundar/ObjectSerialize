package com.binary;

import com.binary.exception.PackageException;
import com.binary.reader.ObjectReader;
import com.binary.utils.BinaryBuffer;
import com.binary.utils.ByteUtil;
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

    /**
     * 开启对支持空对象的支持
     */
    public static void onOpenSupportNullObject() {
        ByteUtil.LENGTH_OF_BYTE = 1;
        // 0表示对象序列化的对象为空
        ByteUtil.NULL_BYTE = new byte[]{0};
        // 1表示对象不为空
        ByteUtil.NOT_NULL_BYTE = new byte[]{1};
    }

    /**
     * 关闭对支持空对象的支持
     */
    public static void onCloseSupportNullObject() {
        ByteUtil.LENGTH_OF_BYTE = 0;
        // 0表示对象序列化的对象为空
        ByteUtil.NULL_BYTE = new byte[]{};
        // 1表示对象不为空
        ByteUtil.NOT_NULL_BYTE = new byte[]{};
    }
}
