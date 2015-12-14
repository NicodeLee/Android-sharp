package com.nicodelee.model;

/**
 * Created by NocodeLee on 15/12/14.
 * Email：lirizhilirizhi@163.com
 *
 * Demo 实体
 */

public class ItemMod {
  private String title;
  private String Desc;

  public ItemMod(String title,String desc){
    this.title = title;
    this.Desc = desc;
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

}
