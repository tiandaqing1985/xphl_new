package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.DangdangBackSearch;
import com.ruoyi.system.domain.DangdangPcBack;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 当当pc端搜索广告后端 数据层
 *
 * @author ruoyi
 * @date 2019-07-21
 */
public interface DangdangBackSearchMapper {
    /**
     * 查询当当pc端搜索广告后端信息
     *
     * @param id 当当pc端搜索广告后端ID
     * @return 当当pc端搜索广告后端信息
     */
    public DangdangBackSearch selectDangdangBackSearchById(Integer id);

    /**
     * 查询当当pc端搜索广告后端列表
     *
     * @param dangdangBackSearch 当当pc端搜索广告后端信息
     * @return 当当pc端搜索广告后端集合
     */
    public List<DangdangBackSearch> selectDangdangBackSearchList(DangdangBackSearch dangdangBackSearch);

    /**
     * 新增当当pc端搜索广告后端
     *
     * @param dangdangBackSearch 当当pc端搜索广告后端信息
     * @return 结果
     */
    public int insertDangdangBackSearch(DangdangBackSearch dangdangBackSearch);

    /**
     * 修改当当pc端搜索广告后端
     *
     * @param dangdangBackSearch 当当pc端搜索广告后端信息
     * @return 结果
     */
    public int updateDangdangBackSearch(DangdangBackSearch dangdangBackSearch);

    /**
     * 删除当当pc端搜索广告后端
     *
     * @param id 当当pc端搜索广告后端ID
     * @return 结果
     */
    public int deleteDangdangBackSearchById(Integer id);

    /**
     * 批量删除当当pc端搜索广告后端
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDangdangBackSearchByIds(String[] ids);

    void batchInsert(@Param("list") List<DangdangBackSearch> listPage);

    int updateJoin();


}