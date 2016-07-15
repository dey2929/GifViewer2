package com.int403.jabong.gifviewer2.bumptech.load.resource.gifbitmap;


import com.int403.jabong.gifviewer2.bumptech.load.ResourceDecoder;
import com.int403.jabong.gifviewer2.bumptech.load.engine.Resource;
import com.int403.jabong.gifviewer2.bumptech.load.model.ImageVideoWrapper;

import java.io.IOException;
import java.io.InputStream;

/**
 * A {@link ResourceDecoder} that can decode an
 * {@link GifBitmapWrapper} from {@link InputStream} data.
 */
public class GifBitmapWrapperStreamResourceDecoder implements ResourceDecoder<InputStream, GifBitmapWrapper> {
    private final ResourceDecoder<ImageVideoWrapper, GifBitmapWrapper> gifBitmapDecoder;

    public GifBitmapWrapperStreamResourceDecoder(
            ResourceDecoder<ImageVideoWrapper, GifBitmapWrapper> gifBitmapDecoder) {
        this.gifBitmapDecoder = gifBitmapDecoder;
    }

    @Override
    public Resource<GifBitmapWrapper> decode(InputStream source, int width, int height) throws IOException {
        return gifBitmapDecoder.decode(new ImageVideoWrapper(source, null), width, height);
    }

    @Override
    public String getId() {
        return gifBitmapDecoder.getId();
    }
}
