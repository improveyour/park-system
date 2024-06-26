package com.ruoyi.web.controller.park;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.parkingHistory.domain.ParkingRecordInfo;
import com.ruoyi.parkingHistory.service.IParkingRecordInfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 停车记录Controller
 *
 * @author bigcar
 * @date 2024-05-08
 */
@Controller
@RequestMapping("/park/parkingHistory")
public class ParkingRecordInfoController extends BaseController {
    private String prefix = "park/parkingHistory";

    @Autowired
    private IParkingRecordInfoService parkingRecordInfoService;

    @RequiresPermissions("park:parkingHistory:view")
    @GetMapping()
    public String parkingHistory() {
        return prefix + "/parkingHistory";
    }

    /**
     * 查询停车记录列表
     */
    @RequiresPermissions("park:parkingHistory:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ParkingRecordInfo parkingRecordInfo) {
        startPage();
        List<ParkingRecordInfo> list = parkingRecordInfoService.selectParkingRecordInfoList(parkingRecordInfo);
        return getDataTable(list);
    }

    /**
     * 导出停车记录列表
     */
    @RequiresPermissions("park:parkingHistory:export")
    @Log(title = "停车记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ParkingRecordInfo parkingRecordInfo) {
        List<ParkingRecordInfo> list = parkingRecordInfoService.selectParkingRecordInfoList(parkingRecordInfo);
        ExcelUtil<ParkingRecordInfo> util = new ExcelUtil<ParkingRecordInfo>(ParkingRecordInfo.class);
        return util.exportExcel(list, "停车记录数据");
    }

    /**
     * 新增停车记录
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存停车记录
     */
    @RequiresPermissions("park:parkingHistory:add")
    @Log(title = "停车记录", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ParkingRecordInfo parkingRecordInfo) {
        return toAjax(parkingRecordInfoService.insertParkingRecordInfo(parkingRecordInfo));
    }

    /**
     * 修改停车记录
     */
    @RequiresPermissions("park:parkingHistory:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        ParkingRecordInfo parkingRecordInfo = parkingRecordInfoService.selectParkingRecordInfoById(id);
        mmap.put("parkingRecordInfo", parkingRecordInfo);
        return prefix + "/edit";
    }

    /**
     * 修改保存停车记录
     */
    @RequiresPermissions("park:parkingHistory:edit")
    @Log(title = "停车记录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ParkingRecordInfo parkingRecordInfo) {
        return toAjax(parkingRecordInfoService.updateParkingRecordInfo(parkingRecordInfo));
    }

    /**
     * 删除停车记录
     */
    @RequiresPermissions("park:parkingHistory:remove")
    @Log(title = "停车记录", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(parkingRecordInfoService.deleteParkingRecordInfoByIds(ids));
    }
}
