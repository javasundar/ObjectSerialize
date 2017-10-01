package com.binary.utils;

import java.io.UnsupportedEncodingException;

/**
 * 二进制数据工具类
 *
 * @author sundarchen
 */
public class ByteUtil {

    /**
     * long的长度
     */
    public static final int LENGTH_OF_LONG = 8;

    /**
     * 整数占的字节
     */
    public static final int LENGTH_OF_INT = 4;

    /**
     * short占的字节
     */
    public static final int LENGTH_OF_SHORT = 2;

    /**
     * char占的字节
     */
    public static final int LENGTH_OF_CHAR = 2;

    /**
     * 字符串长度
     */
    public static final int LENGTH_OF_STRING = 2;

    /**
     * 在socket传输数据时,如果对象为空时可以有两种方式解决,
     *
     * 1.如果对象为空,则传默认的对象数据到后端,即如果传的对象数据为空,则会重默认的对象数据.
     * 需要调用者自行云创建默认数据
     *
     * 下面是开始第一种方式传数据:
     * {@link ByteUtil#LENGTH_OF_BYTE} = 0
     * {@link ByteUtil#NULL_BYTE} = new byte[]{}
     * {@link ByteUtil#NOT_NULL_BYTE} = new byte[]{}
     *
     * 2.如果对象二进制数据前加一个标识,如果为0,表就示为空数据,
     * 则不传默认数据过云,如果为1就表示不为空,则会把真实数据传到后端
     *
     * 下面是开始第二种方式传数据:
     * {@link ByteUtil#LENGTH_OF_BYTE} = 1
     * {@link ByteUtil#NULL_BYTE} = new byte[]{0}
     * {@link ByteUtil#NOT_NULL_BYTE} = new byte[]{1}
     */
    public static final int LENGTH_OF_BYTE = 0;
    public static byte[] NULL_BYTE = new byte[]{};
    public static byte[] NOT_NULL_BYTE = new byte[]{};

    /**
     * 读取一个字节
     * @param arr
     * @param offset
     * @return
     */
    public static byte bytes2Byte(byte[] arr, int offset) {
        return arr[offset];
    }

    /**
     *
     * <pre>
     * 将4个byte数字组成的数组合并为一个float数.
     * </pre>
     *
     * @param arr
     * @return
     */
    public static float byte4ToFloat(byte[] arr) {
        if (arr == null || arr.length != 4) {
            throw new IllegalArgumentException("byte数组必须不为空,并且是4位!");
        }
        int i = byte4ToInt(arr, 0);
        return Float.intBitsToFloat(i);
    }

    /**
     *
     * <pre>
     * 将一个float数字转换为4个byte数字组成的数组.
     * </pre>
     *
     * @param f
     * @return
     */
    public static byte[] floatToByte4(float f) {
        int i = Float.floatToIntBits(f);
        return intToByte4(i);
    }

    /**
     *
     * <pre>
     * 将八个byte数字组成的数组转换为一个double数字.
     * </pre>
     *
     * @param arr
     * @return
     */
    public static double byte8ToDouble(byte[] arr) {
        if (arr == null || arr.length != 8) {
            throw new IllegalArgumentException("byte数组必须不为空,并且是8位!");
        }
        long l = byte8ToLong(arr, 0);
        return Double.longBitsToDouble(l);
    }

    /**
     *
     * <pre>
     * 将一个double数字转换为8个byte数字组成的数组.
     * </pre>
     *
     * @param i
     * @return
     */
    public static byte[] doubleToByte8(double i) {
        long j = Double.doubleToLongBits(i);
        return longToByte8(j);
    }

    /**
     *
     * <pre>
     * 将一个char字符转换为两个byte数字转换为的数组.
     * </pre>
     *
     * @param c
     * @return
     */
    public static byte[] charToByte2(char c) {
        byte[] arr = new byte[2];
        arr[0] = (byte) (c >> 8);
        arr[1] = (byte) (c & 0xff);
        return arr;
    }

    /**
     *
     * <pre>
     * 将2个byte数字组成的数组转换为一个char字符.
     * </pre>
     *
     * @param arr
     * @return
     */
    public static char byte2ToChar(byte[] arr, int offset) {
        byte[] result = new byte[LENGTH_OF_CHAR];
        System.arraycopy(arr, offset, result, 0, LENGTH_OF_CHAR);
        arr = result;

        if (arr == null || arr.length != 2) {
            throw new IllegalArgumentException("byte数组必须不为空,并且是2位!");
        }
        return (char) (((char) (arr[0] << 8)) | ((char) arr[1]));
    }

    /**
     *
     * <pre>
     * 将一个16位的short转换为长度为2的8位byte数组.
     * </pre>
     *
     * @param s
     * @return
     */
    public static byte[] shortToByte2(Short s) {
        byte[] arr = new byte[2];
        arr[0] = (byte) (s >> 8);
        arr[1] = (byte) (s & 0xff);
        return arr;
    }

    /**
     *
     * <pre>
     * 长度为2的8位byte数组转换为一个16位short数字.
     * </pre>
     *
     * @param arr
     * @return
     */
    public static short byte2ToShort(byte[] arr, int offset) {
        byte[] result = new byte[LENGTH_OF_SHORT];
        System.arraycopy(arr, offset, result, 0, LENGTH_OF_SHORT);
        arr = result;


        if (arr != null && arr.length != 2) {
            throw new IllegalArgumentException("byte数组必须不为空,并且是2位!");
        }
        return (short) (((short) arr[0] << 8) | ((short) arr[1] & 0xff));
    }

    /**
     *
     * <pre>
     * 将short转换为长度为16的byte数组.
     * 实际上每个8位byte只存储了一个0或1的数字
     * 比较浪费.
     * </pre>
     *
     * @param s
     * @return
     */
    public static byte[] shortToByte16(short s) {
        byte[] arr = new byte[16];
        for (int i = 15; i >= 0; i--) {
            arr[i] = (byte) (s & 1);
            s >>= 1;
        }
        return arr;
    }

    public static short byte16ToShort(byte[] arr) {
        if (arr == null || arr.length != 16) {
            throw new IllegalArgumentException("byte数组必须不为空,并且长度为16!");
        }
        short sum = 0;
        for (int i = 0; i < 16; ++i) {
            sum |= (arr[i] << (15 - i));
        }
        return sum;
    }

    /**
     *
     * <pre>
     * 将32位int转换为由四个8位byte数字.
     * </pre>
     *
     * @param sum
     * @return
     */
    public static byte[] intToByte4(int sum) {
        byte[] arr = new byte[4];
        arr[0] = (byte) (sum >> 24);
        arr[1] = (byte) (sum >> 16);
        arr[2] = (byte) (sum >> 8);
        arr[3] = (byte) (sum & 0xff);
        return arr;
    }

    /**
     * <pre>
     * 将长度为4的8位byte数组转换为32位int.
     * </pre>
     *
     * @param arr
     * @return
     */
    public static int byte4ToInt(byte[] arr, int offset) {
        byte[] result = new byte[LENGTH_OF_INT];
        System.arraycopy(arr, offset, result, 0, LENGTH_OF_INT);
        arr = result;

        if (arr == null || arr.length != 4) {
            throw new IllegalArgumentException("byte数组必须不为空,并且是4位!");
        }
        return (int) (((arr[0] & 0xff) << 24) | ((arr[1] & 0xff) << 16) | ((arr[2] & 0xff) << 8) | ((arr[3] & 0xff)));
    }

    /**
     *
     * <pre>
     * 将长度为8的8位byte数组转换为64位long.
     * </pre>
     *
     * 0xff对应16进制,f代表1111,0xff刚好是8位 byte[]
     * arr,byte[i]&0xff刚好满足一位byte计算,不会导致数据丢失. 如果是int计算. int[] arr,arr[i]&0xffff
     *
     * @param arr
     * @return
     */
    public static long byte8ToLong(byte[] arr, int offset) {
        byte[] result = new byte[LENGTH_OF_LONG];
        System.arraycopy(arr, offset, result, 0, LENGTH_OF_LONG);
        arr = result;


        if (arr == null || arr.length != 8) {
            throw new IllegalArgumentException("byte数组必须不为空,并且是8位!");
        }
        return (long) (((long) (arr[0] & 0xff) << 56) | ((long) (arr[1] & 0xff) << 48) | ((long) (arr[2] & 0xff) << 40)
                | ((long) (arr[3] & 0xff) << 32) | ((long) (arr[4] & 0xff) << 24)
                | ((long) (arr[5] & 0xff) << 16) | ((long) (arr[6] & 0xff) << 8) | ((long) (arr[7] & 0xff)));
    }

    /**
     * 将一个long数字转换为8个byte数组组成的数组.
     */
    public static byte[] longToByte8(long sum) {
        byte[] arr = new byte[8];
        arr[0] = (byte) (sum >> 56);
        arr[1] = (byte) (sum >> 48);
        arr[2] = (byte) (sum >> 40);
        arr[3] = (byte) (sum >> 32);
        arr[4] = (byte) (sum >> 24);
        arr[5] = (byte) (sum >> 16);
        arr[6] = (byte) (sum >> 8);
        arr[7] = (byte) (sum & 0xff);
        return arr;
    }

    /**
     *
     * <pre>
     * 将int转换为32位byte.
     * 实际上每个8位byte只存储了一个0或1的数字
     * 比较浪费.
     * </pre>
     *
     * @param num
     * @return
     */
    public static byte[] intToByte32(int num) {
        byte[] arr = new byte[32];
        for (int i = 31; i >= 0; i--) {
            // &1 也可以改为num&0x01,表示取最地位数字.
            arr[i] = (byte) (num & 1);
            // 右移一位.
            num >>= 1;
        }
        return arr;
    }

    /**
     *
     * <pre>
     * 将长度为32的byte数组转换为一个int类型值.
     * 每一个8位byte都只存储了0或1的数字.
     * </pre>
     *
     * @param arr
     * @return
     */
    public static int byte32ToInt(byte[] arr) {
        if (arr == null || arr.length != 32) {
            throw new IllegalArgumentException("byte数组必须不为空,并且长度是32!");
        }
        int sum = 0;
        for (int i = 0; i < 32; ++i) {
            sum |= (arr[i] << (31 - i));
        }
        return sum;
    }

    /**
     *
     * <pre>
     * 将长度为64的byte数组转换为一个long类型值.
     * 每一个8位byte都只存储了0或1的数字.
     * </pre>
     *
     * @param arr
     * @return
     */
    public static long byte64ToLong(byte[] arr) {
        if (arr == null || arr.length != 64) {
            throw new IllegalArgumentException("byte数组必须不为空,并且长度是64!");
        }
        long sum = 0L;
        for (int i = 0; i < 64; ++i) {
            sum |= ((long) arr[i] << (63 - i));
        }
        return sum;
    }

    /**
     *
     * <pre>
     * 将一个long值转换为长度为64的8位byte数组.
     * 每一个8位byte都只存储了0或1的数字.
     * </pre>
     *
     * @param sum
     * @return
     */
    public static byte[] longToByte64(long sum) {
        byte[] arr = new byte[64];
        for (int i = 63; i >= 0; i--) {
            arr[i] = (byte) (sum & 1);
            sum >>= 1;
        }
        return arr;
    }

    /**
     * 二进制数据传成字符串（utf8）
     * @param arr
     * @param offset
     * @return length of the string's byte and the string bytes
     */
    public static Object[] byte2String(byte[] arr, int offset) {
        return byte2String(arr, offset, "utf-8");
    }

    /**
     * 二进制数据转成特定编码的字符串
     * @param arr
     * @param offset
     * @param charset
     * @return length of the string's byte and the string bytes
     */
    public static Object[] byte2String(byte[] arr, int offset, String charset) {
        // 字符串长度数据
        byte[] result = new byte[LENGTH_OF_STRING];
        System.arraycopy(arr, offset, result, 0, LENGTH_OF_STRING);
        short stringLength = byte2ToShort(result, 0);
        if (stringLength == 0) {
            return new Object[]{null, stringLength};
        }

        // 获取到字符串的内容
        byte[] stringbytes = new byte[stringLength];
        System.arraycopy(arr, offset + LENGTH_OF_STRING, stringbytes, 0, stringLength);

        try {
            return new Object[] {new String(stringbytes, charset), stringLength};
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return new Object[] {null, stringLength};
        }
    }

    /**
     * string to byte[], and if not support utf-8 then return the byte[]{0};
     * @param inputs
     * @return
     */
    public static byte[] string2Byte(String inputs) {
        return string2Byte(inputs, "utf-8");
    }

    /**
     * string to byte[], and if not support utf-8 then return the byte[]{0};
     * @param inputs
     * @return
     */
    public static byte[] string2Byte(String inputs, String charset) {
        // string is null
        if (inputs == null || inputs.equals("")) {
            return shortToByte2((short)0);
        }

        try {
            byte[] datas = inputs.getBytes(charset);

            byte[] datalength = shortToByte2((short)datas.length);

            byte[] results = new byte[datalength.length + datas.length];
            // length of the string's length
            System.arraycopy(datalength, 0, results, 0, datalength.length);
            // the string's byte data
            System.arraycopy(datas, 0, results, datalength.length, datas.length);

            return results;
        } catch (UnsupportedEncodingException e) {
            return shortToByte2((short)0);
        }
    }

    /**
     * contract twe byte arrays
     * @param bytes1
     * @param bytes2
     * @return
     */
    public static byte[] contractByte(byte[] bytes1, byte[] bytes2) {
        if (bytes1 == null || bytes2 == null) {
            return null;
        }
        if (bytes1.length == 0) {
            return bytes2;
        }
        if (bytes2.length == 0) {
            return bytes1;
        }

        byte[] results = new byte[bytes1.length + bytes2.length];

        System.arraycopy(bytes1, 0, results, 0, bytes1.length);

        System.arraycopy(bytes2, 0, results, bytes1.length, bytes2.length);
        return results;
    }
}
