package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.common.enums.DangDangFileType;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.DangdangAdditional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.DangdangIosAddMapper;
import com.ruoyi.system.domain.DangdangIosAdd;
import com.ruoyi.system.service.IDangdangIosAddService;
import com.ruoyi.common.core.text.Convert;
import org.springframework.transaction.annotation.Transactional;

/**
 * 当当ios后端数据 服务层实现
 *
 * @author ruoyi
 * @date 2019-07-15
 */
@Service
public class DangdangIosAddServiceImpl implements IDangdangIosAddService {
    @Autowired
    private DangdangIosAddMapper dangdangIosAddMapper;

    /**
     * 文件后缀
     */
    private final static String FILE_END = ".xls";

    /**
     * 查询当当ios后端数据信息
     *
     * @param id 当当ios后端数据ID
     * @return 当当ios后端数据信息
     */
    @Override
    public DangdangIosAdd selectDangdangIosAddById(Integer id) {
        return dangdangIosAddMapper.selectDangdangIosAddById(id);
    }

    /**
     * 查询当当ios后端数据列表
     *
     * @param dangdangIosAdd 当当ios后端数据信息
     * @return 当当ios后端数据集合
     */
    @Override
    public List<DangdangIosAdd> selectDangdangIosAddList(DangdangIosAdd dangdangIosAdd) {
        return dangdangIosAddMapper.selectDangdangIosAddList(dangdangIosAdd);
    }

    /**
     * 新增当当ios后端数据
     *
     * @param dangdangIosAdd 当当ios后端数据信息
     * @return 结果
     */
    @Override
    public int insertDangdangIosAdd(DangdangIosAdd dangdangIosAdd) {
        return dangdangIosAddMapper.insertDangdangIosAdd(dangdangIosAdd);
    }

    /**
     * 修改当当ios后端数据
     *
     * @param dangdangIosAdd 当当ios后端数据信息
     * @return 结果
     */
    @Override
    public int updateDangdangIosAdd(DangdangIosAdd dangdangIosAdd) {
        return dangdangIosAddMapper.updateDangdangIosAdd(dangdangIosAdd);
    }

    /**
     * 删除当当ios后端数据对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteDangdangIosAddByIds(String ids) {
        return dangdangIosAddMapper.deleteDangdangIosAddByIds(Convert.toStrArray(ids));
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public String importBwFront(List<DangdangIosAdd> bwList, Boolean isUpdateSupport, String operName, String fileName) throws Exception {
        if (StringUtils.isNull(bwList) || bwList.size() == 0) {
            throw new BusinessException("导入用户数据不能为空！");
        }

        try {
            String flag = "";
            if (null != bwList && bwList.size() > 0) {
                int pointsDataLimit = 10000;//限制条数
                Integer size = bwList.size();
                //判断是否有必要分批
                if (pointsDataLimit < size) {
                    int part = size / pointsDataLimit;//分批数
                    // System.out.println("共有 ： "+size+"条，！"+" 分为 ："+part+"批");
                    //
                    for (int i = 0; i < part; i++) {
                        //1000条
                        List<DangdangIosAdd> listPage = bwList.subList(0, pointsDataLimit);
                        dangdangIosAddMapper.batchInsert(listPage);
                        //剔除
                        bwList.subList(0, pointsDataLimit).clear();
                    }
                    if (!bwList.isEmpty()) {
                        //表示最后剩下的数据
                        dangdangIosAddMapper.batchInsert(bwList);
                    }
                } else {
                    dangdangIosAddMapper.batchInsert(bwList);
                }
            }

            String typeName = fileName.toLowerCase().endsWith(FILE_END)?fileName.substring(0,fileName.length()-4):fileName.substring(0,fileName.length()-5);
            System.out.println("文件名----" + typeName);
            if (typeName.toLowerCase().endsWith(DangDangFileType.SEARCH_IOS.getMsg())) {
                dangdangIosAddMapper.updateIdentification(DangDangFileType.SEARCH_IOS.getMsg());
            } else if (typeName.toLowerCase().endsWith(DangDangFileType.IOS.getMsg())) {
                dangdangIosAddMapper.updateIdentification(DangDangFileType.IOS.getMsg());
            } else {
                throw new BusinessException("表名不符合要求！");

            }
//			dangdangIosAddMapper.updateGroupword(null);
            return "导入成功";

        } catch (BusinessException e) {
            throw new BusinessException(e.getMessage());
        } catch (Exception e) {
            //TODO
            return "导入失败";
        }

    }
}
