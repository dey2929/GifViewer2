package com.int403.jabong.gifviewer2.bumptech.load.resource.bitmap;

import android.graphics.Bitmap;
import android.os.ParcelFileDescriptor;

import com.int403.jabong.gifviewer2.bumptech.load.Encoder;
import com.int403.jabong.gifviewer2.bumptech.load.ResourceDecoder;
import com.int403.jabong.gifviewer2.bumptech.load.ResourceEncoder;
import com.int403.jabong.gifviewer2.bumptech.load.bumptech.DecodeFormat;
import com.int403.jabong.gifviewer2.bumptech.load.engine.bitmap_recycle.BitmapPool;
import com.int403.jabong.gifviewer2.bumptech.load.resource.NullEncoder;
import com.int403.jabong.gifviewer2.bumptech.load.resource.file.FileToStreamDecoder;
import com.int403.jabong.gifviewer2.bumptech.provider.DataLoadProvider;

import java.io.File;

/**
 * An {@link com.bumptech.glide.provider.DataLoadProvider} that provides classes for decoding and encoding
 * {@link android.graphics.Bitmap}s from {@link android.os.ParcelFileDescriptor} data.
 */
public class FileDescriptorBitmapDataLoadProvider implements DataLoadProvider<ParcelFileDescriptor, Bitmap> {
    private final ResourceDecoder<File, Bitmap> cacheDecoder;
    private final FileDescriptorBitmapDecoder sourceDecoder;
    private final BitmapEncoder encoder;
    private final Encoder<ParcelFileDescriptor> sourceEncoder;

    public FileDescriptorBitmapDataLoadProvider(BitmapPool bitmapPool, DecodeFormat decodeFormat) {
        cacheDecoder = new FileToStreamDecoder<Bitmap>(new StreamBitmapDecoder(bitmapPool, decodeFormat));
        sourceDecoder = new FileDescriptorBitmapDecoder(bitmapPool, decodeFormat);
        encoder = new BitmapEncoder();
        sourceEncoder = NullEncoder.get();
    }

    @Override
    public ResourceDecoder<File, Bitmap> getCacheDecoder() {
        return cacheDecoder;
    }

    @Override
    public ResourceDecoder<ParcelFileDescriptor, Bitmap> getSourceDecoder() {
        return sourceDecoder;
    }

    @Override
    public Encoder<ParcelFileDescriptor> getSourceEncoder() {
        return sourceEncoder;
    }

    @Override
    public ResourceEncoder<Bitmap> getEncoder() {
        return encoder;
    }
}