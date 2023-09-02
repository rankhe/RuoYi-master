//package com.ruoyi.web.controller.system;
//
//import com.ruoyi.common.core.controller.BaseController;
//import com.ruoyi.common.core.domain.AjaxResult;
//import com.ruoyi.common.core.page.TableDataInfo;
//import com.ruoyi.system.domain.ProductBatchInfo;
//import com.ruoyi.system.service.IProductBatchInfoService;
//import com.ruoyi.system.service.IProductInfoService;
//import net.sf.ehcache.util.ProductInfo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import java.util.List;
//
//@Controller
//@RequestMapping("/product/management")
//public class ProductManagerController extends BaseController
//{
//    private String prefix = "product";
//
//    @Autowired
//    private IProductInfoService productInfoService;
//
//    @Autowired
//    private IProductBatchInfoService productBatchInfoService;
//
//    @GetMapping("product")
//    public String list(Long batchId, ModelMap mmap)
//    {
//        mmap.put("batchId",batchId);
//        logger.info("batchId：{}",batchId);
//        return prefix + "/productList";
//    }
//
//
//    @PostMapping("/product/list")
//    @ResponseBody
//    public TableDataInfo productList(Long batchId)
//    {
//        startPage();
//        logger.info("batchId：{}",batchId);
//        List<ProductInfo> list = productInfoService.selectProductInfoList(batchId);
//        return getDataTable(list);
//    }
//
//    @GetMapping("/add")
//    public String add()
//    {
//        return prefix + "/product/add";
//    }
//
//    @PostMapping("/product/add")
//    @ResponseBody
//    public AjaxResult addProduct(com.ruoyi.system.domain.ProductInfo productInfo)
//    {
////        if (!productInfoService.checkConfigKeyUnique(config))
////        {
////            return error("新增参数'" + config.getConfigName() + "'失败，参数键名已存在");
////        }
////        productInfoService.setCreateBy(getLoginName());
//        return toAjax(1);
//    }
//
//    @GetMapping("/remove")
//    public String remove()
//    {
//        return prefix + "/product/remove";
//    }
//
//    @PostMapping("/product/remove")
//    @ResponseBody
//    public AjaxResult removeProduct(Long prodId)
//    {
//        //        if (!productInfoService.checkConfigKeyUnique(config))
//        //        {
//        //            return error("新增参数'" + config.getConfigName() + "'失败，参数键名已存在");
//        //        }
////        config.setCreateBy(getLoginName());
//        return toAjax(1);
//    }
//
//    @GetMapping("/batch/list")
//    public String batchList()
//    {
//        return prefix + "/batchList";
//    }
//
//    @PostMapping("/batch/list")
//    @ResponseBody
//    public TableDataInfo batchList(Long ownerUserId)
//    {
//        startPage();
//        Long ownerId = getSysUser().getUserId();
//        logger.info("ownerId:{}",ownerId);
//        List<ProductBatchInfo> list = productBatchInfoService.selectProductBatchInfoList(1l);
//        return getDataTable(list);
//    }
//}
