package com.nicodelee.app.fast.ui.view.activity;

import com.google.gson.Gson;
import com.nicodelee.http.okhttp.callback.Callback;
import java.io.IOException;
import okhttp3.Response;

/**
 * Created by zhy on 15/12/14.
 */
public abstract class UserCallback extends Callback<User>
{
    @Override
    public User parseNetworkResponse(Response response) throws IOException
    {
        String string = response.body().string();
        User user = new Gson().fromJson(string, User.class);
        return user;
    }


}
