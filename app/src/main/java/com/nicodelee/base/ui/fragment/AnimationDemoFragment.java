package com.nicodelee.base.ui.fragment;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.github.florent37.hollyviewpager.HollyViewPagerBus;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;
import com.nicodelee.base.BaseActivity;
import com.nicodelee.base.BaseFragment;
import com.nicodelee.base.R;
import com.nicodelee.common.util.DevicesUtil;

public class AnimationDemoFragment extends BaseFragment {

  @Bind(R.id.scrollView) ObservableScrollView scrollView;
  @Bind(R.id.title) TextView title;
  @Bind(R.id.card_view) CardView mRootView;
  @Bind(R.id.tv_anim) TextView tvAnim;

  ObjectAnimator objectAnimator;

  public static AnimationDemoFragment newInstance(String title) {
    Bundle args = new Bundle();
    args.putString("title", title);
    AnimationDemoFragment fragment = new AnimationDemoFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Nullable @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_animation_demo, container, false);
  }

  @Override public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    ButterKnife.bind(this, view);
    title.setText(getArguments().getString("title"));
    HollyViewPagerBus.registerScrollView(getActivity(), scrollView);

    mRootView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
        (DevicesUtil.screenHeight) - DevicesUtil.dip2px(mActivity, 48 + 150 + 24)));
  }

  @OnClick({
      R.id.start_anim, R.id.start_anim2, R.id.start_anim3, R.id.start_anim4, R.id.start_anim5,
      R.id.start_anim6
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
      case R.id.start_anim5:
        setAnimatorSe();
        break;
      case R.id.start_anim6:
        Animator animator = AnimatorInflater.loadAnimator(mActivity,R.animator.anin_demo);
        animator.setTarget(tvAnim);
        animator.start();
        break;
    }
  }

  private void setObjectAnimtor() {
    objectAnimator.setDuration(3000);
    objectAnimator.start();
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
}
