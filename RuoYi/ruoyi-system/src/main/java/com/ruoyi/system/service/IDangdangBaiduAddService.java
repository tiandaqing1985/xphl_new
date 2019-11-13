package com.ruoyi.system.service;

import com.ruoyi.system.domain.DangdangBaiduAdd;
import com.ruoyi.system.domain.DangdangSearchAdd;

import java.util.List;

/**
 * 当当后端百度小程序数据 服务层
 *
 * @author ruoyi
 * @date 2019-07-12
 */
public interface IDangdangBaiduAddService {
    /**
     * 查询当当后端百度小程序数据信息
     *
     * @param id 当当后端百度小程序数据ID
     * @return 当当后端百度小程序数据信息
     */
    public DangdangBaiduAdd selectDangdangBaiduAddById(Integer id);

    /**
     * 查询当当后端百度小程序数据列表
     *
     * @param dangdangBaiduAdd 当当后端百度小程序数据信息
     * @return 当当后端百度小程序数据集合
     */
    public List<DangdangBaiduAdd> selectDangdangBaiduAddList(DangdangBaiduAdd dangdangBaiduAdd);

    /**
     * 新增当当后端百度小程序数据
     *
     * @param dangdangBaiduAdd 当当后端百度小程序数据信息
     * @return 结果
     */
    public int insertDangdangBaiduAdd(DangdangBaiduAdd dangdangBaiduAdd);

    /**
     * 修改当当后端百度小程序数据
     *
     * @param dangdangBaiduAdd 当当后端百度小程序数据信息
     * @return 结果
     */
    public int updateDangdangBaiduAdd(DangdangBaiduAdd dangdangBaiduAdd);

    /**
     * 删除当当后端百度小程序数据信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDangdangBaiduAddByIds(String ids);

    public String importBwFront(List<DangdangBaiduAdd> bwList, Boolean isUpdateSupport, String operName);

}
