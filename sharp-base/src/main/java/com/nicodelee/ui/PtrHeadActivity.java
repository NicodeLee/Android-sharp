package com.nicodelee.ui;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import com.nicodelee.base.BaseActivity;
import com.nicodelee.ptr.JdHeader;
import com.nicodelee.ptr.MeituanHeader;
import com.nicodelee.ptr.WindmillHeader;
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
    //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    //setSupportActionBar(toolbar);
    ptr = findViewById(this,R.id.ptr_frame);
    final MeituanHeader header = new MeituanHeader(this);
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

  @Override
  protected CharSequence getTitleName() {
    return "多种下拉头部";
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    super.onCreateOptionsMenu(menu);
    getMenuInflater().inflate(R.menu.menu_ptr, menu);
    return true;
  }


  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();
    if (id == R.id.menu_meituan) {
      final MeituanHeader header = new MeituanHeader(this);
      ptr.setHeaderView(header);
      ptr.addPtrUIHandler(header);
    } else if (id == R.id.menu_jd) {
      final JdHeader header = new JdHeader(this);
      ptr.setHeaderView(header);
      ptr.addPtrUIHandler(header);
    }else if (id == R.id.menu_wind){
      final WindmillHeader header = new WindmillHeader(this);
      ptr.setHeaderView(header);
      ptr.addPtrUIHandler(header);
    }
    return super.onOptionsItemSelected(item);
  }
}
