package com.nicodelee.ptr.header.waterdrop;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Scroller;
import com.nicodelee.sharp.R;
import com.nicodelee.util.Logger;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.indicator.PtrIndicator;

/**
 * Created by NocodeLee on 15/12/15.
 * Email：lirizhilirizhi@163.com
 */
public class WaterDropHeader extends LinearLayout implements PtrUIHandler {

  // 下拉刷新视图（头部视图）
  private LinearLayout headView;

  private WaterDropView mWaterDropView;
  private ProgressBar mProgressBar;

  private int stretchHeight;
  private int readyHeight;
  private static final int DISTANCE_BETWEEN_STRETCH_READY = 250;
  // at bottom, trigger
  private final static float OFFSET_RADIO = 1.8f; // support iOS like pull

  public WaterDropHeader(Context context) {
    this(context, null);
  }

  public WaterDropHeader(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public WaterDropHeader(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context);
  }

  /**
   * 初始化
   */
  private void init(Context context) {
    LayoutInflater inflater = LayoutInflater.from(context);

    /**
     * 头部
     */
    headView = (LinearLayout) inflater.inflate(R.layout.header_waterdrop, this, true);
    mWaterDropView = (WaterDropView) headView.findViewById(R.id.waterdroplist_waterdrop);
    mProgressBar = (ProgressBar) headView.findViewById(R.id.waterdroplist_header_progressbar);
    initHeight();
  }

  private void initHeight() {
    headView.getViewTreeObserver()
        .addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
          @Override public void onGlobalLayout() {
            stretchHeight = mWaterDropView.getHeight();
            readyHeight = stretchHeight + DISTANCE_BETWEEN_STRETCH_READY;
            getViewTreeObserver().removeGlobalOnLayoutListener(this);
          }
        });
  }

  /**
   * 处理处于normal状态的值
   */
  private void handleStateNormal() {
    mWaterDropView.setVisibility(View.VISIBLE);
    mProgressBar.setVisibility(View.GONE);
    headView.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);
  }

  /**
   * 处理水滴拉伸状态
   */
  private void handleStateStretch() {
    mWaterDropView.setVisibility(View.VISIBLE);
    mProgressBar.setVisibility(View.GONE);
    headView.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL);
  }

  /**
   * 处理水滴ready状态，回弹效果
   */
  private void handleStateReady() {
    mWaterDropView.setVisibility(View.VISIBLE);
    mProgressBar.setVisibility(View.GONE);
    Animator shrinkAnimator = mWaterDropView.createAnimator();
    shrinkAnimator.addListener(new AnimatorListenerAdapter() {
      @Override public void onAnimationEnd(Animator animation) {
        //回弹结束后即进入refreshing状态
        handleStateRefreshing();
      }
    });
    shrinkAnimator.start();//开始回弹
  }

  /**
   * 处理正在进行刷新状态
   */
  private void handleStateRefreshing() {
    mWaterDropView.setVisibility(View.GONE);
    mProgressBar.setVisibility(View.VISIBLE);
  }

  /**
   * 处理刷新完毕状态
   */
  private void handleStateEnd() {
    mWaterDropView.setVisibility(View.GONE);
    mProgressBar.setVisibility(View.GONE);
  }

  private void updateHeaderHeight(float delta) {
    int newHeight = (int) delta + headView.getHeight();
    setVisiableHeight(newHeight);
  }

  public void setVisiableHeight(int height) {
    if (height < 0) height = 0;
    ViewGroup.LayoutParams lp = headView.getLayoutParams();
    lp.height = height;
    headView.setLayoutParams(lp);

    //通知水滴进行更新
    float pullOffset =
        (float) Utils.mapValueFromRangeToRange(height, stretchHeight, readyHeight, 0, 1);
    Logger.e(String.format("height:%s,pullOffset:%f", height,pullOffset));
    if (pullOffset > 0 && pullOffset < 1) {
      mWaterDropView.updateComleteState(pullOffset);
      invalidate();
    }
  }

  //=================================处理下拉逻辑==================================

  //重置 View，隐藏忙碌进度条，隐藏箭头 View
  @Override public void onUIReset(PtrFrameLayout frame) {
    ViewGroup.LayoutParams lp = headView.getLayoutParams();
    lp.height = dp2px(getContext(),50);
    headView.setLayoutParams(lp);
    handleStateNormal();
  }

  //准备刷新，隐藏忙碌进度条，显示箭头 View，显示文字，如果是下拉刷新，显示“下拉刷新”，如果是释放刷新，显示“下拉”
  @Override public void onUIRefreshPrepare(PtrFrameLayout frame) {
  }

  //开始刷新，隐藏箭头 View，显示忙碌进度条，显示文字，显示“加载中...”
  @Override public void onUIRefreshBegin(PtrFrameLayout frame) {
    handleStateRefreshing();
  }

  //刷新结束，隐藏箭头 View，隐藏忙碌进度条，显示文字，显示“更新完成”
  @Override public void onUIRefreshComplete(PtrFrameLayout frame) {
    handleStateEnd();
  }

  //下拉过程中位置变化回调。
  @Override public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status,
      PtrIndicator ptrIndicator) {

    final int mOffsetToRefresh = frame.getOffsetToRefresh();
    final int currentPos = ptrIndicator.getCurrentPosY();
    final int lastPos = ptrIndicator.getLastPosY();
    final float offsetY = ptrIndicator.getOffsetY();

    if (isUnderTouch && status == PtrFrameLayout.PTR_STATUS_PREPARE) {
      if (getDropStaue(currentPos)) {
        updateHeaderHeight(offsetY / OFFSET_RADIO);
        //invalidate();
      }
    }

    if (currentPos < mOffsetToRefresh && lastPos >= mOffsetToRefresh) {
      if (isUnderTouch && status == PtrFrameLayout.PTR_STATUS_PREPARE) {
        //handleStateStretch();
      }
    } else if (currentPos > mOffsetToRefresh && lastPos <= mOffsetToRefresh) {
      if (isUnderTouch && status == PtrFrameLayout.PTR_STATUS_PREPARE) {
        //handleStateReady();
      }
    }
  }

  //=================================处理下拉逻辑==================================

  private boolean getDropStaue(int height) {
    return height > dp2px(getContext(), 50);
  }

  private int dp2px(Context context, int dpValue) {
    return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue,
        context.getResources().getDisplayMetrics());
  }
}
