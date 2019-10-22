package com.ruoyi.web.controller.system;

import com.ruoyi.system.service.IPrintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/system/print")
public class PrintController {

    @Autowired
    private IPrintService printService;

    @GetMapping("/view")
    public String view(String num, String type, ModelMap map) {
        map.put("num", num);
        map.put("type1", type);
        if (type.equals("baoxiao")) {

            map.put("btn1", "报销单");
            map.put("btn2", "报销单明细");
            map.put("type2", "baoxiaoDetail");

        } else if (type.equals("duigong")) {

            map.put("btn1", "对公申请");
            map.put("btn2", "申请明细");
            map.put("type2", "duigongDetail");

        }
        return "system/print/printview";

    }

    @GetMapping("/preview")
    @ResponseBody
    public String previewPrint(String num, String type) {

        if (type == null) {
            return "参数不正确";
        }
        if (type.equals("baoxiao")) {
            return printService.previewBaoxiao(num);
        } else if (type.equals("baoxiaoDetail")) {
            return printService.previewBaoxiaoDetail(num);
        } else if (type.equals("jiekuan")) {
            return printService.previewjiekuan(num);
        } else if (type.equals("chuchai")) {
            return printService.previewchucai(num);
        } else if (type.equals("duigong")) {
            return printService.previewduigong(num);
        } else if (type.equals("duigongDetail")) {
            return printService.previewDuigongzhifuDetail(num);
        } else if (type.equals("tuanjian")) {
            return printService.previewTuanjian(num);
        } else if (type.equals("zhaodaifei")) {
            return printService.previewZhaodaifei(num);
        } else {
            return "参数不正确";
        }

    }

}
