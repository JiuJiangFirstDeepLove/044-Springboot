package cn.mju.admintle.vo;

import javax.validation.constraints.NotBlank;
import java.util.Date;

public class ApplicantVo {

    private Long id;
    @NotBlank
    private String username;
    @NotBlank
    private String deptName;
    @NotBlank
    private String jobName;
    private Integer expWages;
    private Date comeDate;
    private String address;
    private Date birthday;
    private String email;
    private Long phone;
    //简历
    private String resume;

    //待面试0面试通过1面试未通过2
    private Integer state;

    public ApplicantVo() {
    }

    public ApplicantVo(Long id, @NotBlank String username, @NotBlank String deptName, @NotBlank String jobName, Integer expWages, Date comeDate, String address, Date birthday, String email, Long phone, String resume, Integer state) {
        this.id = id;
        this.username = username;
        this.deptName = deptName;
        this.jobName = jobName;
        this.expWages = expWages;
        this.comeDate = comeDate;
        this.address = address;
        this.birthday = birthday;
        this.email = email;
        this.phone = phone;
        this.resume = resume;
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

    public Integer getExpWages() {
        return expWages;
    }

    public void setExpWages(Integer expWages) {
        this.expWages = expWages;
    }

    public Date getComeDate() {
        return comeDate;
    }

    public void setComeDate(Date comeDate) {
        this.comeDate = comeDate;
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

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
