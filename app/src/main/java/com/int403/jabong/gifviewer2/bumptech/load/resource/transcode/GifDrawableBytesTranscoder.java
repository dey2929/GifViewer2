package com.int403.jabong.gifviewer2.bumptech.load.resource.transcode;


import com.int403.jabong.gifviewer2.bumptech.load.engine.Resource;
import com.int403.jabong.gifviewer2.bumptech.load.resource.bytes.BytesResource;
import com.int403.jabong.gifviewer2.bumptech.load.resource.gif.GifDrawable;

/**
 * An {@link ResourceTranscoder} that converts
 * {@link GifDrawable} into bytes by obtaining the original bytes of the GIF from
 * the {@link GifDrawable}.
 */
public class GifDrawableBytesTranscoder implements ResourceTranscoder<GifDrawable, byte[]> {
    @Override
    public Resource<byte[]> transcode(Resource<GifDrawable> toTranscode) {
        GifDrawable gifData = toTranscode.get();
        return new BytesResource(gifData.getData());
    }

    @Override
    public String getId() {
        return "GifDrawableBytesTranscoder.com.bumptech.glide.load.resource.transcode";
    }
}
