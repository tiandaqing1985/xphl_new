package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.PrintBaoXiaoVO;
import com.ruoyi.system.domain.SysDept;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.domain.finance.*;
import com.ruoyi.system.service.IPrintService;
import com.ruoyi.system.service.ISysDeptService;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.system.service.finance.*;
import com.ruoyi.system.tool.NumberToCN;
import com.ruoyi.system.tool.PrintUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class PrintServiceImpl implements IPrintService {

    @Autowired
    private IFacReimburseApplyService facReimburseApplyService;
    @Autowired
    private IFacUserApprovalService facUserApprovalService;
    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private IFacLoanApplyService facLoanApplyService;
    @Autowired
    private ISysDeptService sysDeptService;
    @Autowired
    private IFacCostApplyService facCostApplyService;
    @Autowired
    private IFacCostPutupApplyService facCostPutupApplyService;

    /**
     * 预览报销
     *
     * @param num 报销单号
     * @return
     */
    @Override
    public String previewBaoxiao(String num) {
        Map<String, Object> data = new HashMap<String, Object>();

        Map<String, PrintBaoXiaoVO> typeMap = new HashMap<>();

        FacReimburseApply selectVO = new FacReimburseApply();
        selectVO.setNum(num);
        //报销信息
        List<FacReimburseApply> facReimburseApplies = facReimburseApplyService.selectFacReimburseApplyList(selectVO);
        data.put("data", facReimburseApplies.get(0));
        //加班公出交通费报销
        List<ReiTrafficApply> reiTrafficApplys = facReimburseApplyService.selectReiTrafficApply(num);
        //检出加班交通费和公出交通费，放在一起
        String type = null;
        PrintBaoXiaoVO sum = new PrintBaoXiaoVO();
        sum.setType("报销金额人民币（大写）");
        sum.setMoney(new Double(0));
        sum.setDocumentNum(new Double(0));
        for (ReiTrafficApply reiTrafficApply : reiTrafficApplys) {
            //金额保留两位小数
            reiTrafficApply.setAmount(new BigDecimal(reiTrafficApply.getAmount()).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());

            if (reiTrafficApply.getType().equals("公出")) {
                type = "公出交通费";
            } else if (reiTrafficApply.getType().equals("加班")) {
                type = "加班交通费";
            }
            if (typeMap.get(type) == null) {
                PrintBaoXiaoVO printBaoXiaoVO = new PrintBaoXiaoVO();
                printBaoXiaoVO.setType(type);
                printBaoXiaoVO.setDetail("见明细");
                printBaoXiaoVO.setMoney(new Double(0));
                printBaoXiaoVO.setDocumentNum(new Double(0));
                typeMap.put(type, printBaoXiaoVO);
            }
            PrintBaoXiaoVO printBaoXiaoVO = typeMap.get(type);
            printBaoXiaoVO.setDocumentNum(printBaoXiaoVO.getDocumentNum() + reiTrafficApply.getDocumentNum());
            printBaoXiaoVO.setMoney(printBaoXiaoVO.getMoney() + reiTrafficApply.getAmount());
            //计算总共的
            sum.setDocumentNum(sum.getDocumentNum() + reiTrafficApply.getDocumentNum());
            sum.setMoney(sum.getMoney() + reiTrafficApply.getAmount());
        }
        //招待费报销
        ReiHospitalityApply selectHospitalityVo = new ReiHospitalityApply();
        selectHospitalityVo.setNum(num);
        List<ReiHospitalityApply> list = facReimburseApplyService.selectReiHospitalityApplyList(selectHospitalityVo);
        for (ReiHospitalityApply reiHospitalityApply : list) {
            SysUser applicant = sysUserService.selectUserById(reiHospitalityApply.getUser());
            if (applicant != null) {
                reiHospitalityApply.setUserName(sysUserService.selectUserById(reiHospitalityApply.getUser()).getUserName());
            }
            //金额保留两位小数
            reiHospitalityApply.setAmount(new BigDecimal(reiHospitalityApply.getAmount()).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
            type = "招待费报销";
            if (typeMap.get(type) == null) {
                PrintBaoXiaoVO printBaoXiaoVO = new PrintBaoXiaoVO();
                printBaoXiaoVO.setType(type);
                printBaoXiaoVO.setDetail("见明细");
                printBaoXiaoVO.setMoney(new Double(0));
                printBaoXiaoVO.setDocumentNum(new Double(0));
                typeMap.put(type, printBaoXiaoVO);
            }
            PrintBaoXiaoVO printBaoXiaoVO = typeMap.get(type);
            if(reiHospitalityApply.getDocumentNum()==null){
                reiHospitalityApply.setDocumentNum(0);
            }
            printBaoXiaoVO.setDocumentNum(printBaoXiaoVO.getDocumentNum() + reiHospitalityApply.getDocumentNum());
            printBaoXiaoVO.setMoney(printBaoXiaoVO.getMoney() + reiHospitalityApply.getAmount());
            //计算总共的
            sum.setDocumentNum(sum.getDocumentNum() + reiHospitalityApply.getDocumentNum());
            sum.setMoney(sum.getMoney() + reiHospitalityApply.getAmount());
        }
        //其他报销
        List<FacReiAdiApply> facReiAdiApplys = facReimburseApplyService.selectFacReiAdiApply(num);
        //按科目字段分组
        for (FacReiAdiApply facReiAdiApply : facReiAdiApplys) {
            //金额保留两位小数
            facReiAdiApply.setAmount(new BigDecimal(facReiAdiApply.getAmount()).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
            type = facReiAdiApply.getProject();
            if (typeMap.get(type) == null) {
                PrintBaoXiaoVO printBaoXiaoVO = new PrintBaoXiaoVO();
                printBaoXiaoVO.setType(type);
                printBaoXiaoVO.setDetail("见明细");
                printBaoXiaoVO.setMoney(new Double(0));
                printBaoXiaoVO.setDocumentNum(new Double(0));
                typeMap.put(type, printBaoXiaoVO);
            }
            PrintBaoXiaoVO printBaoXiaoVO = typeMap.get(type);
            printBaoXiaoVO.setDocumentNum(printBaoXiaoVO.getDocumentNum() + facReiAdiApply.getDocumentNum());
            printBaoXiaoVO.setMoney(printBaoXiaoVO.getMoney() + facReiAdiApply.getAmount());
            //计算总共的
            sum.setDocumentNum(sum.getDocumentNum() + facReiAdiApply.getDocumentNum());
            sum.setMoney(sum.getMoney() + facReiAdiApply.getAmount());
        }
        List<PrintBaoXiaoVO> values = new ArrayList<>();
        Set<Map.Entry<String, PrintBaoXiaoVO>> entries = typeMap.entrySet();
        for (Map.Entry<String, PrintBaoXiaoVO> entry : entries) {
            values.add(entry.getValue());
        }
        //大写金额
        //金额保留两位小数
        sum.setMoney(new BigDecimal(sum.getMoney()).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
        String moneyStr = NumberToCN.number2CNMontrayUnit(new BigDecimal(sum.getMoney()).setScale(2,BigDecimal.ROUND_HALF_UP));
        sum.setDetail(moneyStr);
        if (values != null && values.size() != 0) {
            values.add(sum);
        }
        //查询当前审批过的记录
        FacUserApproval selectVo = new FacUserApproval();
        selectVo.setApplyId(num);
        List<FacUserApproval> facUserApprovals = facUserApprovalService.selectFacUserApprovalList(selectVo);
        for (int i = 0; i < facUserApprovals.size(); i++) {
            SysUser sysUser = sysUserService.selectUserById(facUserApprovals.get(i).getApproverId());
            if(sysUser!=null){
                facUserApprovals.get(i).setApproverName(sysUser.getUserName());
            }
            if (facUserApprovals.get(i).getApprovalState().equals("1")) {
                facUserApprovals.get(i).setApprovalState("审批通过");
            }else if(facUserApprovals.get(i).getApprovalState().equals("2")){
                facUserApprovals.get(i).setApprovalState("审批拒绝");
            }else{
                facUserApprovals.remove(i);
                i--;
            }
        }
        data.put("huizong", values);
        data.put("shenpiren", facUserApprovals);

        return PrintUtil.printString("baoxiao.ftl", data);
    }

    /**
     * 报销详情
     * @param num
     * @return
     */
    @Override
    public String previewBaoxiaoDetail(String num) {

        Map<String, Object> data = new HashMap<String, Object>();

        FacReimburseApply selectVO = new FacReimburseApply();
        selectVO.setNum(num);
        //报销信息
        List<FacReimburseApply> facReimburseApplies = facReimburseApplyService.selectFacReimburseApplyList(selectVO);
        data.put("data", facReimburseApplies.get(0));
        //加班公出交通费报销
        List<ReiTrafficApply> reiTrafficApplys = facReimburseApplyService.selectReiTrafficApply(num);
        //总金额
        Double sum = new Double(0);
        Integer sumDocumentNum = 0;
        for (ReiTrafficApply reiTrafficApply : reiTrafficApplys) {
            //金额保留两位小数
            reiTrafficApply.setAmount(new BigDecimal(reiTrafficApply.getAmount()).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
            //计算总共的
            sum = sum + reiTrafficApply.getAmount();
            sumDocumentNum = sumDocumentNum + reiTrafficApply.getDocumentNum();
            if(reiTrafficApply.getType().equals(1)){
                reiTrafficApply.setType("公出");
            }else if(reiTrafficApply.getType().equals(2)){
                reiTrafficApply.setType("加班");
            }
        }
        data.put("jiaotong",reiTrafficApplys);
        //招待费报销
        ReiHospitalityApply selectHospitalityVo = new ReiHospitalityApply();
        selectHospitalityVo.setNum(num);
        List<ReiHospitalityApply> list = facReimburseApplyService.selectReiHospitalityApplyList(selectHospitalityVo);
        for (ReiHospitalityApply reiHospitalityApply : list) {
            SysUser applicant = sysUserService.selectUserById(reiHospitalityApply.getUser());
            if (applicant != null) {
                reiHospitalityApply.setUserName(sysUserService.selectUserById(reiHospitalityApply.getUser()).getUserName());
            }
            //金额保留两位小数
            reiHospitalityApply.setAmount(new BigDecimal(reiHospitalityApply.getAmount()).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
            if(reiHospitalityApply.getDocumentNum()==null){
                reiHospitalityApply.setDocumentNum(0);
            }
            //计算总共的
            sum = sum + reiHospitalityApply.getAmount();
            sumDocumentNum = sumDocumentNum + reiHospitalityApply.getDocumentNum();
        }
        data.put("zhaodaifei",list);
        //其他报销
        List<FacReiAdiApply> facReiAdiApplys = facReimburseApplyService.selectFacReiAdiApply(num);
        for (FacReiAdiApply facReiAdiApply : facReiAdiApplys) {
            //金额保留两位小数
            facReiAdiApply.setAmount(new BigDecimal(facReiAdiApply.getAmount()).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
            //计算总共的
            sum = sum + facReiAdiApply.getAmount();
            sumDocumentNum = sumDocumentNum + facReiAdiApply.getDocumentNum();
        }
        data.put("qita", facReiAdiApplys);
        data.put("total",sum);
        data.put("invoices",sumDocumentNum);
        return PrintUtil.printString("baoxiaomingxi.ftl", data);

    }

    /**
     * 出差打印
     * @param num
     * @return
     */
    @Override
    public String previewchucai(String num) {

        Map<String,Object> data = new HashMap<>();
        //查询出差申请
        FacCostApply facCostApply = new FacCostApply();
        facCostApply.setNum(num);
        List<FacCostApply> facCostApplys = facCostApplyService.selectFacCostApplyList(facCostApply);
        facCostApply = facCostApplys.get(0);
        //查出行程
        List<FacCostDetailApply> facCostDetailApplies = facCostApplyService.deatils(num);
        //查出住宿
        FacCostPutupApply facCostPutupApply = new FacCostPutupApply();
        facCostPutupApply.setNum(num);
        List<FacCostPutupApply> facCostPutupApplies = facCostPutupApplyService.selectFacCostPutupApplyList(facCostPutupApply);
        //查出申请人部门
        SysUser sysUser = sysUserService.selectUserById(facCostApply.getUserId());
        SysDept sysDept = sysDeptService.selectDeptById(sysUser.getDeptId());
        data.put("deptName",sysDept.getDeptName());
        data.put("userName",sysUser.getUserName());



        return PrintUtil.printString("chuchaishenqing.ftl", data);
    }

    /**
     * 预览对公支付
     *
     * @param num 单号
     * @return
     */
    @Override
    public String previewDuigongzhifu(String num) {

        return null;
    }

    /**
     * 借款
     * @param num
     * @return
     */
    @Override
    public String previewjiekuan(String num) {

        Map<String,Object> data = new HashMap<>();
        //查询借款
        FacLoanApply facLoanApply = new FacLoanApply();
        facLoanApply.setNum(num);
        List<FacLoanApply> list = facLoanApplyService.selectFacLoanApplyList(facLoanApply);
        for (FacLoanApply v : list) {
            v.setUserName(sysUserService.selectUserById(v.getLoanUser()).getUserName());
            facLoanApply.setUserName(v.getUserName());
        }
        if(list.size()>0){
            facLoanApply = list.get(0);
        }else{
            return null;
        }
        facLoanApply.setAmount(new BigDecimal(facLoanApply.getAmount()).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
        data.put("borrowMoney",facLoanApply);
        SysUser sysUser = sysUserService.selectUserById(facLoanApply.getLoanUser());
        SysDept sysDept = sysDeptService.selectDeptById(sysUser.getDeptId());
        data.put("dept",sysDept.getDeptName());
        //金额大写
        String amountStr = NumberToCN.number2CNMontrayUnit(new BigDecimal(facLoanApply.getAmount()).setScale(2,BigDecimal.ROUND_HALF_UP));
        data.put("amountStr",amountStr);
        //查询当前审批过的记录
        FacUserApproval selectVo = new FacUserApproval();
        selectVo.setApplyId(num);
        List<FacUserApproval> facUserApprovals = facUserApprovalService.selectFacUserApprovalList(selectVo);
        for (int i = 0; i < facUserApprovals.size(); i++) {
            sysUser = sysUserService.selectUserById(facUserApprovals.get(i).getApproverId());
            if(sysUser!=null){
                facUserApprovals.get(i).setApproverName(sysUser.getUserName());
            }
            if (facUserApprovals.get(i).getApprovalState().equals("1")) {
                facUserApprovals.get(i).setApprovalState("审批通过");
            }else if(facUserApprovals.get(i).getApprovalState().equals("2")){
                facUserApprovals.get(i).setApprovalState("审批拒绝");
            }else{
                facUserApprovals.remove(i);
                i--;
            }
        }
        data.put("facUserApprovals",facUserApprovals);
        return PrintUtil.printString("jiekuan.ftl", data);
    }
}
