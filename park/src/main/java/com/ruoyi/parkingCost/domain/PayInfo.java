package com.ruoyi.parkingCost.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 收费标准对象 pay_info
 *
 * @author bigcar
 * @date 2024-04-29
 */
public class PayInfo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 停放时间
     */
    @Excel(name = "停放时间")
    private Integer parkingTime;

    /**
     * 费用
     */
    @Excel(name = "费用")
    private Integer cost;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setParkingTime(Integer parkingTime) {
        this.parkingTime = parkingTime;
    }

    public Integer getParkingTime() {
        return parkingTime;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Integer getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("parkingTime", getParkingTime())
                .append("cost", getCost())
                .toString();
    }
}
