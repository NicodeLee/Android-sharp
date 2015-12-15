package com.nicodelee.app.fast.view;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import butterknife.Bind;
import com.nicodelee.app.fast.R;
import com.nicodelee.base.BaseAdapterItemView;
import com.nicodelee.model.ItemMod;

import static com.nicodelee.qualifier.ClickType.CLICK_TYPE_DEMO_CLICKED;

/**
 * Created by NocodeLee on 15/12/15.
 * Email：lirizhilirizhi@163.com
 */
public class DemoView extends BaseAdapterItemView<ItemMod> {

  @Bind(R.id.demo_title) TextView title;
  @Bind(R.id.demo_desc) TextView desc;
  public DemoView(Context context) {
    super(context);
  }

  @Override public int getLayoutId() {
    return R.layout.view_item_demo;
  }

  @Override public void bind(ItemMod itemMod) {
    title.setText(itemMod.getTitle());
    desc.setText(itemMod.getDesc());

    setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        notifyItemAction(CLICK_TYPE_DEMO_CLICKED);
      }
    });
  }
}
