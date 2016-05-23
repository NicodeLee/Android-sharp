package com.nicodelee.base.ui.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import butterknife.Bind;
import com.github.florent37.hollyviewpager.HollyViewPager;
import com.github.florent37.hollyviewpager.HollyViewPagerConfigurator;
import com.nicodelee.base.BaseActivity;
import com.nicodelee.base.R;
import com.nicodelee.base.ui.fragment.AnimationDemoFragment;
import com.nicodelee.base.ui.fragment.AnimationFragment;
import com.nicodelee.base.ui.fragment.ScrollViewFragment;

/**
 * Created by NocodeLee on 16/1/27.
 * Email：lirizhilirizhi@163.com
 */
public class HollyVPActivity extends BaseActivity {

  int pageCount = 10;

  @Bind(R.id.hollyViewPager) HollyViewPager hollyViewPager;

  @Override protected int getLayoutResId() {
    return R.layout.activity_holly;
  }

  @Override protected CharSequence getTitleName() {
    return "动画集";
  }

  @Override protected void initView() {
    hollyViewPager.getViewPager()
        .setPageMargin(getResources().getDimensionPixelOffset(R.dimen.viewpager_margin));
    hollyViewPager.setConfigurator(new HollyViewPagerConfigurator() {
      @Override public float getHeightPercentForPage(int page) {
        float height = ((page + 4) % 10) / 10f; //headerHeight的比例
        return height;
      }
    });

    hollyViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
      @Override public Fragment getItem(int position) {
        switch (position) {
          case 0:
            return AnimationFragment.newInstance("弹性动画");
          case 1:
            return AnimationDemoFragment.newInstance("官方API");
        }

        return ScrollViewFragment.newInstance((String) getPageTitle(position));
      }

      @Override public int getCount() {
        return pageCount;
      }

      @Override public CharSequence getPageTitle(int position) {
        switch (position) {
          case 0:
            return "backboard";
          case 1:
            return "官方API";
        }
        return "TITLE " + position;
      }
    });
  }
}
