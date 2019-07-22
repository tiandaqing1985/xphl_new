package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.DangdangBaiduAdd;
import com.ruoyi.system.domain.DangdangPcFront;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 当当pc前端 数据层
 *
 * @author ruoyi
 * @date 2019-07-19
 */
public interface DangdangPcFrontMapper {
    /**
     * 查询当当pc前端信息
     *
     * @param id 当当pc前端ID
     * @return 当当pc前端信息
     */
    public DangdangPcFront selectDangdangPcFrontById(Integer id);

    /**
     * 查询当当pc前端列表
     *
     * @param dangdangPcFront 当当pc前端信息
     * @return 当当pc前端集合
     */
    public List<DangdangPcFront> selectDangdangPcFrontList(DangdangPcFront dangdangPcFront);

    /**
     * 新增当当pc前端
     *
     * @param dangdangPcFront 当当pc前端信息
     * @return 结果
     */
    public int insertDangdangPcFront(DangdangPcFront dangdangPcFront);

    /**
     * 修改当当pc前端
     *
     * @param dangdangPcFront 当当pc前端信息
     * @return 结果
     */
    public int updateDangdangPcFront(DangdangPcFront dangdangPcFront);

    /**
     * 删除当当pc前端
     *
     * @param id 当当pc前端ID
     * @return 结果
     */
    public int deleteDangdangPcFrontById(Integer id);

    /**
     * 批量删除当当pc前端
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDangdangPcFrontByIds(String[] ids);

    void batchInsert(@Param("list") List<DangdangPcFront> listPage);

}