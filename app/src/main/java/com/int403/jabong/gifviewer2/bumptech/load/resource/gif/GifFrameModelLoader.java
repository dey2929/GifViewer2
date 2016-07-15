package com.int403.jabong.gifviewer2.bumptech.load.resource.gif;


import com.int403.jabong.gifviewer2.bumptech.Priority;
import com.int403.jabong.gifviewer2.bumptech.load.data.DataFetcher;
import com.int403.jabong.gifviewer2.bumptech.load.model.ModelLoader;
import com.int403.jabong.gifviewer2.bumptech.third_party.gif_decoder.src.main.java.com.bumptech.glide.gifdecoder.GifDecoder;

class GifFrameModelLoader implements ModelLoader<GifDecoder, GifDecoder> {

    @Override
    public DataFetcher<GifDecoder> getResourceFetcher(GifDecoder model, int width, int height) {
        return new GifFrameDataFetcher(model);
    }

    private static class GifFrameDataFetcher implements DataFetcher<GifDecoder> {
        private final GifDecoder decoder;

        public GifFrameDataFetcher(GifDecoder decoder) {
            this.decoder = decoder;
        }

        @Override
        public GifDecoder loadData(Priority priority) {
            return decoder;
        }

        @Override
        public void cleanup() {
            // Do nothing. GifDecoder reads from an arbitrary InputStream, the caller will close that stream.
        }

        @Override
        public String getId() {
            return String.valueOf(decoder.getCurrentFrameIndex());
        }

        @Override
        public void cancel() {
            // Do nothing.
        }
    }
}
