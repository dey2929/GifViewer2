package com.int403.jabong.gifviewer2.bumptech.load.resource.file;


import com.int403.jabong.gifviewer2.bumptech.load.Encoder;
import com.int403.jabong.gifviewer2.bumptech.load.ResourceDecoder;
import com.int403.jabong.gifviewer2.bumptech.load.ResourceEncoder;
import com.int403.jabong.gifviewer2.bumptech.load.engine.Resource;
import com.int403.jabong.gifviewer2.bumptech.load.model.StreamEncoder;
import com.int403.jabong.gifviewer2.bumptech.load.resource.NullResourceEncoder;
import com.int403.jabong.gifviewer2.bumptech.provider.DataLoadProvider;

import java.io.File;
import java.io.InputStream;

/**
 * An {@link DataLoadProvider} that provides encoders and decoders for for obtaining a
 * cache file from {@link InputStream} data.
 */
public class StreamFileDataLoadProvider implements DataLoadProvider<InputStream, File> {
    private static final ErrorSourceDecoder ERROR_DECODER = new ErrorSourceDecoder();

    private final ResourceDecoder<File, File> cacheDecoder;
    private final Encoder<InputStream> encoder;

    public StreamFileDataLoadProvider() {
        cacheDecoder = new FileDecoder();
        encoder = new StreamEncoder();
    }

    @Override
    public ResourceDecoder<File, File> getCacheDecoder() {
        return cacheDecoder;
    }

    @Override
    public ResourceDecoder<InputStream, File> getSourceDecoder() {
        return ERROR_DECODER;
    }

    @Override
    public Encoder<InputStream> getSourceEncoder() {
        return encoder;
    }

    @Override
    public ResourceEncoder<File> getEncoder() {
        return NullResourceEncoder.get();
    }

    private static class ErrorSourceDecoder implements ResourceDecoder<InputStream, File> {
        @Override
        public Resource<File> decode(InputStream source, int width, int height) {
            throw new Error("You cannot decode a File from an InputStream by default,"
                    + " try either #diskCacheStratey(DiskCacheStrategy.SOURCE) to avoid this call or"
                    + " #decoder(ResourceDecoder) to replace this Decoder");
        }

        @Override
        public String getId() {
            return "";
        }
    }
}
