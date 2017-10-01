package com.binary.reader;

import com.binary.utils.BinaryBuffer;

import java.lang.reflect.Type;

/**
 * byte array to short
 * @author sundarchen
 */

public class ShortReader implements IByteReader<Short> {
    @Override
    public Short read(Type type, BinaryBuffer binaryBuffer) {
        return binaryBuffer.readShort();
    }
}
