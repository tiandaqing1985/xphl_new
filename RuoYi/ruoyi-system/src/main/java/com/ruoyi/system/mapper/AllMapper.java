package com.ruoyi.system.mapper;


import com.ruoyi.system.domain.*;


import java.util.List;

public interface AllMapper {


    /**
     * 查询小程序更改后的前后匹配 及前端匹配
     * @param
     * @return
     */
   List<DangDangAll> queryDone(DangdangSearchAdd date);

    /**
     * 查询小程序url的前后匹配
     * @param
     * @return
     */
  List<DangdangBack> queryUrl(DangdangSearchAdd date);

    /**
     * 查询移动端url的匹配
     * @param
     * @return
     */
     List<DangdangBack> queryMod(DangdangSearchAdd date);
}
