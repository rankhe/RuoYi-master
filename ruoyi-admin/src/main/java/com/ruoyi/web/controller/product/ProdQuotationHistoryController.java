package com.ruoyi.web.controller.product;

import java.util.List;
import java.util.stream.Collectors;

import com.ruoyi.system.domain.ProdCategory;
import com.ruoyi.system.domain.ProdInfo;
import com.ruoyi.system.service.IProdCategoryService;
import com.ruoyi.system.service.IProdInfoService;
import com.ruoyi.web.controller.product.vo.ProdQuotationHistoryVO;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
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
import com.ruoyi.system.domain.ProdQuotationHistory;
import com.ruoyi.system.service.IProdQuotationHistoryService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 产品报价信息Controller
 * 
 * @author ruoyi
 * @date 2023-09-01
 */
@Controller
@RequestMapping("/system/history")
public class ProdQuotationHistoryController extends BaseController
{
    private String prefix = "system/history";

    @Autowired
    private IProdQuotationHistoryService prodQuotationHistoryService;

    @Autowired
    private IProdInfoService prodInfoService;

    @Autowired
    private IProdCategoryService prodCategoryService;


    @RequiresPermissions("system:history:view")
    @GetMapping()
    public String history()
    {
        return prefix + "/history";
    }

    /**
     * 查询产品报价信息列表
     */
    @RequiresPermissions("system:history:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ProdQuotationHistory prodQuotationHistory)
    {
        startPage();
        List<ProdQuotationHistory> list = prodQuotationHistoryService.selectProdQuotationHistoryList(prodQuotationHistory);
        List<ProdQuotationHistoryVO> result = list.stream().map(i->{
            ProdQuotationHistoryVO vo  =new  ProdQuotationHistoryVO();
            BeanUtils.copyProperties(i,vo);
             ProdInfo prodInfo = prodInfoService.selectProdInfoById(i.getProdId());
             if(prodInfo!= null)
             {
                 final Long categoryId = prodInfo.getCategoryId();
                 final ProdCategory prodCategory = prodCategoryService.selectProdCategoryById(categoryId);
                 if(prodCategory!=null)
                 {
                     vo.setProdCategory(prodCategory.getName());
                 }
                 vo.setSubCategory1(prodInfo.getSubtype1());
                 vo.setSubCategory2(prodInfo.getSubtype2());
             }
             return vo;
        }).collect(Collectors.toList());
        return getDataTable(result);
    }

    /**
     * 导出产品报价信息列表
     */
    @RequiresPermissions("system:history:export")
    @Log(title = "产品报价信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ProdQuotationHistory prodQuotationHistory)
    {
        List<ProdQuotationHistory> list = prodQuotationHistoryService.selectProdQuotationHistoryList(prodQuotationHistory);
        ExcelUtil<ProdQuotationHistory> util = new ExcelUtil<ProdQuotationHistory>(ProdQuotationHistory.class);
        return util.exportExcel(list, "产品报价信息数据");
    }

    /**
     * 新增产品报价信息
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存产品报价信息
     */
    @RequiresPermissions("system:history:add")
    @Log(title = "产品报价信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ProdQuotationHistory prodQuotationHistory)
    {
        return toAjax(prodQuotationHistoryService.insertProdQuotationHistory(prodQuotationHistory));
    }

    /**
     * 修改产品报价信息
     */
    @RequiresPermissions("system:history:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        ProdQuotationHistory prodQuotationHistory = prodQuotationHistoryService.selectProdQuotationHistoryById(id);
        mmap.put("prodQuotationHistory", prodQuotationHistory);
        return prefix + "/edit";
    }

    /**
     * 修改保存产品报价信息
     */
    @RequiresPermissions("system:history:edit")
    @Log(title = "产品报价信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ProdQuotationHistory prodQuotationHistory)
    {
        return toAjax(prodQuotationHistoryService.updateProdQuotationHistory(prodQuotationHistory));
    }

    /**
     * 删除产品报价信息
     */
    @RequiresPermissions("system:history:remove")
    @Log(title = "产品报价信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(prodQuotationHistoryService.deleteProdQuotationHistoryByIds(ids));
    }
}
