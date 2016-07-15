package com.int403.jabong.gifviewer2.bumptech.load.engine;


import com.int403.jabong.gifviewer2.bumptech.load.Encoder;
import com.int403.jabong.gifviewer2.bumptech.load.Key;
import com.int403.jabong.gifviewer2.bumptech.load.ResourceDecoder;
import com.int403.jabong.gifviewer2.bumptech.load.ResourceEncoder;
import com.int403.jabong.gifviewer2.bumptech.load.Transformation;
import com.int403.jabong.gifviewer2.bumptech.load.resource.transcode.ResourceTranscoder;

class EngineKeyFactory {

    @SuppressWarnings("rawtypes")
    public EngineKey buildKey(String id, Key signature, int width, int height, ResourceDecoder cacheDecoder,
                              ResourceDecoder sourceDecoder, Transformation transformation, ResourceEncoder encoder,
                              ResourceTranscoder transcoder, Encoder sourceEncoder) {
        return new EngineKey(id, signature, width, height, cacheDecoder, sourceDecoder, transformation, encoder,
                transcoder, sourceEncoder);
    }

}
