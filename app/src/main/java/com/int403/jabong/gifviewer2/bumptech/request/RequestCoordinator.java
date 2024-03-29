package com.int403.jabong.gifviewer2.bumptech.request;

/**
 * An interface for coordinating multiple requests with the same {@link com.int403.jabong.gifviewer2.bumptech.request.target.Target}.
 */
public interface RequestCoordinator {

    /**
     * Returns true if the {@link Request} can display a loaded bitmap.
     *
     * @param request The {@link Request} requesting permission to display a bitmap.
     */
    boolean canSetImage(Request request);

    /**
     * Returns true if the {@link Request} can display a placeholder.
     *
     * @param request The {@link Request} requesting permission to display a placeholder.
     */
    boolean canNotifyStatusChanged(Request request);

    /**
     * Returns true if any coordinated {@link Request} has successfully completed.
     *
     * @see Request#isComplete()
     */
    boolean isAnyResourceSet();
}
