package com.ruoyi.system.service.impl;

import com.fasterxml.jackson.databind.node.BigIntegerNode;
import com.ruoyi.system.domain.*;
import com.ruoyi.system.mapper.AllMapper;
import com.ruoyi.system.mapper.DangdangBackMapper;
import com.ruoyi.system.service.DangDangAllImportService;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @program: ruoyi->DangDangAllImportServiceImpl
 * @author: gakki
 * @create: 2019-07-16 20:39
 **/
@Service
public class DangDangAllImportServiceImpl implements DangDangAllImportService {

    private static final Logger log = LoggerFactory.getLogger(DangDangAllImportServiceImpl.class);

    @Autowired
    AllMapper allMapper;
    @Autowired
    DangdangBackMapper dangdangBackMapper;


    @Override
    public List<DangDangAll> importDangDangAll(DangdangBack date) {
       DecimalFormat bigDecimel = new java.text.DecimalFormat("#.00");
        List<Integer> doneIds = allMapper.otherDone(date);
        List<Integer> modIds = allMapper.otherMOd(date);
        List<Integer> same = (List<Integer>) getSame(doneIds, modIds);
        List<Integer> urlIds = allMapper.otherUrl(date);
        List<Integer> all = (List<Integer>) getSame(same,urlIds);
        System.out.println("其他数量"+all.size());
        List<DangDangAll> dangDangAlls = allMapper.selectByIds(all);
        System.out.println("其他出库金额"+dangDangAlls.stream().mapToDouble(DangDangAll::getOutboundAmount).sum());
        List<DangDangAll> doneAlls = allMapper.queryDone(date);
        List<DangdangBack> dangDangModBacks = dangdangBackMapper.queryMod(date);
        List<DangdangBack> appletsBacks = dangdangBackMapper.queryUrl(date);
        for (int i = 0; i < doneAlls.size(); i++) {
            doneAlls.get(i).setClickRate(doneAlls.get(i).getClickRate() == null ? "0.00%" : doneAlls.get(i).getClickRate() + "%");
            if (doneAlls.get(i).getClickRate().equals("0")) {
                doneAlls.get(i).setClickRate("0.00%");
            }
            doneAlls.get(i).setfAppShowData(doneAlls.get(i).getfAppShowData() == null ? 0 : doneAlls.get(i).getfAppShowData());
            doneAlls.get(i).setfAppClick(doneAlls.get(i).getfAppClick() == null ? 0 : doneAlls.get(i).getfAppClick());
            doneAlls.get(i).setfAppCost(doneAlls.get(i).getfAppCost() == null ? 0.0 : doneAlls.get(i).getfAppCost());
            doneAlls.get(i).setfLetsShowData(doneAlls.get(i).getfLetsShowData() == null ? 0 : doneAlls.get(i).getfLetsShowData());
            doneAlls.get(i).setfLetsClick(doneAlls.get(i).getfLetsClick() == null ? 0 : doneAlls.get(i).getfLetsClick());
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
                doneAlls.get(i).setLetOutboundNewCustomersPureInfiltration(appletsBacks.get(i).getOutboundNewCustomersPureInfiltration() == null ? 0 : appletsBacks.get(i).getOutboundNewCustomersPureInfiltration());
                doneAlls.get(i).setLetOutboundOrderNumber(appletsBacks.get(i).getOutboundOrderNumber() == null ? 0 : appletsBacks.get(i).getOutboundOrderNumber());
                doneAlls.get(i).setLetOutboundAmount(appletsBacks.get(i).getOutboundAmount() == null ? 0.0 : appletsBacks.get(i).getOutboundAmount());
            }
            doneAlls.get(i).setUv(doneAlls.get(i).getDoneUv() + doneAlls.get(i).getModUv() + doneAlls.get(i).getLetUv());
            doneAlls.get(i).setAddActiveUser(doneAlls.get(i).getDoneAddActiveUser() + doneAlls.get(i).getModAddActiveUser() + doneAlls.get(i).getLetAddActiveUser());
            doneAlls.get(i).setCoollectingPureNewCustomers(doneAlls.get(i).getDoneCoollectingPureNewCustomers() + doneAlls.get(i).getModCoollectingPureNewCustomers() + doneAlls.get(i).getLetCoollectingPureNewCustomers());
            doneAlls.get(i).setBookingNewCustomersPureInfiltration(doneAlls.get(i).getDoneBookingNewCustomersPureInfiltration() + doneAlls.get(i).getModCoollectingPureNewCustomers() + doneAlls.get(i).getLetCoollectingPureNewCustomers());
            doneAlls.get(i).setOrderingOrders(doneAlls.get(i).getDoneOrderingOrders() + doneAlls.get(i).getModCoollectingPureNewCustomers() + doneAlls.get(i).getLetCoollectingPureNewCustomers());
            doneAlls.get(i).setReceivingAmount(doneAlls.get(i).getDoneReceivingAmount() + doneAlls.get(i).getModReceivingAmount() + doneAlls.get(i).getLetReceivingAmount());
            doneAlls.get(i).setPureOutOfTheLibrary(doneAlls.get(i).getDonePureOutOfTheLibrary() + doneAlls.get(i).getModPureOutOfTheLibrary() + doneAlls.get(i).getLetPureOutOfTheLibrary());
            doneAlls.get(i).setOutboundNewCustomersPureInfiltration(doneAlls.get(i).getDoneOutboundNewCustomersPureInfiltration() + doneAlls.get(i).getModOutboundNewCustomersPureInfiltration() + doneAlls.get(i).getLetOutboundNewCustomersPureInfiltration());
            doneAlls.get(i).setOutboundOrderNumber(doneAlls.get(i).getDoneOutboundOrderNumber() + doneAlls.get(i).getModOutboundOrderNumber() + doneAlls.get(i).getLetOutboundOrderNumber());
            doneAlls.get(i).setOutboundAmount(doneAlls.get(i).getDoneOutboundAmount() + doneAlls.get(i).getModOutboundAmount() + doneAlls.get(i).getLetOutboundAmount());

            //*************************************************



            //出库纯新客出库金额 小程序url 小程序更改后汇总
            doneAlls.get(i).setAllOutboundAmount(doneAlls.get(i).getDoneOutboundAmount() + doneAlls.get(i).getLetOutboundAmount());
            doneAlls.get(i).setAllPureOutOfTheLibrary(doneAlls.get(i).getDonePureOutOfTheLibrary() + doneAlls.get(i).getLetPureOutOfTheLibrary());
            //去除app消费 .subtract(new BigDecimal( doneAlls.get(i).getfAppCost().toString())).doubleValue()
            doneAlls.get(i).setRemoveAppCost(new BigDecimal(doneAlls.get(i).getfCost().toString()).subtract(new BigDecimal(doneAlls.get(i).getfAppCost().toString())).doubleValue());
            Double appActCost = doneAlls.get(i).getRemoveAppCost() / 1.3;//ctr
            DecimalFormat df = new DecimalFormat("#.00");
            //去除app实际消费
            if (appActCost.compareTo(Double.valueOf(0.00)) <= 0) {
                doneAlls.get(i).setAppActCost(Double.valueOf("0.00"));
            }
            doneAlls.get(i).setAppActCost(Double.valueOf(df.format(appActCost)));
            //去除app的点击
            int rAppClick = (doneAlls.get(i).getfClick() - doneAlls.get(i).getfAppClick()) >= 0 ? (doneAlls.get(i).getfClick() - doneAlls.get(i).getfAppClick()) : 0;
            doneAlls.get(i).setRemoveAppClick(rAppClick);
            //出库转化率
            if (doneAlls.get(i).getOutboundOrderNumber() == 0) {
                doneAlls.get(i).setOutBoundRate("-");
            } else if (doneAlls.get(i).getAppActCost().equals(Double.valueOf("0.00"))) {
                doneAlls.get(i).setOutBoundRate("100%");
            }
            //出库转化率
            //总出库订单数
            BigDecimal allOutOrders = new BigDecimal(doneAlls.get(i).getOutboundOrderNumber()).multiply(new BigDecimal(100));
            //去除app的点击
            int removeAppClick = doneAlls.get(i).getfClick() - doneAlls.get(i).getfAppClick();
            if (removeAppClick <= 0) {
                doneAlls.get(i).setOutBoundRate("-");
            } else {
                BigDecimal xx = allOutOrders.divide(new BigDecimal(removeAppClick), 2, RoundingMode.HALF_DOWN);
                doneAlls.get(i).setOutBoundRate(bigDecimel.format(xx) + "%");
            }

            if (doneAlls.get(i).getOutBoundRate().startsWith(".")) {
                doneAlls.get(i).setOutBoundRate("0" + doneAlls.get(i).getOutBoundRate());
            }
            //CTR
            BigDecimal ctrCost = new BigDecimal(doneAlls.get(i).getfClick()).subtract(new BigDecimal(doneAlls.get(i).getfAppClick()));
            BigDecimal v1 = ((ctrCost.compareTo(new BigDecimal(0)) >= 0 ? ctrCost : new BigDecimal(0)).multiply(new BigDecimal(100))).divide((new BigDecimal(doneAlls.get(i).getfShowDate()).compareTo(new BigDecimal(0)) > 0 ? new BigDecimal(doneAlls.get(i).getfShowDate()) : new BigDecimal(1)), 2, RoundingMode.HALF_DOWN);
            doneAlls.get(i).setCTR(bigDecimel.format(v1) + "%");
            if (doneAlls.get(i).getCTR().startsWith(".")) {
                doneAlls.get(i).setCTR("0" + doneAlls.get(i).getCTR());
            }
            //CPC
            BigDecimal cpcCost = new BigDecimal(doneAlls.get(i).getfCost()).subtract(new BigDecimal(doneAlls.get(i).getfAppCost()));
            BigDecimal cpcClick = new BigDecimal(doneAlls.get(i).getfClick()).subtract(new BigDecimal(doneAlls.get(i).getfAppClick()));
            BigDecimal v2 = (cpcCost.compareTo(new BigDecimal(0)) >= 0 ? cpcCost : new BigDecimal(0)).divide(cpcClick.compareTo(new BigDecimal(0)) > 0 ? cpcClick : new BigDecimal(1), 2, RoundingMode.HALF_DOWN);
            doneAlls.get(i).setCPC(bigDecimel.format(v2));
            if (doneAlls.get(i).getCPC().startsWith(".")) {
                doneAlls.get(i).setCPC("0" + doneAlls.get(i).getCPC());
            }
            //出库ROI
            final BigDecimal v3 = new BigDecimal(doneAlls.get(i).getOutboundAmount()).divide(new BigDecimal(doneAlls.get(i).getAppActCost().compareTo(Double.valueOf("0.00")) > 0 ? doneAlls.get(i).getAppActCost() : 1), 2, RoundingMode.HALF_DOWN);
//            double v3 = doneAlls.get(i).getOutboundAmount() / doneAlls.get(i).getAppActCost();
            doneAlls.get(i).setOutBoundROI(bigDecimel.format(v3));
            if (doneAlls.get(i).getOutBoundROI().startsWith(".")) {
                doneAlls.get(i).setOutBoundROI("0" + doneAlls.get(i).getOutBoundROI());
            }
            //出库新客成本
            if (doneAlls.get(i).getPureOutOfTheLibrary() == 0) {
                doneAlls.get(i).setOutBoubdCost("-");
            } else {
                BigDecimal v4 = new BigDecimal(doneAlls.get(i).getAppActCost()).divide(new BigDecimal(doneAlls.get(i).getPureOutOfTheLibrary()), 2, RoundingMode.HALF_DOWN);
                doneAlls.get(i).setOutBoubdCost(bigDecimel.format(v4));
                if (doneAlls.get(i).getOutBoubdCost().startsWith(".")) {
                    doneAlls.get(i).setOutBoubdCost("0" + doneAlls.get(i).getOutBoubdCost());
                }
            }


            //收订订单转化率
            int result = doneAlls.get(i).getfClick() - doneAlls.get(i).getfAppClick();
            if (result == 0) {
                doneAlls.get(i).setOrderRate("-");
            }
            BigDecimal orderRate = new BigDecimal(doneAlls.get(i).getOrderingOrders()).multiply(new BigDecimal(100)).divide(new BigDecimal(result > 0 ? result : 1), 2, RoundingMode.HALF_DOWN);
//                int orderRate = doneAlls.get(i).getOrderingOrders() / result;
            doneAlls.get(i).setOrderRate(bigDecimel.format(orderRate) + "%");
            if (doneAlls.get(i).getOrderRate().startsWith(".")) {
                doneAlls.get(i).setOrderRate("0" + doneAlls.get(i).getOrderRate());
            }
            //收订ROi
            if (doneAlls.get(i).getAppActCost().compareTo(Double.valueOf("0.00")) <= 0) {
                doneAlls.get(i).setOrderROI(df.format(doneAlls.get(i).getReceivingAmount() >= 0.00 ? doneAlls.get(i).getReceivingAmount() : 0.00));
            }

            if (doneAlls.get(i).getReceivingAmount().compareTo(Double.valueOf("0.0")) == 0) {
                doneAlls.get(i).setOrderROI("0.00");
            }


            BigDecimal orderROi = new BigDecimal(doneAlls.get(i).getReceivingAmount()).divide(new BigDecimal(doneAlls.get(i).getAppActCost().compareTo(0.00) > 0 ? doneAlls.get(i).getAppActCost() : 1), 2, RoundingMode.HALF_DOWN);

            doneAlls.get(i).setOrderROI(bigDecimel.format(orderROi));
            if (doneAlls.get(i).getOrderROI().startsWith(".")) {
                doneAlls.get(i).setOrderROI("0" + bigDecimel.format(orderROi));
            }
            //收订新客成本
            if (doneAlls.get(i).getCoollectingPureNewCustomers() == 0) {
                doneAlls.get(i).setOrderNewCost("-");
            } else {
                BigDecimal removeAppCost = new BigDecimal(doneAlls.get(i).getfCost()).subtract(new BigDecimal(doneAlls.get(i).getfAppCost())).divide(new BigDecimal(1.3), 2, RoundingMode.HALF_DOWN);
                BigDecimal orderNewCost = removeAppCost.divide(new BigDecimal(doneAlls.get(i).getCoollectingPureNewCustomers()), 2, RoundingMode.HALF_DOWN);
                doneAlls.get(i).setOrderNewCost(bigDecimel.format(orderNewCost));
                if (bigDecimel.format(orderNewCost).startsWith(".")) {
                    doneAlls.get(i).setOrderNewCost("0" + bigDecimel.format(orderNewCost));
                }
            }
        }
        System.out.println("H5总出库金额"+doneAlls.stream().mapToDouble(DangDangAll::getModOutboundAmount).sum());
        System.out.println("小程序总出库金额"+doneAlls.stream().mapToDouble(DangDangAll::getDoneOutboundAmount).sum());
        System.out.println("小程序更改后总出库金额"+doneAlls.stream().mapToDouble(DangDangAll::getLetOutboundAmount).sum());
        System.out.println("总出库金额"+doneAlls.stream().mapToDouble(DangDangAll::getOutboundAmount).sum());
        doneAlls.addAll(dangDangAlls);
        System.out.println("总出库金额"+doneAlls.stream().mapToDouble(DangDangAll::getOutboundAmount).sum());
        return doneAlls;
    }

    public static Collection getSame(Collection collmax, Collection collmin) {
        //使用LinkedList防止差异过大时,元素拷贝
        Collection csReturn = new LinkedList();
        Collection max = collmax;
        Collection min = collmin;
        //先比较大小,这样会减少后续map的if判断次数
        if (collmax.size() < collmin.size()) {
            max = collmin;
            min = collmax;
        }
        //直接指定大小,防止再散列
        Map<Object, Integer> map = new HashMap<Object, Integer>(max.size());
        for (Object object : max) {
            map.put(object, 1);
        }
        for (Object object : min) {
            if (map.get(object) != null) {
                csReturn.add(object);
            }
        }
        return csReturn;
    }

}
