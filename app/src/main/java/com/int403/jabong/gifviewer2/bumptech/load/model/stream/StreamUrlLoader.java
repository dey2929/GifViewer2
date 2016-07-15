package com.int403.jabong.gifviewer2.bumptech.load.model.stream;

import android.content.Context;
import com.int403.jabong.gifviewer2.bumptech.load.model.GlideUrl;
import com.int403.jabong.gifviewer2.bumptech.load.model.GenericLoaderFactory;
import com.int403.jabong.gifviewer2.bumptech.load.model.ModelLoader;
import com.int403.jabong.gifviewer2.bumptech.load.model.ModelLoaderFactory;
import com.int403.jabong.gifviewer2.bumptech.load.model.UrlLoader;

import java.io.InputStream;
import java.net.URL;

/**
 * A wrapper class that translates {@link URL} objects into {@link com.int403.jabong.gifviewer2.bumptech.load.model.GlideUrl}
 * objects and then uses the wrapped {@link com.int403.jabong.gifviewer2.bumptech.load.model.ModelLoader} for
 * {@link com.int403.jabong.gifviewer2.bumptech.load.model.GlideUrl}s to load the {@link InputStream} data.
 */
public class StreamUrlLoader extends UrlLoader<InputStream> {

    /**
     * The default factory for {@link com.int403.jabong.gifviewer2.bumptech.load.model.stream.StreamUrlLoader}s.
     */
    public static class Factory implements ModelLoaderFactory<URL, InputStream> {
        @Override
        public ModelLoader<URL, InputStream> build(Context context, GenericLoaderFactory factories) {
            return new StreamUrlLoader(factories.buildModelLoader(GlideUrl.class, InputStream.class));
        }

        @Override
        public void teardown() {
            // Do nothing.
        }
    }

    public StreamUrlLoader(ModelLoader<GlideUrl, InputStream> glideUrlLoader) {
        super(glideUrlLoader);
    }
}
