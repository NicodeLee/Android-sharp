package com.nicodelee.app.fast.ui.view;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.nicodelee.app.fast.R;
import com.nicodelee.app.fast.model.ItemListMod;
import io.nlopez.smartadapters.views.BindableFrameLayout;
import static com.nicodelee.app.fast.qualifier.ClickType.*;

/**
 * Created by NocodeLee on 15/12/15.
 * Email：lirizhilirizhi@163.com
 */
public class DemoListView extends BindableFrameLayout<ItemListMod> {

  @Bind(R.id.demo_list_title) TextView listTitle;
  public DemoListView(Context context) {
    super(context);
  }

  @Override
  public void onViewInflated() {
    ButterKnife.bind(this);
  }

  @Override public int getLayoutId() {
    return R.layout.view_item_list_demo;
  }

  @Override public void bind(ItemListMod itemListMod) {
    listTitle.setText(itemListMod.getTitle());
    setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        notifyItemAction(CLICK_TYPE_LIST_CLICKED);
      }
    });
  }
}
