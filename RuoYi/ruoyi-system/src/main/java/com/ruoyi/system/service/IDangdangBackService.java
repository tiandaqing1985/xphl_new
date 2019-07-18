package com.ruoyi.system.service;

import com.ruoyi.system.domain.DangDangAll;
import com.ruoyi.system.domain.DangdangBack;
import com.ruoyi.system.domain.DangdangBaiduAdd;

import java.util.List;

/**
 * 当当后端汇总 服务层
 *
 * @author ruoyi
 * @date 2019-07-18
 */
public interface IDangdangBackService {
    /**
     * 查询当当后端汇总信息
     *
     * @param id 当当后端汇总ID
     * @return 当当后端汇总信息
     */
    public DangdangBack selectDangdangBackById(Integer id);

    /**
     * 查询当当后端汇总列表
     *
     * @param dangdangBack 当当后端汇总信息
     * @return 当当后端汇总集合
     */
    public List<DangdangBack> selectDangdangBackList(DangdangBack dangdangBack);

    /**
     * 新增当当后端汇总
     *
     * @param dangdangBack 当当后端汇总信息
     * @return 结果
     */
    public int insertDangdangBack(DangdangBack dangdangBack);

    /**
     * 修改当当后端汇总
     *
     * @param dangdangBack 当当后端汇总信息
     * @return 结果
     */
    public int updateDangdangBack(DangdangBack dangdangBack);

    /**
     * 删除当当后端汇总信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDangdangBackByIds(String ids);

    String importBwFront(List<DangdangBack> bwList, Boolean isUpdateSupport, String operName,String fileName);

}
