package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.DangDangAll;
import com.ruoyi.system.domain.DangDangModBack;
import com.ruoyi.system.domain.DangdangAppletsBack;
import com.ruoyi.system.domain.DangdangSearchAdd;
import com.ruoyi.system.mapper.AllMapper;
import com.ruoyi.system.service.DangDangAllImportService;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @program: ruoyi->DangDangAllImportServiceImpl
 * @author: gakki
 * @create: 2019-07-16 20:39
 **/
@Service
public class DangDangAllImportServiceImpl implements DangDangAllImportService {




    @Autowired
    AllMapper allMapper;

    @Override
    public List<DangDangAll> importDangDangAll(DangdangSearchAdd date) {

        List<DangDangAll> doneAlls = allMapper.queryDone(date);
        for (DangDangAll doneAll : doneAlls) {
            doneAll.setfAppShowData(doneAll.getfAppShowData()==null?0:doneAll.getfAppShowData());
            doneAll.setfAppClick(doneAll.getfAppClick()==null?0:doneAll.getfAppClick());
            doneAll.setfAppCost(doneAll.getfAppCost()==null?0.0:doneAll.getfAppCost());
            doneAll.setfLetsShowData(doneAll.getfAppShowData()==null?0:doneAll.getfAppShowData());
            doneAll.setfLetsClick(doneAll.getfLetsClick()==null?0:doneAll.getfClick());
            doneAll.setfLetsCost(doneAll.getfLetsCost()==null?0.0:doneAll.getfLetsCost());
            doneAll.setDoneUv(doneAll.getDoneUv()==null?0:doneAll.getDoneUv());
            doneAll.setDoneAddActiveUser(doneAll.getDoneAddActiveUser()==null?0:doneAll.getDoneAddActiveUser());
            doneAll.setDoneCoollectingPureNewCustomers(doneAll.getDoneCoollectingPureNewCustomers()==null?0:doneAll.getDoneCoollectingPureNewCustomers());
            doneAll.setDoneBookingNewCustomersPureInfiltration(doneAll.getDoneBookingNewCustomersPureInfiltration()==null?0:doneAll.getDoneBookingNewCustomersPureInfiltration());
            doneAll.setDoneOrderingOrders(doneAll.getDoneOrderingOrders()==null?0:doneAll.getDoneOrderingOrders());
            doneAll.setDoneReceivingAmount(doneAll.getDoneReceivingAmount()==null?0.0:doneAll.getDoneReceivingAmount());
            doneAll.setDonePureOutOfTheLibrary(doneAll.getDonePureOutOfTheLibrary()==null?0:doneAll.getDonePureOutOfTheLibrary());
            doneAll.setDoneOutboundNewCustomersPureInfiltration(doneAll.getDoneOutboundNewCustomersPureInfiltration()==null?0:doneAll.getDoneOutboundNewCustomersPureInfiltration());
            doneAll.setDoneOutboundOrderNumber(doneAll.getDoneOutboundOrderNumber()==null?0:doneAll.getDoneOutboundOrderNumber());
            doneAll.setDoneOutboundAmount(doneAll.getDoneOutboundAmount()==null?0.0:doneAll.getDoneOutboundAmount());
        }

        List<DangDangModBack> dangDangModBacks = allMapper.queryMod(date);
        List<DangdangAppletsBack> appletsBacks = allMapper.queryUrl(date);
        for (DangDangAll doneAll : doneAlls) {
            for (DangDangModBack dangDangModBack : dangDangModBacks) {
                     if (dangDangModBack==null){
                         doneAll.setModUv(0);
                         doneAll.setModAddActiveUser(0);
                         doneAll.setModCoollectingPureNewCustomers(0);
                         doneAll.setModBookingNewCustomersPureInfiltration(0);
                         doneAll.setModOrderingOrders(0);
                         doneAll.setModReceivingAmount(0.0);
                         doneAll.setModPureOutOfTheLibrary(0);
                         doneAll.setModOutboundNewCustomersPureInfiltration(0);
                         doneAll.setModOutboundOrderNumber(0);
                         doneAll.setModOutboundAmount(0.0);
                     } else {
                         doneAll.setModUv(dangDangModBack.getModUv()==null?0:dangDangModBack.getModUv());
                         doneAll.setModAddActiveUser(dangDangModBack.getModAddActiveUser()==null?0:dangDangModBack.getModAddActiveUser());
                         doneAll.setModCoollectingPureNewCustomers(dangDangModBack.getModCoollectingPureNewCustomers()==null?0:dangDangModBack.getModCoollectingPureNewCustomers());
                         doneAll.setModBookingNewCustomersPureInfiltration(dangDangModBack.getModBookingNewCustomersPureInfiltration()==null?0:dangDangModBack.getModBookingNewCustomersPureInfiltration());
                         doneAll.setModOrderingOrders(dangDangModBack.getModOrderingOrders()==null?0:dangDangModBack.getModOrderingOrders());
                         doneAll.setModReceivingAmount(dangDangModBack.getModReceivingAmount()==null?0.0:dangDangModBack.getModReceivingAmount());
                         doneAll.setModPureOutOfTheLibrary(dangDangModBack.getModPureOutOfTheLibrary()==null?0:dangDangModBack.getModPureOutOfTheLibrary());
                         doneAll.setModOutboundNewCustomersPureInfiltration(dangDangModBack.getModOutboundNewCustomersPureInfiltration()==null?0:dangDangModBack.getModOutboundNewCustomersPureInfiltration());
                         doneAll.setModOutboundOrderNumber(dangDangModBack.getModOutboundOrderNumber()==null?0:dangDangModBack.getModOutboundOrderNumber());
                         doneAll.setModOutboundAmount(dangDangModBack.getModOutboundAmount()==null?0.0:dangDangModBack.getModOutboundAmount());
                     }
            }
        }
        for (DangDangAll doneAll : doneAlls) {
            for (DangdangAppletsBack appletsBack : appletsBacks) {
                 if (appletsBack==null){
                     doneAll.setLetUv(0);
                     doneAll.setLetAddActiveUser(0);
                     doneAll.setLetCoollectingPureNewCustomers(0);
                     doneAll.setLetBookingNewCustomersPureInfiltration(0);
                     doneAll.setLetOrderingOrders(0);
                     doneAll.setLetReceivingAmount(0.0);
                     doneAll.setLetPureOutOfTheLibrary(0);
                     doneAll.setLetOutboundNewCustomersPureInfiltration(0);
                     doneAll.setLetOutboundOrderNumber(0);
                     doneAll.setLetOutboundAmount(0.0);
                 }else {
                     doneAll.setLetUv(appletsBack.getLetUv()==null?0:appletsBack.getLetUv());
                     doneAll.setLetAddActiveUser(appletsBack.getLetAddActiveUser()==null?0:appletsBack.getLetAddActiveUser());
                     doneAll.setLetCoollectingPureNewCustomers(appletsBack.getLetCoollectingPureNewCustomers()==null?0:appletsBack.getLetCoollectingPureNewCustomers());
                     doneAll.setLetBookingNewCustomersPureInfiltration(appletsBack.getLetBookingNewCustomersPureInfiltration()==null?0:appletsBack.getLetBookingNewCustomersPureInfiltration());
                     doneAll.setLetOrderingOrders(appletsBack.getLetOrderingOrders()==null?0:appletsBack.getLetOrderingOrders());
                     doneAll.setLetReceivingAmount(appletsBack.getLetReceivingAmount()==null?0.0:appletsBack.getLetReceivingAmount());
                     doneAll.setLetPureOutOfTheLibrary(appletsBack.getLetPureOutOfTheLibrary()==null?0:appletsBack.getLetPureOutOfTheLibrary());
                     doneAll.setLetOutboundNewCustomersPureInfiltration(appletsBack.getLetOutboundNewCustomersPureInfiltration()==null?0:appletsBack.getLetOutboundNewCustomersPureInfiltration());
                     doneAll.setLetOutboundOrderNumber(appletsBack.getLetOutboundOrderNumber()==null?0:appletsBack.getLetOutboundOrderNumber());
                     doneAll.setLetOutboundAmount(appletsBack.getLetOutboundAmount()==null?0.0:appletsBack.getLetOutboundAmount());
                 }
            }
        }
        //分条汇总

        for (DangDangAll doneAll : doneAlls) {
            doneAll.setUv(doneAll.getDoneUv()+doneAll.getModUv()+doneAll.getLetUv());
            doneAll.setAddActiveUser(doneAll.getDoneAddActiveUser()+doneAll.getModAddActiveUser()+doneAll.getLetUv());
            doneAll.setCoollectingPureNewCustomers(doneAll.getDoneCoollectingPureNewCustomers()+doneAll.getModCoollectingPureNewCustomers()+doneAll.getLetCoollectingPureNewCustomers());
            doneAll.setBookingNewCustomersPureInfiltration(doneAll.getDoneBookingNewCustomersPureInfiltration()+doneAll.getModCoollectingPureNewCustomers()+doneAll.getLetCoollectingPureNewCustomers());
            doneAll.setOrderingOrders(doneAll.getDoneOrderingOrders()+doneAll.getModCoollectingPureNewCustomers()+doneAll.getLetCoollectingPureNewCustomers());
            doneAll.setReceivingAmount(doneAll.getDoneReceivingAmount()+doneAll.getModReceivingAmount()+doneAll.getLetReceivingAmount());
            doneAll.setPureOutOfTheLibrary(doneAll.getDonePureOutOfTheLibrary()+doneAll.getModPureOutOfTheLibrary()+doneAll.getLetPureOutOfTheLibrary());
            doneAll.setOutboundNewCustomersPureInfiltration(doneAll.getDoneOutboundNewCustomersPureInfiltration()+doneAll.getModOutboundNewCustomersPureInfiltration()+doneAll.getLetOutboundNewCustomersPureInfiltration());
            doneAll.setOutboundOrderNumber(doneAll.getDoneOutboundOrderNumber()+doneAll.getModOutboundOrderNumber()+doneAll.getLetOutboundOrderNumber());
            doneAll.setOutboundAmount(doneAll.getDoneOutboundAmount()+doneAll.getModOutboundAmount()+doneAll.getLetOutboundAmount());
            //出库纯新客出库金额 小程序url 小程序更改后汇总
            doneAll.setAllOutboundAmount(doneAll.getDoneOutboundAmount()+doneAll.getLetOutboundAmount());
            doneAll.setAllPureOutOfTheLibrary(doneAll.getDonePureOutOfTheLibrary()+doneAll.getLetPureOutOfTheLibrary());
        }
     return  doneAlls;
    }

}
