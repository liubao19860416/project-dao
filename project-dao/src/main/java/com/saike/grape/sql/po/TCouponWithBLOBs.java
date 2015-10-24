package com.saike.grape.sql.po;

public class TCouponWithBLOBs extends TCoupon {
    private String ruledetail;

    private String description;

    public String getRuledetail() {
        return ruledetail;
    }

    public void setRuledetail(String ruledetail) {
        this.ruledetail = ruledetail == null ? null : ruledetail.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}