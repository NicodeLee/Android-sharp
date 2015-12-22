package com.nicodelee.ptr.header;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.nicodelee.sharp.R;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.indicator.PtrIndicator;

/**
 * Created by NocodeLee on 15/12/16.
 * Email：lirizhilirizhi@163.com
 */
public class JdHeader extends RelativeLayout implements PtrUIHandler {

  private LayoutInflater inflater;
  private ViewGroup headView;
  private ImageView ivSpeed;
  private ImageView ivRefresh;
  private AnimationDrawable mAnimDrawable;
  private Animation mTwinkleAnim;

  public JdHeader(Context context) {
    this(context, null);
  }

  public JdHeader(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public JdHeader(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context);
  }

  private void init(Context context){
    inflater = LayoutInflater.from(context);
    headView = (ViewGroup)inflater.inflate(R.layout.header_jd,this,true);
    ivRefresh = (ImageView) headView.findViewById(R.id.ivRefresh);
    ivSpeed = (ImageView) headView.findViewById(R.id.ivSpeed);
    mAnimDrawable = (AnimationDrawable) ivRefresh.getBackground();
    mTwinkleAnim = AnimationUtils.loadAnimation(getContext(), R.anim.twinkle);
  }

  @Override public void onUIReset(PtrFrameLayout frame) {
    mAnimDrawable.stop();
    ivSpeed.clearAnimation();
    ivSpeed.setVisibility(GONE);
  }

  @Override public void onUIRefreshPrepare(PtrFrameLayout frame) {
    ivSpeed.clearAnimation();
    ivSpeed.setVisibility(GONE);
  }

  @Override public void onUIRefreshBegin(PtrFrameLayout frame) {
    ivSpeed.setVisibility(VISIBLE);
    ivSpeed.startAnimation(mTwinkleAnim);
    if (!mAnimDrawable.isRunning()){
      mAnimDrawable.start();
    }
  }

  @Override public void onUIRefreshComplete(PtrFrameLayout frame) {
    ivSpeed.clearAnimation();
    if (mAnimDrawable.isRunning()){
      mAnimDrawable.stop();
    }
  }

  @Override public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status,
      PtrIndicator ptrIndicator) {
  }
}
