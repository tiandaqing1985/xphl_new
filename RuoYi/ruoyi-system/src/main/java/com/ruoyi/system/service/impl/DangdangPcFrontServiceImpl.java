package com.ruoyi.system.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.DangdangPcFrontMapper;
import com.ruoyi.system.service.IDangdangPcFrontService;
import com.ruoyi.common.core.text.Convert;

/**
 * 当当pc前端 服务层实现
 *
 * @author ruoyi
 * @date 2019-07-19
 */
@Service
public class DangdangPcFrontServiceImpl implements IDangdangPcFrontService {
    @Autowired
    private DangdangPcFrontMapper dangdangPcFrontMapper;

    /**
     * 查询当当pc前端信息
     *
     * @param id 当当pc前端ID
     * @return 当当pc前端信息
     */
    @Override
    public DangdangPcFront selectDangdangPcFrontById(Integer id) {
        return dangdangPcFrontMapper.selectDangdangPcFrontById(id);
    }

    @Override
    public List<DangDangPcAll> selectDangdangPcFrontList(DangdangPcFront dangdangPcFront) {
        java.text.DecimalFormat format = new java.text.DecimalFormat("#.00");
        List<DangDangPcAll> dangDangPcAlls = dangdangPcFrontMapper.importAll(dangdangPcFront);
        for (DangDangPcAll dangDangPcAll : dangDangPcAlls) {
            //实际消费
            if (new BigDecimal(dangDangPcAll.getCost()).compareTo(new BigDecimal(0)) != 1) {
                dangDangPcAll.setActCost(0.00);
            } else {
                BigDecimal actCost = new BigDecimal(dangDangPcAll.getCost()).divide(new BigDecimal(1.3), 2, RoundingMode.HALF_DOWN);
                dangDangPcAll.setActCost(actCost.doubleValue());
            }
            //出库订单转化率
            if (dangDangPcAll.getClick() == 0) {
                dangDangPcAll.setOutBoundRate("-");
            } else {
                BigDecimal outBoundRate = new BigDecimal(dangDangPcAll.getOutOrders())
                        .multiply(new BigDecimal(100))
                        .divide(new BigDecimal(dangDangPcAll.getClick()), 2, RoundingMode.HALF_DOWN);
                dangDangPcAll.setOutBoundRate(format.format(outBoundRate) + "%");
                if (dangDangPcAll.getOutBoundRate().startsWith(".")) {
                    dangDangPcAll.setOutBoundRate("0" + dangDangPcAll.getOutBoundRate());
                }
            }
            //出库ROI
            if (new BigDecimal(dangDangPcAll.getActCost()).compareTo(new BigDecimal(0)) != 1) {
                dangDangPcAll.setOutBoundROI("-");
            } else {
                BigDecimal outBoundROI = new BigDecimal(dangDangPcAll.getOutAmount()).
                        divide(new BigDecimal(dangDangPcAll.getActCost()), 2, RoundingMode.HALF_DOWN);
                dangDangPcAll.setOutBoundROI(format.format(outBoundROI) );
              if (dangDangPcAll.getOutBoundROI().startsWith(".")){
                  dangDangPcAll.setOutBoundROI("0"+dangDangPcAll.getOutBoundROI());
              }
            }
            //出库新客成本
            if (dangDangPcAll.getOutNumber() == 0) {
                dangDangPcAll.setOutBoubdCost("-");
            } else {
                BigDecimal outBoubdCost = new BigDecimal(dangDangPcAll.getActCost()).divide(new BigDecimal(dangDangPcAll.getOutNumber()), 2, RoundingMode.HALF_DOWN);
                dangDangPcAll.setOutBoubdCost(format.format(outBoubdCost));
            }
            //收订订单转化率
            if (dangDangPcAll.getClick() == 0) {
                dangDangPcAll.setOrderRate("-");
            } else {
                BigDecimal orderRate = new BigDecimal(dangDangPcAll.getReceiveOrders()).multiply(new BigDecimal(100)).divide(new BigDecimal(dangDangPcAll.getClick()), 2, RoundingMode.HALF_DOWN);
                dangDangPcAll.setOrderRate(format.format(orderRate) + "%");
                if (dangDangPcAll.getOrderRate().startsWith(".")) {
                    dangDangPcAll.setOrderRate("0" + dangDangPcAll.getOrderRate());
                }
            }
            //收订ROI orderROI
            if (new BigDecimal(dangDangPcAll.getActCost()).compareTo(new BigDecimal(0)) != 1) {
                dangDangPcAll.setOrderROI("-");
            } else {
                BigDecimal orderRoi = new BigDecimal(dangDangPcAll.getReceiveAmount())
                        .divide(new BigDecimal(dangDangPcAll.getActCost()),
                                2, RoundingMode.HALF_DOWN);
                dangDangPcAll.setOrderROI(format.format(orderRoi));
                if (dangDangPcAll.getOrderROI().startsWith(".")){
                    dangDangPcAll.setOrderROI("0"+dangDangPcAll.getOrderROI());

                }
            }
            // 收订新客成本   orderNewCost
            if (dangDangPcAll.getReceiveAccount()==0){
               dangDangPcAll.setOrderNewCost("-");
            }else {
                BigDecimal orderNewCost = new BigDecimal(dangDangPcAll.getActCost()).divide(new BigDecimal(dangDangPcAll.getReceiveAccount()), 2, RoundingMode.HALF_DOWN);
                dangDangPcAll.setOrderNewCost(format.format(orderNewCost));
            }
        }
        List<DangDangPcAll> other = dangdangPcFrontMapper.other(dangdangPcFront);
        dangDangPcAlls.addAll(other);

        return dangDangPcAlls;
    }


    /**
     * 新增当当pc前端
     *
     * @param dangdangPcFront 当当pc前端信息
     * @return 结果
     */
    @Override
    public int insertDangdangPcFront(DangdangPcFront dangdangPcFront) {
        return dangdangPcFrontMapper.insertDangdangPcFront(dangdangPcFront);
    }

    /**
     * 修改当当pc前端
     *
     * @param dangdangPcFront 当当pc前端信息
     * @return 结果
     */
    @Override
    public int updateDangdangPcFront(DangdangPcFront dangdangPcFront) {
        return dangdangPcFrontMapper.updateDangdangPcFront(dangdangPcFront);
    }

    /**
     * 删除当当pc前端对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteDangdangPcFrontByIds(String ids) {
        return dangdangPcFrontMapper.deleteDangdangPcFrontByIds(Convert.toStrArray(ids));
    }

    @Override
    public String importBwFront(List<DangdangPcFront> bwList, Boolean isUpdateSupport, String operName) {
        if (StringUtils.isNull(bwList) || bwList.size() == 0) {
            throw new BusinessException("导入用户数据不能为空！");
        }

        try {
            String flag = "";
            if (null != bwList && bwList.size() > 0) {
                int pointsDataLimit = 10000;//限制条数
                Integer size = bwList.size();
                //判断是否有必要分批
                if (pointsDataLimit < size) {
                    int part = size / pointsDataLimit;//分批数
                    // System.out.println("共有 ： "+size+"条，！"+" 分为 ："+part+"批");
                    //
                    for (int i = 0; i < part; i++) {
                        //1000条
                        List<DangdangPcFront> listPage = bwList.subList(0, pointsDataLimit);
                        dangdangPcFrontMapper.batchInsert(listPage);
                        //剔除
                        bwList.subList(0, pointsDataLimit).clear();
                    }
                    if (!bwList.isEmpty()) {
                        //表示最后剩下的数据
                        dangdangPcFrontMapper.batchInsert(bwList);
                    }
                } else {
                    dangdangPcFrontMapper.batchInsert(bwList);
                }

            }
            return "导入成功";
        } catch (Exception e) {
            e.printStackTrace();
            return "导入失败";
        }

    }
}
