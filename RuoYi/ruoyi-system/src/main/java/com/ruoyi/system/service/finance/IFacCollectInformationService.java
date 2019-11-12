package com.ruoyi.system.service.finance;

import com.ruoyi.system.domain.finance.FacCollectInformation;

import java.util.List;

/**
 * 团建费报销 服务层
 *
 * @author ruoyi
 * @date 2019-11-07
 */
public interface IFacCollectInformationService {
    /**
     * 查询团建费报销信息
     *
     * @param id 团建费报销ID
     * @return 团建费报销信息
     */
    public FacCollectInformation selectFacCollectInformationById(Long id);

    /**
     * 查询团建费报销列表
     *
     * @param facCollectInformation 团建费报销信息
     * @return 团建费报销集合
     */
    public List<FacCollectInformation> selectFacCollectInformationList(FacCollectInformation facCollectInformation);

    /**
     * 新增团建费报销
     *
     * @param facCollectInformation 团建费报销信息
     * @return 结果
     */
    public int insertFacCollectInformation(FacCollectInformation facCollectInformation);

    /**
     * 修改团建费报销
     *
     * @param facCollectInformation 团建费报销信息
     * @return 结果
     */
    public int updateFacCollectInformation(FacCollectInformation facCollectInformation);

    /**
     * 删除团建费报销信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteFacCollectInformationByIds(String ids);

    public double selectAmount(String num);

    public double selectMoney(String num);


}
