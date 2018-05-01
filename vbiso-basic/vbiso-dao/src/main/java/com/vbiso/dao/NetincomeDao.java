package com.vbiso.dao;

import com.vbiso.domain.NetIncomeDo;
import com.vbiso.exception.BaseException;
import com.vbiso.mapping.FieldDo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: wenliujie
 * @Description:
 * @Date: Created in 下午7:07 2018/5/1
 * @Modified By:
 */
public interface NetincomeDao {

  NetIncomeDo getNetincome(@Param("userId") long userId)throws BaseException;

  int insertNetincome(NetIncomeDo netIncomeDo);

  int updateNetincome(@Param("userId") long userId,@Param("list")List<FieldDo> fieldDo);

  double getSumNetincome(@Param("userId") long userId);

}
