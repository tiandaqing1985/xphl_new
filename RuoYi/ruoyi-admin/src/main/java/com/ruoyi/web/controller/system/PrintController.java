package com.ruoyi.web.controller.system;

import com.ruoyi.system.service.IPrintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/system/print")
public class PrintController {

    @Autowired
    private IPrintService printService;

    @GetMapping("preview")
    @ResponseBody
    public String previewPrint(String num,String type) {

        if(type==null){
            return "参数不正确";
        }
        if(type.equals("baoxiao")){
            return printService.previewBaoxiao(num);
        }else if(type.equals("baoxiaoDetail")){
            return printService.previewBaoxiaoDetail(num);
        }else{
            return "参数不正确";
        }

    }

}
