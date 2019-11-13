package com.ruoyi.system.service;

import com.ruoyi.system.domain.BwFront;
import com.ruoyi.system.domain.BwZhanXian;
import com.ruoyi.system.domain.JfFront;

import java.util.List;

/**
 * 宝沃前端 服务层
 *
 * @author ruoyi
 * @date 2019-07-09
 */
public interface IBwFrontService {
    /**
     * 查询宝沃前端信息
     *
     * @param id 宝沃前端ID
     * @return 宝沃前端信息
     */
    public BwFront selectBwFrontById(Integer id);

    /**
     * 查询宝沃前端列表
     *
     * @param bwFront 宝沃前端信息
     * @return 宝沃前端集合
     */
    public List<BwFront> selectBwFrontList(BwFront bwFront);

    /**
     * 新增宝沃前端
     *
     * @param bwFront 宝沃前端信息
     * @return 结果
     */
    public int insertBwFront(BwFront bwFront);

    /**
     * 修改宝沃前端
     *
     * @param bwFront 宝沃前端信息
     * @return 结果
     */
    public int updateBwFront(BwFront bwFront);

    /**
     * 删除宝沃前端信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBwFrontByIds(String ids);


    public String importBwFront(List<BwFront> bwList, Boolean isUpdateSupport, String operName);
    /**
     * 查询宝沃展现列表
     * @return
     */
    List<BwZhanXian> showList();


}
