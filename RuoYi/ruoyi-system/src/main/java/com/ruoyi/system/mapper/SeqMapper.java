package com.ruoyi.system.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SeqMapper {
    public String selectNumByType(String type1,String type2);
    public void insertSeq(String type1,String type2);
    public void addSeqNumByType(String type1,String type2);
}
