package com.int403.jabong.gifviewer2.bumptech.load.resource.file;


import com.int403.jabong.gifviewer2.bumptech.load.resource.SimpleResource;

import java.io.File;

/**
 * A simple {@link com.int403.jabong.gifviewer2.bumptech.load.engine.Resource} that wraps a {@link File}.
 */
public class FileResource extends SimpleResource<File> {
    public FileResource(File file) {
        super(file);
    }
}
