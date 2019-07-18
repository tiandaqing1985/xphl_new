package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.DangdangMatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.DangdangAppFrontMapper;
import com.ruoyi.system.domain.DangdangAppFront;
import com.ruoyi.system.service.IDangdangAppFrontService;
import com.ruoyi.common.core.text.Convert;
import org.springframework.transaction.annotation.Transactional;

/**
 * 当当APP前端 服务层实现
 *
 * @author ruoyi
 * @date 2019-07-11
 */
@Service
public class DangdangAppFrontServiceImpl implements IDangdangAppFrontService {
    @Autowired
    private DangdangAppFrontMapper dangdangAppFrontMapper;

    /**
     * 查询当当APP前端信息
     *
     * @param id 当当APP前端ID
     * @return 当当APP前端信息
     */
    @Override
    public DangdangAppFront selectDangdangAppFrontById(Integer id) {
        return dangdangAppFrontMapper.selectDangdangAppFrontById(id);
    }

    /**
     * 查询当当APP前端列表
     *
     * @param dangdangAppFront 当当APP前端信息
     * @return 当当APP前端集合
     */
    @Override
    public List<DangdangAppFront> selectDangdangAppFrontList(DangdangAppFront dangdangAppFront) {
        return dangdangAppFrontMapper.selectDangdangAppFrontList(dangdangAppFront);
    }

    /**
     * 新增当当APP前端
     *
     * @param dangdangAppFront 当当APP前端信息
     * @return 结果
     */
    @Override
    public int insertDangdangAppFront(DangdangAppFront dangdangAppFront) {
        return dangdangAppFrontMapper.insertDangdangAppFront(dangdangAppFront);
    }

    /**
     * 修改当当APP前端
     *
     * @param dangdangAppFront 当当APP前端信息
     * @return 结果
     */
    @Override
    public int updateDangdangAppFront(DangdangAppFront dangdangAppFront) {
        return dangdangAppFrontMapper.updateDangdangAppFront(dangdangAppFront);
    }

    /**
     * 删除当当APP前端对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteDangdangAppFrontByIds(String ids) {
        return dangdangAppFrontMapper.deleteDangdangAppFrontByIds(Convert.toStrArray(ids));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String importBwFront(List<DangdangAppFront> bwList, Boolean isUpdateSupport, String operName, String fileName) {
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
                        List<DangdangAppFront> listPage = bwList.subList(0, pointsDataLimit);
                        dangdangAppFrontMapper.batchInsert(listPage);
                        //剔除
                        bwList.subList(0, pointsDataLimit).clear();
                    }
                    if (!bwList.isEmpty()) {
                        //表示最后剩下的数据
                        dangdangAppFrontMapper.batchInsert(bwList);
                    }
                } else {
                    dangdangAppFrontMapper.batchInsert(bwList);
                }
            }
            dangdangAppFrontMapper.updateGroupword(null);
            return "导入成功";
        } catch (Exception e) {
            return "导入失败";
        }
    }
}
