package com.ruoyi.system.service;

import com.ruoyi.system.domain.DangDangAll;
import com.ruoyi.system.domain.DangDangPcAll;
import com.ruoyi.system.domain.DangdangBaiduAdd;
import com.ruoyi.system.domain.DangdangPcFront;

import java.util.List;

/**
 * 当当pc前端 服务层
 *
 * @author ruoyi
 * @date 2019-07-19
 */
public interface IDangdangPcFrontService {
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
    public List<DangDangPcAll> selectDangdangPcFrontList(DangdangPcFront dangdangPcFront);

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
     * 删除当当pc前端信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDangdangPcFrontByIds(String ids);

    public String importBwFront(List<DangdangPcFront> bwList, Boolean isUpdateSupport, String operName);

}
