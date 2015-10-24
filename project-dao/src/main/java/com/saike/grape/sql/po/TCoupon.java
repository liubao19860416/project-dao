package com.saike.grape.sql.po;

import java.util.Date;

public class TCoupon {
    private Long id;

    private String code;

    private String name;

    private Integer fittominkm;

    private Integer fittomaxkm;

    private Integer fittomindaysused;

    private Integer fittomaxdaysused;

    private String fittoemissionvolume;

    private String fittovehiclescope;

    private String fittocity;

    private Date begindatetime;

    private Date enddatetime;

    private Long validindays;

    private Float moneyamount;

    private String color1;

    private String color2;

    private String rulebrief;

    private Boolean actived;

    private Boolean deleted;

    private Date createddatetime;

    private Date updateddatetime;

    private String fittodealercode;

    private Integer usekmvalue;

    private String typecode;

    private String rulestring;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getFittominkm() {
        return fittominkm;
    }

    public void setFittominkm(Integer fittominkm) {
        this.fittominkm = fittominkm;
    }

    public Integer getFittomaxkm() {
        return fittomaxkm;
    }

    public void setFittomaxkm(Integer fittomaxkm) {
        this.fittomaxkm = fittomaxkm;
    }

    public Integer getFittomindaysused() {
        return fittomindaysused;
    }

    public void setFittomindaysused(Integer fittomindaysused) {
        this.fittomindaysused = fittomindaysused;
    }

    public Integer getFittomaxdaysused() {
        return fittomaxdaysused;
    }

    public void setFittomaxdaysused(Integer fittomaxdaysused) {
        this.fittomaxdaysused = fittomaxdaysused;
    }

    public String getFittoemissionvolume() {
        return fittoemissionvolume;
    }

    public void setFittoemissionvolume(String fittoemissionvolume) {
        this.fittoemissionvolume = fittoemissionvolume == null ? null : fittoemissionvolume.trim();
    }

    public String getFittovehiclescope() {
        return fittovehiclescope;
    }

    public void setFittovehiclescope(String fittovehiclescope) {
        this.fittovehiclescope = fittovehiclescope == null ? null : fittovehiclescope.trim();
    }

    public String getFittocity() {
        return fittocity;
    }

    public void setFittocity(String fittocity) {
        this.fittocity = fittocity == null ? null : fittocity.trim();
    }

    public Date getBegindatetime() {
        return begindatetime;
    }

    public void setBegindatetime(Date begindatetime) {
        this.begindatetime = begindatetime;
    }

    public Date getEnddatetime() {
        return enddatetime;
    }

    public void setEnddatetime(Date enddatetime) {
        this.enddatetime = enddatetime;
    }

    public Long getValidindays() {
        return validindays;
    }

    public void setValidindays(Long validindays) {
        this.validindays = validindays;
    }

    public Float getMoneyamount() {
        return moneyamount;
    }

    public void setMoneyamount(Float moneyamount) {
        this.moneyamount = moneyamount;
    }

    public String getColor1() {
        return color1;
    }

    public void setColor1(String color1) {
        this.color1 = color1 == null ? null : color1.trim();
    }

    public String getColor2() {
        return color2;
    }

    public void setColor2(String color2) {
        this.color2 = color2 == null ? null : color2.trim();
    }

    public String getRulebrief() {
        return rulebrief;
    }

    public void setRulebrief(String rulebrief) {
        this.rulebrief = rulebrief == null ? null : rulebrief.trim();
    }

    public Boolean getActived() {
        return actived;
    }

    public void setActived(Boolean actived) {
        this.actived = actived;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Date getCreateddatetime() {
        return createddatetime;
    }

    public void setCreateddatetime(Date createddatetime) {
        this.createddatetime = createddatetime;
    }

    public Date getUpdateddatetime() {
        return updateddatetime;
    }

    public void setUpdateddatetime(Date updateddatetime) {
        this.updateddatetime = updateddatetime;
    }

    public String getFittodealercode() {
        return fittodealercode;
    }

    public void setFittodealercode(String fittodealercode) {
        this.fittodealercode = fittodealercode == null ? null : fittodealercode.trim();
    }

    public Integer getUsekmvalue() {
        return usekmvalue;
    }

    public void setUsekmvalue(Integer usekmvalue) {
        this.usekmvalue = usekmvalue;
    }

    public String getTypecode() {
        return typecode;
    }

    public void setTypecode(String typecode) {
        this.typecode = typecode == null ? null : typecode.trim();
    }

    public String getRulestring() {
        return rulestring;
    }

    public void setRulestring(String rulestring) {
        this.rulestring = rulestring == null ? null : rulestring.trim();
    }
}