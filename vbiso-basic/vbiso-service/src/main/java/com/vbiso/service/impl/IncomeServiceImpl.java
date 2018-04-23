package com.vbiso.service.impl;

import com.vbiso.dao.IncomeDao;
import com.vbiso.domain.IncomeDo;
import com.vbiso.domain.PageDo;
import com.vbiso.exception.BaseException;
import com.vbiso.result.ServiceResult;
import com.vbiso.service.IncomeService;
import com.vbiso.utils.JsonUtil;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: wenliujie
 * @Description:
 * @Date: Created in 下午11:04 2018/4/14
 * @Modified By:
 */
@Service
public class IncomeServiceImpl implements IncomeService {

  private static Logger logger=Logger.getLogger(IncomeServiceImpl.class);

  @Autowired
  private IncomeDao incomeDao;



  @Override
  public ServiceResult<PageDo<List<IncomeDo>>> selectByPage(long userId, int start, int size) {
    ServiceResult<PageDo<List<IncomeDo>>> result=new ServiceResult<>();
    try {
      List<IncomeDo> incomeDos = incomeDao.selectPage(userId, start, size);
      long totalCount = incomeDao.getTotalCount(userId);
      PageDo<List<IncomeDo>> pageDo = new PageDo<>();
      pageDo.setUserId(userId);
      pageDo.setTotalCount(totalCount);
      pageDo.setPage(incomeDos);
      result.setData(pageDo);
      result.setSuccess(true);
      result.setCode(0);
    } catch (BaseException e) {
      result.setSuccess(false);
      result.setCode(-1);
      logger.error("selectByPage method throw an exception:",e);
    }
    return result;
  }

  @Override
  public ServiceResult<Integer> addIncome(IncomeDo incomeDo) {
    ServiceResult<Integer> result = new ServiceResult<>();
    try {
      int i = incomeDao.insertIncome(incomeDo);
      result.setData(i);
      result.setSuccess(true);
      result.setCode(0);
    } catch (BaseException e) {
      logger.error("income insert erro "+ JsonUtil.toJson(incomeDo),e);
    }
    return result;
  }
}
