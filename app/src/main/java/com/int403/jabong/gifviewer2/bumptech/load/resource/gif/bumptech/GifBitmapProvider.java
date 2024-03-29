package com.int403.jabong.gifviewer2.bumptech.load.resource.gif.bumptech;


import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.int403.jabong.gifviewer2.bumptech.load.engine.bitmap_recycle.BitmapPool;
import com.int403.jabong.gifviewer2.bumptech.load.engine.bitmap_recycle.bumptech.ArrayPool;
import com.int403.jabong.gifviewer2.bumptech.third_party.gif_decoder.src.main.java.com.bumptech.glide.gifdecoder.GifDecoder;


/**
 * Implements {@link com.int403.jabong.gifviewer2.bumptech.third_party.gif_decoder.src.main.java.com.bumptech.glide.gifdecoder.GifDecoder.BitmapProvider} by wrapping Glide's
 * {@link BitmapPool}.
 */
public final class GifBitmapProvider implements GifDecoder.BitmapProvider {
  private final BitmapPool bitmapPool;
  @Nullable private final ArrayPool arrayPool;

  /**
   * Constructs an instance without a shared byte array pool. Byte arrays will be always constructed
   * when requested.
   */
  public GifBitmapProvider(BitmapPool bitmapPool) {
    this(bitmapPool, null /* arrayPool */);
  }

  /**
   * Constructs an instance with a shared array pool. Arrays will be reused where
   * possible.
   */
  public GifBitmapProvider(BitmapPool bitmapPool, ArrayPool arrayPool) {
    this.bitmapPool = bitmapPool;
    this.arrayPool = arrayPool;
  }

  @NonNull
  @Override
  public Bitmap obtain(int width, int height, Bitmap.Config config) {
    return bitmapPool.getDirty(width, height, config);
  }

  @Override
  public void release(Bitmap bitmap) {
    bitmapPool.put(bitmap);
  }

  @Override
  public byte[] obtainByteArray(int size) {
    if (arrayPool == null) {
      return new byte[size];
    }
    return arrayPool.get(size, byte[].class);
  }

  @SuppressWarnings("PMD.UseVarargs")
  @Override
  public void release(byte[] bytes) {
    if (arrayPool == null) {
      return;
    }
    arrayPool.put(bytes, byte[].class);
  }

  @Override
  public int[] obtainIntArray(int size) {
    if (arrayPool == null) {
      return new int[size];
    }
    return arrayPool.get(size, int[].class);
  }

  @SuppressWarnings("PMD.UseVarargs")
  @Override
  public void release(int[] array) {
    if (arrayPool == null) {
      return;
    }
    arrayPool.put(array, int[].class);
  }
}
