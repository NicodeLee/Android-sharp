package com.nicodelee.app.fast.ui.view.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import butterknife.Bind;
import com.nicodelee.app.fast.model.DataGenerator;
import com.nicodelee.app.fast.model.ItemListMod;
import com.nicodelee.app.fast.ui.view.DemoListView;
import com.nicodelee.base.BaseActivity;
import com.nicodelee.common.colours.Colour;
import com.nicodelee.ptr.header.JdHeader;
import com.nicodelee.ptr.header.MeituanHeader;
import com.nicodelee.ptr.header.RentalsSunHeader;
import com.nicodelee.ptr.header.WindmillHeader;
import com.nicodelee.ptr.loadmore.LoadMoreContainer;
import com.nicodelee.ptr.loadmore.LoadMoreHandler;
import com.nicodelee.ptr.loadmore.LoadMoreRecyclerViewContainer;
import com.nicodelee.sharp.R;
import in.srain.cube.views.ptr.PtrClassicDefaultHeader;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.header.MaterialHeader;
import in.srain.cube.views.ptr.header.StoreHouseHeader;
import in.srain.cube.views.ptr.header.StoreHousePath;
import io.nlopez.smartadapters.SmartAdapter;
import io.nlopez.smartadapters.adapters.RecyclerMultiAdapter;
import java.util.List;

/**
 * Created by NocodeLee on 15/12/16.
 * Email：lirizhilirizhi@163.com
 */
public class PtrHeadActivity extends BaseActivity {

  @Bind(R.id.ptr_frame) PtrClassicFrameLayout ptr;
  @Bind(R.id.recycler_view) RecyclerView recyclerView;
  @Bind(R.id.view_container) LoadMoreRecyclerViewContainer viewContainer;
  //@Bind(R.id.list_view) ListView listView;
  //@Bind(R.id.list_view_container) LoadMoreListViewContainer viewContainer;

  private RecyclerMultiAdapter adapter;
  //private MultiAdapter adapter;
  private int pageNum = 10;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override protected int getLayoutResId() {
    return R.layout.header_activity_ptr;
  }

  @Override protected void initView() {
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    List<ItemListMod> itemMods = DataGenerator.generateItemList(pageNum);
    adapter =
        SmartAdapter.items(itemMods).map(ItemListMod.class, DemoListView.class).into(recyclerView);
    //showToast(String.format("adapter=%b", adapter == null));
    //viewContainer.setAdapter(adapter);

    final MeituanHeader header = new MeituanHeader(this);
    ptr.setHeaderView(header);
    ptr.addPtrUIHandler(header);

    ptr.setPtrHandler(new PtrHandler() {
      @Override public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
        return PtrDefaultHandler.checkContentCanBePulledDown(frame, recyclerView, header);
      }

      @Override public void onRefreshBegin(PtrFrameLayout frame) {
        frame.postDelayed(new Runnable() {
          @Override public void run() {
            setLoadMore();
            adapter.setItems(DataGenerator.generateItemList(pageNum));
            ptr.refreshComplete();
          }
        }, 1500);
      }
    });

    viewContainer.setAdapter(adapter);//添加footer之前设置
    viewContainer.useDefaultFooter();
    viewContainer.setLoadMoreHandler(new LoadMoreHandler() {
      @Override public void onLoadMore(LoadMoreContainer loadMoreContainer) {
        new Handler().postDelayed(new Runnable() {
          @Override public void run() {
            adapter.addItems(DataGenerator.generateItemList(pageNum));
            viewContainer.loadMoreFinish(false, checkMore());
          }
        }, 800);
      }
    });
    setLoadMore();
  }

  @Override protected CharSequence getTitleName() {
    return "多种下拉头部";
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    super.onCreateOptionsMenu(menu);
    getMenuInflater().inflate(R.menu.menu_ptr, menu);
    return true;
  }

  //change head Demo
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
       * you can add more letters by {@link StoreHousePath#addChar}
       */
      header.initWithString("Loading...");
      header.setTextColor(Colour.black50PercentColor());
      ptr.setHeaderView(header);
      ptr.addPtrUIHandler(header);
    } else if (id == R.id.menu_rentalssun) {
      final RentalsSunHeader header = new RentalsSunHeader(this);
      header.setUp(ptr);
      ptr.setHeaderView(header);
      ptr.addPtrUIHandler(header);
    }
    return super.onOptionsItemSelected(item);
  }

  private int maxPage = 5;
  private int currentPage = 1;

  private boolean checkMore() {
    return ++currentPage <= maxPage;
  }

  private void setLoadMore() {
    currentPage = 1;
    viewContainer.loadMoreFinish(false, true);
  }
}
