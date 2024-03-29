package com.bumptech.glide.load.resource.bitmap;

import android.content.Context;
import android.graphics.Bitmap;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.util.Preconditions;

import java.nio.ByteBuffer;
import java.security.MessageDigest;

/**
 * A {@link BitmapTransformation} which rounds the corners of a bitmap.
 */
public final class RoundedCorners extends BitmapTransformation {
  private static final String ID = "com.bumptech.com.int403.jabong.gifviewer2.glide.load.resource.bitmap.RoundedCorners";
  private static final byte[] ID_BYTES = ID.getBytes(CHARSET);

  private final int roundingRadius;

  /**
   * @param roundingRadius the corner radius (in device-specific pixels).
   * @throws IllegalArgumentException if rounding radius is 0 or less.
   */
  public RoundedCorners(BitmapPool bitmapPool, int roundingRadius) {
    super(bitmapPool);
    Preconditions.checkArgument(roundingRadius > 0, "roundingRadius must be greater than 0.");
    this.roundingRadius = roundingRadius;
  }

  /**
   * @param roundingRadius the corner radius (in device-specific pixels).
   * @throws IllegalArgumentException if rounding radius is 0 or less.
   */
  public RoundedCorners(Context context, int roundingRadius) {
    super(context);
    Preconditions.checkArgument(roundingRadius > 0, "roundingRadius must be greater than 0.");
    this.roundingRadius = roundingRadius;
  }

  @Override
  protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
    return TransformationUtils.roundedCorners(pool, toTransform, outWidth, outHeight,
        roundingRadius);
  }

  @Override
  public boolean equals(Object o) {
    return (o instanceof RoundedCorners) && ((RoundedCorners) o).roundingRadius == roundingRadius;
  }

  @Override
  public int hashCode() {
    return ID.hashCode() + roundingRadius;
  }

  @Override
  public void updateDiskCacheKey(MessageDigest messageDigest) {
    messageDigest.update(ID_BYTES);

    byte[] radiusData = ByteBuffer.allocate(4).putInt(roundingRadius).array();
    messageDigest.update(radiusData);
  }
}
