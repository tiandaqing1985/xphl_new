package com.ruoyi.system.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SeqMapper {
    public String selectNumByType(String type1,String type2,String region);
    public void insertSeq(String type1,String type2,String region);
    public void addSeqNumByType(String type1,String type2,String region);
}
