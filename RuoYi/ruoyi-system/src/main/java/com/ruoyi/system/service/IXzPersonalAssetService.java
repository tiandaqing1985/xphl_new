package com.ruoyi.system.service;

import com.ruoyi.system.domain.XzPersonalAsset;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 个人资产 服务层
 *
 * @author ruoyi
 * @date 2019-08-27
 */
public interface IXzPersonalAssetService {
    /**
     * 查询个人资产信息
     *
     * @param id 个人资产ID
     * @return 个人资产信息
     */
    public XzPersonalAsset selectXzPersonalAssetById(Long id);

    /**
     * 查询个人资产列表
     *
     * @param xzPersonalAsset 个人资产信息
     * @return 个人资产集合
     */
    public List<XzPersonalAsset> selectXzPersonalAssetList(XzPersonalAsset xzPersonalAsset);

    /**
     * 新增个人资产
     *
     * @param xzPersonalAsset 个人资产信息
     * @return 结果
     */
    public int insertXzPersonalAsset(XzPersonalAsset xzPersonalAsset);

    /**
     * 修改个人资产
     *
     * @param xzPersonalAsset 个人资产信息
     * @return 结果
     */
    public int updateXzPersonalAsset(XzPersonalAsset xzPersonalAsset);

    /**
     * 删除个人资产信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteXzPersonalAssetByIds(String ids);

    List<XzPersonalAsset> selectXzPersonalAssetByUserIds(Map map);
}
