package com.nicodelee.base.ui.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.view.View;
import android.widget.FrameLayout;
import butterknife.Bind;
import com.nicodelee.base.BaseActivity;
import com.nicodelee.base.R;
import com.nicodelee.util.Logger;

/**
 * Created by NocodeLee on 16/1/21.
 * Email：lirizhilirizhi@163.com
 *
 * see also: https://github.com/tumblr/Backboard
 */
public class DemoActivity extends BaseActivity {

  @Bind(R.id.bottom_sheet) FrameLayout bottomSheet;

  @Override protected int getLayoutResId() {
    return R.layout.activity_demo;
  }

  @Override protected CharSequence getTitleName() {
    return "Android Support Library";
  }

  @Override protected void initView() {
    BottomSheetBehavior behavior = BottomSheetBehavior.from(bottomSheet);
    behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
      @Override public void onStateChanged(@NonNull View bottomSheet, int newState) {
        // React to state change
        Logger.e("React to state change");
      }

      @Override public void onSlide(@NonNull View bottomSheet, float slideOffset) {
        // React to dragging events
        Logger.e("React to dragging events");
      }
    });
  }

}
