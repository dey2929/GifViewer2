package com.int403.jabong.gifviewer2.bumptech.load.resource.file;


import com.int403.jabong.gifviewer2.bumptech.load.ResourceDecoder;
import com.int403.jabong.gifviewer2.bumptech.load.engine.Resource;

import java.io.File;

/**
 * A simple {@link ResourceDecoder} that creates resource for a given {@link File}.
 */
public class FileDecoder implements ResourceDecoder<File, File> {

    @Override
    public Resource<File> decode(File source, int width, int height) {
        return new FileResource(source);
    }

    @Override
    public String getId() {
        return "";
    }
}
