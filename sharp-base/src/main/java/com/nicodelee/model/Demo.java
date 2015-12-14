package com.nicodelee.model;

/**
 * Created by NocodeLee on 15/12/14.
 * Email：lirizhilirizhi@163.com
 */
public enum Demo {
  PTR("Ultra Pull To Refresh","百万兄的下拉刷新"),
  PROPRESSWHEEL("PropressWheel","圆形loading");

  public final String title;
  public final String desc;

  Demo(String title, String desc) {
    this.title = title;
    this.desc = desc;
  }
}
