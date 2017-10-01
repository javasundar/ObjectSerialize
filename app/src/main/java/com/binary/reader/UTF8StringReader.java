package com.binary.reader;

import com.binary.utils.BinaryBuffer;

import java.lang.reflect.Type;

/**
 * byte array to string
 * @author sundarchen
 */

public class UTF8StringReader implements IByteReader<String> {

    @Override
    public String read(Type type, BinaryBuffer binaryBuffer) {
        return binaryBuffer.readUTF8String();
    }

}
