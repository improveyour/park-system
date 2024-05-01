package com.ruoyi.web.controller.park;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.parkingInfo.domain.CarPlateInfo;
import com.ruoyi.parkingInfo.service.ICarPlateInfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 车牌Controller
 *
 * @author bigcar
 * @date 2024-05-01
 */
@Controller
@RequestMapping("/park/plateInfo")
public class CarPlateInfoController extends BaseController {
    private String prefix = "park/plateInfo";

    @Autowired
    private ICarPlateInfoService carPlateInfoService;

    @RequiresPermissions("park:plateInfo:view")
    @GetMapping()
    public String plateInfo() {
        return prefix + "/plateInfo";
    }

    /**
     * 查询车牌列表
     */
    @RequiresPermissions("park:plateInfo:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CarPlateInfo carPlateInfo) {
        startPage();
        List<CarPlateInfo> list = carPlateInfoService.selectCarPlateInfoList(carPlateInfo);
        return getDataTable(list);
    }

    /**
     * 导出车牌列表
     */
    @RequiresPermissions("park:plateInfo:export")
    @Log(title = "车牌", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CarPlateInfo carPlateInfo) {
        List<CarPlateInfo> list = carPlateInfoService.selectCarPlateInfoList(carPlateInfo);
        ExcelUtil<CarPlateInfo> util = new ExcelUtil<CarPlateInfo>(CarPlateInfo.class);
        return util.exportExcel(list, "车牌数据");
    }

    /**
     * 新增车牌
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存车牌
     */
    @RequiresPermissions("park:plateInfo:add")
    @Log(title = "车牌", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CarPlateInfo carPlateInfo) {
        return toAjax(carPlateInfoService.insertCarPlateInfo(carPlateInfo));
    }

    /**
     * 修改车牌
     */
    @RequiresPermissions("park:plateInfo:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        CarPlateInfo carPlateInfo = carPlateInfoService.selectCarPlateInfoById(id);
        mmap.put("carPlateInfo", carPlateInfo);
        return prefix + "/edit";
    }

    /**
     * 修改保存车牌
     */
    @RequiresPermissions("park:plateInfo:edit")
    @Log(title = "车牌", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CarPlateInfo carPlateInfo) {
        return toAjax(carPlateInfoService.updateCarPlateInfo(carPlateInfo));
    }

    /**
     * 删除车牌
     */
    @RequiresPermissions("park:plateInfo:remove")
    @Log(title = "车牌", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(carPlateInfoService.deleteCarPlateInfoByIds(ids));
    }
}
