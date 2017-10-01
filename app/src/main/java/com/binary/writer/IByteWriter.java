package com.binary.writer;

import com.binary.exception.PackageException;
import com.binary.interfaces.ISupoortType;

/**
 *
 * @author sundarchen
 */

public interface IByteWriter<T> extends ISupoortType {

    byte[] write(T t, Class tClass) throws PackageException;
}
