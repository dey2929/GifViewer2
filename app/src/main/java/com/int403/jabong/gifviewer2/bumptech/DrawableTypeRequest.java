package com.int403.jabong.gifviewer2.bumptech;

import android.content.Context;
import android.os.ParcelFileDescriptor;

import com.int403.jabong.gifviewer2.bumptech.load.model.ImageVideoModelLoader;
import com.int403.jabong.gifviewer2.bumptech.load.model.ImageVideoWrapper;
import com.int403.jabong.gifviewer2.bumptech.load.model.ModelLoader;
import com.int403.jabong.gifviewer2.bumptech.load.resource.drawable.GlideDrawable;
import com.int403.jabong.gifviewer2.bumptech.load.resource.gifbitmap.GifBitmapWrapper;
import com.int403.jabong.gifviewer2.bumptech.load.resource.transcode.ResourceTranscoder;
import com.int403.jabong.gifviewer2.bumptech.manager.Lifecycle;
import com.int403.jabong.gifviewer2.bumptech.manager.RequestTracker;
import com.int403.jabong.gifviewer2.bumptech.provider.DataLoadProvider;
import com.int403.jabong.gifviewer2.bumptech.provider.FixedLoadProvider;
import com.int403.jabong.gifviewer2.bumptech.request.FutureTarget;
import com.int403.jabong.gifviewer2.bumptech.request.target.Target;

import java.io.File;
import java.io.InputStream;

/**
 * A class for creating a load request that loads either an animated GIF drawable or a Bitmap drawable directly, or
 * adds an {@link com.int403.jabong.gifviewer2.bumptech.load.resource.transcode.ResourceTranscoder} to transcode the data into a
 * resource type other than a {@link android.graphics.drawable.Drawable}.
 *
 * @param <ModelType> The type of model to use to load the {@link android.graphics.drawable.BitmapDrawable} or
 * {@link com.int403.jabong.gifviewer2.bumptech.load.resource.gif.GifDrawable}.
 */
public class DrawableTypeRequest<ModelType> extends DrawableRequestBuilder<ModelType> implements DownloadOptions {
    private final ModelLoader<ModelType, InputStream> streamModelLoader;
    private final ModelLoader<ModelType, ParcelFileDescriptor> fileDescriptorModelLoader;
    private final RequestManager.OptionsApplier optionsApplier;

    private static <A, Z, R> FixedLoadProvider<A, ImageVideoWrapper, Z, R> buildProvider(Glide glide,
                                                                                         ModelLoader<A, InputStream> streamModelLoader,
                                                                                         ModelLoader<A, ParcelFileDescriptor> fileDescriptorModelLoader, Class<Z> resourceClass,
                                                                                         Class<R> transcodedClass,
                                                                                         ResourceTranscoder<Z, R> transcoder) {
        if (streamModelLoader == null && fileDescriptorModelLoader == null) {
            return null;
        }

        if (transcoder == null) {
            transcoder = glide.buildTranscoder(resourceClass, transcodedClass);
        }
        DataLoadProvider<ImageVideoWrapper, Z> dataLoadProvider = glide.buildDataProvider(ImageVideoWrapper.class,
                resourceClass);
        ImageVideoModelLoader<A> modelLoader = new ImageVideoModelLoader<A>(streamModelLoader,
                fileDescriptorModelLoader);
        return new FixedLoadProvider<A, ImageVideoWrapper, Z, R>(modelLoader, transcoder, dataLoadProvider);
    }

    DrawableTypeRequest(Class<ModelType> modelClass, ModelLoader<ModelType, InputStream> streamModelLoader,
                        ModelLoader<ModelType, ParcelFileDescriptor> fileDescriptorModelLoader, Context context, Glide glide,
                        RequestTracker requestTracker, Lifecycle lifecycle, RequestManager.OptionsApplier optionsApplier) {
        super(context, modelClass,
                buildProvider(glide, streamModelLoader, fileDescriptorModelLoader, GifBitmapWrapper.class,
                        GlideDrawable.class, null),
                glide, requestTracker, lifecycle);
        this.streamModelLoader = streamModelLoader;
        this.fileDescriptorModelLoader = fileDescriptorModelLoader;
        this.optionsApplier = optionsApplier;
    }

    /**
     * Attempts to always load the resource as a {@link android.graphics.Bitmap}, even if it could actually be animated.
     *
     * @return A new request builder for loading a {@link android.graphics.Bitmap}
     */
    public BitmapTypeRequest<ModelType> asBitmap() {
        return optionsApplier.apply(new BitmapTypeRequest<ModelType>(this, streamModelLoader,
                fileDescriptorModelLoader, optionsApplier));
    }

    /**
     * Attempts to always load the resource as a {@link com.int403.jabong.gifviewer2.bumptech.load.resource.gif.GifDrawable}.
     * <p>
     *     If the underlying data is not a GIF, this will fail. As a result, this should only be used if the model
     *     represents an animated GIF and the caller wants to interact with the GIfDrawable directly. Normally using
     *     just an {@link com.int403.jabong.gifviewer2.bumptech.DrawableTypeRequest} is sufficient because it will determine whether or
     *     not the given data represents an animated GIF and return the appropriate animated or not animated
     *     {@link android.graphics.drawable.Drawable} automatically.
     * </p>
     *
     * @return A new request builder for loading a {@link com.int403.jabong.gifviewer2.bumptech.load.resource.gif.GifDrawable}.
     */
    public GifTypeRequest<ModelType> asGif() {
        return optionsApplier.apply(new GifTypeRequest<ModelType>(this, streamModelLoader, optionsApplier));
    }

    /**
     * {@inheritDoc}
     */
    public <Y extends Target<File>> Y downloadOnly(Y target) {
        return getDownloadOnlyRequest().downloadOnly(target);
    }

    /**
     * {@inheritDoc}
     */
    public FutureTarget<File> downloadOnly(int width, int height) {
        return getDownloadOnlyRequest().downloadOnly(width, height);
    }

    private GenericTranscodeRequest<ModelType, InputStream, File> getDownloadOnlyRequest() {
        return optionsApplier.apply(new GenericTranscodeRequest<ModelType, InputStream, File>(File.class, this,
                streamModelLoader, InputStream.class, File.class, optionsApplier));
    }
}
