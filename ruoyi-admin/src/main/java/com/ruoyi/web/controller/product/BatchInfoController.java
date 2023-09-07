package com.ruoyi.web.controller.product;

import java.util.Calendar;
import java.util.List;

import com.ruoyi.common.enums.CommonStatus;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.BatchInfo;
import com.ruoyi.system.service.IBatchInfoService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 批次信息Controller
 * 
 * @author ruoyi
 * @date 2023-09-01
 */
@Controller
@RequestMapping("/product/batch")
public class BatchInfoController extends BaseController
{
    private String prefix = "product/batch";

    @Autowired
    private IBatchInfoService batchInfoService;

    @RequiresPermissions("product:batch:view")
    @GetMapping()
    public String info()
    {
        return prefix + "/batch";
    }

    /**
     * 查询批次信息列表
     */
    @RequiresPermissions("product:batch:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(BatchInfo batchInfo)
    {
        startPage();
        batchInfo.setStatus(CommonStatus.OK.getCode());
        List<BatchInfo> list = batchInfoService.selectBatchInfoList(batchInfo);
        return getDataTable(list);
    }

    /**
     * 查询指定发布者发布的批次
     */
//    @RequiresPermissions("product:batch:list")
    @PostMapping("/list/{publish}")
    @ResponseBody
    public List<BatchInfo> list(@PathVariable(value = "publish") Long publish)
    {
        startPage();
        logger.info("publish[{}]",publish);
        BatchInfo batchInfo = new  BatchInfo();
        batchInfo.setOwnerId(publish);
        List<BatchInfo> list = batchInfoService.selectBatchInfoList(batchInfo);
        return list;
    }
    /**
     * 导出批次信息列表
     */
    @RequiresPermissions("product:batch:export")
    @Log(title = "批次信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BatchInfo batchInfo)
    {
        List<BatchInfo> list = batchInfoService.selectBatchInfoList(batchInfo);
        ExcelUtil<BatchInfo> util = new ExcelUtil<BatchInfo>(BatchInfo.class);
        return util.exportExcel(list, "批次信息数据");
    }

    /**
     * 新增批次信息
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存批次信息
     */
    @RequiresPermissions("product:batch:add")
    @Log(title = "批次信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(BatchInfo batchInfo)
    {
        batchInfo.setOwnerId(getUserId());
        batchInfo.setQuotation(0l);
        batchInfo.setCreateBy(getLoginName());
        batchInfo.setCreateTime(Calendar.getInstance().getTime());
        return toAjax(batchInfoService.insertBatchInfo(batchInfo));
    }

    /**
     * 修改批次信息
     */
    @RequiresPermissions("product:batch:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        BatchInfo batchInfo = batchInfoService.selectBatchInfoById(id);
        mmap.put("batchInfo", batchInfo);
        return prefix + "/edit";
    }

    /**
     * 修改保存批次信息
     */
    @RequiresPermissions("product:batch:edit")
    @Log(title = "批次信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(BatchInfo batchInfo)
    {
        return toAjax(batchInfoService.updateBatchInfo(batchInfo));
    }

    /**
     * 删除批次信息
     */
    @RequiresPermissions("product:batch:remove")
    @Log(title = "批次信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(batchInfoService.deleteBatchInfoByIds(ids));
    }
}
