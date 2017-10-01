package com.binary.writer;

import com.binary.utils.ByteUtil;

/**
 * integer to byte array
 * @author sundarchen
 */

public class IntegerWriter implements IByteWriter<Integer> {


    @Override
    public byte[] write(Integer integer, Class tClass) {
        return ByteUtil.intToByte4(integer);
    }
}
