package com.nicodelee.ptr.header;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.nicodelee.sharp.R;
import in.srain.cube.views.ptr.PtrClassicDefaultHeader;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.indicator.PtrIndicator;

/**
 * Created by NocodeLee on 15/12/15.
 * Email：lirizhilirizhi@163.com
 */
public class MeituanHeader extends FrameLayout implements PtrUIHandler {

  private LayoutInflater inflater;
  private ViewGroup headView;

  private ImageView mPullDownView;
  private ImageView mReleaseRefreshingView;

  private AnimationDrawable mChangeToReleaseRefreshAd;
  private AnimationDrawable mRefreshingAd;

  private int mChangeToReleaseRefreshAnimResId;
  private int mRefreshingAnimResId;

  public MeituanHeader(Context context) {
    this(context, null);
  }

  public MeituanHeader(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public MeituanHeader(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context);
  }

  private void init(Context context) {
    inflater = LayoutInflater.from(context);
    headView = (ViewGroup) inflater.inflate(R.layout.header_meituan, this, true);
    mPullDownView = (ImageView) headView.findViewById(R.id.iv_meituan_pull_down);
    mReleaseRefreshingView = (ImageView) headView.findViewById(R.id.iv_meituan_release_refreshing);
  }

  //重置 View，隐藏忙碌进度条，隐藏箭头 View
  @Override public void onUIReset(PtrFrameLayout frame) {
    onEndRefreshing();
  }

  //准备刷新，隐藏忙碌进度条，显示箭头 View，显示文字，如果是下拉刷新，显示“下拉刷新”，如果是释放刷新，显示“下拉”
  @Override public void onUIRefreshPrepare(PtrFrameLayout frame) {
    mChangeToReleaseRefreshAnimResId = R.mipmap.bga_refresh_mt_pull_down;
    changeToPullDown();
  }

  //开始刷新，隐藏箭头 View，显示忙碌进度条，显示文字，显示“加载中...”
  @Override public void onUIRefreshBegin(PtrFrameLayout frame) {
    mRefreshingAnimResId = R.anim.bga_refresh_mt_refreshing;
    changeToRefreshing();
  }

  //刷新结束，隐藏箭头 View，隐藏忙碌进度条，显示文字，显示“更新完成”
  @Override public void onUIRefreshComplete(PtrFrameLayout frame) {
    mChangeToReleaseRefreshAnimResId = R.mipmap.bga_refresh_mt_pull_down;
    changeToPullDown();
    onEndRefreshing();
  }

  /**
   *下拉过程中位置变化回调。
   * @see PtrClassicDefaultHeader
   * 参考默认下拉头部逻辑
   */
  @Override public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status,
      PtrIndicator ptrIndicator) {
    final int mOffsetToRefresh = frame.getOffsetToRefresh();
    final int currentPos = ptrIndicator.getCurrentPosY();
    final int lastPos = ptrIndicator.getLastPosY();

    final float scale = ptrIndicator.getCurrentPercent();
    if (scale <= 1.0f) handleScale(scale);

    if (currentPos < mOffsetToRefresh && lastPos >= mOffsetToRefresh) {
      if (isUnderTouch && status == PtrFrameLayout.PTR_STATUS_PREPARE) {
        mChangeToReleaseRefreshAnimResId = R.mipmap.bga_refresh_mt_pull_down;
        changeToPullDown();
      }
    } else if (currentPos > mOffsetToRefresh && lastPos <= mOffsetToRefresh) {
      if (isUnderTouch && status == PtrFrameLayout.PTR_STATUS_PREPARE) {
        mChangeToReleaseRefreshAnimResId = R.anim.bga_refresh_mt_change_to_release_refresh;
          changeToReleaseRefresh();
      }
    }
  }

  public void handleScale(float scale) {
    scale = 0.1f + 0.9f * scale;
    ViewCompat.setPivotX(mPullDownView,mPullDownView.getWidth()/2);
    ViewCompat.setScaleX(mPullDownView, scale);
    ViewCompat.setPivotY(mPullDownView, mPullDownView.getHeight());
    ViewCompat.setScaleY(mPullDownView, scale);
  }

  public void changeToPullDown() {
    mPullDownView.setVisibility(VISIBLE);
    mPullDownView.setImageResource(mChangeToReleaseRefreshAnimResId);
    mReleaseRefreshingView.setVisibility(INVISIBLE);
  }

  public void changeToReleaseRefresh() {
    mReleaseRefreshingView.setImageResource(mChangeToReleaseRefreshAnimResId);
    mChangeToReleaseRefreshAd = (AnimationDrawable) mReleaseRefreshingView.getDrawable();

    mReleaseRefreshingView.setVisibility(VISIBLE);
    mPullDownView.setVisibility(INVISIBLE);

    mChangeToReleaseRefreshAd.start();
  }

  public void changeToRefreshing() {
    stopChangeToReleaseRefreshAd();

    mReleaseRefreshingView.setImageResource(mRefreshingAnimResId);
    mRefreshingAd = (AnimationDrawable) mReleaseRefreshingView.getDrawable();

    mReleaseRefreshingView.setVisibility(VISIBLE);
    mPullDownView.setVisibility(INVISIBLE);

    mRefreshingAd.start();
  }

  public void onEndRefreshing() {
    stopChangeToReleaseRefreshAd();
    stopRefreshingAd();
  }

  private void stopRefreshingAd() {
    if (mRefreshingAd != null) {
      mRefreshingAd.stop();
      mRefreshingAd = null;
    }
  }

  private void stopChangeToReleaseRefreshAd() {
    if (mChangeToReleaseRefreshAd != null) {
      mChangeToReleaseRefreshAd.stop();
      mChangeToReleaseRefreshAd = null;
    }
  }
}
