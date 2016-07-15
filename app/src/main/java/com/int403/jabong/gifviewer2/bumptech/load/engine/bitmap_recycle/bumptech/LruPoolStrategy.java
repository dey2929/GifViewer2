package com.int403.jabong.gifviewer2.bumptech.load.engine.bitmap_recycle.bumptech;

import android.graphics.Bitmap;
import android.support.annotation.Nullable;

interface LruPoolStrategy {
  void put(Bitmap bitmap);

  @Nullable
  Bitmap get(int width, int height, Bitmap.Config config);

  @Nullable
  Bitmap removeLast();

  String logBitmap(Bitmap bitmap);

  String logBitmap(int width, int height, Bitmap.Config config);

  int getSize(Bitmap bitmap);
}
