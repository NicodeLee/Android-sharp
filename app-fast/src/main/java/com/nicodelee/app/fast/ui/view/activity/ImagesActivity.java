package com.nicodelee.app.fast.ui.view.activity;

import android.net.Uri;
import android.os.Bundle;
import butterknife.Bind;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.nicodelee.app.fast.R;
import com.nicodelee.base.BaseActivity;

/**
 * Created by NocodeLee on 15/12/24.
 * Email：lirizhilirizhi@163.com
 */
public class ImagesActivity extends BaseActivity {



  @Bind(R.id.main_sdv) SimpleDraweeView simpleDraweeView;

  @Override protected int getLayoutResId() {
    return R.layout.activity_images;
  }

  @Override protected CharSequence getTitleName() {
    return "图片加载";
  }

  @Override protected void initView() {
    Uri imageUri = Uri.parse("https://avatars0.githubusercontent.com/u/3689377?v=3&amp;s=460");
    simpleDraweeView.setImageURI(imageUri);
    DraweeController controller = Fresco.newDraweeControllerBuilder()
        //重试之后要加载的图片URI地址
        .setUri(imageUri)
        //设置点击重试是否开启
        .setTapToRetryEnabled(true)
        //设置旧的Controller
        .setOldController(simpleDraweeView.getController())
        .build();

    simpleDraweeView.setController(controller);
  }
}
