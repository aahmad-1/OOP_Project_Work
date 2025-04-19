package com.example.oopprojectwork;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.media.MediaPlayer;
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

        // Prepare MediaPlayer for the clash sound
        final MediaPlayer clashSound = MediaPlayer.create(context, R.raw.sword_clash);

        // Set listener to repeat the sound once
        clashSound.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            private int playCount = 0;

            @Override
            public void onCompletion(MediaPlayer mp) {
                if (playCount < 1) { // Repeat once
                    playCount++;
                    clashSound.start();
                } else {
                    clashSound.release(); // Release resources after the second play
                }
            }
        });

        // Start the first clash animation
        leftSword.startAnimation(leftAnim);
        rightSword.startAnimation(rightAnim);

        // Play the clash sound
        clashSound.start();

        // Add a slight delay for the second clash
        new Handler().postDelayed(() -> {
            // Start the second clash animation
            leftSword.startAnimation(leftAnim);
            rightSword.startAnimation(rightAnim);

            // Play the clash sound again
            clashSound.start();
        }, leftAnim.getDuration() + 200); // Add 200ms delay after the first animation
    }

    public static void startTrainingAnimation(final ProgressBar progressBar, final View flashOverlay,
                                              final TextView completionText, Runnable onComplete) {
        if (progressBar.getVisibility() == View.VISIBLE) return;

        progressBar.setProgress(0);
        progressBar.setVisibility(View.VISIBLE);
        flashOverlay.setVisibility(View.VISIBLE);
        completionText.setVisibility(View.GONE);

        // Find ability text views that might need to be hidden later
        View rootView = progressBar.getRootView();
        final TextView dodgeAbilityText = rootView.findViewById(R.id.DodgeAbility);
        final TextView specialAttackAbilityText = rootView.findViewById(R.id.SpecialAttackAbility);

        // Flash animation during loading
        AlphaAnimation flash = new AlphaAnimation(0.3f, 0.8f);
        flash.setDuration(4000);
        flash.setRepeatMode(AlphaAnimation.REVERSE);
        flash.setRepeatCount(Animation.INFINITE);
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

                // Celebration animation for the text
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

                // Execute the completion callback
                if (onComplete != null) {
                    onComplete.run();
                }

                // Hide the text and any ability texts after 3 seconds
                new Handler().postDelayed(() -> {
                    completionText.setVisibility(View.GONE);

                    // Also hide any ability texts that might have been shown
                    if (dodgeAbilityText != null) {
                        dodgeAbilityText.setVisibility(View.GONE);
                    }
                    if (specialAttackAbilityText != null) {
                        specialAttackAbilityText.setVisibility(View.GONE);
                    }
                }, 3000);
            }
        });
        animator.start();
    }
}