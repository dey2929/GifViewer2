package com.int403.jabong.gifviewer2.bumptech.load.engine.bitmap_recycle;

import android.graphics.Bitmap;

/**
 * An { com.int403.jabong.gifviewer2.bumptech.load.engine.bitmap_recycle.BitmapPool BitmapPool} implementation that rejects all
 * {@link Bitmap Bitmap}s added to it and always returns {@code null} from get.
 */
public class BitmapPoolAdapter implements BitmapPool {
    @Override
    public int getMaxSize() {
        return 0;
    }

    @Override
    public void setSizeMultiplier(float sizeMultiplier) {
        // Do nothing.
    }

    @Override
    public boolean put(Bitmap bitmap) {
        return false;
    }

    @Override
    public Bitmap get(int width, int height, Bitmap.Config config) {
        return null;
    }

    @Override
    public Bitmap getDirty(int width, int height, Bitmap.Config config) {
        return null;
    }

    @Override
    public void clearMemory() {
        // Do nothing.
    }

    @Override
    public void trimMemory(int level) {
        // Do nothing.
    }
}
