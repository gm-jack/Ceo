package com.example.yxy.ceo.model.Info;

/**
 * Created by yxy on 2016/9/1.
 */

public class UserInfo {

    /**
     * code : 0
     * msg : success
     * rst : {"id":10,"username":"徐海龙","corporation":"test","job":"job2","mobile":"123456789","email":"xuhai@123.com","userType":"I","inviteCode":null,"seatNumber":null,"isRegister":true,"clientId":"11111","createTime":1472701722069,"updateTime":null}
     */

    private int code;
    private String msg;
    /**
     * id : 10
     * username : 徐海龙
     * corporation : test
     * job : job2
     * mobile : 123456789
     * email : xuhai@123.com
     * userType : I（国际）D（国内）
     * inviteCode : null
     * seatNumber : null
     * isRegister : true
     * clientId : 11111
     * createTime : 1472701722069
     * updateTime : null
     */

    private RstBean rst;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public RstBean getRst() {
        return rst;
    }

    public void setRst(RstBean rst) {
        this.rst = rst;
    }

    public static class RstBean {
        private int id;
        private String username;
        private String corporation;
        private String job;
        private String mobile;
        private String email;
        private String userType;
        private Object inviteCode;
        private Object seatNumber;
        private boolean isRegister;
        private String clientId;
        private long createTime;
        private Object updateTime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getCorporation() {
            return corporation;
        }

        public void setCorporation(String corporation) {
            this.corporation = corporation;
        }

        public String getJob() {
            return job;
        }

        public void setJob(String job) {
            this.job = job;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getUserType() {
            return userType;
        }

        public void setUserType(String userType) {
            this.userType = userType;
        }

        public Object getInviteCode() {
            return inviteCode;
        }

        public void setInviteCode(Object inviteCode) {
            this.inviteCode = inviteCode;
        }

        public Object getSeatNumber() {
            return seatNumber;
        }

        public void setSeatNumber(Object seatNumber) {
            this.seatNumber = seatNumber;
        }

        public boolean isIsRegister() {
            return isRegister;
        }

        public void setIsRegister(boolean isRegister) {
            this.isRegister = isRegister;
        }

        public String getClientId() {
            return clientId;
        }

        public void setClientId(String clientId) {
            this.clientId = clientId;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public Object getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(Object updateTime) {
            this.updateTime = updateTime;
        }
    }
}
