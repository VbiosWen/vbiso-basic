package com.vbiso.domain;

/**
 * @Author: wenliujie
 * @Description:
 * @Date: Created in 上午10:54 2018/4/19
 * @Modified By:
 */
public class CategoryDo {

  private long categoryId;

  private long userId;

  private String categoryDesc;

  public long getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(long categoryId) {
    this.categoryId = categoryId;
  }

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }

  public String getCategoryDesc() {
    return categoryDesc;
  }

  public void setCategoryDesc(String categoryDesc) {
    this.categoryDesc = categoryDesc;
  }
}
