package com.int403.jabong.gifviewer2.bumptech.load.resource;


import com.int403.jabong.gifviewer2.bumptech.load.ResourceEncoder;
import com.int403.jabong.gifviewer2.bumptech.load.engine.Resource;

import java.io.OutputStream;

/**
 * A simple {@link ResourceEncoder} that never writes data.
 *
 * @param <T> The type of the resource that will always fail to be encoded.
 */
public class NullResourceEncoder<T> implements ResourceEncoder<T> {
    private static final NullResourceEncoder<?> NULL_ENCODER = new NullResourceEncoder<Object>();

    /**
     * Returns a NullResourceEncoder for the given type.
     *
     * @param <T> The type of data to be written (or in this case not written).
     */
    @SuppressWarnings("unchecked")
    public static <T> NullResourceEncoder<T> get() {
        return (NullResourceEncoder<T>) NULL_ENCODER;
    }

    @Override
    public boolean encode(Resource<T> data, OutputStream os) {
        return false;
    }

    @Override
    public String getId() {
        return "";
    }
}
