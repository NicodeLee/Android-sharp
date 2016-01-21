package com.nicodelee.http.okhttp.decoder;

import org.json.JSONException;

public interface IStrategyDecoder {
  Object decodeJson(String t) throws JSONException;
  //ResponstResult decodeJson(String t) throws JSONException;
}
