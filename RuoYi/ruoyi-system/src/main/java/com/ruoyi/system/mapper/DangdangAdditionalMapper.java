package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.DangdangAdditional;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 当当后端额外数据（品专，搜索） 数据层
 *
 * @author ruoyi
 * @date 2019-07-12
 */
public interface DangdangAdditionalMapper {
    /**
     * 查询当当后端额外数据（品专，搜索）信息
     *
     * @param id 当当后端额外数据（品专，搜索）ID
     * @return 当当后端额外数据（品专，搜索）信息
     */
    public DangdangAdditional selectDangdangAdditionalById(Integer id);

    /**
     * 查询当当后端额外数据（品专，搜索）列表
     *
     * @param dangdangAdditional 当当后端额外数据（品专，搜索）信息
     * @return 当当后端额外数据（品专，搜索）集合
     */
    public List<DangdangAdditional> selectDangdangAdditionalList(DangdangAdditional dangdangAdditional);

    /**
     * 新增当当后端额外数据（品专，搜索）
     *
     * @param dangdangAdditional 当当后端额外数据（品专，搜索）信息
     * @return 结果
     */
    public int insertDangdangAdditional(DangdangAdditional dangdangAdditional);

    /**
     * 修改当当后端额外数据（品专，搜索）
     *
     * @param dangdangAdditional 当当后端额外数据（品专，搜索）信息
     * @return 结果
     */
    public int updateDangdangAdditional(DangdangAdditional dangdangAdditional);

    /**
     * 删除当当后端额外数据（品专，搜索）
     *
     * @param id 当当后端额外数据（品专，搜索）ID
     * @return 结果
     */
    public int deleteDangdangAdditionalById(Integer id);

    /**
     * 批量删除当当后端额外数据（品专，搜索）
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDangdangAdditionalByIds(String[] ids);

    void batchInsert(@Param("list") List<DangdangAdditional> listPage);

    /**
     * 修改
     *
     * @param str
     * @return
     */
    public int updateGroupword(String str);

    public int updateBaiduIdentification();
}