package com.ruoyi.system.service;

import com.ruoyi.system.domain.DangdangPcFront;
import com.ruoyi.system.domain.DangdangPcMatch;

import java.util.List;

/**
 * 当当PC匹配 服务层
 *
 * @author ruoyi
 * @date 2019-07-19
 */
public interface IDangdangPcMatchService {
    /**
     * 查询当当PC匹配信息
     *
     * @param id 当当PC匹配ID
     * @return 当当PC匹配信息
     */
    public DangdangPcMatch selectDangdangPcMatchById(Integer id);

    /**
     * 查询当当PC匹配列表
     *
     * @param dangdangPcMatch 当当PC匹配信息
     * @return 当当PC匹配集合
     */
    public List<DangdangPcMatch> selectDangdangPcMatchList(DangdangPcMatch dangdangPcMatch);

    /**
     * 新增当当PC匹配
     *
     * @param dangdangPcMatch 当当PC匹配信息
     * @return 结果
     */
    public int insertDangdangPcMatch(DangdangPcMatch dangdangPcMatch);

    /**
     * 修改当当PC匹配
     *
     * @param dangdangPcMatch 当当PC匹配信息
     * @return 结果
     */
    public int updateDangdangPcMatch(DangdangPcMatch dangdangPcMatch);

    /**
     * 删除当当PC匹配信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDangdangPcMatchByIds(String ids);

    public String importBwFront(List<DangdangPcMatch> bwList, Boolean isUpdateSupport, String operName);

}
