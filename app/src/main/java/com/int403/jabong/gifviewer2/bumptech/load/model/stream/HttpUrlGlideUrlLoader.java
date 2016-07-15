package com.int403.jabong.gifviewer2.bumptech.load.model.stream;

import android.content.Context;
import com.int403.jabong.gifviewer2.bumptech.load.data.DataFetcher;
import com.int403.jabong.gifviewer2.bumptech.load.data.HttpUrlFetcher;
import com.int403.jabong.gifviewer2.bumptech.load.model.GenericLoaderFactory;
import com.int403.jabong.gifviewer2.bumptech.load.model.GlideUrl;
import com.int403.jabong.gifviewer2.bumptech.load.model.ModelCache;
import com.int403.jabong.gifviewer2.bumptech.load.model.ModelLoader;
import com.int403.jabong.gifviewer2.bumptech.load.model.ModelLoaderFactory;

import java.io.InputStream;

/**
 * An {@link com.int403.jabong.gifviewer2.bumptech.load.model.ModelLoader} for translating {@link com.int403.jabong.gifviewer2.bumptech.load.model.GlideUrl}
 * (http/https URLS) into {@link InputStream} data.
 */
public class HttpUrlGlideUrlLoader implements ModelLoader<GlideUrl, InputStream> {

    private final ModelCache<GlideUrl, GlideUrl> modelCache;

    /**
     * The default factory for {@link com.int403.jabong.gifviewer2.bumptech.load.model.stream.HttpUrlGlideUrlLoader}s.
     */
    public static class Factory implements ModelLoaderFactory<GlideUrl, InputStream> {
        private final ModelCache<GlideUrl, GlideUrl> modelCache = new ModelCache<GlideUrl, GlideUrl>(500);

        @Override
        public ModelLoader<GlideUrl, InputStream> build(Context context, GenericLoaderFactory factories) {
            return new HttpUrlGlideUrlLoader(modelCache);
        }

        @Override
        public void teardown() {
            // Do nothing.
        }
    }

    public HttpUrlGlideUrlLoader() {
        this(null);
    }

    public HttpUrlGlideUrlLoader(ModelCache<GlideUrl, GlideUrl> modelCache) {
        this.modelCache = modelCache;
    }

    @Override
    public DataFetcher<InputStream> getResourceFetcher(GlideUrl model, int width, int height) {
        // GlideUrls memoize parsed URLs so caching them saves a few object instantiations and time spent parsing urls.
        GlideUrl url = model;
        if (modelCache != null) {
            url = modelCache.get(model, 0, 0);
            if (url == null) {
                modelCache.put(model, 0, 0, model);
                url = model;
            }
        }
        return new HttpUrlFetcher(url);
    }
}
