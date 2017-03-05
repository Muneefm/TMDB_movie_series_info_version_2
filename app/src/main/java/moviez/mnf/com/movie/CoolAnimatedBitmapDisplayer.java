package moviez.mnf.com.movie;

import android.graphics.Bitmap;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;

import com.nostra13.universalimageloader.core.assist.LoadedFrom;
import com.nostra13.universalimageloader.core.display.BitmapDisplayer;
import com.nostra13.universalimageloader.core.imageaware.ImageAware;

/**
 * Created by Muneef on 02/05/15.
 */
public class CoolAnimatedBitmapDisplayer implements BitmapDisplayer {
    private long durationMillis;

    public CoolAnimatedBitmapDisplayer() {
        this.durationMillis = 500L;
    }

    public CoolAnimatedBitmapDisplayer(long paramLong) {
        this.durationMillis = paramLong;
    }

    public static void postLoadAnimate(View paramView, long paramLong) {
        if (paramView != null) {
            AnimationSet localAnimationSet = new AnimationSet(false);
            ScaleAnimation localScaleAnimation = new ScaleAnimation(0.9F, 1.0F, 0.9F, 1.0F, 1, 0.5F, 1, 0.5F);
            localScaleAnimation.setDuration(paramLong);
            localScaleAnimation.setInterpolator(new DecelerateInterpolator());
            localAnimationSet.addAnimation(localScaleAnimation);
            AlphaAnimation localAlphaAnimation = new AlphaAnimation(0.0F, 1.0F);
            localAlphaAnimation.setDuration(paramLong);
            localAlphaAnimation.setInterpolator(new DecelerateInterpolator());
            localAnimationSet.addAnimation(localAlphaAnimation);
            paramView.startAnimation(localAnimationSet);
        }
    }

    public static void preLoadAnimate(View paramView, long paramLong) {
        if (paramView != null) {
            AnimationSet localAnimationSet = new AnimationSet(false);
            ScaleAnimation localScaleAnimation = new ScaleAnimation(1.0F, 0.9F, 1.0F, 0.9F, 1, 0.5F, 1, 0.5F);
            localScaleAnimation.setDuration(paramLong);
            localScaleAnimation.setInterpolator(new DecelerateInterpolator());
            localAnimationSet.addAnimation(localScaleAnimation);
            AlphaAnimation localAlphaAnimation = new AlphaAnimation(1.0F, 0.0F);
            localAlphaAnimation.setDuration(paramLong);
            localAlphaAnimation.setInterpolator(new DecelerateInterpolator());
            localAnimationSet.addAnimation(localAlphaAnimation);
            paramView.startAnimation(localAnimationSet);
        }
    }

    public void display(Bitmap paramBitmap, ImageAware paramImageAware, LoadedFrom paramLoadedFrom) {
        preLoadAnimate(paramImageAware.getWrappedView(), this.durationMillis);
        paramImageAware.setImageBitmap(paramBitmap);
        postLoadAnimate(paramImageAware.getWrappedView(), this.durationMillis);
    }
}
