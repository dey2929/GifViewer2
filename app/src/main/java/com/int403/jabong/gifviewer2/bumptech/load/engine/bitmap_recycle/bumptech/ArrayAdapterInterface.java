package com.int403.jabong.gifviewer2.bumptech.load.engine.bitmap_recycle.bumptech;
/**
 * Interface for handling operations on a primitive array type.
 * @param <T> Array type (eg byte[], int[])
 */
public interface ArrayAdapterInterface<T> {

  /**
   * TAG for logging.
   */
  String getTag();

  /**
   * Return the length of the given array.
   */
  int getArrayLength(T array);

  /**
   * Reset the array for re-use (e.g. set all values to 0).
   */
  void resetArray(T array);

  /**
   * Allocate and return an array of the specified size.
   */
  T newArray(int length);

  /**
   * Return the size of an element in the array in bytes (e.g. for int return 4).
   */
  int getElementSizeInBytes();
}
