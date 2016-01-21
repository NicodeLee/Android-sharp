package com.nicodelee.app.fast.ui.view.activity;

import android.widget.TextView;
import butterknife.Bind;
import butterknife.OnClick;
import com.nicodelee.app.fast.R;
import com.nicodelee.base.BaseActivity;
import com.nicodelee.common.commonview.CommonToast;
import com.nicodelee.http.okhttp.OkHttpUtils;
import com.nicodelee.util.Logger;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by NocodeLee on 15/12/24.
 * Email：lirizhilirizhi@163.com
 *
 * Demo Base @see https://github.com/hongyangAndroid/okhttp-utils
 */
public class HttpShowActivity extends BaseActivity {
  @Bind(R.id.httpReslut) TextView httpReslut;

  @Override protected int getLayoutResId() {
    return R.layout.activity_http;
  }

  @Override protected CharSequence getTitleName() {
    return "Http 请求演示";
  }

  //String url = "https://publicobject.com/helloworld.txt";
  String url = "http://www.baidu.com/";
  String baseUrl = "http://1.beautifulwords.applinzi.com/";

  @OnClick(R.id.getHttp) void getData() {
    CommonToast.showToast(this, "请求中...");
    httpReslut.setText("");
    new Thread(new Runnable() {
      @Override public void run() {
        try {
          getRun(url);
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }).start();
  }

  @OnClick(R.id.get_http_async) void dogetAsync() {
    CommonToast.showToast(this, "异步请求");
    httpReslut.setText("");
    getAsync(url);
  }

  @OnClick(R.id.get_html) void getHtml() {
    OkHttpUtils.get().url(url).build().execute(new StringCallback() {
      @Override public void onError(Call call, Exception e) {
      }

      @Override public void onResponse(String response) {
        httpReslut.setText(response);
      }
    });
  }

  @OnClick(R.id.get_user) void getUser() {
    String url = baseUrl + "user";
    OkHttpUtils.get().url(url).build().execute(new UserCallback() {
      @Override public void onError(Call call, Exception e) {

      }

      @Override public void onResponse(User response) {
        httpReslut.setText(
            String.format("name=%s,pwd=%s,desc=%s", response.name, response.pwd, response.desc));
      }
    });
  }

  OkHttpClient client = new OkHttpClient();

  //同步get请求
  String getRun(String url) throws IOException {
    Request request = new Request.Builder().url(url).build();
    Response response = client.newCall(request).execute();
    if (response.isSuccessful()) {
      final String res = response.body().string();
      Logger.e(res);
      runOnUiThread(new Runnable() {
        @Override public void run() {
          httpReslut.setText(res);
        }
      });
    }
    return response.body().string();
  }

  public void getAsync(String url) {
    Request request = new Request.Builder().url(url).build();

    client.newCall(request).enqueue(new Callback() {
      @Override public void onFailure(Call call, IOException e) {
        e.printStackTrace();
      }

      @Override public void onResponse(Call call, Response response) throws IOException {
        final String res = response.body().string();
        runOnUiThread(new Runnable() {
          @Override public void run() {
            httpReslut.setText(res);
          }
        });
      }
    });
  }
}
