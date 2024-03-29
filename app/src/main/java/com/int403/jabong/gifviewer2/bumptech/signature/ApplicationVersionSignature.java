package com.int403.jabong.gifviewer2.bumptech.signature;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.int403.jabong.gifviewer2.bumptech.load.bumptech.Key;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * A utility class for obtaining a {@link Key} signature containing the application version
 * name using {@link PackageInfo#versionCode}.
 */
public final class ApplicationVersionSignature {
    private static final ConcurrentHashMap<String, Key> PACKAGE_NAME_TO_KEY = new ConcurrentHashMap<String, Key>();

    /**
     * Returns the signature {@link Key} for version code of the Application of the given
     * Context.
     */
    public static Key obtain(Context context) {
        String packageName = context.getPackageName();
        Key result = PACKAGE_NAME_TO_KEY.get(packageName);
        if (result == null) {
            Key toAdd = obtainVersionSignature(context);
            result = PACKAGE_NAME_TO_KEY.putIfAbsent(packageName, toAdd);
            // There wasn't a previous mapping, so toAdd is now the Key.
            if (result == null) {
                result = toAdd;
            }
        }

        return result;
    }

    // Visible for testing.
    static void reset() {
        PACKAGE_NAME_TO_KEY.clear();
    }

    private static Key obtainVersionSignature(Context context) {
        PackageInfo pInfo = null;
        try {
            pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            // Should never happen.
            e.printStackTrace();
        }
        final String versionCode;
        if (pInfo != null) {
            versionCode = String.valueOf(pInfo.versionCode);
        } else {
            versionCode = UUID.randomUUID().toString();
        }
        return new StringSignature(versionCode);
    }

    private ApplicationVersionSignature() {
        // Empty for visibility.
    }
}
