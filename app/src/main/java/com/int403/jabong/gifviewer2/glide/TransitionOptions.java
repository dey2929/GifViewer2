package com.int403.jabong.gifviewer2.glide;

import com.bumptech.glide.request.transition.NoTransition;
import com.bumptech.glide.request.transition.TransitionFactory;
import com.bumptech.glide.request.transition.ViewAnimationFactory;
import com.bumptech.glide.request.transition.ViewPropertyAnimationFactory;
import com.bumptech.glide.request.transition.ViewPropertyTransition;
import com.bumptech.glide.util.Preconditions;

/**
 * A base class for setting a transition to use on a resource when a load completes.
 *
 * @param <CHILD>         The implementation of this class to return to chain methods.
 * @param <TranscodeType> The type of resource that will be animated.
 */
public abstract class TransitionOptions<CHILD extends TransitionOptions<CHILD, TranscodeType>,
    TranscodeType> implements Cloneable {
  private TransitionFactory<? super TranscodeType> transitionFactory = NoTransition.getFactory();

  /**
   * Removes any existing animation put on the builder. Will be overridden by subsequent calls that
   * put an animation.
   *
   * @return This request builder.
   */
  public final CHILD dontTransition() {
    return transition(NoTransition.getFactory());
  }

  /**
   * Sets a {@link android.view.animation} to run on the wrapped target when an resource load
   * finishes. Will only be run if the resource was loaded asynchronously (ie was not in the memory
   * cache)
   *
   * @param viewAnimationId The resource id of the {@link android.view.animation} to use as the
   *                        transition.
   * @return This request builder.
   */
  public final CHILD transition(int viewAnimationId) {
    return transition(new ViewAnimationFactory<TranscodeType>(viewAnimationId));
  }

  /**
   * Sets an animator to run a {@link android.view.ViewPropertyAnimator} on a view that the target
   * may be wrapping when a resource load finishes. Will only be run if the load was loaded
   * asynchronously (ie was not in the memory cache).
   *
   * @param animator The {@link com.bumptech.glide.request.transition.ViewPropertyTransition
   *                 .Animator} to run.
   * @return This request builder.
   */
  public final CHILD transition(ViewPropertyTransition.Animator animator) {
    return transition(new ViewPropertyAnimationFactory<TranscodeType>(animator));
  }

  public final CHILD transition(TransitionFactory<? super TranscodeType> transitionFactory) {
    this.transitionFactory = Preconditions.checkNotNull(transitionFactory);
    return self();
  }

  @SuppressWarnings("unchecked")
  @Override
  protected final CHILD clone() {
    try {
      return (CHILD) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new RuntimeException(e);
    }
  }

  final TransitionFactory<? super TranscodeType> getTransitionFactory() {
    return transitionFactory;
  }

  @SuppressWarnings("unchecked")
  private CHILD self() {
    return (CHILD) this;
  }
}
