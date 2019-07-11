package com.ruoyi.system.service;

import com.ruoyi.system.domain.DangdangAppletsFront;
import com.ruoyi.system.domain.DangdangSummary;

import java.util.List;

/**
 * 当当前端消费汇总 服务层
 *
 * @author ruoyi
 * @date 2019-07-11
 */
public interface IDangdangSummaryService {
    /**
     * 查询当当前端消费汇总信息
     *
     * @param id 当当前端消费汇总ID
     * @return 当当前端消费汇总信息
     */
    public DangdangSummary selectDangdangSummaryById(Integer id);

    /**
     * 查询当当前端消费汇总列表
     *
     * @param dangdangSummary 当当前端消费汇总信息
     * @return 当当前端消费汇总集合
     */
    public List<DangdangSummary> selectDangdangSummaryList(DangdangSummary dangdangSummary);

    /**
     * 新增当当前端消费汇总
     *
     * @param dangdangSummary 当当前端消费汇总信息
     * @return 结果
     */
    public int insertDangdangSummary(DangdangSummary dangdangSummary);

    /**
     * 修改当当前端消费汇总
     *
     * @param dangdangSummary 当当前端消费汇总信息
     * @return 结果
     */
    public int updateDangdangSummary(DangdangSummary dangdangSummary);

    /**
     * 删除当当前端消费汇总信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDangdangSummaryByIds(String ids);

    public String importBwFront(List<DangdangSummary> bwList, Boolean isUpdateSupport, String operName);
}
