package com.nicodelee.app.fast.ui.view.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Bind;
import com.nicodelee.app.fast.R;
import com.nicodelee.app.fast.ui.view.DemoView;
import com.nicodelee.base.BaseActivity;
import com.nicodelee.app.fast.model.ItemMod;
import com.nicodelee.app.fast.model.DataGenerator;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import io.nlopez.smartadapters.SmartAdapter;
import io.nlopez.smartadapters.utils.ViewEventListener;
import java.util.List;

import static com.nicodelee.app.fast.qualifier.ClickType.CLICK_TYPE_DEMO_CLICKED;

public class MainActivity extends BaseActivity implements ViewEventListener<ItemMod> {

  @Bind(R.id.recycler_view) RecyclerView recyclerView;
  @Bind(R.id.ptr_frameLayout) PtrClassicFrameLayout ptrFrameLayout;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override protected int getLayoutResId() {
    return R.layout.activity_recyclerview;
  }

  @Override protected void initView() {
    List<ItemMod> itemMods = DataGenerator.generateDemos();
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    SmartAdapter.items(itemMods)
        .map(ItemMod.class, DemoView.class)
        .listener(this)
        .into(recyclerView);

    ptrFrameLayout.setPtrHandler(new PtrDefaultHandler() {
      @Override public void onRefreshBegin(PtrFrameLayout frame) {
        frame.postDelayed(new Runnable() {
          @Override public void run() {
            ptrFrameLayout.refreshComplete();
          }
        }, 2000);
      }
    });

  }

  @Override public void onViewEvent(int actionId, ItemMod itemMod, int option, View view) {
    switch (actionId) {
      case CLICK_TYPE_DEMO_CLICKED:
        if (itemMod.getClassName() != null) {
          skipIntent(itemMod.getClassName(), false);
        }
        break;
    }
  }
}
