package com.int403.jabong.gifviewer2.bumptech.load.resource.bitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import com.int403.jabong.gifviewer2.bumptech.load.engine.bitmap_recycle.BitmapPool;

import java.security.MessageDigest;


/**
 * Scales the image uniformly (maintaining the image's aspect ratio) so that one of the dimensions of the image
 * will be equal to the given dimension and the other will be less than the given dimension.
 */
public class FitCenter extends com.int403.jabong.gifviewer2.bumptech.load.resource.bitmap.bumptech.BitmapTransformation {

    private static final String ID = "com.bumptech.glide.load.resource.bitmap.FitCenter";
    private static final byte[] ID_BYTES = ID.getBytes(CHARSET);

    public FitCenter(Context context) {
        super(context);
    }

    public FitCenter(BitmapPool bitmapPool) {
        super(bitmapPool);
    }

    @Override
    protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth,
                               int outHeight) {
        return TransformationUtils.fitCenter(pool, toTransform, outWidth, outHeight);
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof FitCenter;
    }

    @Override
    public int hashCode() {
        return ID.hashCode();
    }

    @Override
    public void updateDiskCacheKey(MessageDigest messageDigest) {
        messageDigest.update(ID_BYTES);
    }
}


