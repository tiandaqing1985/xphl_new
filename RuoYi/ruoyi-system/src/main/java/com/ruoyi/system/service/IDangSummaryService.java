package com.ruoyi.system.service;

import com.ruoyi.system.domain.DangSummary;



import java.util.List;

/**
 * 当当前端消费汇总 服务层
 *
 * @author ruoyi
 * @date 2019-07-11
 */
public interface IDangSummaryService {
    /**
     * 查询当当前端消费汇总信息
     *
     * @param id 当当前端消费汇总ID
     * @return 当当前端消费汇总信息
     */
    public DangSummary selectDangdangSummaryById(Integer id);

    /**
     * 查询当当前端消费汇总列表
     *
     * @param dangdangSummary 当当前端消费汇总信息
     * @return 当当前端消费汇总集合
     */
    public List<DangSummary> selectDangdangSummaryList(DangSummary dangdangSummary);

    /**
     * 新增当当前端消费汇总
     *
     * @param dangdangSummary 当当前端消费汇总信息
     * @return 结果
     */
    public int insertDangdangSummary(DangSummary dangdangSummary);

    /**
     * 修改当当前端消费汇总
     *
     * @param dangdangSummary 当当前端消费汇总信息
     * @return 结果
     */
    public int updateDangdangSummary(DangSummary dangdangSummary);

    /**
     * 删除当当前端消费汇总信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDangdangSummaryByIds(String ids);

    public String importBwFront(List<DangSummary> bwList, Boolean isUpdateSupport, String operName);
}
