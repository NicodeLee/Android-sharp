package com.nicodelee.ui;

import android.os.Bundle;
import com.nicodelee.base.BaseActivity;
import com.nicodelee.ptr.JdHeader;
import com.nicodelee.ptr.MeituanHeader;
import com.nicodelee.sharp.R;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by NocodeLee on 15/12/16.
 * Email：lirizhilirizhi@163.com
 */
public class PtrHeadActivity extends BaseActivity {

  private PtrFrameLayout ptr;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override protected int getLayoutResId() {
    return R.layout.header_activity_ptr;
  }

  @Override protected void initView() {
    ptr = findViewById(this,R.id.ptr_frame);
    final MeituanHeader header = new MeituanHeader(this);
    //final JdHeader header = new JdHeader(this);
    //final WindmillHeader header = new WindmillHeader(this);
    ptr.setHeaderView(header);
    ptr.addPtrUIHandler(header);

    ptr.setPtrHandler(new PtrDefaultHandler() {
      @Override public void onRefreshBegin(PtrFrameLayout frame) {
        frame.postDelayed(new Runnable() {
          @Override public void run() {
            ptr.refreshComplete();
          }
        }, 2000);
      }
    });
  }
}
