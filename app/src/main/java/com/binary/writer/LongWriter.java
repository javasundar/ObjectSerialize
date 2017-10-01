package com.binary.writer;

import com.binary.utils.ByteUtil;

/**
 * long to byte array
 * @author sundarchen
 */

public class LongWriter implements IByteWriter<Long> {
    @Override
    public byte[] write(Long aLong, Class tClass) {
        return ByteUtil.longToByte8(aLong);
    }
}
