package com.example.smartcity4.pojo;

import com.google.gson.annotations.SerializedName;

public class User {

    /**
     * msg : 操作成功
     * code : 200
     * user : {"userId":2,"userName":"test01","nickName":"测试用户 01","email":"ljxl@qq.com","phonenumber":"13800000000","sex":"0","avatar":"","idCard":"210211199909090014","balance":9800,"score":10000}
     */

    @SerializedName("msg")
    private String msg;
    @SerializedName("code")
    private int code;
    @SerializedName("user")
    private UserDTO user;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public static class UserDTO {
        /**
         * userId : 2
         * userName : test01
         * nickName : 测试用户 01
         * email : ljxl@qq.com
         * phonenumber : 13800000000
         * sex : 0
         * avatar :
         * idCard : 210211199909090014
         * balance : 9800
         * score : 10000
         */

        @SerializedName("userId")
        private int userId;
        @SerializedName("userName")
        private String userName;
        @SerializedName("nickName")
        private String nickName;
        @SerializedName("email")
        private String email;
        @SerializedName("phonenumber")
        private String phonenumber;
        @SerializedName("sex")
        private String sex;
        @SerializedName("avatar")
        private String avatar;
        @SerializedName("idCard")
        private String idCard;
        @SerializedName("balance")
        private int balance;
        @SerializedName("score")
        private int score;

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhonenumber() {
            return phonenumber;
        }

        public void setPhonenumber(String phonenumber) {
            this.phonenumber = phonenumber;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getIdCard() {
            return idCard;
        }

        public void setIdCard(String idCard) {
            this.idCard = idCard;
        }

        public int getBalance() {
            return balance;
        }

        public void setBalance(int balance) {
            this.balance = balance;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }
    }
}
