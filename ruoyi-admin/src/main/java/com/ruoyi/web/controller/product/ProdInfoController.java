package com.ruoyi.web.controller.product;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.ShiroUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.product.domain.ProdCategory;
import com.ruoyi.product.domain.ProdInfo;
import com.ruoyi.product.service.IBatchInfoService;
import com.ruoyi.product.service.IProdCategoryService;
import com.ruoyi.product.service.IProdInfoService;
import com.ruoyi.web.controller.product.vo.ProdInfoVO;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 产品信息Controller
 * 
 * @author ruoyi
 * @date 2023-09-01
 */
@Controller
@RequestMapping("/product/info")
public class ProdInfoController extends BaseController
{
    private String prefix = "product/info";

    @Autowired
    private IProdInfoService prodInfoService;

    @Autowired
    private IProdCategoryService prodCategoryService;

    @Autowired
    private IBatchInfoService batchInfoService;

    @RequiresPermissions("product:info:view")
    @GetMapping()
    public String info()
    {
        return prefix + "/info";
    }

    /**
     * 查询产品信息列表
     */
//    @RequiresPermissions("product:info:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ProdInfo prodInfo)
    {
        startPage();
        List<ProdInfo> list = prodInfoService.selectProdInfoList(prodInfo);
        List<ProdInfoVO> result = list.stream().map(i -> {
            ProdInfoVO vo = new ProdInfoVO();
            BeanUtils.copyProperties(i, vo);
            final Long categoryId = i.getCategoryId();
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
    @RequiresPermissions("product:info:export")
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
    public String add(ModelMap modelMap)
    {
        modelMap.put("batches",batchInfoService.selectProductBatchInfoList(getUserId()));
        modelMap.put("categories", prodCategoryService.selectCategoryAll());
        return prefix + "/add";
    }

    /**
     * 新增保存产品信息
     */
    @RequiresPermissions("product:info:add")
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
    @RequiresPermissions("product:info:edit")
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
    @RequiresPermissions("product:info:edit")
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
    @RequiresPermissions("product:info:remove")
    @Log(title = "产品信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(prodInfoService.deleteProdInfoByIds(ids));
    }

    /**
     * 导入产品信息
     * @param file
     * @param updateSupport
     * @return
     * @throws Exception
     */
    @RequiresPermissions("product:info:import")
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<ProdInfoVO> util = new ExcelUtil<ProdInfoVO>(ProdInfoVO.class);
        List<ProdInfoVO> prodInfoVOS = util.importExcel(file.getInputStream());

        List<ProdInfo> prodInfoList =  new ArrayList<>();
        if(!CollectionUtils.isEmpty(prodInfoVOS))
        {
            prodInfoList = prodInfoVOS.stream().map(i->{
                ProdInfo prodInfo = new ProdInfo();
                BeanUtils.copyProperties(i,prodInfo);
                return prodInfo;
            }).collect(Collectors.toList());
        }
        String operName = ShiroUtils.getSysUser().getLoginName();
        Integer message = prodInfoService.importProduct(prodInfoList, updateSupport, operName);
        return AjaxResult.success(message);
    }

    @RequiresPermissions("product:info:view")
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
        ExcelUtil<ProdInfoVO> util = new ExcelUtil<ProdInfoVO>(ProdInfoVO.class);
        return util.importTemplateExcel("产品数据");
    }
}
