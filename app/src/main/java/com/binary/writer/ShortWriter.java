package com.binary.writer;

import com.binary.utils.ByteUtil;

/**
 * short to byte array
 * @author sundarchen
 */

public class ShortWriter implements IByteWriter<Short> {

    @Override
    public byte[] write(Short aShort, Class tClass) {
        return ByteUtil.shortToByte2(aShort);
    }
}
