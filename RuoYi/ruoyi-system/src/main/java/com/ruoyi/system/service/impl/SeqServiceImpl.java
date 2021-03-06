package com.ruoyi.system.service.impl;

import com.ruoyi.system.mapper.SeqMapper;
import com.ruoyi.system.service.SeqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeqServiceImpl implements SeqService {

    @Autowired
    private SeqMapper seqMapper;

    @Override
    public String selectNumByType(String type1, String type2, String region) {
        return seqMapper.selectNumByType(type1, type2, region);
    }

    @Override
    public void insertSeq(String type1, String type2, String region) {
        seqMapper.insertSeq(type1, type2,region);
    }

    @Override
    public void addSeqNumByType(String type1, String type2, String region) {
        seqMapper.addSeqNumByType(type1, type2,region);
    }
}
