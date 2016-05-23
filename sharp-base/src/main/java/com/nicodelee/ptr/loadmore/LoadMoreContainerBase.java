package com.nicodelee.ptr.loadmore;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import com.nicodelee.util.Logger;

/**
 * @author huqiu.lhq
 */
public abstract class LoadMoreContainerBase extends LinearLayout implements LoadMoreContainer {

  private AbsListView.OnScrollListener mOnScrollListener;
  private LoadMoreUIHandler mLoadMoreUIHandler;
  private LoadMoreHandler mLoadMoreHandler;

  private boolean mIsLoading;
  private boolean mHasMore = false;
  private boolean mAutoLoadMore = true;
  private boolean mLoadError = false;

  private boolean mListEmpty = true;
  private boolean mShowLoadingForFirstPage = false;
  private View mFooterView;

  private AbsListView mAbsListView;
  private RecyclerView mrecyclerView;

  private View contentView;

  public LoadMoreContainerBase(Context context) {
    super(context);
  }

  public LoadMoreContainerBase(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  @Override protected void onFinishInflate() {
    super.onFinishInflate();
    contentView = retrieveContainerView();
    init();
  }

  public void useDefaultFooter() {
    LoadMoreDefaultFooterView footerView = new LoadMoreDefaultFooterView(getContext());
    footerView.setVisibility(GONE);
    setLoadMoreView(footerView);
    setLoadMoreUIHandler(footerView);
  }

  private void init() {

    if (mFooterView != null) {
      addFooterView(mFooterView);
    }

    if (contentView instanceof ListView) {
      mAbsListView = (AbsListView) contentView;
      mAbsListView.setOnScrollListener(new AbsListView.OnScrollListener() {
        private boolean mIsEnd = false;

        @Override public void onScrollStateChanged(AbsListView view, int scrollState) {

          if (null != mOnScrollListener) {
            mOnScrollListener.onScrollStateChanged(view, scrollState);
          }
          if (scrollState == SCROLL_STATE_IDLE) {
            if (mIsEnd) {
              onReachBottom();
            }
          }
        }

        @Override public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
            int totalItemCount) {
          if (null != mOnScrollListener) {
            mOnScrollListener.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount);
          }
          if (firstVisibleItem + visibleItemCount >= totalItemCount - 1) {
            mIsEnd = true;
          } else {
            mIsEnd = false;
          }
        }
      });
    } else if (contentView instanceof RecyclerView) {
      mrecyclerView = (RecyclerView) contentView;
      mrecyclerView.addOnScrollListener(new RecyclerViewOnScrollListener());
    }
  }

  /**
   * 滑动监听
   */
  private class RecyclerViewOnScrollListener extends RecyclerView.OnScrollListener {

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
      if (newState == RecyclerView.SCROLL_STATE_IDLE && isScollBottom(recyclerView)) {
        onReachBottom();
      }
    }

    private boolean isScollBottom(RecyclerView recyclerView) {
      return !isCanScollVertically(recyclerView);
    }

    private boolean isCanScollVertically(RecyclerView recyclerView) {
      if (android.os.Build.VERSION.SDK_INT < 14) {
        return ViewCompat.canScrollVertically(recyclerView, 1) || recyclerView.getScrollY() < recyclerView.getHeight();
      } else {
        return ViewCompat.canScrollVertically(recyclerView, 1);
      }
    }

  }

  private void tryToPerformLoadMore() {
    if (mIsLoading) {
      return;
    }

    // no more content and also not load for first page
    if (!mHasMore && !(mListEmpty && mShowLoadingForFirstPage)) {
      return;
    }

    mIsLoading = true;

    if (mLoadMoreUIHandler != null) {
      mLoadMoreUIHandler.onLoading(this);
    }
    if (null != mLoadMoreHandler) {
      mLoadMoreHandler.onLoadMore(this);
    }
  }

  private void onReachBottom() {
    // if has error, just leave what it should be
    if (mLoadError) {
      return;
    }
    if (mAutoLoadMore) {
      tryToPerformLoadMore();
    } else {
      if (mHasMore) {
        mLoadMoreUIHandler.onWaitToLoadMore(this);
      }
    }
  }

  @Override public void setShowLoadingForFirstPage(boolean showLoading) {
    mShowLoadingForFirstPage = showLoading;
  }

  @Override public void setAutoLoadMore(boolean autoLoadMore) {
    mAutoLoadMore = autoLoadMore;
  }

  @Override public void setOnScrollListener(AbsListView.OnScrollListener l) {
    mOnScrollListener = l;
  }

  @Override public void setLoadMoreView(View view) {
    // has not been initialized
    if (contentView == null) {
      mFooterView = view;
      return;
    }
    // remove previous
    if (mFooterView != null && mFooterView != view) {
      removeFooterView(view);
    }

    // add current
    mFooterView = view;
    mFooterView.setOnClickListener(new OnClickListener() {
      @Override public void onClick(View view) {
        tryToPerformLoadMore();
      }
    });

    addFooterView(view);
  }

  @Override public void setLoadMoreUIHandler(LoadMoreUIHandler handler) {
    mLoadMoreUIHandler = handler;
  }

  @Override public void setLoadMoreHandler(LoadMoreHandler handler) {
    mLoadMoreHandler = handler;
  }

  /**
   * page has loaded
   */
  @Override public void loadMoreFinish(boolean emptyResult, boolean hasMore) {
    mLoadError = false;
    mListEmpty = emptyResult;
    mIsLoading = false;
    mHasMore = hasMore;

    if (mLoadMoreUIHandler != null) {
      mLoadMoreUIHandler.onLoadFinish(this, emptyResult, hasMore);
    }
  }

  @Override public void loadMoreError(int errorCode, String errorMessage) {
    mIsLoading = false;
    mLoadError = true;
    if (mLoadMoreUIHandler != null) {
      mLoadMoreUIHandler.onLoadError(this, errorCode, errorMessage);
    }
  }

  protected abstract void addFooterView(View view);

  protected abstract void removeFooterView(View view);

  protected abstract View retrieveContainerView();

}