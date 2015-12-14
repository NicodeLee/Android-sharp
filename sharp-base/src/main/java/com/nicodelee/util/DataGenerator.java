package com.nicodelee.util;

import com.nicodelee.model.Demo;
import com.nicodelee.model.ItemMod;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by NocodeLee on 15/12/14.
 * Email：lirizhilirizhi@163.com
 *
 * 数据提供者
 */
public class DataGenerator {

  public static List<ItemMod> generateDemos(){
    List<ItemMod> result = new ArrayList<>();
    for (Demo demo: Demo.values()){
      result.add(new ItemMod(demo.title,demo.desc));
    }
    return result;
  }
}
