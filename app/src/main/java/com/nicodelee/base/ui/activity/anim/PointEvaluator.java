package com.nicodelee.base.ui.activity.anim;

import android.animation.TypeEvaluator;

/**
 * Created by NocodeLee on 16/2/19.
 * Email：lirizhilirizhi@163.com
 */
public class PointEvaluator implements TypeEvaluator {

  @Override public Object evaluate(float fraction, Object startValue, Object endValue) {
    Point startPoint = (Point)startValue;
    Point endPoint = (Point)endValue;
    float x = startPoint.getX() + fraction * (endPoint.getX()) - startPoint.getX();
    float y = startPoint.getY() + fraction * (endPoint.getY()) -startPoint.getY();
    Point point = new Point(x,y);
    return point;
  }
}
