package com.int403.jabong.gifviewer2.bumptech.request.animation;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;

/**
 * A cross fade {@link GlideAnimation} for {@link Drawable}s
 * that uses an {@link TransitionDrawable} to transition from an existing drawable
 * already visible on the target to a new drawable. If no existing drawable exists, this class can instead fall back
 * to a default animation that doesn't rely on {@link TransitionDrawable}.
 *
 * @param <T> The type of the {@link Drawable} that will be animated.
 */
public class DrawableCrossFadeViewAnimation<T extends Drawable> implements GlideAnimation<T> {
    private final GlideAnimation<T> defaultAnimation;
    private final int duration;

    /**
     * Constructor that takes a default animation and a duration in milliseconds that the cross fade animation should
     * last.
     * @param duration The duration that the cross fade animation should run if there is something to cross fade from
     *                 when a new {@link Drawable} is set.
     */
    public DrawableCrossFadeViewAnimation(GlideAnimation<T> defaultAnimation, int duration) {
        this.defaultAnimation = defaultAnimation;
        this.duration = duration;
    }

    /**
     * Animates from the previous drawable to the current drawable in one of two ways.
     *
     * <ol>
     *     <li>Using the default animation provided in the constructor if the previous drawable is null</li>
     *     <li>Using the cross fade animation with the duration provided in the constructor if the previous
     *     drawable is non null</li>
     * </ol>
     *
     * @param current {@inheritDoc}
     * @param adapter  {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public boolean animate(T current, ViewAdapter adapter) {
        Drawable previous = adapter.getCurrentDrawable();
        if (previous != null) {
            TransitionDrawable transitionDrawable = new TransitionDrawable(new Drawable[] { previous, current });
            transitionDrawable.setCrossFadeEnabled(true);
            transitionDrawable.startTransition(duration);
            adapter.setDrawable(transitionDrawable);
            return true;
        } else {
            defaultAnimation.animate(current, adapter);
            return false;
        }
    }
}
