package com.int403.jabong.gifviewer2.bumptech.load;


import com.int403.jabong.gifviewer2.bumptech.load.engine.Resource;

/**
 * An interface for writing data from a resource to some persistent data store (i.e. a local File cache).
 *
 * @param <T> The type of the data contained by the resource.
 */
public interface ResourceEncoder<T> extends Encoder<Resource<T>> {
    // specializing the generic arguments
}
