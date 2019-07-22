package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.*;
import com.ruoyi.system.mapper.AllMapper;
import com.ruoyi.system.mapper.DangdangBackMapper;
import com.ruoyi.system.service.DangDangAllImportService;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: ruoyi->DangDangAllImportServiceImpl
 * @author: gakki
 * @create: 2019-07-16 20:39
 **/
@Service
public class DangDangAllImportServiceImpl implements DangDangAllImportService {


    @Autowired
    AllMapper allMapper;
    @Autowired
    DangdangBackMapper dangdangBackMapper;




    @Override
    public List<DangDangAll> importDangDangAll(DangdangSearchAdd date) {
        List<DangDangAll> other = allMapper.other(date);
        List<DangDangAll> doneAlls = allMapper.queryDone(date);
        List<DangdangBack> dangDangModBacks = dangdangBackMapper.queryMod(date);
        List<DangdangBack> appletsBacks = dangdangBackMapper.queryUrl(date);
        for (int i = 0; i < doneAlls.size(); i++) {
            doneAlls.get(i).setfAppShowData(doneAlls.get(i).getfAppShowData() == null ? 0 : doneAlls.get(i).getfAppShowData());
            doneAlls.get(i).setfAppClick(doneAlls.get(i).getfAppClick() == null ? 0 : doneAlls.get(i).getfAppClick());
            doneAlls.get(i).setfAppCost(doneAlls.get(i).getfAppCost() == null ? 0.0 : doneAlls.get(i).getfAppCost());
            doneAlls.get(i).setfLetsShowData(doneAlls.get(i).getfAppShowData() == null ? 0 : doneAlls.get(i).getfAppShowData());
            doneAlls.get(i).setfLetsClick(doneAlls.get(i).getfLetsClick() == null ? 0 : doneAlls.get(i).getfClick());
            doneAlls.get(i).setfLetsCost(doneAlls.get(i).getfLetsCost() == null ? 0.0 : doneAlls.get(i).getfLetsCost());
            doneAlls.get(i).setDoneUv(doneAlls.get(i).getDoneUv() == null ? 0 : doneAlls.get(i).getDoneUv());
            doneAlls.get(i).setDoneAddActiveUser(doneAlls.get(i).getDoneAddActiveUser() == null ? 0 : doneAlls.get(i).getDoneAddActiveUser());
            doneAlls.get(i).setDoneCoollectingPureNewCustomers(doneAlls.get(i).getDoneCoollectingPureNewCustomers() == null ? 0 : doneAlls.get(i).getDoneCoollectingPureNewCustomers());
            doneAlls.get(i).setDoneBookingNewCustomersPureInfiltration(doneAlls.get(i).getDoneBookingNewCustomersPureInfiltration() == null ? 0 : doneAlls.get(i).getDoneBookingNewCustomersPureInfiltration());
            doneAlls.get(i).setDoneOrderingOrders(doneAlls.get(i).getDoneOrderingOrders() == null ? 0 : doneAlls.get(i).getDoneOrderingOrders());
            doneAlls.get(i).setDoneReceivingAmount(doneAlls.get(i).getDoneReceivingAmount() == null ? 0.0 : doneAlls.get(i).getDoneReceivingAmount());
            doneAlls.get(i).setDonePureOutOfTheLibrary(doneAlls.get(i).getDonePureOutOfTheLibrary() == null ? 0 : doneAlls.get(i).getDonePureOutOfTheLibrary());
            doneAlls.get(i).setDoneOutboundNewCustomersPureInfiltration(doneAlls.get(i).getDoneOutboundNewCustomersPureInfiltration() == null ? 0 : doneAlls.get(i).getDoneOutboundNewCustomersPureInfiltration());
            doneAlls.get(i).setDoneOutboundOrderNumber(doneAlls.get(i).getDoneOutboundOrderNumber() == null ? 0 : doneAlls.get(i).getDoneOutboundOrderNumber());
            doneAlls.get(i).setDoneOutboundAmount(doneAlls.get(i).getDoneOutboundAmount() == null ? 0.0 : doneAlls.get(i).getDoneOutboundAmount());
            if (dangDangModBacks.get(i) == null) {
                doneAlls.get(i).setModUv(0);
                doneAlls.get(i).setModAddActiveUser(0);
                doneAlls.get(i).setModCoollectingPureNewCustomers(0);
                doneAlls.get(i).setModBookingNewCustomersPureInfiltration(0);
                doneAlls.get(i).setModOrderingOrders(0);
                doneAlls.get(i).setModReceivingAmount(0.0);
                doneAlls.get(i).setModPureOutOfTheLibrary(0);
                doneAlls.get(i).setModOutboundNewCustomersPureInfiltration(0);
                doneAlls.get(i).setModOutboundOrderNumber(0);
                doneAlls.get(i).setModOutboundAmount(0.0);
            } else {
                doneAlls.get(i).setModUv(dangDangModBacks.get(i).getUv() == null ? 0 : dangDangModBacks.get(i).getUv());
                doneAlls.get(i).setModAddActiveUser(dangDangModBacks.get(i).getAddActiveUser() == null ? 0 : dangDangModBacks.get(i).getAddActiveUser());
                doneAlls.get(i).setModCoollectingPureNewCustomers(dangDangModBacks.get(i).getCoollectingPureNewCustomers() == null ? 0 : dangDangModBacks.get(i).getCoollectingPureNewCustomers());
                doneAlls.get(i).setModBookingNewCustomersPureInfiltration(dangDangModBacks.get(i).getBookingNewCustomersPureInfiltration() == null ? 0 : dangDangModBacks.get(i).getBookingNewCustomersPureInfiltration());
                doneAlls.get(i).setModOrderingOrders(dangDangModBacks.get(i).getOrderingOrders() == null ? 0 : dangDangModBacks.get(i).getOrderingOrders());
                doneAlls.get(i).setModReceivingAmount(dangDangModBacks.get(i).getReceivingAmount() == null ? 0.0 : dangDangModBacks.get(i).getReceivingAmount());
                if (dangDangModBacks.get(i).getPureOutOfTheLibrary() != null) {
                    doneAlls.get(i).setModPureOutOfTheLibrary(dangDangModBacks.get(i).getPureOutOfTheLibrary());
                }
                doneAlls.get(i).setModPureOutOfTheLibrary(dangDangModBacks.get(i).getPureOutOfTheLibrary() == null ? 0 : dangDangModBacks.get(i).getPureOutOfTheLibrary());
                doneAlls.get(i).setModOutboundNewCustomersPureInfiltration(dangDangModBacks.get(i).getOutboundNewCustomersPureInfiltration() == null ? 0 : dangDangModBacks.get(i).getOutboundNewCustomersPureInfiltration());
                doneAlls.get(i).setModOutboundOrderNumber(dangDangModBacks.get(i).getOutboundOrderNumber() == null ? 0 : dangDangModBacks.get(i).getOutboundOrderNumber());
                doneAlls.get(i).setModOutboundAmount(dangDangModBacks.get(i).getOutboundAmount() == null ? 0.0 : dangDangModBacks.get(i).getOutboundAmount());
            }
            if (appletsBacks.get(i) == null) {
                doneAlls.get(i).setLetUv(0);
                doneAlls.get(i).setLetAddActiveUser(0);
                doneAlls.get(i).setLetCoollectingPureNewCustomers(0);
                doneAlls.get(i).setLetBookingNewCustomersPureInfiltration(0);
                doneAlls.get(i).setLetOrderingOrders(0);
                doneAlls.get(i).setLetReceivingAmount(0.0);
                doneAlls.get(i).setLetPureOutOfTheLibrary(0);
                doneAlls.get(i).setLetOutboundNewCustomersPureInfiltration(0);
                doneAlls.get(i).setLetOutboundOrderNumber(0);
                doneAlls.get(i).setLetOutboundAmount(0.0);
            } else {
                doneAlls.get(i).setLetUv(appletsBacks.get(i).getUv() == null ? 0 : appletsBacks.get(i).getUv());
                doneAlls.get(i).setLetAddActiveUser(appletsBacks.get(i).getAddActiveUser() == null ? 0 : appletsBacks.get(i).getAddActiveUser());
                doneAlls.get(i).setLetCoollectingPureNewCustomers(appletsBacks.get(i).getCoollectingPureNewCustomers() == null ? 0 : appletsBacks.get(i).getCoollectingPureNewCustomers());
                doneAlls.get(i).setLetBookingNewCustomersPureInfiltration(appletsBacks.get(i).getBookingNewCustomersPureInfiltration() == null ? 0 : appletsBacks.get(i).getBookingNewCustomersPureInfiltration());
                doneAlls.get(i).setLetOrderingOrders(appletsBacks.get(i).getOrderingOrders() == null ? 0 : appletsBacks.get(i).getOrderingOrders());
                doneAlls.get(i).setLetReceivingAmount(appletsBacks.get(i).getReceivingAmount() == null ? 0.0 : appletsBacks.get(i).getReceivingAmount());
                if (appletsBacks.get(i).getPureOutOfTheLibrary() != null) {
                    doneAlls.get(i).setLetPureOutOfTheLibrary(appletsBacks.get(i).getPureOutOfTheLibrary());
                }
                doneAlls.get(i).setLetOutboundNewCustomersPureInfiltration(doneAlls.get(i).getOutboundNewCustomersPureInfiltration() == null ? 0 : doneAlls.get(i).getOutboundNewCustomersPureInfiltration());
                doneAlls.get(i).setLetOutboundOrderNumber(doneAlls.get(i).getOutboundOrderNumber() == null ? 0 : doneAlls.get(i).getOutboundOrderNumber());
                doneAlls.get(i).setLetOutboundAmount(doneAlls.get(i).getOutboundAmount() == null ? 0.0 : doneAlls.get(i).getOutboundAmount());
            }
            doneAlls.get(i).setUv(doneAlls.get(i).getDoneUv() + doneAlls.get(i).getModUv() + doneAlls.get(i).getLetUv());
            doneAlls.get(i).setAddActiveUser(doneAlls.get(i).getDoneAddActiveUser() + doneAlls.get(i).getModAddActiveUser() + doneAlls.get(i).getLetUv());
            doneAlls.get(i).setCoollectingPureNewCustomers(doneAlls.get(i).getDoneCoollectingPureNewCustomers() + doneAlls.get(i).getModCoollectingPureNewCustomers() + doneAlls.get(i).getLetCoollectingPureNewCustomers());
            doneAlls.get(i).setBookingNewCustomersPureInfiltration(doneAlls.get(i).getDoneBookingNewCustomersPureInfiltration() + doneAlls.get(i).getModCoollectingPureNewCustomers() + doneAlls.get(i).getLetCoollectingPureNewCustomers());
            doneAlls.get(i).setOrderingOrders(doneAlls.get(i).getDoneOrderingOrders() + doneAlls.get(i).getModCoollectingPureNewCustomers() + doneAlls.get(i).getLetCoollectingPureNewCustomers());
            doneAlls.get(i).setReceivingAmount(doneAlls.get(i).getDoneReceivingAmount() + doneAlls.get(i).getModReceivingAmount() + doneAlls.get(i).getLetReceivingAmount());
            doneAlls.get(i).setPureOutOfTheLibrary(doneAlls.get(i).getDonePureOutOfTheLibrary() + doneAlls.get(i).getModPureOutOfTheLibrary() + doneAlls.get(i).getLetPureOutOfTheLibrary());
            doneAlls.get(i).setOutboundNewCustomersPureInfiltration(doneAlls.get(i).getDoneOutboundNewCustomersPureInfiltration() + doneAlls.get(i).getModOutboundNewCustomersPureInfiltration() + doneAlls.get(i).getLetOutboundNewCustomersPureInfiltration());
            doneAlls.get(i).setOutboundOrderNumber(doneAlls.get(i).getDoneOutboundOrderNumber() + doneAlls.get(i).getModOutboundOrderNumber() + doneAlls.get(i).getLetOutboundOrderNumber());
            doneAlls.get(i).setOutboundAmount(doneAlls.get(i).getDoneOutboundAmount() + doneAlls.get(i).getModOutboundAmount() + doneAlls.get(i).getLetOutboundAmount());
            //出库纯新客出库金额 小程序url 小程序更改后汇总
            doneAlls.get(i).setAllOutboundAmount(doneAlls.get(i).getDoneOutboundAmount() + doneAlls.get(i).getLetOutboundAmount());
            doneAlls.get(i).setAllPureOutOfTheLibrary(doneAlls.get(i).getDonePureOutOfTheLibrary() + doneAlls.get(i).getLetPureOutOfTheLibrary());
            //去除app消费 .subtract(new BigDecimal( doneAlls.get(i).getfAppCost().toString())).doubleValue()
            doneAlls.get(i).setRemoveAppCost(new BigDecimal(doneAlls.get(i).getfCost().toString()).subtract(new BigDecimal(doneAlls.get(i).getfAppCost().toString())).doubleValue());
            Double appActCost =  doneAlls.get(i).getRemoveAppCost()/ 1.3;//ctr
            DecimalFormat df = new DecimalFormat("#.00");
            //去除app实际消费
            System.out.println("去除app实际消费"+df.format(appActCost));
            doneAlls.get(i).setAppActCost(Double.valueOf(df.format(appActCost)));
            //出库转化率
            if (doneAlls.get(i).getOutboundOrderNumber()==0){
                doneAlls.get(i).setOutBoundRate("0%");
            }else  if (doneAlls.get(i).getAppActCost()==0){
                doneAlls.get(i).setOutBoundRate("100%");
            }
            double v = doneAlls.get(i).getOutboundOrderNumber() / doneAlls.get(i).getAppActCost();
            System.out.println("出库转化率"+v);
            doneAlls.get(i).setOutBoundRate(df.format(v)+"%");
            //CTR
            double v1 = (doneAlls.get(i).getfCost() - doneAlls.get(i).getfAppClick()) / doneAlls.get(i).getfShowDate();
            doneAlls.get(i).setCTR(df.format(v1));
            //CPC
            double v2 = (doneAlls.get(i).getfCost() - doneAlls.get(i).getfAppCost()) / (doneAlls.get(i).getfClick() - doneAlls.get(i).getfAppClick());
            doneAlls.get(i).setCPC(df.format(v2));
            //出库ROI
            double v3 = doneAlls.get(i).getOutboundAmount() / doneAlls.get(i).getAppActCost();
            doneAlls.get(i).setOutBoundROI(df.format(v3));
            //出库新客成本
            double v4 = doneAlls.get(i).getAppActCost() / doneAlls.get(i).getPureOutOfTheLibrary();
            doneAlls.get(i).setOutBoubdCost(df.format(v4));
            //收订订单转化率
            int result =doneAlls.get(i).getfClick() - doneAlls.get(i).getfAppClick();
            if (result==0){
                doneAlls.get(i).setOrderRate("100%");
            }else {
                int orderRate = doneAlls.get(i).getOrderingOrders() / result;
                doneAlls.get(i).setOrderRate(String.valueOf(orderRate));
            }

            //收订ROi
            double orderROi = doneAlls.get(i).getReceivingAmount() / doneAlls.get(i).getAppActCost();
            doneAlls.get(i).setOrderROI(df.format(orderROi));
            //收订新客成本
            double orderNewCost = doneAlls.get(i).getAppActCost() / doneAlls.get(i).getCoollectingPureNewCustomers();
            doneAlls.get(i).setOrderNewCost(df.format(orderNewCost));
        }
        System.out.println("长度"+doneAlls.size());
        System.out.println("移动端总数量" + doneAlls.stream().mapToInt(DangDangAll::getLetPureOutOfTheLibrary).sum());
        System.out.println("url总数量" + doneAlls.stream().mapToInt(DangDangAll::getModPureOutOfTheLibrary).sum());
        doneAlls.addAll(other);
        System.out.println("其他长度"+doneAlls.size());
        return doneAlls;
    }



}
