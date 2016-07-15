package com.int403.jabong.gifviewer2.bumptech.load.resource.bitmap;

import android.graphics.Bitmap;

import com.int403.jabong.gifviewer2.bumptech.load.DecodeFormat;
import com.int403.jabong.gifviewer2.bumptech.load.Encoder;
import com.int403.jabong.gifviewer2.bumptech.load.ResourceDecoder;
import com.int403.jabong.gifviewer2.bumptech.load.ResourceEncoder;
import com.int403.jabong.gifviewer2.bumptech.load.engine.bitmap_recycle.BitmapPool;
import com.int403.jabong.gifviewer2.bumptech.load.model.StreamEncoder;
import com.int403.jabong.gifviewer2.bumptech.load.resource.file.FileToStreamDecoder;
import com.int403.jabong.gifviewer2.bumptech.provider.DataLoadProvider;

import java.io.File;
import java.io.InputStream;

/**
 * An {@link DataLoadProvider} that provides decoders and encoders for decoding and caching
 * {@link Bitmap}s using {@link InputStream} data.
 */
public class StreamBitmapDataLoadProvider implements DataLoadProvider<InputStream, Bitmap> {
    private final StreamBitmapDecoder decoder;
    private final BitmapEncoder encoder;
    private final StreamEncoder sourceEncoder;
    private final FileToStreamDecoder<Bitmap> cacheDecoder;

    public StreamBitmapDataLoadProvider(BitmapPool bitmapPool, DecodeFormat decodeFormat) {
        sourceEncoder = new StreamEncoder();
        decoder = new StreamBitmapDecoder(bitmapPool, decodeFormat);
        encoder = new BitmapEncoder();
        cacheDecoder = new FileToStreamDecoder<Bitmap>(decoder);
    }

    @Override
    public ResourceDecoder<File, Bitmap> getCacheDecoder() {
        return cacheDecoder;
    }

    @Override
    public ResourceDecoder<InputStream, Bitmap> getSourceDecoder() {
        return decoder;
    }

    @Override
    public Encoder<InputStream> getSourceEncoder() {
        return sourceEncoder;
    }

    @Override
    public ResourceEncoder<Bitmap> getEncoder() {
        return encoder;
    }
}
