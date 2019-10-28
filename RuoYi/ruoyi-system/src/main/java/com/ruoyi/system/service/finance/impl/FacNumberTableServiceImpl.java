package com.ruoyi.system.service.finance.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.system.domain.finance.FacNumberTable;
import com.ruoyi.system.mapper.finance.FacNumberTableMapper;
import com.ruoyi.system.service.finance.IFacNumberTableService;

/**
 * 财务编号 服务层实现
 * 
 * @author ruoyi
 * @date 2019-10-25
 */
@Service
public class FacNumberTableServiceImpl implements IFacNumberTableService {
	@Autowired
	private FacNumberTableMapper facNumberTableMapper;

	/**
	 * 查询财务编号信息
	 * 
	 * @param id
	 *            财务编号ID
	 * @return 财务编号信息
	 */
	@Override
	public FacNumberTable selectFacNumberTableById(Long id) {
		return facNumberTableMapper.selectFacNumberTableById(id);
	}

	/**
	 * 查询财务编号列表
	 * 
	 * @param facNumberTable
	 *            财务编号信息
	 * @return 财务编号集合
	 */
	@Override
	public List<FacNumberTable> selectFacNumberTableList(
			FacNumberTable facNumberTable) {
		return facNumberTableMapper.selectFacNumberTableList(facNumberTable);
	}

	/**
	 * 新增财务编号
	 * 
	 * @param facNumberTable
	 *            财务编号信息
	 * @return 结果
	 */
	@Override
	public int insertFacNumberTable(FacNumberTable facNumberTable) {
		return facNumberTableMapper.insertFacNumberTable(facNumberTable);
	}

	/**
	 * 修改财务编号
	 * 
	 * @param facNumberTable
	 *            财务编号信息
	 * @return 结果
	 */
	@Override
	public int updateFacNumberTable(FacNumberTable facNumberTable) {
		return facNumberTableMapper.updateFacNumberTable(facNumberTable);
	}

	/**
	 * 删除财务编号对象
	 * 
	 * @param ids
	 *            需要删除的数据ID
	 * @return 结果
	 */
	@Override
	public int deleteFacNumberTableByIds(String ids) {
		return facNumberTableMapper
				.deleteFacNumberTableByIds(Convert.toStrArray(ids));
	}

	@Override
	public String getNum(String head, String time) {
		FacNumberTable facNumberTable = new FacNumberTable();
		facNumberTable.setHead(head);
		facNumberTable.setTime(time);
		List<FacNumberTable> facNum = facNumberTableMapper
				.selectFacNumberTableList(facNumberTable);
		if (facNum == null||facNum.size()==0) {
			facNumberTable.setNumber(0001);
			facNumberTable.setNum(head + time + "0001");
			facNumberTableMapper.insertFacNumberTable(facNumberTable);
			return head + time + "0001";
		} else {
			int number = facNumberTableMapper.selectNumber(facNumberTable).getNumber() + 1;
			FacNumberTable fac = new FacNumberTable();
			fac.setNum(head + time + String.format("%04d", number));
			fac.setHead(head);
			fac.setTime(time);
			fac.setNumber(number);
			facNumberTableMapper.insertFacNumberTable(fac); 
			return head + time + String.format("%04d", number); 
		} 
	}

}
