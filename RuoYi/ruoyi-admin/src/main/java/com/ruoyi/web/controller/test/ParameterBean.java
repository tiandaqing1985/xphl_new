package com.ruoyi.web.controller.test;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.CreditCardNumber;


import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import java.util.Date;


/**
 * @program: JWT->ParameterBean
 * @author: gakki
 * @create: 2019-07-26 14:24
 **/
@DefaultFiled
public class ParameterBean extends Object {
    /**
     * 银行卡号
     */
    @CreditCardNumber(message = "银行卡开户行账号不对")
    private String bankAmount;


    /**
     * 账户
     */
    @NotBlank(message = "账户不能为空")
    private String account;
    @Future(message = "时间不对")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date time;
    /**
     * 备注
     */
    @DefaultFiled(msg = "无")
    private String remarks;
    @DefaultFiled(date = "2019-01-01")
    private Date testTime;
    @DefaultFiled(msg="1")
    private  Integer num;

    public String getBankAmount() {
        return bankAmount;
    }

    public void setBankAmount(String bankAmount) {
        this.bankAmount = bankAmount;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Date getTestTime() {
        return testTime;
    }

    public void setTestTime(Date testTime) {
        this.testTime = testTime;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
