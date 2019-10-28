package com.ruoyi.system.service.finance;
import java.util.List;

import com.ruoyi.system.domain.finance.FacNumberTable;

/**
 * 财务编号 服务层
 * 
 * @author ruoyi
 * @date 2019-10-25
 */
public interface IFacNumberTableService {
	/**
	 * 查询财务编号信息
	 * 
	 * @param id
	 *            财务编号ID
	 * @return 财务编号信息
	 */
	public FacNumberTable selectFacNumberTableById(Long id);

	/**
	 * 查询财务编号列表
	 * 
	 * @param facNumberTable
	 *            财务编号信息
	 * @return 财务编号集合
	 */
	public List<FacNumberTable> selectFacNumberTableList(
			FacNumberTable facNumberTable);

	/**
	 * 新增财务编号
	 * 
	 * @param facNumberTable
	 *            财务编号信息
	 * @return 结果
	 */
	public int insertFacNumberTable(FacNumberTable facNumberTable);

	/**
	 * 修改财务编号
	 * 
	 * @param facNumberTable
	 *            财务编号信息
	 * @return 结果
	 */
	public int updateFacNumberTable(FacNumberTable facNumberTable);

	/**
	 * 删除财务编号信息
	 * 
	 * @param ids
	 *            需要删除的数据ID
	 * @return 结果
	 */
	public int deleteFacNumberTableByIds(String ids);

	public String getNum(String head, String time);

}
