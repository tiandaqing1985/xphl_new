package com.ruoyi.system.service;

import com.ruoyi.system.domain.DangdangAdditional;
import com.ruoyi.system.domain.DangdangSearchAdd;

import java.util.List;

/**
 * 当当后端数据（搜索） 服务层
 *
 * @author ruoyi
 * @date 2019-07-12
 */
public interface IDangdangSearchAddService {
    /**
     * 查询当当后端数据（搜索）信息
     *
     * @param id 当当后端数据（搜索）ID
     * @return 当当后端数据（搜索）信息
     */
    public DangdangSearchAdd selectDangdangSearchAddById(Integer id);

    /**
     * 查询当当后端数据（搜索）列表
     *
     * @param dangdangSearchAdd 当当后端数据（搜索）信息
     * @return 当当后端数据（搜索）集合
     */
    public List<DangdangSearchAdd> selectDangdangSearchAddList(DangdangSearchAdd dangdangSearchAdd);

    /**
     * 新增当当后端数据（搜索）
     *
     * @param dangdangSearchAdd 当当后端数据（搜索）信息
     * @return 结果
     */
    public int insertDangdangSearchAdd(DangdangSearchAdd dangdangSearchAdd);

    /**
     * 修改当当后端数据（搜索）
     *
     * @param dangdangSearchAdd 当当后端数据（搜索）信息
     * @return 结果
     */
    public int updateDangdangSearchAdd(DangdangSearchAdd dangdangSearchAdd);

    /**
     * 删除当当后端数据（搜索）信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDangdangSearchAddByIds(String ids);

    public String importBwFront(List<DangdangSearchAdd> bwList, Boolean isUpdateSupport, String operName);
}
