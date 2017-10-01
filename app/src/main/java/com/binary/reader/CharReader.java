package com.binary.reader;

import com.binary.utils.BinaryBuffer;

import java.lang.reflect.Type;

/**
 * byte array to char
 * @author sundarchen
 */

public class CharReader implements IByteReader<Character> {
    @Override
    public Character read(Type type, BinaryBuffer binaryBuffer) {
        return binaryBuffer.readChar();
    }
}
