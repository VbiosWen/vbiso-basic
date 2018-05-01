package com.vbiso.pojo;

/**
 * @Author: wenliujie
 * @Description:
 * @Date: Created in 下午9:59 2018/4/25
 * @Modified By:
 */
public class IncomeExpensesQueryPojo {

  private int page;

  private int limit;

  private long start;

  private long end;

  private long categoryId;

  private long userId;

  public long getCategoryId() {
    return categoryId;
  }

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }

  public void setCategoryId(long categoryId) {
    this.categoryId = categoryId;
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
    return page*limit;
  }

  public void setPage(int page) {
    this.page = page-1;
  }

  public int getLimit() {
    return limit;
  }

  public void setLimit(int limit) {
    this.limit = limit;
  }

}
