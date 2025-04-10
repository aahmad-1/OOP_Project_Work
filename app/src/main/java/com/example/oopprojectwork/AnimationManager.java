package com.example.oopprojectwork;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Handler;
import android.view.View;

import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import android.view.animation.AnimationUtils;
import android.view.animation.OvershootInterpolator;
import android.widget.ProgressBar;
import android.widget.TextView;

public class AnimationManager {

    public static void playSwordClashAnimation(final Context context, final View leftSword, final View rightSword) {
        // Load animations
        final Animation leftAnim = AnimationUtils.loadAnimation(context, R.anim.left_sword_anim);
        final Animation rightAnim = AnimationUtils.loadAnimation(context, R.anim.right_sword_anim);

        // Ensure swords are visible
        leftSword.setVisibility(View.VISIBLE);
        rightSword.setVisibility(View.VISIBLE);

        // Start the first clash animation
        leftSword.startAnimation(leftAnim);
        rightSword.startAnimation(rightAnim);

        // Use a handler to delay the second clash after the first one completes
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Start the second clash animation
                leftSword.startAnimation(leftAnim);
                rightSword.startAnimation(rightAnim);
            }
        }, leftAnim.getDuration()); // Use the duration of the first animation for the delay
    }

    public static void startTrainingAnimation(final ProgressBar progressBar, final View flashOverlay, final TextView completionText) {
        if (progressBar.getVisibility() == View.VISIBLE) return;

        progressBar.setProgress(0);
        progressBar.setVisibility(View.VISIBLE);
        flashOverlay.setVisibility(View.VISIBLE);
        completionText.setVisibility(View.GONE);

        // Flash animation during loading
        AlphaAnimation flash = new AlphaAnimation(0.3f, 0.8f);
        flash.setDuration(4000);
        flash.setRepeatMode(AlphaAnimation.REVERSE);
        flash.setRepeatCount(AlphaAnimation.INFINITE);
        flashOverlay.startAnimation(flash);

        // Animate progress bar
        ObjectAnimator animator = ObjectAnimator.ofInt(progressBar, "progress", 0, 100);
        animator.setDuration(4000);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                flashOverlay.clearAnimation();
                progressBar.setVisibility(View.GONE);
                flashOverlay.setVisibility(View.GONE);

                // 🎉 Celebration animation for the text
                completionText.setScaleX(0f);
                completionText.setScaleY(0f);
                completionText.setAlpha(0f);
                completionText.setVisibility(View.VISIBLE);

                completionText.animate()
                        .scaleX(1f)
                        .scaleY(1f)
                        .alpha(1f)
                        .setDuration(600)
                        .setInterpolator(new OvershootInterpolator())
                        .start();

                // Optional: Hide the text after 3 seconds
                new Handler().postDelayed(() -> completionText.setVisibility(View.GONE), 3000);
            }
        });
        animator.start();
    }







}
