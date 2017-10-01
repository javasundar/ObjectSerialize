package com.binary.writer;

import com.binary.utils.ByteUtil;

/**
 * char to byte
 * @author sundarchen
 */

public class CharWriter implements IByteWriter<Character> {

    @Override
    public byte[] write(Character character, Class tClass) {
        return ByteUtil.charToByte2(character);
    }

}
