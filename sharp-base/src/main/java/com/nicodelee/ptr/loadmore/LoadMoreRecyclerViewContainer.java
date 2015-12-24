package com.nicodelee.ptr.loadmore;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import com.nicodelee.ptr.loadmore.recyclerview.HFAdapter;
import com.nicodelee.ptr.loadmore.recyclerview.HFRecyclerAdapter;
import com.nicodelee.util.Logger;

/**
 * @author NicodeLee
 */
public class LoadMoreRecyclerViewContainer extends LoadMoreContainerBase {

  private RecyclerView mRecyelerView;
  private HFAdapter hfAdapter;

  public LoadMoreRecyclerViewContainer(Context context) {
    super(context);
  }

  public LoadMoreRecyclerViewContainer(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  @Override protected void addFooterView(View view) {
    RecyclerView.Adapter<?> adapter2 = (RecyclerView.Adapter<?>) getAdapter();
    if (adapter instanceof HFAdapter) {
      hfAdapter = (HFAdapter) adapter;
    } else {
      hfAdapter = new HFRecyclerAdapter(adapter2);
    }
    adapter2 = hfAdapter;
    hfAdapter.addFooter(view);
    mRecyelerView.setAdapter(adapter2);
  }

  @Override protected void removeFooterView(View view) {
    hfAdapter.removeFooter(view);
  }

  @Override protected View retrieveContainerView() {
    mRecyelerView = (RecyclerView) getChildAt(0);
    return mRecyelerView;
  }

  private RecyclerView.Adapter adapter;

  public RecyclerView.Adapter getAdapter() {
    return adapter;
  }

  public void setAdapter(RecyclerView.Adapter adapter) {
    this.adapter = adapter;
  }
}
