package com.int403.jabong.gifviewer2.bumptech.load;

/**
 * Details how an {@link ResourceEncoder} will encode a resource to cache.
 */
public enum EncodeStrategy {
  /**
   * Writes the original unmodified data for the resource to disk, not include downsampling or
   * transformations.
   */
  SOURCE,

  /**
   * Writes the decoded, downsampled and transformed data for the resource to disk.
   */
  TRANSFORMED,

  /**
   * Will write no data.
   */
  NONE,
}
