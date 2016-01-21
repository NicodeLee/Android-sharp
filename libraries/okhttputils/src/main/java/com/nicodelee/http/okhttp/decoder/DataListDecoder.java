package com.nicodelee.http.okhttp.decoder;

import com.google.gson.Gson;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;

public class DataListDecoder<T> implements IStrategyDecoder {

  private Type listType;

  public DataListDecoder(Type listType) {
    super();
    this.listType = listType;
  }

  public List<?> decodeJson(String string) throws JSONException {
    List<?> list = new ArrayList<>();
    try {
      list = new Gson().fromJson(string, listType);
    } catch (Exception e) {
      e.printStackTrace();
      return list;
    }
    return list;
  }
}
