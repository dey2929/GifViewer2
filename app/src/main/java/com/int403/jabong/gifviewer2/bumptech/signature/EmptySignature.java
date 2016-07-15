package com.int403.jabong.gifviewer2.bumptech.signature;


import com.int403.jabong.gifviewer2.bumptech.load.Key;

import java.security.MessageDigest;

/**
 * An empty key that is always equal to all other empty keys.
 */
public final class EmptySignature implements Key {
    private static final EmptySignature EMPTY_KEY = new EmptySignature();

    public static EmptySignature obtain() {
        return EMPTY_KEY;
    }

    private EmptySignature() {
        // Empty.
    }

    @Override
    public void updateDiskCacheKey(MessageDigest messageDigest) {
        // Do nothing.
    }
}
