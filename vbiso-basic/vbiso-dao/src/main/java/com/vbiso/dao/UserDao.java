package com.vbiso.dao;

import com.vbiso.domain.IncomeCountDo;
import com.vbiso.domain.QueryDo;
import com.vbiso.domain.UserDo;
import com.vbiso.exception.BaseException;
import com.vbiso.mapping.FieldDo;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Author: wenliujie
 * @Description:
 * @Date: Created in 下午4:15 2018/3/11
 * @Modified By:
 */
@Repository
public interface UserDao {

  String DAO_NAME = "userDao";

  UserDo getByUserId(UserDo userDo) throws BaseException;

  int insertUser(UserDo userDo) throws BaseException;

  int updateFieldByUserId(@Param("userId") long userId, @Param("list") List<FieldDo> list)
      throws BaseException;

  UserDo getUserInfo(@Param("userId") long userId) throws BaseException;

  UserDo getUserInfoNoPass(@Param("userId") long userId) throws BaseException;

  UserDo getUserInfoByMobile(@Param("mobile")String mobile)throws BaseException;



}
