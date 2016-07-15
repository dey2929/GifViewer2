package com.int403.jabong.gifviewer2.bumptech.load.resource.gif;

import android.graphics.Bitmap;

import com.int403.jabong.gifviewer2.bumptech.load.ResourceDecoder;
import com.int403.jabong.gifviewer2.bumptech.load.engine.Resource;
import com.int403.jabong.gifviewer2.bumptech.load.engine.bitmap_recycle.BitmapPool;
import com.int403.jabong.gifviewer2.bumptech.load.resource.bitmap.BitmapResource;
import com.int403.jabong.gifviewer2.bumptech.third_party.gif_decoder.src.main.java.com.bumptech.glide.gifdecoder.GifDecoder;


class GifFrameResourceDecoder implements ResourceDecoder<GifDecoder, Bitmap> {
    private final BitmapPool bitmapPool;

    public GifFrameResourceDecoder(BitmapPool bitmapPool) {
        this.bitmapPool = bitmapPool;
    }

    @Override
    public Resource<Bitmap> decode(GifDecoder source, int width, int height) {
        Bitmap bitmap = source.getNextFrame();
        return BitmapResource.obtain(bitmap, bitmapPool);
    }

    @Override
    public String getId() {
        return "GifFrameResourceDecoder.com.bumptech.glide.load.resource.gif";
    }
}
