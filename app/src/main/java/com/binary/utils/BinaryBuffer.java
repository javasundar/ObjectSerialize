package com.binary.utils;

import com.binary.utils.ByteUtil;

/**
 * 二进制数据buffer
 *
 * @author sundarchen
 */
public class BinaryBuffer {

    /**
     * 指针位置
     */
    private int offset;

    /**
     * 缓存二进制数据
     */
    private byte[] cacheBytes;

    public BinaryBuffer(byte[] cacheBytes) {
        this.cacheBytes = cacheBytes;
    }

    /**
     * 读取long
     * @return
     */
    public long readLong() {
        long result = ByteUtil.byte8ToLong(cacheBytes, offset);
        offset += ByteUtil.LENGTH_OF_LONG;
        return result;
    }

    /**
     * 读取型数据
     * @return
     */
    public int readInt() {
        int result = ByteUtil.byte4ToInt(cacheBytes, offset);
        offset += ByteUtil.LENGTH_OF_INT;
        return result;
    }

    /**
     * 读取short数据
     * @return
     */
    public short readShort() {
        short result = ByteUtil.byte2ToShort(cacheBytes, offset);
        offset += ByteUtil.LENGTH_OF_SHORT;
        return result;
    }

    /**
     * 读取char
     * @return
     */
    public char readChar() {
        char result = ByteUtil.byte2ToChar(cacheBytes, offset);
        offset += ByteUtil.LENGTH_OF_CHAR;
        return result;
    }

    /**
     * 读取字符串
     * @return
     */
    public String readUTF8String() {
        Object[] result = ByteUtil.byte2String(cacheBytes, offset);
        offset += ByteUtil.LENGTH_OF_STRING + (short)(result[1]);
        return (String) result[0];
    }

    /**
     * 读取字符串
     * @param charset
     * @return
     */
    public String readString(String charset) {
        Object[] result = ByteUtil.byte2String(cacheBytes, offset, charset);
        offset += ByteUtil.LENGTH_OF_STRING + (int)(result[1]);
        return (String) result[0];
    }

    /**
     * 读取一个字节
     * @return
     */
    public byte readByte() {
        byte result = ByteUtil.bytes2Byte(cacheBytes, offset);
        offset += ByteUtil.LENGTH_OF_BYTE;
        return result;
    }
}
