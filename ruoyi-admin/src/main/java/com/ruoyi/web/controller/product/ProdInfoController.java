package com.ruoyi.web.controller.product;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.enums.CommonStatus;
import com.ruoyi.common.utils.ShiroUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.product.domain.BatchInfo;
import com.ruoyi.product.domain.ProdCategory;
import com.ruoyi.product.domain.ProdInfo;
import com.ruoyi.product.service.IBatchInfoService;
import com.ruoyi.product.service.IProdCategoryService;
import com.ruoyi.product.service.IProdInfoService;
import com.ruoyi.web.controller.product.vo.ProdInfoExcelVO;
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
import java.util.Optional;
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
            if(categoryId != null && categoryId != -1)
            {
                final ProdCategory prodCategory = prodCategoryService.selectProdCategoryById(categoryId);
                if(prodCategory != null)
                {
                    vo.setCategoryName(prodCategory.getName());
                }
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
        List<ProdInfoExcelVO> excelVOList  = null;
        ExcelUtil<ProdInfoExcelVO> util = null;
        try
        {
            List<ProdInfo> list = prodInfoService.selectProdInfoList(prodInfo);
            excelVOList = list.stream().map(i->{
                ProdInfoExcelVO vo =  new ProdInfoExcelVO();
                com.ruoyi.common.utils.bean.BeanUtils.copyProperties(i,vo);
                vo.setBatchNo(i.getBatchNo());
                final ProdCategory prodCategory = this.prodCategoryService.selectProdCategoryById(i.getCategoryId());
                if(prodCategory!= null)
                {
                    vo.setCateLevel(prodCategory.getLevel());
                    vo.setCategoryName(prodCategory.getName());
                }
                return vo;
            }).collect(Collectors.toList());
            util = new ExcelUtil<ProdInfoExcelVO>(ProdInfoExcelVO.class);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return error("导出异常："+e.getMessage());
        }
        return util.exportExcel(excelVOList, "产品信息数据");
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
        mmap.put("categories", prodCategoryService.selectCategoryAll());
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
        ExcelUtil<ProdInfoExcelVO> util = new ExcelUtil<ProdInfoExcelVO>(ProdInfoExcelVO.class);
        List<ProdInfoExcelVO> prodInfoVOS = util.importExcel(file.getInputStream());
        ProdCategory condition = new ProdCategory();
        condition.setOwnerUserId(getUserId());
        condition.setStatus(CommonStatus.OK.getCode());
        List<ProdCategory> prodCategories =  this.prodCategoryService.selectProdCategoryList(condition);
        List<ProdInfo> prodInfoList =  new ArrayList<>();
        if(!CollectionUtils.isEmpty(prodInfoVOS))
        {
            final AjaxResult ajaxResult = checkImportData(prodInfoVOS);
            if(!ajaxResult.equals(AjaxResult.success()))
                return ajaxResult;
            for(ProdInfoExcelVO i : prodInfoVOS)
            {
                ProdInfo prodInfo = new ProdInfo();
                final String batchNo = i.getBatchNo();
                BatchInfo batchInfoCondition = new BatchInfo();
                batchInfoCondition.setBatchNo(batchNo);
                batchInfoCondition.setOwnerId(getUserId());
                final List<BatchInfo> batchInfos = this.batchInfoService.selectBatchInfoList(batchInfoCondition);
                if(CollectionUtils.isEmpty(batchInfos))
                {
                    return error("服务匹配到批次号：["+batchNo+"]");
                }
                BatchInfo batchInfo = batchInfos.get(0);
                prodInfo.setBatchNo(batchNo);
                prodInfo.setBatchId(String.valueOf(batchInfo.getId()));
                final Integer cateLevel = i.getCateLevel();
                final String categoryName = i.getCategoryName();
                if(cateLevel != null && StringUtils.isNotEmpty(categoryName))
                {
                    final Optional<ProdCategory> opt = prodCategories.stream()
                            .filter(c -> c.getLevel().equals(cateLevel) && c.getName().equals(categoryName)).findFirst();
                    if(!opt.isPresent())
                    {
                        return error("无法匹配到分类等级为["+cateLevel+"]的分类：["+categoryName+"]");
                    }
                    prodInfo.setCategoryId(opt.get().getId());
                    prodInfo.setCategoryName(categoryName);
                }
                if(!updateSupport)
                {
                    ProdInfo con  =new ProdInfo();
                    con.setBatchNo(batchNo);
                    con.setName(i.getName());
                    con.setCategoryId(prodInfo.getCategoryId());
                    List<ProdInfo> prodInfos = prodInfoService.selectProdInfoList(con);
                    if(!CollectionUtils.isEmpty(prodInfos))
                    {
                        return error("已经存在分类为["+i.getCategoryName()+"]型号为["+i.getName()+"]的记录");
                    }
                }
                BeanUtils.copyProperties(i, prodInfo);
                prodInfo.setStatus(CommonStatus.OK.getCode());
                prodInfoList.add(prodInfo);
            }
        }
        String operName = ShiroUtils.getSysUser().getLoginName();
        Integer message = prodInfoService.importProduct(prodInfoList, updateSupport, operName);
        return AjaxResult.success(message);
    }

    private AjaxResult checkImportData(List<ProdInfoExcelVO> prodInfoExcelVO1s)
    {
        if(CollectionUtils.isEmpty(prodInfoExcelVO1s))
        {
            return error("导入数据为空");
        }
        for(ProdInfoExcelVO vo:prodInfoExcelVO1s)
        {
            if(StringUtils.isEmpty(vo.getName()))
            {
                return error("型号不能为空");
            }
            if(StringUtils.isEmpty(vo.getBatchNo()))
            {
                return error("批次号不能为空");
            }
            if(vo.getCount() != null && vo.getCount() < 1)
            {
                return error("数量不能为空且必须大于0");
            }
            if(vo.getCateLevel() != null && (vo.getCateLevel() < 1 || vo.getCateLevel() > 2))
            {
                return error("分类等级只能是1和2");
            }
        }

        return success();
    }

    @RequiresPermissions("product:info:view")
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
        ExcelUtil<ProdInfoExcelVO> util = new ExcelUtil<ProdInfoExcelVO>(ProdInfoExcelVO.class);
        return util.importTemplateExcel("产品数据");
    }
}
