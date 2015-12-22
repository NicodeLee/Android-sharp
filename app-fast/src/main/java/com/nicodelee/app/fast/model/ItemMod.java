package com.nicodelee.app.fast.model;

/**
 * Created by NocodeLee on 15/12/14.
 * Email：lirizhilirizhi@163.com
 *
 * Demo 实体
 */

public class ItemMod {
  private String title;
  private String Desc;
  private Class<?> className;

  public ItemMod(String title,String desc,Class<?> className){
    this.title = title;
    this.Desc = desc;
    this.className = className;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDesc() {
    return Desc;
  }

  public void setDesc(String desc) {
    Desc = desc;
  }

  public Class<?> getClassName() {
    return className;
  }

  public void setClassName(Class<?> className) {
    this.className = className;
  }
}
