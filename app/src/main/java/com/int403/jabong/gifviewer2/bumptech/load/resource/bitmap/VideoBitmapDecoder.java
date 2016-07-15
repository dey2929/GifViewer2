package com.int403.jabong.gifviewer2.bumptech.load.resource.bitmap;

import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.os.ParcelFileDescriptor;

import com.int403.jabong.gifviewer2.bumptech.load.DecodeFormat;
import com.int403.jabong.gifviewer2.bumptech.load.engine.bitmap_recycle.BitmapPool;

import java.io.IOException;

/**
 * An {@link BitmapDecoder} that can decode a thumbnail frame
 * {@link Bitmap} from a {@link ParcelFileDescriptor} containing a video.
 *
 * @see MediaMetadataRetriever
 */
public class VideoBitmapDecoder implements BitmapDecoder<ParcelFileDescriptor> {
    private static final MediaMetadataRetrieverFactory DEFAULT_FACTORY =  new MediaMetadataRetrieverFactory();
    private MediaMetadataRetrieverFactory factory;

    public VideoBitmapDecoder() {
        this(DEFAULT_FACTORY);
    }

    VideoBitmapDecoder(MediaMetadataRetrieverFactory factory) {
        this.factory = factory;
    }

    @Override
    public Bitmap decode(ParcelFileDescriptor resource, BitmapPool bitmapPool, int outWidth, int outHeight,
                         DecodeFormat decodeFormat)
            throws IOException {
        MediaMetadataRetriever mediaMetadataRetriever = factory.build();
        mediaMetadataRetriever.setDataSource(resource.getFileDescriptor());
        Bitmap result = mediaMetadataRetriever.getFrameAtTime();
        mediaMetadataRetriever.release();
        resource.close();
        return result;
    }

    @Override
    public String getId() {
        return "VideoBitmapDecoder.com.bumptech.glide.load.resource.bitmap";
    }

    // Visible for testing.
    static class MediaMetadataRetrieverFactory {
        public MediaMetadataRetriever build() {
            return new MediaMetadataRetriever();
        }
    }
}
