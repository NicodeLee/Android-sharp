package com.nicodelee.ptr.loadmore;

import android.view.View;
import android.widget.AbsListView;

/**
 * Created by NocodeLee on 15/12/21.
 * Email：lirizhilirizhi@163.com
 */
public interface LoadMoreContainer {
  public void setShowLoadingForFirstPage(boolean showLoading);

  public void setAutoLoadMore(boolean autoLoadMore);

  public void setOnScrollListener(AbsListView.OnScrollListener l);

  public void setLoadMoreView(View view);

  public void setLoadMoreUIHandler(LoadMoreUIHandler handler);

  public void setLoadMoreHandler(LoadMoreHandler handler);

  /**
   * When data has loaded
   *
   * @param emptyResult
   * @param hasMore
   */
  public void loadMoreFinish(boolean emptyResult, boolean hasMore);

  /**
   * When something unexpected happened while loading the data
   *
   * @param errorCode
   * @param errorMessage
   */
  public void loadMoreError(int errorCode, String errorMessage);
}
