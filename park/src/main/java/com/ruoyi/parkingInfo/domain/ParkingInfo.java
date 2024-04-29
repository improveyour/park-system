package com.ruoyi.parkingInfo.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 车位信息对象 parking_info
 *
 * @author bigcar
 * @date 2024-04-29
 */
public class ParkingInfo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 车位编号
     */
    private Long id;

    /**
     * 车牌号
     */
    @Excel(name = "车牌号")
    private String carPlate;

    /**
     * 车位位置
     */
    @Excel(name = "车位位置")
    private String position;

    /**
     * 车位状态,0-空闲,1-占用
     */
    @Excel(name = "车位状态,0-空闲,1-占用")
    private Integer status;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setCarPlate(String carPlate) {
        this.carPlate = carPlate;
    }

    public String getCarPlate() {
        return carPlate;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPosition() {
        return position;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("carPlate", getCarPlate())
                .append("position", getPosition())
                .append("status", getStatus())
                .toString();
    }
}
