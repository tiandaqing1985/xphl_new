package com.ruoyi.system.service;

public interface SeqService {
    public String selectNumByType(String type1,String type2);
    public void insertSeq(String type1,String type2);
    public void addSeqNumByType(String type1,String type2);
}
