package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.DangdangIosAdd;
import com.ruoyi.system.domain.DangdangSearchAdd;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 当当ios后端数据 数据层
 *
 * @author ruoyi
 * @date 2019-07-15
 */
public interface DangdangIosAddMapper {
    /**
     * 查询当当ios后端数据信息
     *
     * @param id 当当ios后端数据ID
     * @return 当当ios后端数据信息
     */
    public DangdangIosAdd selectDangdangIosAddById(Integer id);

    /**
     * 查询当当ios后端数据列表
     *
     * @param dangdangIosAdd 当当ios后端数据信息
     * @return 当当ios后端数据集合
     */
    public List<DangdangIosAdd> selectDangdangIosAddList(DangdangIosAdd dangdangIosAdd);

    /**
     * 新增当当ios后端数据
     *
     * @param dangdangIosAdd 当当ios后端数据信息
     * @return 结果
     */
    public int insertDangdangIosAdd(DangdangIosAdd dangdangIosAdd);

    /**
     * 修改当当ios后端数据
     *
     * @param dangdangIosAdd 当当ios后端数据信息
     * @return 结果
     */
    public int updateDangdangIosAdd(DangdangIosAdd dangdangIosAdd);

    /**
     * 删除当当ios后端数据
     *
     * @param id 当当ios后端数据ID
     * @return 结果
     */
    public int deleteDangdangIosAddById(Integer id);

    /**
     * 批量删除当当ios后端数据
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDangdangIosAddByIds(String[] ids);

    void batchInsert(@Param("list") List<DangdangIosAdd> listPage);

    /**
     * 修改
     *
     * @param str
     * @return
     */
    public int updateGroupword(String str);

    public int updateIdentification(String str);
}