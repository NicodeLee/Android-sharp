package com.nicodelee.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.ButterKnife;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.nicodelee.sharp.R;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public abstract class BaseActivity extends AppCompatActivity {

  public Toolbar toolbarView;
  public TextView toolbarTitleView;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Fresco.initialize(this);
    setContentView(getLayoutResId());
    initializeToolbar();
    initView();
  }

  @Override public void onContentChanged() {//布局改变回调
    super.onContentChanged();
    ButterKnife.bind(this);
  }

  @Override protected void onPostCreate(@Nullable Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);
    if (!isChild()) {
      onTitleChanged(getTitleName(), getTitleColor());
    }
  }

  protected CharSequence getTitleName() {
    return getTitle();
  }

  @Override
  protected void onTitleChanged(CharSequence title, int color) {
    super.onTitleChanged(title, color);
    if (toolbarTitleView == null) {
      return;
    }
    toolbarTitleView.setText(title);
  }

  protected void initializeToolbar() {
    toolbarView = (Toolbar) findViewById(R.id.toolbar);
    toolbarTitleView = (TextView) findViewById(R.id.toolbar_title);

    if (toolbarView == null) {
      return;
    }
    setSupportActionBar(toolbarView);
    if (toolbarTitleView != null) {
      getSupportActionBar().setDisplayShowTitleEnabled(false);
    }
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_back);
      toolbarView.setNavigationOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {
          finish();
        }
      });
  }

  public void showToast(String message) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
  }

  public void showShortToast(String message) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
  }

  public <T> T findViewById(View v, int id) {
    return (T) v.findViewById(id);
  }

  public <T> T findViewById(Activity activity, int id) {
    return (T) activity.findViewById(id);
  }

  public void skipIntent(Class clz, HashMap<String, Object> map, boolean isFinish) {
    Intent intent = new Intent(this, clz);
    if (map != null) {
      Iterator it = map.entrySet().iterator();

      while (it.hasNext()) {

        Map.Entry entry = (Map.Entry) it.next();

        String key = (String) entry.getKey();

        Serializable value = (Serializable) entry.getValue();

        intent.putExtra(key, value);
      }
    }
    startActivity(intent);
    if (isFinish) finish();
  }

  public void skipIntent(Class clz, HashMap<String, Object> map, int code) {
    Intent intent = new Intent(this, clz);
    if (map != null) {
      Iterator it = map.entrySet().iterator();

      while (it.hasNext()) {
        Map.Entry entry = (Map.Entry) it.next();
        String key = (String) entry.getKey();
        Serializable value = (Serializable) entry.getValue();
        intent.putExtra(key, value);
      }
    }
    startActivityForResult(intent, code);
  }

  public void skipIntent(Class clz, int code, boolean isFinish) {
    Intent intent = new Intent(this, clz);
    startActivityForResult(intent, code);
    if (isFinish) finish();
  }

  public void skipIntent(Class clz, boolean isFinish) {
    Intent intent = new Intent(this, clz);
    startActivity(intent);
    if (isFinish) finish();
  }

  public Object getExtra(String name) {
    return getIntent().getSerializableExtra(name);
  }

  //@Override public void onStart() {
  //  if (isStickyAvailable()) {
  //    EventBus.getDefault().register(this);
  //  } else {
  //    EventBus.getDefault().registerSticky(this);
  //  }
  //  super.onStart();
  //}
  //
  //@Override public void onStop() {
  //  EventBus.getDefault().unregister(this);
  //  super.onStop();
  //}
  //
  //public void onEvent(Object event) {
  //}

  protected boolean isStickyAvailable() {
    return false;
  }

  abstract protected @LayoutRes int getLayoutResId();

  protected void initView() {
  }
}
