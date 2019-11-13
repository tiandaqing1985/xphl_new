package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.DangdangPcMatch;
import com.ruoyi.system.domain.DangdangPzBack;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 当当pc端品专 数据层
 *
 * @author ruoyi
 * @date 2019-07-21
 */
public interface DangdangPzBackMapper {
    /**
     * 查询当当pc端品专信息
     *
     * @param id 当当pc端品专ID
     * @return 当当pc端品专信息
     */
    public DangdangPzBack selectDangdangPzBackById(Integer id);

    /**
     * 查询当当pc端品专列表
     *
     * @param dangdangPzBack 当当pc端品专信息
     * @return 当当pc端品专集合
     */
    public List<DangdangPzBack> selectDangdangPzBackList(DangdangPzBack dangdangPzBack);

    /**
     * 新增当当pc端品专
     *
     * @param dangdangPzBack 当当pc端品专信息
     * @return 结果
     */
    public int insertDangdangPzBack(DangdangPzBack dangdangPzBack);

    /**
     * 修改当当pc端品专
     *
     * @param dangdangPzBack 当当pc端品专信息
     * @return 结果
     */
    public int updateDangdangPzBack(DangdangPzBack dangdangPzBack);

    /**
     * 删除当当pc端品专
     *
     * @param id 当当pc端品专ID
     * @return 结果
     */
    public int deleteDangdangPzBackById(Integer id);

    /**
     * 批量删除当当pc端品专
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDangdangPzBackByIds(String[] ids);

    void batchInsert(@Param("list") List<DangdangPzBack> listPage);


    List<DangdangPzBack> importPz(DangdangPzBack dangdangPzBack);
}