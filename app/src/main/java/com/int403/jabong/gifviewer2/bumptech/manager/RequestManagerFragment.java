package com.int403.jabong.gifviewer2.bumptech.manager;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Fragment;
import android.os.Build;

import com.int403.jabong.gifviewer2.bumptech.RequestManager;


/**
 * A view-less {@link Fragment} used to safely store an {@link RequestManager} that
 * can be used to start, stop and manage Glide requests started for targets the fragment or activity this fragment is a
 * child of.
 *
 * @see .SupportRequestManagerFragment
 * @see RequestManagerRetriever
 * @see RequestManager
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class RequestManagerFragment extends Fragment {
    private final ActivityFragmentLifecycle lifecycle;
    private RequestManager requestManager;

    public RequestManagerFragment() {
        this(new ActivityFragmentLifecycle());
    }

    // For testing only.
    @SuppressLint("ValidFragment")
    RequestManagerFragment(ActivityFragmentLifecycle lifecycle) {
        this.lifecycle = lifecycle;
    }

    /**
     * Sets the current {@link RequestManager}.
     *
     * @param requestManager The request manager to use.
     */
    public void setRequestManager(RequestManager requestManager) {
        this.requestManager = requestManager;
    }

    ActivityFragmentLifecycle getLifecycle() {
        return lifecycle;
    }

    /**
     * Returns the current {@link RequestManager} or null if none exists.
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
