package com.ruoyi.web.controller.product;

import java.util.List;
import java.util.stream.Collectors;

import com.ruoyi.system.domain.ProdCategory;
import com.ruoyi.system.service.IProdCategoryService;
import com.ruoyi.web.controller.product.vo.ProdInfoVO;
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
import com.ruoyi.system.domain.ProdInfo;
import com.ruoyi.system.service.IProdInfoService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 产品信息Controller
 * 
 * @author ruoyi
 * @date 2023-09-01
 */
@Controller
@RequestMapping("/system/info")
public class ProdInfoController extends BaseController
{
    private String prefix = "system/info";

    @Autowired
    private IProdInfoService prodInfoService;

    @Autowired
    private IProdCategoryService prodCategoryService;

    @RequiresPermissions("system:info:view")
    @GetMapping()
    public String info()
    {
        return prefix + "/info";
    }

    /**
     * 查询产品信息列表
     */
    @RequiresPermissions("system:info:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ProdInfo prodInfo)
    {
        startPage();
        List<ProdInfo> list = prodInfoService.selectProdInfoList(prodInfo);
        List<ProdInfoVO> result = list.stream().map(i -> {
            ProdInfoVO vo = new ProdInfoVO();
            BeanUtils.copyProperties(i, vo);
            final Long categoryId = prodInfo.getCategoryId();
            final ProdCategory prodCategory = prodCategoryService.selectProdCategoryById(categoryId);
            if(prodCategory != null)
            {
                vo.setProdCategory(prodCategory.getName());
            }
            return vo;
        }).collect(Collectors.toList());
        return getDataTable(result);
    }

    /**
     * 导出产品信息列表
     */
    @RequiresPermissions("system:info:export")
    @Log(title = "产品信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ProdInfo prodInfo)
    {
        List<ProdInfo> list = prodInfoService.selectProdInfoList(prodInfo);
        ExcelUtil<ProdInfo> util = new ExcelUtil<ProdInfo>(ProdInfo.class);
        return util.exportExcel(list, "产品信息数据");
    }

    /**
     * 新增产品信息
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存产品信息
     */
    @RequiresPermissions("system:info:add")
    @Log(title = "产品信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ProdInfo prodInfo)
    {
        return toAjax(prodInfoService.insertProdInfo(prodInfo));
    }

    /**
     * 修改产品信息
     */
    @RequiresPermissions("system:info:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        ProdInfo prodInfo = prodInfoService.selectProdInfoById(id);
        mmap.put("prodInfo", prodInfo);
        return prefix + "/edit";
    }

    /**
     * 修改保存产品信息
     */
    @RequiresPermissions("system:info:edit")
    @Log(title = "产品信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ProdInfo prodInfo)
    {
        return toAjax(prodInfoService.updateProdInfo(prodInfo));
    }

    /**
     * 删除产品信息
     */
    @RequiresPermissions("system:info:remove")
    @Log(title = "产品信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(prodInfoService.deleteProdInfoByIds(ids));
    }
}
