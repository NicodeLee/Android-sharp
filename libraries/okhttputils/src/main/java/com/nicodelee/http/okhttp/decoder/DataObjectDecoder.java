package com.nicodelee.http.okhttp.decoder;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import org.json.JSONException;

public class DataObjectDecoder implements IStrategyDecoder {

  protected Class entityClass;

  public DataObjectDecoder(Class theClass) {
    super();
    entityClass = theClass;
  }

  public Object decodeJson(String t) throws JSONException {
    Object data = null;
    try {
      data = new Gson().fromJson(t, entityClass);
    } catch (JsonSyntaxException e) {
      e.printStackTrace();
      return data;
    }
    return data;
  }
}
