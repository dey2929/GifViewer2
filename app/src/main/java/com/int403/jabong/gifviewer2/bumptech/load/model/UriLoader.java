package com.int403.jabong.gifviewer2.bumptech.load.model;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import com.int403.jabong.gifviewer2.bumptech.load.data.DataFetcher;

/**
 * A base ModelLoader for {@link Uri}s that handles local {@link Uri}s directly and routes
 * remote {@link Uri}s to a wrapped {@link com.int403.jabong.gifviewer2.bumptech.load.model.ModelLoader} that handles
 * {@link com.int403.jabong.gifviewer2.bumptech.load.model.GlideUrl}s.
 *
 * @param <T> The type of data that will be retrieved for {@link Uri}s.
 */
public abstract class UriLoader<T> implements ModelLoader<Uri, T> {
    private final Context context;
    private final ModelLoader<GlideUrl, T> urlLoader;

    public UriLoader(Context context, ModelLoader<GlideUrl, T> urlLoader) {
        this.context = context;
        this.urlLoader = urlLoader;
    }

    @Override
    public final DataFetcher<T> getResourceFetcher(Uri model, int width, int height) {
        final String scheme = model.getScheme();

        DataFetcher<T> result = null;
        if (isLocalUri(scheme)) {
            if (AssetUriParser.isAssetUri(model)) {
                String path = AssetUriParser.toAssetPath(model);
                result = getAssetPathFetcher(context, path);
            } else {
                result = getLocalUriFetcher(context, model);
            }
        } else if (urlLoader != null && ("http".equals(scheme) || "https".equals(scheme))) {
            result = urlLoader.getResourceFetcher(new GlideUrl(model.toString()), width, height);
        }

        return result;
    }

    protected abstract DataFetcher<T> getLocalUriFetcher(Context context, Uri uri);

    protected abstract DataFetcher<T> getAssetPathFetcher(Context context, String path);

    private static boolean isLocalUri(String scheme) {
        return ContentResolver.SCHEME_FILE.equals(scheme)
                || ContentResolver.SCHEME_CONTENT.equals(scheme)
                || ContentResolver.SCHEME_ANDROID_RESOURCE.equals(scheme);
    }
}
