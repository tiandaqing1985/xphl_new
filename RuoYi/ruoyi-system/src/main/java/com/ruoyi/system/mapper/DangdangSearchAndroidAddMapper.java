package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.DangdangSearchAndroidAdd;
import com.ruoyi.system.domain.DangdangSearchIosAdd;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 当当搜索安卓后端数据 数据层
 *
 * @author ruoyi
 * @date 2019-07-15
 */
public interface DangdangSearchAndroidAddMapper {
    /**
     * 查询当当搜索安卓后端数据信息
     *
     * @param id 当当搜索安卓后端数据ID
     * @return 当当搜索安卓后端数据信息
     */
    public DangdangSearchAndroidAdd selectDangdangSearchAndroidAddById(Integer id);

    /**
     * 查询当当搜索安卓后端数据列表
     *
     * @param dangdangSearchAndroidAdd 当当搜索安卓后端数据信息
     * @return 当当搜索安卓后端数据集合
     */
    public List<DangdangSearchAndroidAdd> selectDangdangSearchAndroidAddList(DangdangSearchAndroidAdd dangdangSearchAndroidAdd);

    /**
     * 新增当当搜索安卓后端数据
     *
     * @param dangdangSearchAndroidAdd 当当搜索安卓后端数据信息
     * @return 结果
     */
    public int insertDangdangSearchAndroidAdd(DangdangSearchAndroidAdd dangdangSearchAndroidAdd);

    /**
     * 修改当当搜索安卓后端数据
     *
     * @param dangdangSearchAndroidAdd 当当搜索安卓后端数据信息
     * @return 结果
     */
    public int updateDangdangSearchAndroidAdd(DangdangSearchAndroidAdd dangdangSearchAndroidAdd);

    /**
     * 删除当当搜索安卓后端数据
     *
     * @param id 当当搜索安卓后端数据ID
     * @return 结果
     */
    public int deleteDangdangSearchAndroidAddById(Integer id);

    /**
     * 批量删除当当搜索安卓后端数据
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDangdangSearchAndroidAddByIds(String[] ids);

    void batchInsert(@Param("list") List<DangdangSearchAndroidAdd> listPage);

    /**
     * 修改
     *
     * @param str
     * @return
     */
    public int updateGroupword(String str);
}