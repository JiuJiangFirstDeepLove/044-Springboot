package cn.mju.admintle.vo;

import java.util.Date;


public class UserVo {
    private Long id;
    private String username;
    private String address;
    private Date birthday;
    private String email;
    private Long phone;
    private String deptName;
    private String jobName;
    private Integer state;

    public UserVo() {
    }

    public UserVo(Long id, String username, String address, Date birthday, String email, Long phone, String deptName, String jobName, Integer state) {
        this.id = id;
        this.username = username;
        this.address = address;
        this.birthday = birthday;
        this.email = email;
        this.phone = phone;
        this.deptName = deptName;
        this.jobName = jobName;
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

}
