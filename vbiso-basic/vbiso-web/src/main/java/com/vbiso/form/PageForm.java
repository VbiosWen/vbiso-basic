package com.vbiso.form;

/**
 * @Author: wenliujie
 * @Description:
 * @Date: Created in 下午4:50 2018/4/18
 * @Modified By:
 */
public class PageForm {

  private int page;

  private int limit;

  private long start;

  private long end;

  private long categoryId;

  public void setCategoryId(long categoryId) {
    this.categoryId = categoryId;
  }

  public long getCategoryId() {
    return categoryId;
  }

  public long getStart() {
    return start;
  }

  public void setStart(long start) {
    this.start = start;
  }

  public long getEnd() {
    return end;
  }

  public void setEnd(long end) {
    this.end = end;
  }

  public int getPage() {
    return page;
  }

  public void setPage(int page) {
    this.page = page;
  }

  public int getLimit() {
    return limit;
  }

  public void setLimit(int limit) {
    this.limit = limit;
  }
}
