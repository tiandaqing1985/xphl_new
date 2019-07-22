package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.DangdangPcFront;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.DangdangPcMatchMapper;
import com.ruoyi.system.domain.DangdangPcMatch;
import com.ruoyi.system.service.IDangdangPcMatchService;
import com.ruoyi.common.core.text.Convert;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * 当当PC匹配 服务层实现
 *
 * @author ruoyi
 * @date 2019-07-19
 */
@Service
public class DangdangPcMatchServiceImpl implements IDangdangPcMatchService {
    @Autowired
    private DangdangPcMatchMapper dangdangPcMatchMapper;

    /**
     * 查询当当PC匹配信息
     *
     * @param id 当当PC匹配ID
     * @return 当当PC匹配信息
     */
    @Override
    public DangdangPcMatch selectDangdangPcMatchById(Integer id) {
        return dangdangPcMatchMapper.selectDangdangPcMatchById(id);
    }

    /**
     * 查询当当PC匹配列表
     *
     * @param dangdangPcMatch 当当PC匹配信息
     * @return 当当PC匹配集合
     */
    @Override
    public List<DangdangPcMatch> selectDangdangPcMatchList(DangdangPcMatch dangdangPcMatch) {
        return dangdangPcMatchMapper.selectDangdangPcMatchList(dangdangPcMatch);
    }

    /**
     * 新增当当PC匹配
     *
     * @param dangdangPcMatch 当当PC匹配信息
     * @return 结果
     */
    @Override
    public int insertDangdangPcMatch(DangdangPcMatch dangdangPcMatch) {
        return dangdangPcMatchMapper.insertDangdangPcMatch(dangdangPcMatch);
    }

    /**
     * 修改当当PC匹配
     *
     * @param dangdangPcMatch 当当PC匹配信息
     * @return 结果
     */
    @Override
    public int updateDangdangPcMatch(DangdangPcMatch dangdangPcMatch) {
        return dangdangPcMatchMapper.updateDangdangPcMatch(dangdangPcMatch);
    }

    /**
     * 删除当当PC匹配对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteDangdangPcMatchByIds(String ids) {
        return dangdangPcMatchMapper.deleteDangdangPcMatchByIds(Convert.toStrArray(ids));
    }

    @Transactional
    @Override
    public String importBwFront(List<DangdangPcMatch> bwList, Boolean isUpdateSupport, String operName) {
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
                        List<DangdangPcMatch> listPage = bwList.subList(0, pointsDataLimit);
                        dangdangPcMatchMapper.batchInsert(listPage);
                        //剔除
                        bwList.subList(0, pointsDataLimit).clear();
                    }
                    if (!bwList.isEmpty()) {
                        //表示最后剩下的数据
                        dangdangPcMatchMapper.batchInsert(bwList);
                    }
                } else {
                    dangdangPcMatchMapper.batchInsert(bwList);
                }
            }
            dangdangPcMatchMapper.updateBack();
            dangdangPcMatchMapper.updateUv();
            return "导入成功";
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();// catch块中显示让事务回滚，保证数据完整性
            e.printStackTrace();
            return "导入失败";
        }

    }
}
