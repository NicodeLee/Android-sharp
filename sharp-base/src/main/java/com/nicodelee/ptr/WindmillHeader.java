package com.nicodelee.ptr;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.nicodelee.sharp.R;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.indicator.PtrIndicator;

/**
 * Created by NocodeLee on 15/12/15.
 * Email：lirizhilirizhi@163.com
 *
 * see http://a.codekk.com/detail/Android/Grumoon/android-Ultra-Pull-To-Refresh%20%E6%BA%90%E7%A0%81%E8%A7%A3%E6%9E%90
 * 将此代码抄写一遍了解自定义头部流程
 *
 * 小风车头部的下拉
 *
 */
public class WindmillHeader extends FrameLayout implements PtrUIHandler{

  private LayoutInflater inflater;

  // 下拉刷新视图（头部视图）
  private ViewGroup headView;

  // 下拉刷新文字
  private TextView tvHeadTitle;

  // 下拉图标
  private ImageView ivWindmill;

  private WindmillDrawable drawable;

  public WindmillHeader(Context context) {
    this(context, null);
  }

  public WindmillHeader(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public WindmillHeader(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context);
  }

  /**
   * 初始化
   * @param context
   */
  private void init(Context context){
    inflater = LayoutInflater.from(context);

    /**
     * 头部
     */
    headView = (ViewGroup)inflater.inflate(R.layout.header_windmill,this,true);
    ivWindmill = (ImageView) headView.findViewById(R.id.iv_windmill);
    tvHeadTitle = (TextView) headView.findViewById(R.id.tv_head_title);

    drawable = new WindmillDrawable(context,ivWindmill);
    ivWindmill.setImageDrawable(drawable);

  }

  //=================================处理下拉逻辑==================================

  //重置 View，隐藏忙碌进度条，隐藏箭头 View
  @Override public void onUIReset(PtrFrameLayout frame) {
    tvHeadTitle.setText("下拉刷新");
    drawable.stop();

  }

  //准备刷新，隐藏忙碌进度条，显示箭头 View，显示文字，如果是下拉刷新，显示“下拉刷新”，如果是释放刷新，显示“下拉”
  @Override public void onUIRefreshPrepare(PtrFrameLayout frame) {
    tvHeadTitle.setText("下拉刷新");
  }

  //开始刷新，隐藏箭头 View，显示忙碌进度条，显示文字，显示“加载中...”
  @Override public void onUIRefreshBegin(PtrFrameLayout frame) {
    tvHeadTitle.setText("正在刷新...");
    drawable.start();
  }

  //刷新结束，隐藏箭头 View，隐藏忙碌进度条，显示文字，显示“更新完成”
  @Override public void onUIRefreshComplete(PtrFrameLayout frame) {
    ivWindmill.clearAnimation();
    tvHeadTitle.setText("刷新完成");
  }

  //下拉过程中位置变化回调。
  @Override public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status,
      PtrIndicator ptrIndicator) {

    final int mOffsetToRefresh = frame.getOffsetToRefresh();
    final int currentPos = ptrIndicator.getCurrentPosY();
    final int lastPos = ptrIndicator.getLastPosY();

    if (isUnderTouch && status == PtrFrameLayout.PTR_STATUS_PREPARE) {
      drawable.postRotation(currentPos - lastPos);
      invalidate();
    }

    if (currentPos < mOffsetToRefresh && lastPos >= mOffsetToRefresh) {
      if (isUnderTouch && status == PtrFrameLayout.PTR_STATUS_PREPARE) {
        tvHeadTitle.setText("下拉刷新");

      }
    } else if (currentPos > mOffsetToRefresh && lastPos <= mOffsetToRefresh) {
      if (isUnderTouch && status == PtrFrameLayout.PTR_STATUS_PREPARE) {
        tvHeadTitle.setText("松开刷新");
      }
    }
  }
}
