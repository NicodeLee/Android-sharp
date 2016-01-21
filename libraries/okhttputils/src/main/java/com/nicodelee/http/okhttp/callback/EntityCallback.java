package com.nicodelee.http.okhttp.callback;

import java.io.IOException;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by NicodeLee on 15/12/14.
 */
public abstract class EntityCallback extends Callback<Object> {

  @Override public void onError(Call call, Exception e) {
  }

  @Override public Object parseNetworkResponse(Response entity) throws IOException {
    return  entity;
  }
}
