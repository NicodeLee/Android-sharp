package com.nicodelee.app.fast.ui.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import butterknife.Bind;
import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringSystem;
import com.nicodelee.app.fast.R;
import com.nicodelee.base.BaseActivity;
import com.tumblr.backboard.Actor;
import com.tumblr.backboard.MotionProperty;
import com.tumblr.backboard.performer.Performer;

/**
 * Created by NocodeLee on 16/1/21.
 * Email：lirizhilirizhi@163.com
 *
 * see also: https://github.com/tumblr/Backboard
 *
 */
public class AnimationActivity extends BaseActivity {

  private View[] mCircles;
  @Bind(R.id.rl_root) RelativeLayout mRootView;

  @Override protected int getLayoutResId() {
    return R.layout.fragment_appear;
  }

  @Override protected CharSequence getTitleName() {
    return "动画";
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    mCircles = new View[3];
    mCircles[0] = findViewById(R.id.circle0);
    mCircles[1] = findViewById(R.id.circle1);
    mCircles[2] = findViewById(R.id.circle2);

    final SpringSystem springSystem = SpringSystem.create();
    final Spring[] springs = new Spring[3];
    final Actor[] actors = new Actor[3];

    final Spring mSpring =
        springSystem.createSpring().addListener(new Performer(toolbarView, View.TRANSLATION_Y));
    new Actor.Builder(springSystem, toolbarView).addTranslateMotion(MotionProperty.X)
        .addTranslateMotion(MotionProperty.Y)
        .build();

    // attach listeners
    for (int i = 0; i < mCircles.length; i++) {
      springs[i] = springSystem.createSpring();
      springs[i].addListener(new Performer(mCircles[i], View.TRANSLATION_Y));
      mCircles[i].setTag(springs[i]);
      actors[i] = new Actor.Builder(springSystem, mCircles[i]).addTranslateMotion(MotionProperty.X)
          .addTranslateMotion(MotionProperty.Y)
          .build();
    }

    mRootView.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {

        int[] rootLocation = new int[2];
        v.getLocationInWindow(rootLocation);
        int[] location = new int[2];
        for (int i = 0; i < mCircles.length; i++) {

          if (springs[i].getEndValue() == 0) { // hide
            mCircles[i].getLocationInWindow(location);
            double endValue = mRootView.getMeasuredHeight() - location[1] +
                rootLocation[1] +
                2 * Math.random() * mCircles[i].getMeasuredHeight();

            springs[i].setEndValue(endValue);

            mSpring.setEndValue(-toolbarView.getHeight());
          } else {
            springs[i].setEndValue(0); // appear
            mSpring.setEndValue(0);
          }
        }
      }
    });
  }
}
