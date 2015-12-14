package com.nicodelee.app.fast;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import butterknife.Bind;
import com.nicodelee.base.BaseActivity;
import com.nicodelee.model.ItemMod;
import com.nicodelee.util.DataGenerator;
import com.nicodelee.util.Logger;
import java.util.List;

public class MainActivity extends BaseActivity {

  @Bind(R.id.recycler_view) RecyclerView recyclerView;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override protected int getLayoutResId() {
    return R.layout.activity_recyclerview;
  }

  @Override protected void initView() {
    List<ItemMod> itemMods = DataGenerator.generateDemos();
    for (ItemMod itemMod: itemMods){
      //showToast(itemMod.getTitle()+","+itemMod.getDesc());
      Logger.e(itemMod.getTitle()+","+itemMod.getDesc());
    }
  }

  //   startActivity(new Intent(MainActivity.this, PropressWheelActivity.class));
}
