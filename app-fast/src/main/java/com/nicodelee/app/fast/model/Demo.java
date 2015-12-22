package com.nicodelee.app.fast.model;

import com.nicodelee.app.fast.ui.view.activity.PropressWheelActivity;
import com.nicodelee.app.fast.ui.view.activity.PtrHeadActivity;

/**
 * Created by NocodeLee on 15/12/14.
 * Email：lirizhilirizhi@163.com
 */
public enum Demo{
  PTR("Ultra Pull To Refresh","百万兄的下拉刷新(多种自定义头部)", PtrHeadActivity.class),
  PROPRESSWHEEL("PropressWheel","圆形loading", PropressWheelActivity.class);

  public final String title;//标题
  public final String desc;//描述
  public final Class<?> clssName;//跳转的Activity

  Demo(String title, String desc, Class<?> clssName) {
    this.title = title;
    this.desc = desc;
    this.clssName = clssName;
  }
}
