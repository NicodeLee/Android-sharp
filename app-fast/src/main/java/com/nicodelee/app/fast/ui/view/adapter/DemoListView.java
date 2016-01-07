package com.nicodelee.app.fast.ui.view.adapter;

import android.content.Context;
import android.widget.TextView;
import butterknife.Bind;
import com.nicodelee.app.fast.R;
import com.nicodelee.app.fast.base.BaseAdapterItemView;
import com.nicodelee.app.fast.model.ItemListMod;

/**
 * Created by NocodeLee on 15/12/15.
 * Email：lirizhilirizhi@163.com
 */
public class DemoListView extends BaseAdapterItemView<ItemListMod> {

  @Bind(R.id.demo_list_title) TextView listTitle;
  public DemoListView(Context context) {
    super(context);
  }

  //@Override
  //public void onViewInflated() {
  //  ButterKnife.bind(this);
  //}

  @Override public int getLayoutId() {
    return R.layout.view_item_list_demo;
  }

  @Override public void bind(ItemListMod itemListMod) {
    listTitle.setText(itemListMod.getTitle());
    //setOnClickListener(new OnClickListener() {
    //  @Override
    //  public void onClick(View v) {
    //    notifyItemAction(CLICK_TYPE_LIST_CLICKED);
    //  }
    //});
  }
}
