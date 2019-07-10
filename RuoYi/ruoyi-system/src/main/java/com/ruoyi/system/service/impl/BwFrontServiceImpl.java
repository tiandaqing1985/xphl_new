package com.ruoyi.system.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.BwZhanXian;
import com.ruoyi.system.domain.JfFront;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.BwFrontMapper;
import com.ruoyi.system.domain.BwFront;
import com.ruoyi.system.service.IBwFrontService;
import com.ruoyi.common.core.text.Convert;

/**
 * 宝沃前端 服务层实现
 *
 * @author ruoyi
 * @date 2019-07-09
 */
@Service
public class BwFrontServiceImpl implements IBwFrontService {
    @Autowired
    private BwFrontMapper bwFrontMapper;

    /**
     * 查询宝沃前端信息
     *
     * @param id 宝沃前端ID
     * @return 宝沃前端信息
     */
    @Override
    public BwFront selectBwFrontById(Integer id) {
        return bwFrontMapper.selectBwFrontById(id);
    }

    /**
     * 查询宝沃前端列表
     *
     * @param bwFront 宝沃前端信息
     * @return 宝沃前端集合
     */
    @Override
    public List<BwFront> selectBwFrontList(BwFront bwFront) {
        return bwFrontMapper.selectBwFrontList(bwFront);
    }

    /**
     * 新增宝沃前端
     *
     * @param bwFront 宝沃前端信息
     * @return 结果
     */
    @Override
    public int insertBwFront(BwFront bwFront) {
        return bwFrontMapper.insertBwFront(bwFront);
    }

    /**
     * 修改宝沃前端
     *
     * @param bwFront 宝沃前端信息
     * @return 结果
     */
    @Override
    public int updateBwFront(BwFront bwFront) {
        return bwFrontMapper.updateBwFront(bwFront);
    }

    /**
     * 删除宝沃前端对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteBwFrontByIds(String ids) {
        return bwFrontMapper.deleteBwFrontByIds(Convert.toStrArray(ids));
    }

    @Override
    public String importBwFront(List<BwFront> bwList, Boolean isUpdateSupport, String operName) {
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
                        List<BwFront> listPage = bwList.subList(0, pointsDataLimit);
                        bwFrontMapper.batchInsert(listPage);
                        //剔除
                        bwList.subList(0, pointsDataLimit).clear();
                    }
                    if (!bwList.isEmpty()) {
                        //表示最后剩下的数据
                        bwFrontMapper.batchInsert(bwList);
                    }
                } else {
                    bwFrontMapper.batchInsert(bwList);
                }
            }
//			bwFrontMapper.updateGroupword(null);
            return "导入成功";
        } catch (Exception e) {
            e.printStackTrace();
            return "导入失败";
        }

    }

    /**
     * 查询宝沃展现列表
     *
     * @return
     */
    @Override
    public List<BwZhanXian> showList() {
        List<BwZhanXian> showList = bwFrontMapper.showList();
        for (BwZhanXian bwZhanXian : showList) {
            BigDecimal bigDecimal = new BigDecimal(bwZhanXian.getCtr());
            bwZhanXian.setCtr(bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
            BigDecimal bigDecimal1 = new BigDecimal(bwZhanXian.getCpc());
            bwZhanXian.setCpc(bigDecimal1.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        }
        for (BwZhanXian bwZhanXian : showList) {
            StringBuffer cpc = new StringBuffer();
            cpc.append(String.valueOf(bwZhanXian.getCpc()));
            bwZhanXian.setCpc1(cpc.toString());
            StringBuffer ctr = new StringBuffer();
            BigDecimal bigDecimal = new BigDecimal(bwZhanXian.getCtr());
            BigDecimal multiply =  bigDecimal.setScale(2, BigDecimal.ROUND_FLOOR).multiply(new BigDecimal(100));
            ctr.append(String.valueOf(multiply));
            ctr.append("%");
            bwZhanXian.setCtr1(ctr.toString());
        }
        //处理每月合计
        Map<String, List<BwZhanXian>> collect = showList.stream().collect(Collectors.groupingBy(BwZhanXian::getYearMonth));
        for (Map.Entry<String, List<BwZhanXian>> entry : collect.entrySet()) {
            BwZhanXian bwZhanXian = new BwZhanXian();
            bwZhanXian.setYearMonth(entry.getKey());
            bwZhanXian.setDate("合计");
            int allSumShow = entry.getValue().stream().mapToInt(BwZhanXian::getSumShow).sum();
            int allSumClick = entry.getValue().stream().mapToInt(BwZhanXian::getSumclick).sum();
            double allSumCost = entry.getValue().stream().mapToDouble(BwZhanXian::getSumCost).sum();
            int i = allSumClick /allSumShow ;//ctr
            double v = allSumCost / allSumClick; //cpc
            bwZhanXian.setSumShow(allSumShow);
            bwZhanXian.setSumclick(allSumClick);
            BigDecimal cpc = new BigDecimal(v);
            BigDecimal ctr = new BigDecimal(i);
            BigDecimal multiply = ctr.multiply(new BigDecimal(1000).setScale(2, BigDecimal.ROUND_UNNECESSARY));
            bwZhanXian.setCtr1(String.valueOf(multiply)+"%");
            bwZhanXian.setCpc1(String.valueOf(cpc.setScale(2, BigDecimal.ROUND_HALF_DOWN)));
            bwZhanXian.setSumCost(allSumCost);
            showList.add(bwZhanXian);
        }

        return showList;

    }
}
