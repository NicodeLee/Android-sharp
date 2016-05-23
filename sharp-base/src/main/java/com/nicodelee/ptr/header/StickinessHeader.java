package com.nicodelee.ptr.header;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.nicodelee.sharp.R;
import com.nicodelee.util.Logger;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.indicator.PtrIndicator;

/**
 * Created by NocodeLee on 15/12/16.
 * Email：lirizhilirizhi@163.com
 */
public class StickinessHeader extends LinearLayout implements PtrUIHandler {

  private ViewGroup headView;
  private StickinessView stickinessView;

  public StickinessHeader(Context context) {
    this(context, null);
  }

  public StickinessHeader(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public StickinessHeader(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context);
  }

  private void init(Context context){
    LayoutInflater inflater = LayoutInflater.from(context);
    headView = (ViewGroup) inflater.inflate(R.layout.header_stickiness, this, true);
    stickinessView = (StickinessView)headView.findViewById(R.id.stickinessRefreshView);

    stickinessView.setRotateImage(R.mipmap.refresh_stickiness);
    stickinessView.setStickinessColor(R.color.burntOrangeColor);
  }

  @Override public void onUIReset(PtrFrameLayout frame) {
    stickinessView.setRotateImage(R.mipmap.refresh_stickiness);
    stickinessView.setStickinessColor(R.color.burntOrangeColor);
    reSetRefersh();
  }

  @Override public void onUIRefreshPrepare(PtrFrameLayout frame) {
    stickinessView.smoothToIdle();
  }

  @Override public void onUIRefreshBegin(PtrFrameLayout frame) {
    stickinessView.startRefreshing(headView);
  }

  @Override public void onUIRefreshComplete(PtrFrameLayout frame) {
    reSetRefersh();
  }

  private void reSetRefersh(){
    stickinessView.smoothToIdle();
    stickinessView.stopRefresh();
  }

  @Override public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status,
      PtrIndicator ptrIndicator) {
    final int mOffsetToRefresh = frame.getOffsetToRefresh();
    final int currentPos = ptrIndicator.getCurrentPosY();
    final int lastPos = ptrIndicator.getLastPosY();


    if (isUnderTouch && status == PtrFrameLayout.PTR_STATUS_PREPARE) {
      stickinessView.setMoveYDistance(currentPos);
      if (stickinessView.canChangeToRefreshing()){
        frame.setPullToRefresh(true);
        frame.autoRefresh(true);
      }
      invalidate();
    }

    if (currentPos < mOffsetToRefresh && lastPos >= mOffsetToRefresh) {
      if (isUnderTouch && status == PtrFrameLayout.PTR_STATUS_PREPARE) {
        //tvHeadTitle.setText("下拉刷新");
      }
    } else if (currentPos > mOffsetToRefresh && lastPos <= mOffsetToRefresh) {
      if (isUnderTouch && status == PtrFrameLayout.PTR_STATUS_PREPARE) {
        //tvHeadTitle.setText("松开刷新");
        //frame.setPullToRefresh(true);
        //frame.autoRefresh(true);
      }
    }

    final float scale = ptrIndicator.getCurrentPercent();
    if (currentPos == 0) {
      stickinessView.smoothToIdle();
    }

  }
}
