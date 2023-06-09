package com.ruoyi.web.controller.system;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.system.domain.FinancingHistory;
import com.ruoyi.system.service.ICorporateInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/system/corporate")
public class CorporateInfoController extends BaseController
{
    private String prefix = "system/corporate";

    @Autowired
    private ICorporateInfoService corporateInfoService;


    @GetMapping()
    public String list()
    {
        return prefix + "/list";
    }

    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(FinancingHistory financingHistory)
    {
        startPage();
        List<FinancingHistory> list = corporateInfoService.selectFinancingHistoryList(financingHistory);
        return getDataTable(list);
    }
}
