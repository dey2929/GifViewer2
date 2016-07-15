package com.int403.jabong.gifviewer2.bumptech.load.engine;


import com.int403.jabong.gifviewer2.bumptech.load.Key;

interface EngineJobListener {

    void onEngineJobComplete(Key key, EngineResource<?> resource);

    void onEngineJobCancelled(EngineJob engineJob, Key key);
}
