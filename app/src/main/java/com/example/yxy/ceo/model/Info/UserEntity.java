package com.example.yxy.ceo.model.Info;

/**
 * Created by yxy on 2016/9/1.
 */

public class UserEntity {
    /**
     * username	是	String	用户名称
     * job	是	String	职位
     * corporation	是	String	企业
     * phone	否	String	手机号
     * email	否	String	邮箱
     * clientId	否	String	设备唯一编号
     * userType	否	String	国际或国内标示
     */
    private String username;
    private String job;
    private String corporation;
    private String phone;
    private String email;
    private String clientId;
    private String userType;

    public UserEntity() {
    }

    public UserEntity(String username, String job, String corporation, String phone, String email, String clientId, String userType) {
        this.username = username;
        this.job = job;
        this.corporation = corporation;
        this.phone = phone;
        this.email = email;
        this.clientId = clientId;
        this.userType = userType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getCorporation() {
        return corporation;
    }

    public void setCorporation(String corporation) {
        this.corporation = corporation;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "username='" + username + '\'' +
                ", job='" + job + '\'' +
                ", corporation='" + corporation + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", clientId='" + clientId + '\'' +
                ", userType='" + userType + '\'' +
                '}';
    }
}
