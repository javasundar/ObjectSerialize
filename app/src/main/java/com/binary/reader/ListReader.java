package com.binary.reader;

import com.binary.exception.PackageException;
import com.binary.utils.BinaryBuffer;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * 列表读取
 */
public class ListReader implements IByteReader<Object> {

    @Override
    public Object read(Type type, BinaryBuffer binaryBuffer) throws PackageException {
        short listLength = binaryBuffer.readShort();
        if (listLength <= 0) {
            return null;
        }

        if (!(type instanceof ParameterizedType)) {
            throw new PackageException("获取不到ArrayList的存放数据类型!");
        }

        Type arrayListType = ((ParameterizedType) type).getActualTypeArguments()[0];
        if (arrayListType == null) {
            throw new PackageException("获取不到ArrayList的存放数据类型!");
        }
        List<Object> results = new ArrayList<>();
        for (int index = 0; index < listLength; index ++) {
            results.add(ObjectReader.staticRead((Class) arrayListType, binaryBuffer));
        }
        return results;
    }
}
