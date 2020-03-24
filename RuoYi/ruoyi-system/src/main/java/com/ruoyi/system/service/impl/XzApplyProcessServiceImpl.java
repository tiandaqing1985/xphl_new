package com.ruoyi.system.service.impl;

import java.util.*;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.SysRole;
import com.ruoyi.system.domain.XzPurchaseResourceApply;
import com.ruoyi.system.service.ISysRoleService;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.system.service.finance.ApprovalProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.XzApplyProcessMapper;
import com.ruoyi.system.domain.XzApplyProcess;
import com.ruoyi.system.service.IXzApplyProcessService;
import com.ruoyi.common.core.text.Convert;
import org.springframework.transaction.annotation.Transactional;

/**
 * 采购审批流程 服务层实现
 *
 * @author ruoyi
 * @date 2019-12-06
 */
@Service
public class XzApplyProcessServiceImpl implements IXzApplyProcessService {
    @Autowired
    private XzApplyProcessMapper xzApplyProcessMapper;
    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private ISysRoleService sysRoleService;
    @Autowired
    private ApprovalProcessService approvalProcessService;

    /**
     * 查询采购审批流程信息
     *
     * @param id 采购审批流程ID
     * @return 采购审批流程信息
     */
    @Override
    public XzApplyProcess selectXzApplyProcessById(Long id) {
        return xzApplyProcessMapper.selectXzApplyProcessById(id);
    }

    @Override
    public String selectApplyProcessApproverNameByApplyId(Long id) {
        return xzApplyProcessMapper.selectApplyProcessApproverNameByApplyId(id);
    }

    /**
     * 查询采购审批流程列表
     *
     * @param xzApplyProcess 采购审批流程信息
     * @return 采购审批流程集合
     */
    @Override
    public List<XzApplyProcess> selectXzApplyProcessList(XzApplyProcess xzApplyProcess) {
        return xzApplyProcessMapper.selectXzApplyProcessList(xzApplyProcess);
    }

    /**
     * 新增采购审批流程
     *
     * @param xzApplyProcess 采购审批流程信息
     * @return 结果
     */
    @Override
    public int insertXzApplyProcess(XzApplyProcess xzApplyProcess) {
        return xzApplyProcessMapper.insertXzApplyProcess(xzApplyProcess);
    }

    /**
     * 修改采购审批流程
     *
     * @param xzApplyProcess 采购审批流程信息
     * @return 结果
     */
    @Override
    public int updateXzApplyProcess(XzApplyProcess xzApplyProcess) {
        return xzApplyProcessMapper.updateXzApplyProcess(xzApplyProcess);
    }

    /**
     * 删除采购审批流程对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteXzApplyProcessByIds(String ids) {
        return xzApplyProcessMapper.deleteXzApplyProcessByIds(Convert.toStrArray(ids));
    }

    @Override
    public void insertXzApplyProcess(LinkedHashSet<Long> approverIds, Long id) {
        if (approverIds.size() != 0) {

            XzApplyProcess insertVo = new XzApplyProcess();
            insertVo.setStatus("wait");
            insertVo.setApproverId(id.toString());
            insertVo.setApplyId(id);
            Long[] ids = approverIds.toArray(new Long[approverIds.size()]);
            for (int i = 0; i < ids.length; i++) {
                insertVo.setLevel(String.valueOf(i + 1));
                insertVo.setApproverId(ids[i].toString());
                xzApplyProcessMapper.insertXzApplyProcess(insertVo);
            }

        }
    }

    @Override
    public Set<Long> createXzPurchaseResourceProcess(Long id, Long applyUserId, Double amount) {

        LinkedHashSet<Long> approverIds = new LinkedHashSet<>();

        // 获取总的报销申请金额

        Long leaderId = sysUserService.selectApproverIdByApplyerId(applyUserId);
        Long upLeaderId = sysUserService.selectUpApproverIdByApplyerId(applyUserId);
        // 报销审批流开始
        String roleName = "";
        List<String> queryRoleName = sysRoleService.queryRoleName(applyUserId);
        for (String name : queryRoleName) {
            roleName = roleName + "," + name.trim().toLowerCase();
        }
        if (roleName.contains("ceo")) {
            // 是ceo 直接通过

            // 如果为coo申请 交给ceo审批
        } else if (roleName.contains("coo")) {
            approverIds.add(101L);
        }
        // 普通员工
        else {
            if (!roleName.contains("部门leader")) {
                approverIds.add(leaderId);
            }
            // 获取该申请人下的所有领导
            LinkedList<Long> centerId = (LinkedList<Long>) sysUserService.selectCenterIdByUserId(applyUserId);

            if (centerId != null && centerId.size() > 0) {
                for (int i = centerId.size() - 1; i >= 0; i--) {
                    boolean flg = false;
                    if (centerId.get(i).equals(applyUserId)) {
                        continue;
                    }
                    if (centerId.get(i) == leaderId) {
                        if (roleName.contains("部门leader")) {
                            approverIds.add(centerId.get(i));
                        }
                    } else {
                        approverIds.add(centerId.get(i));
                    }
                    List<SysRole> sysRoles = sysRoleService.selectRolesByUserId(centerId.get(i));
                    boolean isCaywu = false;
                    boolean isRenshi = false;
                    boolean isXingzheng = false;
                    for (SysRole role : sysRoles) {
                        if (role.isFlag()) {
                            if (role.getRoleId() == 13) {
                                // 是否财务
                                isCaywu = true;
                            } else if (role.getRoleId().equals(6) || role.getRoleId().equals(3)) {
                                // 是否人事
                                isRenshi = true;
                            } else if (role.getRoleId().equals(16) || role.getRoleId().equals(17)) {
                                // 是否行政
                                isXingzheng = true;
                            }
                        }
                    }

                    if (centerId.get(i).intValue() == 103) { // 如果是审批人是 coo
                        // 直接结束
                        flg = true;
                    } else if (isCaywu || isRenshi) {// 财务审批规则

                        if (amount <= 2000.00) {
                            flg = true;
                        } else if (amount > 2000.00) {
                            approverIds.add(194L);
                            if (amount > 10000.00) {
                                approverIds.add(103L);
                            }
                            flg = true;
                        }
                    } else if (isXingzheng) {
                        if (amount <= 10000) {
                            approverIds.add(194L);
                            flg = true;
                        } else if (amount > 10000.00) {
                            approverIds.add(103L);
                            flg = true;
                        }
                    } else if (centerId.get(i).intValue() == 194) {// 到韩总处

                        if (amount <= 10000.00) {
                            flg = true;
                        }
                    } else {
                    }
                    if (flg) {
                        insertXzApplyProcess(approverIds, id);
                        return approverIds;
                    }
                }
            }
        }
        insertXzApplyProcess(approverIds, id);
        return approverIds;

    }

    /**
     * 转译审批状态码
     * @param status
     * @return
     */
    @Override
    public String transferStatus(String status){

        if(status==null||status.equals("")){
            return "";
        }else if(status.equals("success")){
            return "同意";
        }else if(status.equals("fail")){
            return "驳回";
        }else if (status.equals("wait")){
            return "待审批";
        }else {
            return "";
        }
    }

}
