package com.int403.jabong.gifviewer2.bumptech.manager;

import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;

import com.int403.jabong.gifviewer2.bumptech.RequestManager;


/**
 * A view-less {@link Fragment} used to safely store an
 * {@link RequestManager} that can be used to start, stop and manage Glide requests started for
 * targets within the fragment or activity this fragment is a child of.
 *
 * @see RequestManagerFragment
 * @see RequestManagerRetriever
 * @see RequestManager
 */
public class SupportRequestManagerFragment extends Fragment {
    private RequestManager requestManager;
    private final ActivityFragmentLifecycle lifecycle;

    public SupportRequestManagerFragment() {
        this(new ActivityFragmentLifecycle());
    }

    // For testing only.
    @SuppressLint("ValidFragment")
    public SupportRequestManagerFragment(ActivityFragmentLifecycle lifecycle) {
        this.lifecycle = lifecycle;
    }

    /**
     * Sets the current {@link RequestManager}.
     *
     * @param requestManager The manager to set.
     */
    public void setRequestManager(RequestManager requestManager) {
        this.requestManager = requestManager;
    }

    ActivityFragmentLifecycle getLifecycle() {
        return lifecycle;
    }

    /**
     * Returns the current {@link RequestManager} or null if none is set.
     */
    public RequestManager getRequestManager() {
        return requestManager;
    }

    @Override
    public void onStart() {
        super.onStart();
        lifecycle.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        lifecycle.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        lifecycle.onDestroy();
    }
}
