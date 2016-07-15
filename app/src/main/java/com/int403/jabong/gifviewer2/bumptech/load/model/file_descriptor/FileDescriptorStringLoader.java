package com.int403.jabong.gifviewer2.bumptech.load.model.file_descriptor;

import android.content.Context;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import com.int403.jabong.gifviewer2.bumptech.Glide;
import com.int403.jabong.gifviewer2.bumptech.load.model.GenericLoaderFactory;
import com.int403.jabong.gifviewer2.bumptech.load.model.ModelLoader;
import com.int403.jabong.gifviewer2.bumptech.load.model.ModelLoaderFactory;
import com.int403.jabong.gifviewer2.bumptech.load.model.StringLoader;

/**
 * A {@link ModelLoader} For translating {@link String} models, such as file paths, into {@link ParcelFileDescriptor}
 * data.
 */
public class FileDescriptorStringLoader extends StringLoader<ParcelFileDescriptor>
        implements FileDescriptorModelLoader<String> {

    /**
     * The default factory for {@link com.int403.jabong.gifviewer2.bumptech.load.model.file_descriptor.FileDescriptorStringLoader}s.
     */
    public static class Factory implements ModelLoaderFactory<String, ParcelFileDescriptor> {
        @Override
        public ModelLoader<String, ParcelFileDescriptor> build(Context context, GenericLoaderFactory factories) {
            return new FileDescriptorStringLoader(factories.buildModelLoader(Uri.class, ParcelFileDescriptor.class));
        }

        @Override
        public void teardown() {
            // Do nothing.
        }
    }

    public FileDescriptorStringLoader(Context context) {
        this(Glide.buildFileDescriptorModelLoader(Uri.class, context));
    }

    public FileDescriptorStringLoader(ModelLoader<Uri, ParcelFileDescriptor> uriLoader) {
        super(uriLoader);
    }
}
