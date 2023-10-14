package com.example.smartcity4.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Pl {

    /**
     * code : 200
     * msg : 查询成功
     * rows : [{"id":8,"appType":"smart_city","newsId":28,"content":"支持","commentDate":"2021-05-11 17:30:25","userId":2,"likeNum":0,"userName":"test01","newsTitle":"iPhone 13 再爆猛料：不止刘海屏有望缩小，超大杯或 将搭载 LTPO 屏"}]
     * total : 1
     */

    @SerializedName("code")
    private int code;
    @SerializedName("msg")
    private String msg;
    @SerializedName("total")
    private String total;
    @SerializedName("rows")
    private List<RowsDTO> rows;

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

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<RowsDTO> getRows() {
        return rows;
    }

    public void setRows(List<RowsDTO> rows) {
        this.rows = rows;
    }

    public static class RowsDTO {
        /**
         * id : 8
         * appType : smart_city
         * newsId : 28
         * content : 支持
         * commentDate : 2021-05-11 17:30:25
         * userId : 2
         * likeNum : 0
         * userName : test01
         * newsTitle : iPhone 13 再爆猛料：不止刘海屏有望缩小，超大杯或 将搭载 LTPO 屏
         */

        @SerializedName("id")
        private int id;
        @SerializedName("appType")
        private String appType;
        @SerializedName("newsId")
        private int newsId;
        @SerializedName("content")
        private String content;
        @SerializedName("commentDate")
        private String commentDate;
        @SerializedName("userId")
        private int userId;
        @SerializedName("likeNum")
        private int likeNum;
        @SerializedName("userName")
        private String userName;
        @SerializedName("newsTitle")
        private String newsTitle;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getAppType() {
            return appType;
        }

        public void setAppType(String appType) {
            this.appType = appType;
        }

        public int getNewsId() {
            return newsId;
        }

        public void setNewsId(int newsId) {
            this.newsId = newsId;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCommentDate() {
            return commentDate;
        }

        public void setCommentDate(String commentDate) {
            this.commentDate = commentDate;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getLikeNum() {
            return likeNum;
        }

        public void setLikeNum(int likeNum) {
            this.likeNum = likeNum;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getNewsTitle() {
            return newsTitle;
        }

        public void setNewsTitle(String newsTitle) {
            this.newsTitle = newsTitle;
        }
    }
}
