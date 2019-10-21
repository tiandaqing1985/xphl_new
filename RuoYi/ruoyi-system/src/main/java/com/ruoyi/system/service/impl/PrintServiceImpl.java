package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.PrintBaoXiaoVO;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.domain.finance.FacReiAdiApply;
import com.ruoyi.system.domain.finance.FacReimburseApply;
import com.ruoyi.system.domain.finance.FacUserApproval;
import com.ruoyi.system.domain.finance.ReiTrafficApply;
import com.ruoyi.system.service.IPrintService;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.system.service.finance.IFacReimburseApplyService;
import com.ruoyi.system.service.finance.IFacUserApprovalService;
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

        //其他报销f
        List<FacReiAdiApply> facReiAdiApplys = facReimburseApplyService.selectFacReiAdiApply(num);
        //按科目字段分组
        for (FacReiAdiApply facReiAdiApply : facReiAdiApplys) {
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
        String moneyStr = NumberToCN.number2CNMontrayUnit(new BigDecimal(sum.getMoney()));
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
                facUserApprovals.get(i).setApprovalSight("审批通过");
            }else if(facUserApprovals.get(i).getApprovalState().equals("2")){
                facUserApprovals.get(i).setApprovalSight("审批拒绝");
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



        return null;
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

}
