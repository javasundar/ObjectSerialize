package com.binary.writer;

import com.binary.exception.PackageException;
import com.binary.utils.ByteUtil;

import java.util.List;

/**
 * 列表数据转化成二进制数据
 */
public class ListWriter implements IByteWriter<List<?>> {

    @Override
    public byte[] write(List<?> objects, Class tClass) throws PackageException {
        // 两位表示list的长度
        if (objects == null ||objects.size() <= 0) {
            return new byte[]{0, 0};
        }
        // 获取ArrayList的类型
        Class type = objects.get(0).getClass();

        Object object;
        byte[] resultBytes = ByteUtil.shortToByte2((short)objects.size());
        for (int index = 0; index < objects.size(); index ++) {
            object = objects.get(index);
            resultBytes = ByteUtil.contractByte(resultBytes, ObjectWriter.staticWrite(object, type));
        }
        return resultBytes;
    }

}
