package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.common.enums.DangDangFileType;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.DangdangAdditionalMapper;
import com.ruoyi.system.domain.DangdangAdditional;
import com.ruoyi.system.service.IDangdangAdditionalService;
import com.ruoyi.common.core.text.Convert;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * 当当后端额外数据（品专，搜索） 服务层实现
 *
 * @author ruoyi
 * @date 2019-07-12
 */
@Service
public class DangdangAdditionalServiceImpl implements IDangdangAdditionalService {
    @Autowired
    private DangdangAdditionalMapper dangdangAdditionalMapper;

    /**
     * 文件后缀
     */
    private final static String FILE_END = ".xls";

    /**
     * 查询当当后端额外数据（品专，搜索）信息
     *
     * @param id 当当后端额外数据（品专，搜索）ID
     * @return 当当后端额外数据（品专，搜索）信息
     */
    @Override
    public DangdangAdditional selectDangdangAdditionalById(Integer id) {
        return dangdangAdditionalMapper.selectDangdangAdditionalById(id);
    }

    /**
     * 查询当当后端额外数据（品专，搜索）列表
     *
     * @param dangdangAdditional 当当后端额外数据（品专，搜索）信息
     * @return 当当后端额外数据（品专，搜索）集合
     */
    @Override
    public List<DangdangAdditional> selectDangdangAdditionalList(DangdangAdditional dangdangAdditional) {
        return dangdangAdditionalMapper.selectDangdangAdditionalList(dangdangAdditional);
    }

    /**
     * 新增当当后端额外数据（品专，搜索）
     *
     * @param dangdangAdditional 当当后端额外数据（品专，搜索）信息
     * @return 结果
     */
    @Override
    public int insertDangdangAdditional(DangdangAdditional dangdangAdditional) {
        return dangdangAdditionalMapper.insertDangdangAdditional(dangdangAdditional);
    }

    /**
     * 修改当当后端额外数据（品专，搜索）
     *
     * @param dangdangAdditional 当当后端额外数据（品专，搜索）信息
     * @return 结果
     */
    @Override
    public int updateDangdangAdditional(DangdangAdditional dangdangAdditional) {
        return dangdangAdditionalMapper.updateDangdangAdditional(dangdangAdditional);
    }

    /**
     * 删除当当后端额外数据（品专，搜索）对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteDangdangAdditionalByIds(String ids) {
        return dangdangAdditionalMapper.deleteDangdangAdditionalByIds(Convert.toStrArray(ids));
    }


    @Override
    public String importBwFront(List<DangdangAdditional> bwList, Boolean isUpdateSupport, String operName, String fileName) {
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
                        List<DangdangAdditional> listPage = bwList.subList(0, pointsDataLimit);
                        //剔除
                        bwList.subList(0, pointsDataLimit).clear();
                    }
                    if (!bwList.isEmpty()) {
                        //表示最后剩下的数据
                        dangdangAdditionalMapper.batchInsert(bwList);
                    }
                } else {
                    dangdangAdditionalMapper.batchInsert(bwList);
                }
            }
            dangdangAdditionalMapper.updateGroupword(null);
            return "导入成功";
        } catch (Exception e) {
            e.printStackTrace();
            return "导入失败";
        }
    }


    @Transactional
    public String importBwFrontAdditional(List<DangdangAdditional> bwList, Boolean isUpdateSupport, String operName, String fileName) {
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
                        List<DangdangAdditional> listPage = bwList.subList(0, pointsDataLimit);
                        //剔除
                        bwList.subList(0, pointsDataLimit).clear();
                    }
                    if (!bwList.isEmpty()) {
                        //表示最后剩下的数据
                        dangdangAdditionalMapper.batchInsert(bwList);
                    }
                } else {
                    dangdangAdditionalMapper.batchInsert(bwList);
                }
            }

            //区分各分类
            String typeName = distinguishEndWith(fileName).toLowerCase();
            //品专
            if (typeName.endsWith(DangDangFileType.ADDITONAL.getMsg())) {
                dangdangAdditionalMapper.updateGroupword(DangDangFileType.ADDITONAL.getMsg());
                return "导入成功";
                //搜索
            } else if (typeName.endsWith(DangDangFileType.SEARCH.getMsg())) {
                dangdangAdditionalMapper.updateGroupword(DangDangFileType.SEARCH.getMsg());
                return "导入成功";
                //搜索安卓
            } else if (typeName.endsWith(DangDangFileType.SEARCH_ANDROID.getMsg())) {
                dangdangAdditionalMapper.updateGroupword(DangDangFileType.SEARCH_ANDROID.getMsg());
                return "导入成功";
                //安卓
            } else if (typeName.endsWith(DangDangFileType.ANDROID.getMsg())) {
                dangdangAdditionalMapper.updateGroupword(DangDangFileType.ANDROID.getMsg());
                return "导入成功";
                //百度小程序
            } else if (typeName.endsWith(DangDangFileType.BAIDU.getMsg())) {
                dangdangAdditionalMapper.updateGroupword(DangDangFileType.BAIDU.getMsg());
                dangdangAdditionalMapper.updateBaiduIdentification();
                return "导入成功";
            } else {
                throw new BusinessException("导入表名称不符");
            }
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();// catch块中显示让事务回滚，保证数据完整性
            e.printStackTrace();
            return "导入失败";
        }
    }

    /**
     * 去掉文件后缀 .xls .xlsx
     *
     * @param fileName
     * @return
     */
    private String distinguishEndWith(String fileName) {
        return fileName.toLowerCase().endsWith(FILE_END) ? fileName.substring(0, fileName.length() - 4) : fileName.substring(0, fileName.length() - 5);
    }
}
