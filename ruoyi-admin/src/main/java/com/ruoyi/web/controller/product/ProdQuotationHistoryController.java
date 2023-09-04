package com.ruoyi.web.controller.product;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.system.domain.ProdCategory;
import com.ruoyi.system.domain.ProdInfo;
import com.ruoyi.system.service.IProdCategoryService;
import com.ruoyi.system.service.IProdInfoService;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.web.controller.product.vo.MyProdQuotationExportVO;
import com.ruoyi.web.controller.product.vo.ProdQuotationHistoryVO;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
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

    @Autowired
    private ISysUserService sysUserService;

    @RequiresPermissions("system:history:view")
    @GetMapping()
    public String history()
    {
        return prefix + "/history";
    }

    /**
     * 查询产收到的报价
     */
    @RequiresPermissions("system:history:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ProdQuotationHistory prodQuotationHistory)
    {
        startPage();

        prodQuotationHistory.setOwnerUserId(getUserId());
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
        prodQuotationHistory.setOwnerUserId(getUserId());
        List<ProdQuotationHistory> list = prodQuotationHistoryService.selectProdQuotationHistoryList(prodQuotationHistory);
        ExcelUtil<ProdQuotationHistory> util = new ExcelUtil<ProdQuotationHistory>(ProdQuotationHistory.class);
        return util.exportExcel(list, "产品报价信息数据");
    }



    /**
     * 新增产品报价信息
     */
    @GetMapping("/add")
    public String add(ModelMap modelMap)
    {
        List<SysUser> sysUsers = sysUserService.selectUserByRoleId(100l);
        modelMap.put("publishes",sysUsers);
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
        prodQuotationHistory.setQuoterUserId(getUserId());
        prodQuotationHistory.setCreateTime(Calendar.getInstance().getTime());
        prodQuotationHistory.setCreateBy(getLoginName());
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


    /**
     * 更新报价状态
     */
    @RequiresPermissions("system:history:remove")
    @Log(title = "更新报价状态", businessType = BusinessType.UPDATE)
    @PostMapping( "/updateStatus")
    @ResponseBody
    public AjaxResult updateStatus(Long id)
    {
        logger.info("updateStatus[{}]",id);
        ProdQuotationHistory prodQuotationHistory = prodQuotationHistoryService.selectProdQuotationHistoryById(id);
        prodQuotationHistory.setStatus(2l);
        return toAjax(prodQuotationHistoryService.updateProdQuotationHistory(prodQuotationHistory));
    }

    @RequiresPermissions("system:history:quoter:view")
    @GetMapping("/quoter")
    public String quoter()
    {
        return prefix + "/quoter";
    }

    /**
     * 查询我的报价信息
     */
    @RequiresPermissions("system:history:quoter/list")
    @PostMapping("/quoter/list")
    @ResponseBody
    public TableDataInfo quoterlist(ProdQuotationHistory prodQuotationHistory)
    {
        startPage();
        prodQuotationHistory.setQuoterUserId(getUserId());
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
     * 导出我的报价信息
     */
    @RequiresPermissions("system:history:quoter:export")
    @Log(title = "产品报价信息", businessType = BusinessType.EXPORT)
    @PostMapping("/quoter/export")
    @ResponseBody
    public AjaxResult quoterExport(ProdQuotationHistory prodQuotationHistory)
    {
        prodQuotationHistory.setQuoterUserId(getUserId());
        List<ProdQuotationHistory> list = prodQuotationHistoryService.selectProdQuotationHistoryList(prodQuotationHistory);

        List<MyProdQuotationExportVO> exportList = new ArrayList<>();
        if(!CollectionUtils.isEmpty(list))
        {
            exportList = list.stream().map(i -> {
                MyProdQuotationExportVO vo = new MyProdQuotationExportVO();
                BeanUtils.copyProperties(i, vo);
                return vo;
            }).collect(Collectors.toList());
        }
        ExcelUtil<MyProdQuotationExportVO> util = new ExcelUtil<MyProdQuotationExportVO>(MyProdQuotationExportVO.class);
        return util.exportExcel(exportList, "我的报价历史数据");
    }

    /**
     * 修改我的产品报价
     */
    @RequiresPermissions("system:history:quoter:edit")
    @GetMapping("/quoter/edit/{id}")
    public String editQuoter(@PathVariable("id") Long id, ModelMap mmap)
    {
        ProdQuotationHistory prodQuotationHistory = prodQuotationHistoryService.selectProdQuotationHistoryById(id);
        mmap.put("prodQuotationHistory", prodQuotationHistory);
        return prefix + "/editQuoter";
    }

//    @RequiresPermissions("system:history:remove")
    @Log(title = "校验报价的唯一性", businessType = BusinessType.OTHER)
    @PostMapping( "/checkProdHistoryUnique")
    @ResponseBody
    public boolean checkProdHistoryUnique(ProdQuotationHistory history)
    {
        ProdQuotationHistory prodQuotationHistory = prodQuotationHistoryService.checkProdHistoryUnique(history);
        return prodQuotationHistory == null;
    }
}
