package com.ruoyi.system.service.finance.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.system.domain.Dingding;
import com.ruoyi.system.domain.UserModel;
import com.ruoyi.system.domain.finance.FacReiMealApply;
import com.ruoyi.system.mapper.OaDingdingMapper;
import com.ruoyi.system.mapper.finance.FacReiMealApplyMapper;
import com.ruoyi.system.service.finance.IFacReiMealApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 加班餐报销 服务层实现
 *
 * @author ruoyi
 * @date 2020-07-21
 */
@Service
public class FacReiMealApplyServiceImpl implements IFacReiMealApplyService {
    @Autowired
    private FacReiMealApplyMapper facReiMealApplyMapper;
    @Autowired
    private OaDingdingMapper oaDingdingMapper;

    /**
     * 查询加班餐报销信息
     *
     * @param id 加班餐报销ID
     * @return 加班餐报销信息
     */
    @Override
    public FacReiMealApply selectFacReiMealApplyById(Long id) {
        return facReiMealApplyMapper.selectFacReiMealApplyById(id);
    }

    /**
     * 查询加班餐报销列表
     *
     * @param facReiMealApply 加班餐报销信息
     * @return 加班餐报销集合
     */
    @Override
    public List<FacReiMealApply> selectFacReiMealApplyList(FacReiMealApply facReiMealApply) {
        return facReiMealApplyMapper.selectFacReiMealApplyList(facReiMealApply);
    }


    @Override
    public List<UserModel> selectAllUserModel(Date addDate) {
        try {
            Dingding ding = new Dingding();
            ding.setWorkDate(addDate);
            ding.setCheckType("offDuty");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date = sdf.format(addDate);
            String[] weekDays = {"日", "一", "二", "三", "四", "五", "六"};
            Calendar cal = Calendar.getInstance();
            cal.setTime(addDate);
            int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
            if (w < 0){
                w = 0;
            }
            String standardTime = date+ " 21:00:00";
            if(weekDays[w].equals("六")||weekDays[w].equals("日")){
                standardTime = date + " 15:00:00";
            }   else{
                 standardTime = date + " 21:00:00";
            }

            Date standardDate = sdf2.parse(standardTime);
            ding.setUserCheckTime(standardDate);
            List<UserModel> list = facReiMealApplyMapper.selectAllUserModel(ding);

            return list;
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;

    }


    /**
     * 新增加班餐报销
     *
     * @param facReiMealApply 加班餐报销信息
     * @return 结果
     */
    @Override
    public int insertFacReiMealApply(FacReiMealApply facReiMealApply) {
        return facReiMealApplyMapper.insertFacReiMealApply(facReiMealApply);
    }

    /**
     * 修改加班餐报销
     *
     * @param facReiMealApply 加班餐报销信息
     * @return 结果
     */
    @Override
    public int updateFacReiMealApply(FacReiMealApply facReiMealApply) {
        return facReiMealApplyMapper.updateFacReiMealApply(facReiMealApply);
    }

    /**
     * 删除加班餐报销对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteFacReiMealApplyByIds(String ids) {
        return facReiMealApplyMapper.deleteFacReiMealApplyByIds(Convert.toStrArray(ids));
    }

}
