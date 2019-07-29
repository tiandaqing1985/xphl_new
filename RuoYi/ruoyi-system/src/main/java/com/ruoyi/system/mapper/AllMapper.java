package com.ruoyi.system.mapper;


import com.ruoyi.system.domain.*;
import org.apache.ibatis.annotations.Param;
import scala.Int;


import java.util.List;

public interface AllMapper {


    /**
     * 查询小程序更改后的前后匹配 及前端匹配
     *
     * @param
     * @return
     */
    List<DangDangAll> queryDone(DangdangBack date);

    /**
     * 查询小程序url的前后匹配
     *
     * @param
     * @return
     */
    List<DangdangBack> queryUrl(DangdangBack date);

    /**
     * 查询移动端url的匹配
     *
     * @param
     * @return
     */
    List<DangdangBack> queryMod(DangdangBack date);

    List<Integer> otherDone(DangdangBack date);

    List<Integer> otherUrl(DangdangBack date);

    List<Integer> otherMOd(DangdangBack date);

    List<DangDangAll> selectByIds(@Param("ids") List<Integer> ids);

    DangDangAll selectIds(String id);


}
