package com.ruoyi.system.service.finance;

import com.ruoyi.system.domain.UserModel;
import com.ruoyi.system.domain.finance.FacReiMealApply;

import java.util.Date;
import java.util.List;

/**
 * 加班餐报销 服务层
 *
 * @author ruoyi
 * @date 2020-07-21
 */
public interface IFacReiMealApplyService {
    /**
     * 查询加班餐报销信息
     *
     * @param id 加班餐报销ID
     * @return 加班餐报销信息
     */
    public FacReiMealApply selectFacReiMealApplyById(Long id);

    /**
     * 查询加班餐报销列表
     *
     * @param facReiMealApply 加班餐报销信息
     * @return 加班餐报销集合
     */
    public List<FacReiMealApply> selectFacReiMealApplyList(FacReiMealApply facReiMealApply);


    public List<UserModel> selectAllUserModel(Date addDate);


    /**
     * 新增加班餐报销
     *
     * @param facReiMealApply 加班餐报销信息
     * @return 结果
     */
    public int insertFacReiMealApply(FacReiMealApply facReiMealApply);

    /**
     * 修改加班餐报销
     *
     * @param facReiMealApply 加班餐报销信息
     * @return 结果
     */
    public int updateFacReiMealApply(FacReiMealApply facReiMealApply);

    /**
     * 删除加班餐报销信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteFacReiMealApplyByIds(String ids);

}
