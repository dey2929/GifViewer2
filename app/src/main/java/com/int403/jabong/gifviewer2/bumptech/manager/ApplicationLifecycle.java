package com.int403.jabong.gifviewer2.bumptech.manager;

/**
 * A {@link Lifecycle} implementation for tracking and notifying listeners of
 * {@link android.app.Application} lifecycle events.
 *
 * <p>
 *     Since there are essentially no {@link android.app.Application} lifecycle events, this class simply defaults to
 *     notifying new listeners that they are started.
 * </p>
 */
class ApplicationLifecycle implements Lifecycle {
    @Override
    public void addListener(LifecycleListener listener) {
        listener.onStart();
    }
}
