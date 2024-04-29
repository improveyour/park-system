package com.ruoyi.parkingHistory.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 查询停车记录对象 parking_record_info
 *
 * @author bigcar
 * @date 2024-04-29
 */
public class ParkingRecordInfo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 缴费金额
     */
    @Excel(name = "缴费金额")
    private Integer pay;

    /**
     * 车位位置
     */
    @Excel(name = "车位位置")
    private String positions;

    /**
     * 入库时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "入库时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date parkingTime;

    /**
     * 出库时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "出库时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date leaveTime;

    /**
     * 车牌号
     */
    @Excel(name = "车牌号")
    private String carPlate;

    /**
     * 车位编号
     */
    @Excel(name = "车位编号")
    private String parkingId;

    /**
     * 缴费时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "缴费时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date payTime;

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

    public void setParkingTime(Date parkingTime) {
        this.parkingTime = parkingTime;
    }

    public Date getParkingTime() {
        return parkingTime;
    }

    public void setLeaveTime(Date leaveTime) {
        this.leaveTime = leaveTime;
    }

    public Date getLeaveTime() {
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

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Date getPayTime() {
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
