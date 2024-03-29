package com.int403.jabong.gifviewer2.bumptech.load.data;

import android.content.res.AssetManager;
import android.util.Log;

import com.int403.jabong.gifviewer2.bumptech.Priority;

import java.io.IOException;

/**
 * An abstract class for obtaining data for an asset path using an {@link AssetManager}.
 *
 * @param <T> The type of data obtained from the asset path (InputStream, FileDescriptor etc).
 */
public abstract class AssetPathFetcher<T> implements DataFetcher<T> {
    private static final String TAG = "AssetUriFetcher";
    private final String assetPath;
    private final AssetManager assetManager;
    private T data;

    public AssetPathFetcher(AssetManager assetManager, String assetPath) {
        this.assetManager = assetManager;
        this.assetPath = assetPath;
    }

    @Override
    public T loadData(Priority priority) throws Exception {
        data = loadResource(assetManager, assetPath);
        return data;
    }

    @Override
    public void cleanup() {
        if (data == null) {
            return;
        }
        try {
            close(data);
        } catch (IOException e) {
            if (Log.isLoggable(TAG, Log.VERBOSE)) {
                Log.v(TAG, "Failed to close data", e);
            }
        }

    }

    @Override
    public String getId() {
        return assetPath;
    }

    @Override
    public void cancel() {
        // Do nothing.
    }

    /**
     * Opens the given asset path with the given {@link AssetManager} and returns the conrete data
     * type returned by the AssetManager.
     *
     * @param assetManager An AssetManager to use to open the given path.
     * @param path A string path pointing to a resource in assets to open.
     */
    protected abstract T loadResource(AssetManager assetManager, String path) throws IOException;

    /**
     * Closes the concrete data type if necessary.
     *
     * @param data The data to close.
     * @throws IOException
     */
    protected abstract void close(T data) throws IOException;
}
