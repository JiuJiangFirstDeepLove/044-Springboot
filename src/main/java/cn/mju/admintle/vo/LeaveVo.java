package cn.mju.admintle.vo;

import java.util.Date;

public class LeaveVo {

    private Integer id;
    private  String userName;
    private Date beginTime;
    private Date endTime;
    private String reason;
    //0待批准，1批准，-1不批准
    private Integer state;

    public LeaveVo() {
    }

    public LeaveVo(Integer id, String userName, Date beginTime, Date endTime, String reason, Integer state) {
        this.id = id;
        this.userName = userName;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.reason = reason;
        this.state = state;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
