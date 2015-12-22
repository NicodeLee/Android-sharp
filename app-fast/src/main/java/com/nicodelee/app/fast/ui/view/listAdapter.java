package com.nicodelee.app.fast.ui.view;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.nicodelee.common.util.ListUtils;
import java.util.List;

/**
 * Created by NocodeLee on 15/12/21.
 * Email：lirizhilirizhi@163.com
 */
public class ListAdapter extends BaseAdapter {
  private List mlist;


  @Override public int getCount() {
    return ListUtils.getSize(mlist);
  }

  @Override public Object getItem(int position) {
    return null;
  }

  @Override public long getItemId(int position) {
    return 0;
  }

  @Override public View getView(int position, View convertView, ViewGroup parent) {
    return null;
  }
}
