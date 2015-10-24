package com.saike.grape.sql.po;

public class CouponWithBLOBs extends Coupon {
    private String ruleDetail;

    private String description;

    public String getRuleDetail() {
        return ruleDetail;
    }

    public void setRuleDetail(String ruleDetail) {
        this.ruleDetail = ruleDetail == null ? null : ruleDetail.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}