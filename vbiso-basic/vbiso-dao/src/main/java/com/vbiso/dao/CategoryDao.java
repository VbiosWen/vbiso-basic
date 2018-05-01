package com.vbiso.dao;

import com.vbiso.domain.CategoryDo;
import com.vbiso.exception.BaseException;
import com.vbiso.mapping.FieldDo;
import com.vbiso.utils.FieldMappingUtil;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Author: wenliujie
 * @Description:
 * @Date: Created in 上午10:54 2018/4/19
 * @Modified By:
 */
@Repository
public interface CategoryDao {

  List<CategoryDo> getCategoryList(@Param("userId") long userId) throws BaseException;

  int insertCategory(CategoryDo categoryDo) throws BaseException;

  int updateCategory(@Param("userId") long userId,@Param("list") List<FieldDo> fieldDos)throws BaseException;

  int deleteCategory(@Param("userId") long userId,@Param("categoryId") long categoryId) throws BaseException;

  CategoryDo getByCategoryId(@Param("categoryId") long categoryId);

  List<CategoryDo> selectCategoryList(@Param("userId") long userId);

}
