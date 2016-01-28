package com.nicodelee.base.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringSystem;
import com.github.florent37.hollyviewpager.HollyViewPagerBus;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;
import com.nicodelee.base.BaseFragment;
import com.nicodelee.base.R;
import com.tumblr.backboard.Actor;
import com.tumblr.backboard.MotionProperty;
import com.tumblr.backboard.performer.Performer;

public class AnimationFragment extends BaseFragment {

    @Bind(R.id.scrollView) ObservableScrollView scrollView;
    @Bind(R.id.card_view) CardView mRootView;
    private View[] mCircles;


    public static AnimationFragment newInstance(String title){
        Bundle args = new Bundle();
        args.putString("title",title);
        AnimationFragment fragment = new AnimationFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_animation, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        HollyViewPagerBus.registerScrollView(getActivity(), scrollView);
        initView();
    }

    private void initView(){
        mCircles = new View[3];
        mCircles[0] = mRootView.findViewById(R.id.circle0);
        mCircles[1] = mRootView.findViewById(R.id.circle1);
        mCircles[2] = mRootView.findViewById(R.id.circle2);

        final SpringSystem springSystem = SpringSystem.create();
        final Spring[] springs = new Spring[3];
        final Actor[] actors = new Actor[3];


        // attach listeners
        for (int i = 0; i < mCircles.length; i++) {
            springs[i] = springSystem.createSpring();
            springs[i].addListener(new Performer(mCircles[i], View.TRANSLATION_Y));
            mCircles[i].setTag(springs[i]);
            actors[i] = new Actor.Builder(springSystem, mCircles[i]).addTranslateMotion(MotionProperty.X)
                .addTranslateMotion(MotionProperty.Y)
                .build();
        }

        mRootView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {

                int[] rootLocation = new int[2];
                v.getLocationInWindow(rootLocation);
                int[] location = new int[2];
                for (int i = 0; i < mCircles.length; i++) {

                    if (springs[i].getEndValue() == 0) { // hide
                        mCircles[i].getLocationInWindow(location);
                        double endValue = mRootView.getMeasuredHeight() - location[1] +
                            rootLocation[1] +
                            2 * Math.random() * mCircles[i].getMeasuredHeight();

                        springs[i].setEndValue(endValue);//负数时,向上

                    } else {
                        springs[i].setEndValue(0); // appear
                    }
                }
            }
        });
    }
}
