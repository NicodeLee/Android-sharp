package com.nicodelee.base.ui.activity;

import com.google.gson.Gson;
import com.nicodelee.http.okhttp.callback.Callback;
import java.io.IOException;
import okhttp3.Response;

/**
 * Created by NicodeLee on 15/12/14.
 */
public abstract class EntityCallback extends Callback<User> {
  @Override public User parseNetworkResponse(Response entity) throws IOException {
    String string = entity.body().string();
    User user = new Gson().fromJson(string, User.class);
    return user;
  }
}
