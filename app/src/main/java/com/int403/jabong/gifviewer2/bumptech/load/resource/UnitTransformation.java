package com.int403.jabong.gifviewer2.bumptech.load.resource;


import com.int403.jabong.gifviewer2.bumptech.load.Transformation;
import com.int403.jabong.gifviewer2.bumptech.load.engine.Resource;

/**
 * A noop Transformation that simply returns the given resource.
 *
 * @param <T> The type of the resource that will always be returned unmodified.
 */
public class UnitTransformation<T> implements Transformation<T> {
    private static final Transformation<?> TRANSFORMATION = new UnitTransformation<Object>();

    /**
     * Returns a UnitTransformation for the given type.
     *
     * @param <T> The type of the resource to be transformed.
     */
    @SuppressWarnings("unchecked")
    public static <T> UnitTransformation<T> get() {
        return (UnitTransformation<T>) TRANSFORMATION;
    }

    @Override
    public Resource<T> transform(Resource<T> resource, int outWidth, int outHeight) {
        return resource;
    }

    @Override
    public String getId() {
        return "";
    }
}
