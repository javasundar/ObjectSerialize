package com.binary.reader;

import com.binary.utils.BinaryBuffer;

import java.lang.reflect.Type;

/**
 * byte array to integer
 * @author sundarchen
 */

public class IntegerReader implements IByteReader<Integer> {
    @Override
    public Integer read(Type type, BinaryBuffer binaryBuffer) {
        return binaryBuffer.readInt();
    }
}
