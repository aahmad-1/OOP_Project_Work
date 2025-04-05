package com.example.oopprojectwork;

import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

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
}
