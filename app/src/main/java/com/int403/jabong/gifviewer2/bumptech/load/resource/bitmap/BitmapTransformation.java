package com.int403.jabong.gifviewer2.bumptech.load.resource.bitmap;

import android.content.Context;
import android.graphics.Bitmap;

import com.int403.jabong.gifviewer2.bumptech.Glide;
import com.int403.jabong.gifviewer2.bumptech.load.Transformation;
import com.int403.jabong.gifviewer2.bumptech.load.engine.Resource;
import com.int403.jabong.gifviewer2.bumptech.load.engine.bitmap_recycle.BitmapPool;



/**
 * A simple {@link Transformation} for transforming {@link Bitmap}s that
 * abstracts away dealing with {@link Resource} objects for subclasses.
 *
 * Use cases will look something like this:
 * <pre>
 * <code>
 * public class FillSpace extends BaseBitmapTransformation {
 *     {@literal @Override}
 *     public Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
 *         if (toTransform.getWidth() == outWidth && toTransform.getHeight() == outHeight) {
 *             return toTransform;
 *         }
 *
 *         return Bitmap.createScaledBitmap(toTransform, outWidth, outHeight, true);
 *     }
 * }
 * </code>
 * </pre>
 */
public abstract class BitmapTransformation implements Transformation<Bitmap> {

    private BitmapPool bitmapPool;

    public BitmapTransformation(Context context) {
        this(Glide.get(context).getBitmapPool());
    }

    public BitmapTransformation(BitmapPool bitmapPool) {
        this.bitmapPool = bitmapPool;
    }

    @Override
    public final Resource<Bitmap> transform(Resource<Bitmap> resource, int outWidth, int outHeight) {
        if (outWidth <= 0 || outHeight <= 0) {
            throw new IllegalArgumentException("Cannot apply transformation on width: " + outWidth + " or height: "
                    + outHeight + " less than or equal to zero");
        }
        Bitmap toTransform = resource.get();
        Bitmap transformed = transform(bitmapPool, toTransform, outWidth, outHeight);

        final Resource<Bitmap> result;
        if (toTransform.equals(transformed)) {
            result = resource;
        } else {
            result = BitmapResource.obtain(transformed, bitmapPool);
        }

        return result;
    }

    /**
     * Transforms the given {@link Bitmap} based on the given dimensions and returns the transformed
     * result.
     *
     * @param pool A {@link BitmapPool} that can be used to obtain and
     *             return intermediate {@link Bitmap}s used in this transformation. For every
     *             {@link Bitmap} obtained from the pool during this transformation, a
     *             {@link Bitmap} must also be returned.
     * @param toTransform The {@link Bitmap} to transform.
     * @param outWidth The ideal width of the transformed bitmap (does not need to match exactly).
     * @param outHeight The ideal height of the transformed bitmap (does not need to match exactly).
     */
    protected abstract Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight);
}
