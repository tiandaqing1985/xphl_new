package com.ruoyi.system.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.OaDingdingMapper;
import com.ruoyi.system.mapper.OaDingdingUserMapper;
import com.ruoyi.system.mapper.OaOutMapper;
import com.ruoyi.system.mapper.SysDeptMapper;
import com.ruoyi.system.mapper.SysUserMapper;
import com.ruoyi.system.mapper.SysUserRoleMapper;
import com.ruoyi.system.mapper.UserApplyMapper;
import com.ruoyi.system.domain.Dingding;
import com.ruoyi.system.domain.OaDingding;
import com.ruoyi.system.domain.OaDingdingUser;
import com.ruoyi.system.domain.OaOut;
import com.ruoyi.system.domain.SysDept;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.domain.UserApply;
import com.ruoyi.system.service.IOaDingdingService;

/**
 * 钉钉考勤数据 服务层实现
 * 
 * @author ruoyi
 * @date 2019-07-26
 */
@Service
public class OaDingdingServiceImpl implements IOaDingdingService 
{
	private static final Set<SysDept> dSet = new TreeSet<SysDept>();  //部门集合                                                                   

	@Autowired
	private OaDingdingMapper oaDingdingMapper;
	@Autowired
    private SysUserMapper userMapper;
	@Autowired
    private SysDeptMapper deptMapper;
    @Autowired
    private SysUserRoleMapper userRoleMapper;
    @Autowired
    private UserApplyMapper applyMapper;
    @Autowired
    private OaOutMapper outMapper;
    @Autowired
    private OaDingdingUserMapper oaDingdingUserMapper;
    
    
	/**
     * 查询钉钉考勤数据信息
     * 
     * @param userId 钉钉考勤数据ID
     * @return 钉钉考勤数据信息
     */
    @Override
	public OaDingding selectOaDingdingById(Long userId)
	{
	    return oaDingdingMapper.selectOaDingdingById(userId);
	}
	
	/**
     * 查询钉钉考勤数据列表
     * 
     * @param oaDingding 钉钉考勤数据信息
     * @return 钉钉考勤数据集合
     */
	@Override
	public List<Dingding> selectOaDingdingList(Dingding ding)
	{
		if(ding.getUserId() == 1){//admin用户
			return oaDingdingMapper.selectDingData(ding);
		}
		
		SysUser user = userMapper.selectUserById(ding.getUserId());
		
		//人事总监
		SysUser user2 = new SysUser();
		user2.setRoleId(6L);//人事总监
		Long chiefId = userRoleMapper.selectUserIdByRoleId(user2);//人事总监id
		if(chiefId.longValue() == user.getUserId().longValue()){
			ding.setUserId(1L);
			return oaDingdingMapper.selectDingData(ding);
		}
		
		Long upLeaderId =userMapper.selectUpApproverIdByApplyerId(ding.getUserId());//所在部门负责人的上级leader
		user.setRoleId(3L);//人事专员
		Long hrId = userRoleMapper.selectUserIdByRoleId(user);//人事专员id
		SysDept dept = deptMapper.selectDeptByUserId(ding.getUserId());//查询用户所在部门
		Long leaderId = userMapper.selectApproverIdByApplyerId(ding.getUserId());//所在部门负责人

			//人事专员
		if(user.getUserId().longValue()==hrId.longValue()){
			ding.setUserId(1L);
			return oaDingdingMapper.selectDingData(ding);
			
		}
		//其他人事和普通员工这里还有考虑？？？
		else if(user.getUserId().longValue()!=hrId.longValue() && chiefId.longValue() == upLeaderId.longValue()){
			//其他人事==普通员工
			return oaDingdingMapper.selectDingData(ding);
			
		}
		else if(user.getUserId().longValue()!=hrId.longValue() && chiefId.longValue() != upLeaderId.longValue() && leaderId.longValue() != user.getUserId().longValue()){
			//普通员工
			return oaDingdingMapper.selectDingData(ding);
			
		}else{
			//leader
			dept = new SysDept();
			dept.setLeader(user.getUserName());
			dSet.clear();
			getDeptList(dept);	
			ding.setdSet(dSet);
			ding.setUserId(null);
			return oaDingdingMapper.selectDingData(ding);
		}
	}
	
	/**
	 * 递归实现获取当前用户负责的所有部门id
	 * @param dept
	 * @author wgf
	 */
	private void getDeptList(SysDept dept){
		SysDept dept2 = new SysDept();
		List<SysDept> deptList = deptMapper.selectDeptList(dept);
		dSet.addAll(deptList);
		for(SysDept d : deptList){
			dept2.setParentId(d.getDeptId());
			List<SysDept> deptList2 = deptMapper.selectDeptList(dept2);
			if(deptList2 != null && !"".equals(deptList2) && deptList2.size() != 0){
				getDeptList(dept2);
			}
		}
	}
    /**
     * 新增钉钉考勤数据
     * 
     * @param oaDingding 钉钉考勤数据信息
     * @return 结果
     */
	@Override
	public int insertOaDingding(OaDingding oaDingding)
	{
	    return oaDingdingMapper.insertOaDingding(oaDingding);
	}
	
	/**
     * 修改钉钉考勤数据
     * 
     * @param oaDingding 钉钉考勤数据信息
     * @return 结果
     */
	@Override
	public int updateOaDingding(OaDingding oaDingding)
	{
	    return oaDingdingMapper.updateOaDingding(oaDingding);
	}

	/**
     * 删除钉钉考勤数据对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteOaDingdingByIds(String ids)
	{
//		return oaDingdingMapper.deleteOaDingdingByIds(Convert.toStrArray(ids));
		return oaDingdingMapper.deleteOaDingdingByIds(null);

	}

	@Override
	public int insertForeach(List<OaDingding> dingList) {
		
		return oaDingdingMapper.insertForeach(dingList);
	}

	/**
	 * 计算请假后的打卡时间
	 * @param 请假时间
	 * @return 标准打卡时间
	 */
	private Date getWorkDate(Date d,int n){
		Date result = new Date();
		long t = d.getTime();
		t+=n*60*60*1000;
		result.setTime(t);
		return result; 
	}

	@Override
	public int updateOaDingDingByOutAndApply(String applyState, String state) {
		//（一）根据请假情况更新钉钉考勤表
		UserApply apply = new UserApply();
		apply.setApplyState(applyState);//申请状态（1 待审批，2已撤回，3申请成功，4申请失败） 
		apply.setApplyType("1");//申请类型（1请假，2加班，销假） 
		apply.setStatus("0");//是否更新在钉钉考勤表中：0 未更新  1已更新
		List<UserApply> applyList = applyMapper.selectApplyList(apply);//申请成功的请假记录
		
		if(applyList == null){
			return 0;//没有申请成功的请假记录需要更新在钉钉考勤表中
		}
		
		for(UserApply a : applyList){
			OaDingdingUser dingUser = new OaDingdingUser();
			dingUser.setUserName(a.getUserName());			
			List<OaDingdingUser> dingUserList = oaDingdingUserMapper.selectOaDingdingUserList(dingUser);//根据userName查找钉钉考勤用户表中的userId
			if(dingUserList.size() == 0) continue;
			
			Date startTime = a.getStarttime();
			Date endTime = getWorkDate(a.getEndtime(), 24);
			double timeLength = a.getTimelength();
			String timepart1 = a.getTimeapart1();
			String timepart2 = a.getTimeapart2();
			String timepart3 = a.getTimeapart3();
			System.out.println();
			System.out.println("startTime: "+startTime+" endTime: "+endTime);
			System.out.println(" timeLength: "+timeLength+" timepart1: "+timepart1+" timepart2: "+timepart2+" timepart3: "+timepart3);
			System.out.println(" userId: "+dingUserList.get(0).getUserId()+" userName: "+dingUserList.get(0).getUserName()+" applyId: "+a.getApplyId());
			System.out.println();
			
			Dingding di = new Dingding();
			di.setUserId(dingUserList.get(0).getUserId());
			di.setStartTime(startTime);
			di.setEndTime(endTime);
			List<Dingding> diList = oaDingdingMapper.selectOaDingdingListByCondition(di);//查询实际打卡时间
			if(diList.size() == 0) continue;//请假时间区间在oa_dingding表中没有打卡记录
			
			String leaveType = a.getLeaveType();
			Long userId = dingUserList.get(0).getUserId();
			
			//非哺乳假 	上午(10:00——15:00) 下午(15:00——19:00)
			if(!leaveType.equals("10")){
				if(a.getTimelength()%1 == 0){//整数假期天数
					Dingding ding = new Dingding();
					ding.setUserId(userId);
					ding.setTimeResult(leaveType);
					ding.setStartTime(startTime);
					ding.setEndTime(endTime);
					ding.setStatus("1");
					oaDingdingMapper.updateOaDingDingByTime(ding);
				}else{
					if(timepart1.equals("2") && timepart2.equals("1")){//starttime 15:00 ---endtime 15:00
						Date startDate = getWorkDate(startTime,15);//打卡标准值：15点前
						Date endDate = getWorkDate(endTime,15);
						System.out.println("\n 21"+startDate+"  "+endDate+"\n");
						
						Dingding ding = new Dingding();
						ding.setUserId(userId);
						ding.setTimeResult(leaveType);
						ding.setStartTime(startDate);
						ding.setEndTime(endDate);
						ding.setStatus("1");
						oaDingdingMapper.updateOaDingDingByTime(ding);	
					}else if(timepart1.equals("1") && timepart2.equals("1")){//上午 starttime - starttime 15:00
						Date startDate = startTime;//打卡标准值：15点前
						Date endDate = getWorkDate(startTime,15);
						System.out.println("\n 11"+startDate+"  "+endDate+"\n");
						
						Dingding ding = new Dingding();
						ding.setUserId(userId);
						ding.setTimeResult(leaveType);
						ding.setStartTime(startDate);
						ding.setEndTime(endDate);
						ding.setStatus("1");
						oaDingdingMapper.updateOaDingDingByTime(ding);	
					}else if(timepart1.equals("2") && timepart2.equals("2")){//22 下午，第二天下午， starttime 15:00 --- endtime 
						Date startDate = getWorkDate(startTime,15);//打卡标准值：15点前
						Date endDate = endTime;
						if(timeLength < 1)getWorkDate(endTime,15);
						System.out.println("\n 22"+startDate+"  "+endDate+"\n");
						
						Dingding ding = new Dingding();
						ding.setUserId(userId);
						ding.setTimeResult(leaveType);
						ding.setStartTime(startDate);
						ding.setEndTime(endDate);
						ding.setStatus("1");
						if(timeLength < 1) ding.setCheckType("OffDuty");
						oaDingdingMapper.updateOaDingDingByTime(ding);	
					}
				}
			}
			
			//哺乳假 早上(10:00——11:00)	 下午(18:00——19:00)
			if(leaveType.equals("10")){
				Dingding ding = new Dingding();
				ding.setUserId(userId);
				ding.setStartTime(startTime);
				ding.setEndTime(endTime);
				List<Dingding> dingList = oaDingdingMapper.selectOaDingdingListByCondition(ding);//查询实际打卡时间		
				
				if(dingList != null){
					if(a.getTimeapart3().equals("1")){//上午请假
						Date startDate = getWorkDate(startTime,11);//打卡标准值：11点前
						for(Dingding d : dingList){
							if(d.getUserCheckTime().compareTo(startDate) < 0){//打卡时间在标准值内
								Dingding ding2 = new Dingding();
								ding2.setUserId(dingUserList.get(0).getUserId());
								ding2.setCheckType("OnDuty");
								ding2.setTimeResult(leaveType);					
								ding2.setStatus("1");
								oaDingdingMapper.updateOaDingDingByTime(ding2);
							}
						}
					}else{//下午请假
						Date endDate = getWorkDate(endTime,18);
						for(Dingding d : dingList){
							if(d.getUserCheckTime().compareTo(endDate) > 0){//打卡时间在标准值内
								Dingding ding1 = new Dingding();
								ding1.setUserId(dingUserList.get(0).getUserId());
								ding1.setTimeResult(leaveType);						
								ding1.setStatus("1");
								ding1.setCheckType("OffDuty");
								oaDingdingMapper.updateOaDingDingByTime(ding1);
							}
						}
					}
					
				}			
			}
//			applyMapper.updateUserApplyStatusById(a.getApplyId());//0 未更新  1已更新
		}
		
		//（一）根据外出报备情况更新钉钉考勤表
		OaOut out = new OaOut();
		out.setStatus("0");//是否更新在钉钉考勤表中：0 未更新  1已更新
		out.setState(state);//申请状态（1 待审批，2已撤回，3申请成功，4申请失败）
		List<OaOut> outList = outMapper.selectOaOutList(out);//查询申请成功且未更新在钉钉考勤记录表中的外出报备数据
		if(outList.size() == 0) return 1;
		
		for(OaOut o : outList){
			OaDingdingUser dingUser = new OaDingdingUser();
			dingUser.setUserName(o.getUserName());			
			List<OaDingdingUser> dingUserList = oaDingdingUserMapper.selectOaDingdingUserList(dingUser);//根据userName查找钉钉考勤用户表中的userId
			if(dingUserList.size() == 0) continue;

			Dingding ding = new Dingding();
			ding.setUserId(dingUserList.get(0).getUserId());
			ding.setStartTime(o.getStarttime());
			if(o.getStarttime().equals(o.getEndtime())){
				ding.setEndTime(getWorkDate(o.getEndtime(), 24));
			}
			ding.setEndTime(o.getEndtime());
			List<Dingding> dingList = oaDingdingMapper.selectOaDingdingListByCondition(ding);//查询
			if(dingList.size() == 0) continue;
			
			ding.setTimeResult("out");
			ding.setStatus("1");
			oaDingdingMapper.updateOaDingDingByTime(ding);
			
//			OaOut out2 = new OaOut();
//			out2.setOutId(o.getOutId());
//			out2.setStatus("1");
//			outMapper.updateOaOut(out2);
		}
		return 1;
	}

}
