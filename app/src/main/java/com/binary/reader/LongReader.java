package com.binary.reader;

import com.binary.utils.BinaryBuffer;

import java.lang.reflect.Type;

/**
 * byte array to long
 * @author sundarchen
 */

public class LongReader implements IByteReader<Long> {
    @Override
    public Long read(Type type, BinaryBuffer binaryBuffer) {
        return binaryBuffer.readLong();
    }
}
