package com.nicodelee.common.commonview;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.nicodelee.common.R;

/**
 * Created by NocodeLee on 15/12/25.
 * Email：lirizhilirizhi@163.com
 */
public class CommonToast {

  public static void showToast(Context context, String msg) {
    LayoutInflater inflater = LayoutInflater.from(context);
    View layout = inflater.inflate(R.layout.layout_toast,null);
    TextView title = (TextView) layout.findViewById(R.id.tvMsg);
    title.setText(msg);
    Toast toast = new Toast(context);
    toast.setGravity(Gravity.CENTER, 0, 0);
    toast.setDuration(Toast.LENGTH_SHORT);
    toast.setView(layout);
    toast.show();
  }


}
