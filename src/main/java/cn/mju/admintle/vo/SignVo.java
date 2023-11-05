package cn.mju.admintle.vo;

import java.util.Date;

public class SignVo {

    private Integer id;
    private String userName;
    private Date time;
    private boolean late;

    public SignVo() {
    }

    public SignVo(Integer id, String userName, Date time, boolean late) {
        this.id = id;
        this.userName = userName;
        this.time = time;
        this.late = late;
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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public boolean isLate() {
        return late;
    }

    public void setLate(boolean late) {
        this.late = late;
    }

    @Override
    public String toString() {
        return "SignVo{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", time=" + time +
                ", late=" + late +
                '}';
    }
}
