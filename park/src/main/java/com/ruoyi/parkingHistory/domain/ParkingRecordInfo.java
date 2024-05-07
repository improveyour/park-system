package com.ruoyi.parkingHistory.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 查询停车记录对象 parking_record_info
 *
 * @author bigcar
 * @date 2024-05-07
 */
public class ParkingRecordInfo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 缴费金额 */
    @Excel(name = "缴费金额")
    private Integer pay;

    /** 车位位置 */
    @Excel(name = "车位位置")
    private String positions;

    /** 入库时间 */
    @Excel(name = "入库时间")
    private String parkingTime;

    /** 出库时间 */
    @Excel(name = "出库时间")
    private String leaveTime;

    /** 车牌号 */
    @Excel(name = "车牌号")
    private String carPlate;

    /** 车位编号 */
    @Excel(name = "车位编号")
    private String parkingId;

    /** 缴费时间 */
    @Excel(name = "缴费时间")
    private String payTime;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setPay(Integer pay) {
        this.pay = pay;
    }

    public Integer getPay() {
        return pay;
    }

    public void setPositions(String positions) {
        this.positions = positions;
    }

    public String getPositions() {
        return positions;
    }

    public void setParkingTime(String parkingTime) {
        this.parkingTime = parkingTime;
    }

    public String getParkingTime() {
        return parkingTime;
    }

    public void setLeaveTime(String leaveTime) {
        this.leaveTime = leaveTime;
    }

    public String getLeaveTime() {
        return leaveTime;
    }

    public void setCarPlate(String carPlate) {
        this.carPlate = carPlate;
    }

    public String getCarPlate() {
        return carPlate;
    }

    public void setParkingId(String parkingId) {
        this.parkingId = parkingId;
    }

    public String getParkingId() {
        return parkingId;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getPayTime() {
        return payTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("pay", getPay())
                .append("positions", getPositions())
                .append("parkingTime", getParkingTime())
                .append("leaveTime", getLeaveTime())
                .append("carPlate", getCarPlate())
                .append("parkingId", getParkingId())
                .append("payTime", getPayTime())
                .toString();
    }
}
