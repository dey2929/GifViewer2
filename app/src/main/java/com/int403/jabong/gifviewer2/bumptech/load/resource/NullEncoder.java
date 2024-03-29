package com.int403.jabong.gifviewer2.bumptech.load.resource;


import com.int403.jabong.gifviewer2.bumptech.load.Encoder;

import java.io.OutputStream;

/**
 * A simple {@link Encoder} that never writes data.
 *
 * @param <T> type discarded by this Encoder
 */
public class NullEncoder<T> implements Encoder<T> {
    private static final NullEncoder<?> NULL_ENCODER = new NullEncoder<Object>();

    /**
     * Returns an Encoder for the given data type.
     *
     * @param <T> The type of data to be written (or not in this case).
     */
    @SuppressWarnings("unchecked")
    public static <T> Encoder<T> get() {
        return (Encoder<T>) NULL_ENCODER;

    }

    @Override
    public boolean encode(T data, OutputStream os) {
        return false;
    }

    @Override
    public String getId() {
        return "";
    }
}
