package com.ruoyi.system.service;

import com.ruoyi.system.domain.DangdangAndroidAdd;
import com.ruoyi.system.domain.DangdangSearchIosAdd;

import java.util.List;

/**
 * 当当安卓后端数据 服务层
 *
 * @author ruoyi
 * @date 2019-07-15
 */
public interface IDangdangAndroidAddService {
    /**
     * 查询当当安卓后端数据信息
     *
     * @param id 当当安卓后端数据ID
     * @return 当当安卓后端数据信息
     */
    public DangdangAndroidAdd selectDangdangAndroidAddById(Integer id);

    /**
     * 查询当当安卓后端数据列表
     *
     * @param dangdangAndroidAdd 当当安卓后端数据信息
     * @return 当当安卓后端数据集合
     */
    public List<DangdangAndroidAdd> selectDangdangAndroidAddList(DangdangAndroidAdd dangdangAndroidAdd);

    /**
     * 新增当当安卓后端数据
     *
     * @param dangdangAndroidAdd 当当安卓后端数据信息
     * @return 结果
     */
    public int insertDangdangAndroidAdd(DangdangAndroidAdd dangdangAndroidAdd);

    /**
     * 修改当当安卓后端数据
     *
     * @param dangdangAndroidAdd 当当安卓后端数据信息
     * @return 结果
     */
    public int updateDangdangAndroidAdd(DangdangAndroidAdd dangdangAndroidAdd);

    /**
     * 删除当当安卓后端数据信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDangdangAndroidAddByIds(String ids);

    public String importBwFront(List<DangdangAndroidAdd> bwList, Boolean isUpdateSupport, String operName);
}
