package com.vbiso.service;

import com.vbiso.domain.CategoryDo;
import com.vbiso.result.ServiceResult;
import java.util.List;

/**
 * @Author: wenliujie
 * @Description:
 * @Date: Created in 下午9:51 2018/4/26
 * @Modified By:
 */
public interface CategoryService {

  ServiceResult<List<CategoryDo>> selectCatList(long userId);

}
