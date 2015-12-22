package com.nicodelee.app.fast.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by NocodeLee on 15/12/14.
 * Email：lirizhilirizhi@163.com
 *
 * 数据提供者
 */
public class DataGenerator {
  private static final Random RANDOM = new Random();

  public static List<ItemMod> generateDemos() {
    List<ItemMod> result = new ArrayList<>();
    for (Demo demo : Demo.values()) {
      result.add(new ItemMod(demo.title, demo.desc, demo.clssName));
    }
    return result;
  }

  public static List<ItemListMod> generateItemList(int n) {
    List<ItemListMod> result = new ArrayList<>(n);
    for (int i = 0; i < n; i++) {
      ItemListMod itemListMod =
          new ItemListMod(String.valueOf(getCharArry(3)), String.valueOf(getCharArry(6)));
      result.add(itemListMod);
    }
    return result;
  }

  public static List<String> generateStrings(int n) {
    List<String> result = new ArrayList<>(n);
    for (int i = 0; i < n; i++) {
      result.add(String.valueOf(getCharArry(5)));
    }
    return result;
  }

  private static char getChar() {
    return (char) ('a' + RANDOM.nextInt('z' - 'a'));
  }

  private static char[] getCharArry(int n) {
    char[] result = new char[n];
    for (int i = 0; i < n; i++) {
      result[i] = getChar();
    }
    return result;
  }
}
