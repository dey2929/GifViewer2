package com.int403.jabong.gifviewer2.bumptech.load.engine.cache;


import com.int403.jabong.gifviewer2.bumptech.load.Key;
import com.int403.jabong.gifviewer2.bumptech.util.LruCache;
import com.int403.jabong.gifviewer2.bumptech.util.Util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * A class that generates and caches safe and unique string file names from {@link Key}s.
 */
class SafeKeyGenerator {
    private final LruCache<Key, String> loadIdToSafeHash = new LruCache<Key, String>(1000);

    public String getSafeKey(Key key) {
        String safeKey;
        synchronized (loadIdToSafeHash) {
            safeKey = loadIdToSafeHash.get(key);
        }
        if (safeKey == null) {
            try {
                MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
                key.updateDiskCacheKey(messageDigest);
                safeKey = Util.sha256BytesToHex(messageDigest.digest());
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            synchronized (loadIdToSafeHash) {
                loadIdToSafeHash.put(key, safeKey);
            }
        }
        return safeKey;
    }
}
