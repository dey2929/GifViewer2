package com.int403.jabong.gifviewer2.bumptech.load.resource.bitmap.bumptech;

import android.util.Log;

import com.int403.jabong.gifviewer2.bumptech.load.ResourceDecoder;
import com.int403.jabong.gifviewer2.bumptech.load.bumptech.Option;
import com.int403.jabong.gifviewer2.bumptech.load.bumptech.Options;
import com.int403.jabong.gifviewer2.bumptech.load.engine.Resource;
import com.int403.jabong.gifviewer2.bumptech.load.engine.bitmap_recycle.bumptech.ArrayPool;
import com.int403.jabong.gifviewer2.bumptech.load.resource.gif.GifDrawable;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/**
 * A relatively inefficient decoder for {@link GifDrawable}
 * that converts {@link InputStream}s to {@link ByteBuffer}s and then passes
 * the buffer to a wrapped decoder.
 */
public class StreamGifDecoder implements ResourceDecoder<InputStream, GifDrawable> {
  private static final String TAG = "StreamGifDecoder";
  /**
   * If set to {@code true}, disables this decoder
   * ({@link #handles(InputStream, Options)} will return {@code false}). Defaults to
   * {@code false}.
   */
  public static final Option<Boolean> DISABLE_ANIMATION = Option.memory(
      "com.bumptech.glide.load.resource.gif.ByteBufferGifDecoder.DisableAnimation", false);

  private final ResourceDecoder<ByteBuffer, GifDrawable> byteBufferDecoder;
  private final ArrayPool byteArrayPool;

  public StreamGifDecoder(ResourceDecoder<ByteBuffer, GifDrawable> byteBufferDecoder,
      ArrayPool byteArrayPool) {
    this.byteBufferDecoder = byteBufferDecoder;
    this.byteArrayPool = byteArrayPool;
  }

  @Override
  public boolean handles(InputStream source, Options options) throws IOException {
    return !options.get(DISABLE_ANIMATION)
        && new ImageHeaderParser(source, byteArrayPool).getType() == ImageHeaderParser.ImageType.GIF;
  }

  @Override
  public Resource<GifDrawable> decode(InputStream source, int width, int height,
                                      Options options) throws IOException {
    byte[] data = inputStreamToBytes(source);
    if (data == null) {
      return null;
    }
    ByteBuffer byteBuffer = ByteBuffer.wrap(data);
    return byteBufferDecoder.decode(byteBuffer, width, height, options);
  }

  private static byte[] inputStreamToBytes(InputStream is) {
    final int bufferSize = 16384;
    ByteArrayOutputStream buffer = new ByteArrayOutputStream(bufferSize);
    try {
      int nRead;
      byte[] data = new byte[bufferSize];
      while ((nRead = is.read(data)) != -1) {
        buffer.write(data, 0, nRead);
      }
      buffer.flush();
    } catch (IOException e) {
      if (Log.isLoggable(TAG, Log.WARN)) {
        Log.w(TAG, "Error reading data from stream", e);
      }
      return null;
    }
    return buffer.toByteArray();
  }
}
