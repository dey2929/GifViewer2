package com.int403.jabong.gifviewer2.bumptech.load.resource.bitmap.bumptech;

import android.graphics.Bitmap;

import com.int403.jabong.gifviewer2.bumptech.load.ResourceDecoder;
import com.int403.jabong.gifviewer2.bumptech.load.bumptech.Options;
import com.int403.jabong.gifviewer2.bumptech.load.engine.Resource;
import com.int403.jabong.gifviewer2.bumptech.util.bumptech.ByteBufferUtil;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/**
 * Decodes {@link Bitmap Bitmaps} from {@link ByteBuffer ByteBuffers}.
 */
public class ByteBufferBitmapDecoder implements ResourceDecoder<ByteBuffer, Bitmap> {
  private final Downsampler downsampler;

  public ByteBufferBitmapDecoder(Downsampler downsampler) {
    this.downsampler = downsampler;
  }

  @Override
  public boolean handles(ByteBuffer source, Options options) throws IOException {
    return downsampler.handles(source);
  }

  @Override
  public Resource<Bitmap> decode(ByteBuffer source, int width, int height, Options options)
      throws IOException {
    InputStream is = ByteBufferUtil.toStream(source);
    return downsampler.decode(is, width, height, options);
  }


}
