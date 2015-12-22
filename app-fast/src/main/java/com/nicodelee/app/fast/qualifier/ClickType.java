package com.nicodelee.app.fast.qualifier;

import android.support.annotation.IntDef;
import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.SOURCE;
import static com.nicodelee.app.fast.qualifier.ClickType.*;

/**
 * Created by NocodeLee on 15/12/15.
 * Email：lirizhilirizhi@163.com
 */
@Retention(SOURCE) 
@IntDef({
    CLICK_TYPE_DEMO_CLICKED,
    CLICK_TYPE_LIST_CLICKED
})
public @interface ClickType {
  int CLICK_TYPE_DEMO_CLICKED = 1000;
  int CLICK_TYPE_LIST_CLICKED = 1001;
}