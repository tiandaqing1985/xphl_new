package com.ruoyi.system.mapper.finance;



import com.ruoyi.system.domain.finance.DeptUser;
import com.ruoyi.system.domain.finance.FacSysUserApproval;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface ApprovalProcessMapper {

     int insert(FacSysUserApproval facSysUserApproval);

     DeptUser querySecondLevelDept(@Param("userId") long userId);
     DeptUser queryFirstLevelDept(long userId);

     /**
      * 根据登录人角色
      * @param userId
      * @return
      */
     List<String> queryRoleName(@Param("userId") Long userId);

     Long queryHighDeptLeaderId(@Param("userId") Long userId);


     List<FacSysUserApproval> selectFacUserApprovalList(FacSysUserApproval facSysUserApproval);
}
