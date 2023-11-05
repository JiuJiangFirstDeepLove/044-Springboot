package cn.mju.admintle.vo;

import java.util.Date;

public class HealthVo {
    private Integer id;
    private String username;
    private String deptName;
    private String jobName;
    //现住址
    private String address;
    //今日体温
    private Double temp;
    //今日行动轨迹
    private String path;
    //是否接触疫区人员
    //接触1未接触0
    private Integer touch;
    //今日身体状况
    private String state;
    //今天日期
    private Date today;

    public HealthVo() {
    }

    public HealthVo(Integer id, String username, String deptName, String jobName, String address, Double temp, String path, Integer touch, String state, Date today) {
        this.id = id;
        this.username = username;
        this.deptName = deptName;
        this.jobName = jobName;
        this.address = address;
        this.temp = temp;
        this.path = path;
        this.touch = touch;
        this.state = state;
        this.today = today;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getTouch() {
        return touch;
    }

    public void setTouch(Integer touch) {
        this.touch = touch;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getToday() {
        return today;
    }

    public void setToday(Date today) {
        this.today = today;
    }

    @Override
    public String toString() {
        return "HealthVo{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", deptName='" + deptName + '\'' +
                ", jobName='" + jobName + '\'' +
                ", address='" + address + '\'' +
                ", temp=" + temp +
                ", path='" + path + '\'' +
                ", touch=" + touch +
                ", state='" + state + '\'' +
                ", today=" + today +
                '}';
    }
}
