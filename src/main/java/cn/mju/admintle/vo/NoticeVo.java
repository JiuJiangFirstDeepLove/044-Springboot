package cn.mju.admintle.vo;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.Date;

public class NoticeVo {
    private Integer id;
    @NotBlank
    private String title;
    private String content;
    private Date createTime;
    //发布人
    private String userName;

    public NoticeVo() {
    }

    public NoticeVo(Integer id, @NotBlank String title, @NotBlank String content, Date createTime, String userName) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createTime = createTime;
        this.userName = userName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "NoticeVo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                ", userName='" + userName + '\'' +
                '}';
    }
}
