package com.ruoyi.system.service;
//打印相关
public interface IPrintService {
    String previewBaoxiao(String num);

    String previewBaoxiaoDetail(String num);

    String previewjiekuan(String num);

    String previewchucai(String num);

    String previewduigong(String num);

    String previewDuigongzhifuDetail(String num);

    String previewTuanjian(String num);

    String previewZhaodaifei(String num);
}
