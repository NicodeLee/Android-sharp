package com.nicodelee.app.fast.model;

import com.nicodelee.app.fast.ui.view.activity.AnimationActivity;
import com.nicodelee.app.fast.ui.view.activity.HollyVPActivity;
import com.nicodelee.app.fast.ui.view.activity.HttpShowActivity;
import com.nicodelee.app.fast.ui.view.activity.ImagesActivity;
import com.nicodelee.app.fast.ui.view.activity.PropressWheelActivity;
import com.nicodelee.app.fast.ui.view.activity.PtrHeadActivity;

/**
 * Created by NocodeLee on 15/12/14.
 * Email：lirizhilirizhi@163.com
 */
public enum Demo{
  PTR("Ultra Pull To Refresh","百万兄的下拉刷新(多种自定义头部)加自动加载(Recuclerview Listview)", PtrHeadActivity.class),
  IMAGES("主流图片加载框架","UIL Fresco等", ImagesActivity.class),
  PROPRESSWHEEL("PropressWheel","圆形loading", PropressWheelActivity.class),
  Animation(" Animation(动画)","动画 特效,rebound...", HollyVPActivity.class),
  HTTPSHOW("网络部分","最受欢迎的网络请求库", HttpShowActivity.class);

  public final String title;//标题
  public final String desc;//描述
  public final Class<?> clssName;//跳转的Activity

  Demo(String title, String desc, Class<?> clssName) {
    this.title = title;
    this.desc = desc;
    this.clssName = clssName;
  }
}
