package com.vbiso.domain;

import java.util.List;

/**
 * @Author: wenliujie
 * @Description:
 * @Date: Created in 下午12:18 2018/4/10
 * @Modified By:
 */
public class PageDo <T>{

  private long userId;

  private int start;

  private int size;

  private T page;

  private long totalCount;

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }

  public int getStart() {
    return start;
  }

  public void setStart(int start) {
    this.start = start;
  }

  public int getSize() {
    return size;
  }

  public void setSize(int size) {
    this.size = size;
  }

  public T getPage() {
    return page;
  }

  public void setPage(T page) {
    this.page = page;
  }

  public long getTotalCount() {
    return totalCount;
  }

  public void setTotalCount(long totalCount) {
    this.totalCount = totalCount;
  }
}
