package com.nicodelee.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;
import butterknife.ButterKnife;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public abstract class BaseActivity extends AppCompatActivity {


  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(getLayoutResId());
    initView();
  }


  @Override
  public void onContentChanged() {//布局改变回调
    super.onContentChanged();
    ButterKnife.bind(this);
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

  protected void initView(){}

}
