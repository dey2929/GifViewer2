package com.int403.jabong.gifviewer2.bumptech.load.model.stream;

import android.content.Context;
import android.net.Uri;
import com.int403.jabong.gifviewer2.bumptech.Glide;
import com.int403.jabong.gifviewer2.bumptech.load.model.FileLoader;
import com.int403.jabong.gifviewer2.bumptech.load.model.GenericLoaderFactory;
import com.int403.jabong.gifviewer2.bumptech.load.model.ModelLoader;
import com.int403.jabong.gifviewer2.bumptech.load.model.ModelLoaderFactory;

import java.io.File;
import java.io.InputStream;

/**
 * A {@link ModelLoader} For translating {@link File} models for local uris into {@link InputStream} data.
 */
public class StreamFileLoader extends FileLoader<InputStream> implements StreamModelLoader<File> {

    /**
     * The default factory for {@link com.int403.jabong.gifviewer2.bumptech.load.model.stream.StreamFileLoader}s.
     */
    public static class Factory implements ModelLoaderFactory<File, InputStream> {
        @Override
        public ModelLoader<File, InputStream> build(Context context, GenericLoaderFactory factories) {
            return new StreamFileLoader(factories.buildModelLoader(Uri.class, InputStream.class));
        }

        @Override
        public void teardown() {
            // Do nothing.
        }
    }

    public StreamFileLoader(Context context) {
        this(Glide.buildStreamModelLoader(Uri.class, context));
    }

    public StreamFileLoader(ModelLoader<Uri, InputStream> uriLoader) {
        super(uriLoader);
    }

}
