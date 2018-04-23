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

  public int getPage() {
    return page;
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
