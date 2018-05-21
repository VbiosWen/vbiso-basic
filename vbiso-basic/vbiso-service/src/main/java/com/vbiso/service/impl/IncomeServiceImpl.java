package com.vbiso.service.impl;

import com.vbiso.dao.CategoryDao;
import com.vbiso.dao.IncomeDao;
import com.vbiso.domain.CategoryDo;
import com.vbiso.domain.EveryIncomeDo;
import com.vbiso.domain.IncomeCategoryDo;
import com.vbiso.domain.IncomeCountDo;
import com.vbiso.domain.IncomeCountQueryDo;
import com.vbiso.domain.IncomeDo;
import com.vbiso.domain.PageDo;
import com.vbiso.exception.BaseException;
import com.vbiso.pojo.IncomeExpensesQueryPojo;
import com.vbiso.redis.RedisDao;
import com.vbiso.result.ServiceResult;
import com.vbiso.service.IncomeService;
import com.vbiso.service.result.IncomeCategoryResult;
import com.vbiso.service.result.IncomeEveryCatResult;
import com.vbiso.utils.DateUtil;
import com.vbiso.utils.JsonUtil;
import com.vbiso.utils.StringUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

  private static Logger logger = Logger.getLogger(IncomeServiceImpl.class);

  @Autowired
  private IncomeDao incomeDao;

  @Autowired
  private CategoryDao categoryDao;

  @Autowired
  private RedisDao redisDao;


  @Override
  public ServiceResult<PageDo<List<IncomeDo>>> selectByPage(
      IncomeExpensesQueryPojo incomeExpensesQueryPojo) {
    ServiceResult<PageDo<List<IncomeDo>>> result = new ServiceResult<>();
    try {
      List<IncomeDo> incomeDos = incomeDao.selectPage(incomeExpensesQueryPojo);
      long totalCount = incomeDao.getTotalCount(incomeExpensesQueryPojo);
      PageDo<List<IncomeDo>> pageDo = new PageDo<>();
      pageDo.setUserId(incomeExpensesQueryPojo.getUserId());
      pageDo.setTotalCount(totalCount);
      pageDo.setPage(incomeDos);
      result.setData(pageDo);
      result.setSuccess(true);
      result.setCode(0);
    } catch (BaseException e) {
      result.setSuccess(false);
      result.setCode(-1);
      logger.error("selectByPage method throw an exception:", e);
    }
    return result;
  }

  @Override
  public ServiceResult<Integer> addIncome(IncomeDo incomeDo) {
    ServiceResult<Integer> result = new ServiceResult<>();
    try {
      int i = incomeDao.insertIncome(incomeDo);
      redisDao.setExpire(String.valueOf(incomeDo.getUserId()),JsonUtil.toJson(incomeDo),24*60*60*1000);
      result.setData(i);
      result.setSuccess(true);
      result.setCode(0);
    } catch (BaseException e) {
      logger.error("income insert erro " + JsonUtil.toJson(incomeDo), e);
    }
    return result;
  }

  @Override
  public ServiceResult<List<IncomeCountDo>> getDayIncome(IncomeCountQueryDo incomeCountQueryDo) {
    ServiceResult<List<IncomeCountDo>> result = new ServiceResult<>();
    try {
      List<IncomeCountDo> dayIncome = incomeDao.getDayIncome(incomeCountQueryDo);
      result.setData(dayIncome);
      result.setSuccess(true);
      result.setCode(0);
    } catch (BaseException e) {
      result.setSuccess(false);
      logger.error("error get ", e);
    }
    return result;
  }

  @Override
  public ServiceResult<Map<String,List<IncomeCategoryResult>>> getIncomeCategory(
      IncomeCountQueryDo incomeCountQueryDo) {
    ServiceResult<Map<String,List<IncomeCategoryResult>>> result=new ServiceResult<>();
    List<IncomeCategoryDo> categoryIncome = incomeDao.getCategoryIncome(incomeCountQueryDo);
    List<CategoryDo> categoryList = categoryDao.selectCategoryList(incomeCountQueryDo.getUserId());
    List<IncomeCategoryResult> list = buildParam(categoryIncome, categoryList);
    Map<String, List<IncomeCategoryResult>> stringListMap = invertIncomeToMap(list);
    result.setData(stringListMap);
    result.setCode(0);
    result.setSuccess(true);
    result.setMsg("success");
    return result;
  }

  @Override
  public ServiceResult<List<IncomeEveryCatResult>> getEveryCatIncome(
      IncomeCountQueryDo incomeCountQueryDo) {
    ServiceResult<List<IncomeEveryCatResult>> result=new ServiceResult<>();
    List<EveryIncomeDo> everyCategoryIncome = incomeDao
        .getEveryCategoryIncome(incomeCountQueryDo);
    List<CategoryDo> categoryDos = categoryDao.selectCategoryList(incomeCountQueryDo.getUserId());
    result.setSuccess(true);
    result.setMsg("successs");
    result.setData(buildParamToCat(everyCategoryIncome,categoryDos));
    return result;

  }

  @Override
  public ServiceResult<Integer> delSingleIncome(long incomeId) {
    ServiceResult<Integer> result=new ServiceResult<>();
    Integer integer = incomeDao.delSingleIncome(incomeId);
    result.setSuccess(true);
    result.setData(integer);
    result.setCode(0);
    return result;
  }

  private List<IncomeEveryCatResult> buildParamToCat(List<EveryIncomeDo> everyCategoryIncome,
      List<CategoryDo> categoryDos) {
    List<IncomeEveryCatResult> results=new ArrayList<>();
    for(EveryIncomeDo incomeCategoryDo:everyCategoryIncome){
      IncomeEveryCatResult everyCatResult=new IncomeEveryCatResult();
      everyCatResult.setSum(incomeCategoryDo.getIncomeData());
      everyCatResult.setUserId(incomeCategoryDo.getUserId());
      for(CategoryDo categoryDo:categoryDos){
        if(categoryDo.getCategoryId()==incomeCategoryDo.getCategoryId()){
          everyCatResult.setCategory(categoryDo.getCategoryDesc());
          break;
        }
      }
      if(StringUtil.isBlank(everyCatResult.getCategory())){
        everyCatResult.setCategory(String.valueOf(0));
      }
      results.add(everyCatResult);
    }
    return results;
  }

  private Map<String,List<IncomeCategoryResult>> invertIncomeToMap(List<IncomeCategoryResult> incomeCategoryResults) {
    Map<String,List<IncomeCategoryResult>> incomeData=new HashMap<>();
    for(IncomeCategoryResult incomeCategoryResult:incomeCategoryResults){
      incomeData.put(incomeCategoryResult.getCategoryDesc(),new ArrayList<>());
    }
    for(IncomeCategoryResult incomeCategoryResult:incomeCategoryResults){
      incomeData.get(incomeCategoryResult.getCategoryDesc()).add(incomeCategoryResult);
    }
    return incomeData;
  }

  private List<IncomeCategoryResult> buildParam(List<IncomeCategoryDo> categoryIncome,
      List<CategoryDo> categoryList) {
    List<IncomeCategoryResult> result=new ArrayList<>();

    for(IncomeCategoryDo incomeCategoryDo:categoryIncome){
      IncomeCategoryResult incomeCategoryResult = new IncomeCategoryResult();
      incomeCategoryResult.setIncomeData(incomeCategoryDo.getIncomeData());
      incomeCategoryResult.setIncomeDate(incomeCategoryDo.getIncomeDate());
      incomeCategoryResult.setUserId(incomeCategoryDo.getUserId());
      for(CategoryDo categoryDo:categoryList){
        if(categoryDo.getCategoryId()==incomeCategoryDo.getCategoryId()){
          incomeCategoryResult.setCategoryDesc(categoryDo.getCategoryDesc());
          break;
        }
      }
      if(StringUtil.isBlank(incomeCategoryResult.getCategoryDesc())){
        incomeCategoryResult.setCategoryDesc(String.valueOf(0));
      }
      result.add(incomeCategoryResult);
    }
    return result;
  }


}
