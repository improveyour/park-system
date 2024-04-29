package com.ruoyi.web.controller.park;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.parkingInfo.domain.ParkingInfo;
import com.ruoyi.parkingInfo.service.IParkingInfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 车位信息Controller
 *
 * @author bigcar
 * @date 2024-04-29
 */
@Controller
@RequestMapping("/park/parkingInfo")
public class ParkingInfoController extends BaseController {
    private String prefix = "park/parkingInfo";

    @Autowired
    private IParkingInfoService parkingInfoService;

    @RequiresPermissions("park:parkingInfo:view")
    @GetMapping()
    public String parkingInfo() {
        return prefix + "/parkingInfo";
    }

    /**
     * 查询车位信息列表
     */
    @RequiresPermissions("park:parkingInfo:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ParkingInfo parkingInfo) {
        startPage();
        List<ParkingInfo> list = parkingInfoService.selectParkingInfoList(parkingInfo);
        return getDataTable(list);
    }

    /**
     * 导出车位信息列表
     */
    @RequiresPermissions("park:parkingInfo:export")
    @Log(title = "车位信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ParkingInfo parkingInfo) {
        List<ParkingInfo> list = parkingInfoService.selectParkingInfoList(parkingInfo);
        ExcelUtil<ParkingInfo> util = new ExcelUtil<ParkingInfo>(ParkingInfo.class);
        return util.exportExcel(list, "车位信息数据");
    }

    /**
     * 新增车位信息
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存车位信息
     */
    @RequiresPermissions("park:parkingInfo:add")
    @Log(title = "车位信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ParkingInfo parkingInfo) {
        return toAjax(parkingInfoService.insertParkingInfo(parkingInfo));
    }

    /**
     * 修改车位信息
     */
    @RequiresPermissions("park:parkingInfo:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        ParkingInfo parkingInfo = parkingInfoService.selectParkingInfoById(id);
        mmap.put("parkingInfo", parkingInfo);
        return prefix + "/edit";
    }

    /**
     * 修改保存车位信息
     */
    @RequiresPermissions("park:parkingInfo:edit")
    @Log(title = "车位信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ParkingInfo parkingInfo) {
        return toAjax(parkingInfoService.updateParkingInfo(parkingInfo));
    }

    /**
     * 删除车位信息
     */
    @RequiresPermissions("park:parkingInfo:remove")
    @Log(title = "车位信息", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(parkingInfoService.deleteParkingInfoByIds(ids));
    }
}
