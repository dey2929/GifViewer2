package com.int403.jabong.gifviewer2.bumptech.load.data;

import android.content.res.AssetManager;
import android.os.ParcelFileDescriptor;

import java.io.IOException;

/**
 * Fetches an {@link ParcelFileDescriptor} for an asset path.
 */
public class FileDescriptorAssetPathFetcher extends AssetPathFetcher<ParcelFileDescriptor> {
    public FileDescriptorAssetPathFetcher(AssetManager assetManager, String assetPath) {
        super(assetManager, assetPath);
    }

    @Override
    protected ParcelFileDescriptor loadResource(AssetManager assetManager, String path) throws IOException {
        return assetManager.openFd(path).getParcelFileDescriptor();
    }

    @Override
    protected void close(ParcelFileDescriptor data) throws IOException {
        data.close();
    }
}
