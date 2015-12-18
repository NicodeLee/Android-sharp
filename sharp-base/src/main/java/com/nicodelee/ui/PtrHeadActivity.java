package com.nicodelee.ui;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import com.nicodelee.base.BaseActivity;
import com.nicodelee.common.colours.Colour;
import com.nicodelee.common.util.DisplayUtil;
import com.nicodelee.ptr.JdHeader;
import com.nicodelee.ptr.MeituanHeader;
import com.nicodelee.ptr.RentalsSunHeader;
import com.nicodelee.ptr.WindmillHeader;
import com.nicodelee.sharp.R;
import in.srain.cube.views.ptr.PtrClassicDefaultHeader;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.header.MaterialHeader;
import in.srain.cube.views.ptr.header.StoreHouseHeader;

/**
 * Created by NocodeLee on 15/12/16.
 * Email：lirizhilirizhi@163.com
 */
public class PtrHeadActivity extends BaseActivity {

  private PtrFrameLayout ptr;
  //private RecyclerView recyclerView;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override protected int getLayoutResId() {
    return R.layout.header_activity_ptr;
  }

  @Override protected void initView() {
    //recyclerView = findViewById(this, R.id.recycler_view);

    ptr = findViewById(this, R.id.ptr_frame);
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

  @Override protected CharSequence getTitleName() {
    return "多种下拉头部";
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    super.onCreateOptionsMenu(menu);
    getMenuInflater().inflate(R.menu.menu_ptr, menu);
    return true;
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();
    if (id == R.id.menu_meituan) {
      final MeituanHeader header = new MeituanHeader(this);
      ptr.setHeaderView(header);
      ptr.addPtrUIHandler(header);
    } else if (id == R.id.menu_jd) {
      final JdHeader header = new JdHeader(this);
      ptr.setHeaderView(header);
      ptr.addPtrUIHandler(header);
    } else if (id == R.id.menu_wind) {
      final WindmillHeader header = new WindmillHeader(this);
      ptr.setHeaderView(header);
      ptr.addPtrUIHandler(header);
    } else if (id == R.id.menu_defult) {
      final PtrClassicDefaultHeader header = new PtrClassicDefaultHeader(this);
      ptr.setHeaderView(header);
      ptr.addPtrUIHandler(header);
    } else if (id == R.id.menu_material) {
      final MaterialHeader header = new MaterialHeader(this);
      ptr.setHeaderView(header);
      ptr.addPtrUIHandler(header);
    } else if (id == R.id.menu_storehouse) {
      final StoreHouseHeader header = new StoreHouseHeader(this);
      /**
       * using a string, support: A-Z 0-9 - .
       * you can add more letters by {@link in.srain.cube.views.ptr.header.StoreHousePath#addChar}
       */
      header.initWithString("Loading...");
      header.setTextColor(Colour.black50PercentColor());
      ptr.setHeaderView(header);
      ptr.addPtrUIHandler(header);

    } else if (id == R.id.menu_rentalssun) {
      final RentalsSunHeader header = new RentalsSunHeader(this);
      //header.setLayoutParams(new PtrFrameLayout.LayoutParams(-1, -2));
      //header.setPadding(0, DisplayUtil.dp2px(15), 0, DisplayUtil.dp2px(10));
      header.setUp(ptr);
      ptr.setHeaderView(header);
      ptr.addPtrUIHandler(header);
    }
    return super.onOptionsItemSelected(item);
  }
}
