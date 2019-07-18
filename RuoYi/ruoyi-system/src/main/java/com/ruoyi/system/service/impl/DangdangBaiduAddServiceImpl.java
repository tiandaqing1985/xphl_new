package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.DangdangSearchAdd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.DangdangBaiduAddMapper;
import com.ruoyi.system.domain.DangdangBaiduAdd;
import com.ruoyi.system.service.IDangdangBaiduAddService;
import com.ruoyi.common.core.text.Convert;

/**
 * 当当后端百度小程序数据 服务层实现
 *
 * @author ruoyi
 * @date 2019-07-12
 */
@Service
public class DangdangBaiduAddServiceImpl implements IDangdangBaiduAddService {
    @Autowired
    private DangdangBaiduAddMapper dangdangBaiduAddMapper;

    /**
     * 查询当当后端百度小程序数据信息
     *
     * @param id 当当后端百度小程序数据ID
     * @return 当当后端百度小程序数据信息
     */
    @Override
    public DangdangBaiduAdd selectDangdangBaiduAddById(Integer id) {
        return dangdangBaiduAddMapper.selectDangdangBaiduAddById(id);
    }

    /**
     * 查询当当后端百度小程序数据列表
     *
     * @param dangdangBaiduAdd 当当后端百度小程序数据信息
     * @return 当当后端百度小程序数据集合
     */
    @Override
    public List<DangdangBaiduAdd> selectDangdangBaiduAddList(DangdangBaiduAdd dangdangBaiduAdd) {
        return dangdangBaiduAddMapper.selectDangdangBaiduAddList(dangdangBaiduAdd);
    }

    /**
     * 新增当当后端百度小程序数据
     *
     * @param dangdangBaiduAdd 当当后端百度小程序数据信息
     * @return 结果
     */
    @Override
    public int insertDangdangBaiduAdd(DangdangBaiduAdd dangdangBaiduAdd) {
        return dangdangBaiduAddMapper.insertDangdangBaiduAdd(dangdangBaiduAdd);
    }

    /**
     * 修改当当后端百度小程序数据
     *
     * @param dangdangBaiduAdd 当当后端百度小程序数据信息
     * @return 结果
     */
    @Override
    public int updateDangdangBaiduAdd(DangdangBaiduAdd dangdangBaiduAdd) {
        return dangdangBaiduAddMapper.updateDangdangBaiduAdd(dangdangBaiduAdd);
    }

    /**
     * 删除当当后端百度小程序数据对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteDangdangBaiduAddByIds(String ids) {
        return dangdangBaiduAddMapper.deleteDangdangBaiduAddByIds(Convert.toStrArray(ids));
    }

    @Override
    public String importBwFront(List<DangdangBaiduAdd> bwList, Boolean isUpdateSupport, String operName) {
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
                        List<DangdangBaiduAdd> listPage = bwList.subList(0, pointsDataLimit);
                        dangdangBaiduAddMapper.batchInsert(listPage);
                        //剔除
                        bwList.subList(0, pointsDataLimit).clear();
                    }
                    if (!bwList.isEmpty()) {
                        //表示最后剩下的数据
                        dangdangBaiduAddMapper.batchInsert(bwList);
                    }
                } else {
                    dangdangBaiduAddMapper.batchInsert(bwList);
                }
            }
            dangdangBaiduAddMapper.updateGroupword(null);
            return "导入成功";
        } catch (Exception e) {
            e.printStackTrace();
            return "导入失败";
        }

    }
}
