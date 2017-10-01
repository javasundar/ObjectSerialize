package com.binary.reader;

import com.binary.interfaces.ISupoortType;
import com.binary.utils.BinaryBuffer;
import com.binary.exception.PackageException;

import java.lang.reflect.Type;

/**
 * base reader
 * @author sundarchen
 */

public interface IByteReader<T> extends ISupoortType {

    T read(Type type, BinaryBuffer binaryBuffer) throws PackageException;
}
