package com.nicodelee.ptr.header;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import com.nicodelee.sharp.R;

/**
 * Created by NocodeLee on 15/12/16.
 * Email：lirizhilirizhi@163.com
 */
public class WindmillDrawable extends Drawable implements Animatable {

  private Resources resources;

  private Bitmap windmill;

  private View parent;

  private Matrix matrix;

  private Animation animation;

  private boolean isFirstDraw = true;

  private boolean isAnimating;

  public WindmillDrawable(Context context, View parent) {
    resources = context.getResources();
    windmill = BitmapFactory.decodeResource(resources, R.drawable.windmill);

    matrix = new Matrix();
    this.parent = parent;

    animation = new RotateAnimation(360, 0, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
    animation.setInterpolator(new LinearInterpolator());
    animation.setDuration(800);
    animation.setRepeatMode(Animation.RESTART);
    animation.setRepeatCount(Animation.INFINITE);
    animation.setFillAfter(true);

  }

  //drawale重写方法
  @Override public void draw(Canvas canvas) {
    if (isFirstDraw) {
      isFirstDraw = false;
      matrix.setTranslate((getBounds().width() - windmill.getWidth()) / 2, (getBounds().height() - windmill.getHeight()) / 2);
    }

    Paint p = new Paint();
    canvas.drawBitmap(windmill, matrix, p);
  }

  @Override public void setAlpha(int alpha) {

  }

  @Override public void setColorFilter(ColorFilter colorFilter) {

  }

  @Override public int getOpacity() {
    return 0;
  }

  public void postRotation(int degree) {
    matrix.postRotate(degree, getBounds().exactCenterX(), getBounds().exactCenterY());
    invalidateSelf();
  }

  //Animatable回调
  @Override public void start() {
    parent.startAnimation(animation);
    isAnimating = true;
  }

  @Override public void stop() {
    parent.clearAnimation();
    isAnimating = false;
  }

  @Override public boolean isRunning() {
    return isAnimating;
  }

}
