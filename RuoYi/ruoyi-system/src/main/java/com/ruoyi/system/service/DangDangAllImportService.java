package com.ruoyi.system.service;


import com.ruoyi.system.domain.DangDangAll;
import com.ruoyi.system.domain.DangdangSearchAdd;

import java.util.List;

public interface DangDangAllImportService {
    /**
     * 当当导出Excel
     *
     * @param date
     * @return
     */
    public List<DangDangAll> importDangDangAll(DangdangSearchAdd date);
}
