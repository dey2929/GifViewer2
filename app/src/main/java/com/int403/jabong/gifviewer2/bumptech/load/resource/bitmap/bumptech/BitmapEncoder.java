package com.int403.jabong.gifviewer2.bumptech.load.resource.bitmap.bumptech;

import android.graphics.Bitmap;
import android.util.Log;

import com.int403.jabong.gifviewer2.bumptech.load.EncodeStrategy;
import com.int403.jabong.gifviewer2.bumptech.load.bumptech.Option;
import com.int403.jabong.gifviewer2.bumptech.load.bumptech.Options;
import com.int403.jabong.gifviewer2.bumptech.load.engine.Resource;
import com.int403.jabong.gifviewer2.bumptech.util.LogTime;
import com.int403.jabong.gifviewer2.bumptech.util.Util;
import com.int403.jabong.gifviewer2.glide.load.ResourceEncoder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * An {@link ResourceEncoder} that writes {@link Bitmap}s
 * to {@link OutputStream}s.
 *
 * <p> {@link Bitmap}s that return true from
 * {@link Bitmap#hasAlpha ()}} are written using
 * {@link Bitmap.CompressFormat#PNG}
 * to preserve alpha and all other bitmaps are written using
 * {@link Bitmap.CompressFormat#JPEG}. </p>
 *
 * @see Bitmap#compress(Bitmap.CompressFormat, int,
 * OutputStream)
 */
public class BitmapEncoder implements ResourceEncoder<Bitmap> {
  /**
   * An integer option between 0 and 100 that is used as the compression quality.
   *
   * <p> Defaults to 90. </p>
   */
  public static final Option<Integer> COMPRESSION_QUALITY = Option.memory(
      "com.bumptech.glide.load.resource.bitmap.BitmapEncoder.CompressionQuality", 90);

  /**
   * An {@link Bitmap.CompressFormat} option used as the format to encode
   * the {@link Bitmap}.
   *
   * <p> Defaults to {@link Bitmap.CompressFormat#JPEG} for images without alpha
   * and {@link Bitmap.CompressFormat#PNG} for images with alpha. </p>
   */
  public static final Option<Bitmap.CompressFormat> COMPRESSION_FORMAT = Option.memory(
      "com.bumptech.glide.load.resource.bitmap.BitmapEncoder.CompressionFormat");

  private static final String TAG = "BitmapEncoder";


  public boolean encode(Resource<Bitmap> resource, File file, Options options) {
    final Bitmap bitmap = resource.get();

    long start = LogTime.getLogTime();
    Bitmap.CompressFormat format = getFormat(bitmap, options);
    int quality = options.get(COMPRESSION_QUALITY);

    boolean success = false;
    OutputStream os = null;
    try {
      os = new FileOutputStream(file);
      bitmap.compress(format, quality, os);
      os.close();
      success = true;
    } catch (IOException e) {
      if (Log.isLoggable(TAG, Log.DEBUG)) {
        Log.d(TAG, "Failed to encode Bitmap", e);
      }
    } finally {
      if (os != null) {
        try {
          os.close();
        } catch (IOException e) {
          // Do nothing.
        }
      }
    }

    if (Log.isLoggable(TAG, Log.VERBOSE)) {
      Log.v(TAG, "Compressed with type: " + format + " of size " + Util.getBitmapByteSize(bitmap)
          + " in " + LogTime.getElapsedMillis(start));
    }
    return success;
  }

  private Bitmap.CompressFormat getFormat(Bitmap bitmap, Options options) {
    Bitmap.CompressFormat format = options.get(COMPRESSION_FORMAT);
    if (format != null) {
      return format;
    } else if (bitmap.hasAlpha()) {
      return Bitmap.CompressFormat.PNG;
    } else {
      return Bitmap.CompressFormat.JPEG;
    }
  }

  @Override
  public EncodeStrategy getEncodeStrategy(Options options) {
    return EncodeStrategy.TRANSFORMED;
  }


  public boolean encode(Resource<Bitmap> data, OutputStream os) {
    return false;
  }

  @Override
  public String getId() {
    return "BitmapEncoder.com.bumptech.glide.load.resource.bitmap";
  }
}
