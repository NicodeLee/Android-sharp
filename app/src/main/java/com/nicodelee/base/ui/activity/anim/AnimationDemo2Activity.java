package com.nicodelee.base.ui.activity.anim;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import com.nicodelee.base.BaseActivity;
import com.nicodelee.base.R;
import com.nicodelee.util.Logger;

/**
 * Created by NocodeLee on 16/1/27.
 * Email：lirizhilirizhi@163.com
 * http://blog.csdn.net/guolin_blog/article/details/43816093
 */
public class AnimationDemo2Activity extends BaseActivity {

  @Override protected int getLayoutResId() {
    return R.layout.activity_animation_demo2;
  }

  @Override protected CharSequence getTitleName() {
    return "玩玩官方动画API";
  }

  @Override protected void initView() {
    setValueAnimator();
  }

  private void setValueAnimator() {
    Point point1 = new Point(0, 0);
    Point point2 = new Point(100, 300);
    ValueAnimator valueAnimator = ValueAnimator.ofObject(new PointEvaluator(), point1, point2);
    valueAnimator.setDuration(3000);
    valueAnimator.start();
    valueAnimator.addListener(new AnimatorListenerAdapter() {
      @Override public void onAnimationEnd(Animator animation) {
        Logger.e("anim finish ###");
      }
    });

  }

}
