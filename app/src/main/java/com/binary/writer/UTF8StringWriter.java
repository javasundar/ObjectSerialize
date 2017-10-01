package com.binary.writer;

import com.binary.utils.ByteUtil;

/**
 * string to byte array
 * @author sundarchen
 */

public class UTF8StringWriter implements IByteWriter<String> {

    /**
     * string byte data is length(byte of the short of the string) + string byte data
     * @param s
     * @return
     */
    @Override
    public byte[] write(String s, Class tClass) {
        return ByteUtil.string2Byte(s);
    }

}
