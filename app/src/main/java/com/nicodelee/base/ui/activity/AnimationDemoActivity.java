package com.nicodelee.base.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.OnClick;
import com.nicodelee.base.BaseActivity;
import com.nicodelee.base.R;
import com.nicodelee.util.Logger;

/**
 * Created by NocodeLee on 16/1/27.
 * Email：lirizhilirizhi@163.com
 * http://blog.csdn.net/guolin_blog/article/details/43536355
 */
public class AnimationDemoActivity extends BaseActivity {

  @Bind(R.id.tv_anim) TextView tvAnim;

  ObjectAnimator objectAnimator;

  @Override protected int getLayoutResId() {
    return R.layout.activity_animation_demo2;
  }

  @Override protected CharSequence getTitleName() {
    return "玩玩官方动画API";
  }

  @Override protected void initView() {
    //setValueAnimator();
  }

  @OnClick({
      R.id.start_anim, R.id.start_anim2, R.id.start_anim3, R.id.start_anim4, R.id.start_anim5,
      R.id.start_anim6, R.id.start_anim7
  }) void onClick(View view) {
    switch (view.getId()) {
      case R.id.start_anim:
        objectAnimator = ObjectAnimator.ofFloat(tvAnim, "alpha", 1f, 0f, 1f);
        setObjectAnimtor();
        break;
      case R.id.start_anim2:
        objectAnimator = ObjectAnimator.ofFloat(tvAnim, "rotation", 0f, 360f);
        setObjectAnimtor();
        break;
      case R.id.start_anim3:
        float curTranslationX = tvAnim.getTranslationX();
        objectAnimator = ObjectAnimator.ofFloat(tvAnim, "translationX", curTranslationX, -1000f,
            curTranslationX);
        setObjectAnimtor();
        break;
      case R.id.start_anim4:
        objectAnimator = ObjectAnimator.ofFloat(tvAnim, "scaleY", 1f, 3f, 1f);
        setObjectAnimtor();
        break;
      case R.id.start_anim7:
        setScaleXY();
        break;
      case R.id.start_anim5:
        setAnimatorSe();
        break;
      case R.id.start_anim6:
        Animator animator = AnimatorInflater.loadAnimator(this,R.animator.anin_demo);
        animator.setTarget(tvAnim);
        animator.start();
        break;
    }
  }

  private void setValueAnimator() {
    ValueAnimator anim = ValueAnimator.ofFloat(0f, 1f);//ofInt 整数过渡
    anim.setDuration(300);
    anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
      @Override public void onAnimationUpdate(ValueAnimator animation) {
        float currentValue = (float) animation.getAnimatedValue();
        Logger.e("current value = " + currentValue);
      }
    });
    anim.start();
  }

  private void setObjectAnimtor() {
    objectAnimator.setDuration(3000);
    objectAnimator.start();
    objectAnimator.addListener(new AnimatorListenerAdapter() {
      @Override public void onAnimationEnd(Animator animation) {
        //super.onAnimationEnd(animation);
        Logger.e("Anim finish!");
      }
    });
  }

  private void setAnimatorSe() {
    ObjectAnimator moveIn = ObjectAnimator.ofFloat(tvAnim, "translationX", -1000f, 0f);
    ObjectAnimator rotate = ObjectAnimator.ofFloat(tvAnim, "rotation", 0f, 360f);
    ObjectAnimator fadeInOut = ObjectAnimator.ofFloat(tvAnim, "alpha", 1f, 0f, 1f);
    AnimatorSet animSet = new AnimatorSet();
    animSet.play(rotate).with(fadeInOut).after(moveIn);
    animSet.setDuration(3000);
    animSet.start();
  }

  private void setScaleXY(){
    ObjectAnimator scaleX = ObjectAnimator.ofFloat(tvAnim,"scaleX",1f,2f,1f);
    ObjectAnimator scaleY = ObjectAnimator.ofFloat(tvAnim,"scaleY",1f,2f,1);
    AnimatorSet animSet = new AnimatorSet();
    animSet.play(scaleX).with(scaleY);
    animSet.setDuration(3000);
    animSet.start();
  }
}
