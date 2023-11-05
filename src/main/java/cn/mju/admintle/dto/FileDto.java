package cn.mju.admintle.dto;

import java.util.Date;

public class FileDto {
    private Long id;
    private String userName;
    private Date entryTime;
    private Date quitTime;
    private String deptName;
    private String jobName;


    public FileDto() {
    }

    public FileDto(Long id, String userName, Date entryTime, Date quitTime, String deptName, String jobName) {
        this.id = id;
        this.userName = userName;
        this.entryTime = entryTime;
        this.quitTime = quitTime;
        this.deptName = deptName;
        this.jobName = jobName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(Date entryTime) {
        this.entryTime = entryTime;
    }

    public Date getQuitTime() {
        return quitTime;
    }

    public void setQuitTime(Date quitTime) {
        this.quitTime = quitTime;
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
}
