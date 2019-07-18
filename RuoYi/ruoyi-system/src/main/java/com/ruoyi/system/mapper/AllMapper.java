package com.ruoyi.system.mapper;


import com.ruoyi.system.domain.DangDangAll;
import com.ruoyi.system.domain.DangDangModBack;
import com.ruoyi.system.domain.DangdangAppletsBack;
import com.ruoyi.system.domain.DangdangSearchAdd;

import java.sql.Date;
import java.util.List;

public interface AllMapper {


    /**
     * 查询小程序更改后的前后匹配 及前端匹配
     * @param all
     * @return
     */
    public List<DangDangAll> queryDone(DangdangSearchAdd date);

    /**
     * 查询小程序url的前后匹配
     * @param all
     * @return
     */
    public List<DangdangAppletsBack> queryUrl(DangdangSearchAdd date);

    /**
     * 查询移动端url的匹配
     * @param all
     * @return
     */
    public List<DangDangModBack> queryMod(DangdangSearchAdd date);
}
