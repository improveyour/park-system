package com.ruoyi.web.controller.park;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.parkingCost.domain.PayInfo;
import com.ruoyi.parkingCost.service.IPayInfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 收费标准Controller
 *
 * @author bigcar
 * @date 2024-05-13
 */
@Controller
@RequestMapping("/park/parkingCost")
public class PayInfoController extends BaseController {
    private String prefix = "park/parkingCost";

    @Autowired
    private IPayInfoService payInfoService;

    @RequiresPermissions("park:parkingCost:view")
    @GetMapping()
    public String parkingCost() {
        return prefix + "/parkingCost";
    }

    /**
     * 查询收费标准列表
     */
    @RequiresPermissions("park:parkingCost:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(PayInfo payInfo) {
        startPage();
        List<PayInfo> list = payInfoService.selectPayInfoList(payInfo);
        return getDataTable(list);
    }

    /**
     * 导出收费标准列表
     */
    @RequiresPermissions("park:parkingCost:export")
    @Log(title = "收费标准", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(PayInfo payInfo) {
        List<PayInfo> list = payInfoService.selectPayInfoList(payInfo);
        ExcelUtil<PayInfo> util = new ExcelUtil<PayInfo>(PayInfo.class);
        return util.exportExcel(list, "收费标准数据");
    }

    /**
     * 新增收费标准
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存收费标准
     */
    @RequiresPermissions("park:parkingCost:add")
    @Log(title = "收费标准", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(PayInfo payInfo) {
        return toAjax(payInfoService.insertPayInfo(payInfo));
    }

    /**
     * 修改收费标准
     */
    @RequiresPermissions("park:parkingCost:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        PayInfo payInfo = payInfoService.selectPayInfoById(id);
        mmap.put("payInfo", payInfo);
        return prefix + "/edit";
    }

    /**
     * 修改保存收费标准
     */
    @RequiresPermissions("park:parkingCost:edit")
    @Log(title = "收费标准", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(PayInfo payInfo) {
        return toAjax(payInfoService.updatePayInfo(payInfo));
    }

    /**
     * 删除收费标准
     */
    @RequiresPermissions("park:parkingCost:remove")
    @Log(title = "收费标准", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(payInfoService.deletePayInfoByIds(ids));
    }
}
