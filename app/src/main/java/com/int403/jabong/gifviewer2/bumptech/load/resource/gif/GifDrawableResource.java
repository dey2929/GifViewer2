package com.int403.jabong.gifviewer2.bumptech.load.resource.gif;


import com.int403.jabong.gifviewer2.bumptech.load.resource.drawable.DrawableResource;
import com.int403.jabong.gifviewer2.bumptech.util.Util;

/**
 * A resource wrapping an {@link GifDrawable}.
 */
public class GifDrawableResource extends DrawableResource<GifDrawable> {
    public GifDrawableResource(GifDrawable drawable) {
        super(drawable);
    }

    @Override
    public int getSize() {
        return drawable.getData().length + Util.getBitmapByteSize(drawable.getFirstFrame());
    }

    @Override
    public void recycle() {
        drawable.stop();
        drawable.recycle();
    }
}
