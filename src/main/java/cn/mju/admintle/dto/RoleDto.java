package cn.mju.admintle.dto;

public class RoleDto {
    private Integer id;
    private String userName;

    public RoleDto() {
    }

    public RoleDto(Integer id, String userName) {
        this.id = id;
        this.userName = userName;
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
}
