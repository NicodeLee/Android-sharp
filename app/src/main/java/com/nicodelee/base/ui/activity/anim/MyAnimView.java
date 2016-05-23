package com.nicodelee.base.ui.activity.anim;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import com.nicodelee.common.colours.Colour;

/**
 * Created by NocodeLee on 16/2/19.
 * Email：lirizhilirizhi@163.com
 */
public class MyAnimView extends View {

  private  Point currentPoint;

  public static final float RADIUS = 100f;

  private Paint mPaint;

  public MyAnimView(Context context, AttributeSet attrs) {
    super(context, attrs);
    mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    mPaint.setColor(Colour.goldColor());
    //mPaint.setColor(Color.BLUE);
  }

  @Override protected void onDraw(Canvas canvas) {
    if (currentPoint == null){
      currentPoint = new Point(RADIUS,RADIUS);
      drawCricle(canvas);
      startAnimation();
    }else {
      drawCricle(canvas);
    }
  }

  private void drawCricle(Canvas canvas){
    float x = currentPoint.getX();
    float y = currentPoint.getY();
    canvas.drawCircle(x,y,RADIUS,mPaint);
  }

  private void startAnimation(){
    Point startPoint = new Point(RADIUS,RADIUS);
    Point endPoint = new Point(getWidth() - RADIUS,getHeight() -RADIUS);
    ValueAnimator anim = ValueAnimator.ofObject(new PointEvaluator(),startPoint,endPoint);
    anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
      @Override public void onAnimationUpdate(ValueAnimator animation) {
        currentPoint = (Point)animation.getAnimatedValue();
        invalidate();
      }
    });
    anim.setDuration(5000);
    anim.start();

  }

}
