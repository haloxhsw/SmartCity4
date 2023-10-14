package com.example.smartcity4.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class News {

    /**
     * code : 200
     * msg : 查询成功
     * rows : [{"id":5,"cover":"/dev-api/profile/upload/image/2021/04/01/c1eb74b2-e96 4-4388-830a-1b606fc9699f.png","title":"测试新闻标题","subTitle":"测试新闻子标题","content":"<p>内容<img src=\"/dev-api/profile/upload/image/202 1/04/07/a9434ccf-5acf-4bf5-a06e-c3457c6762e9.png\"><\/p>","status":"Y","publishDate":"2021-04-01","tags":null,"commentNum":1,"likeNum":2,"readNum":10,"type":"2","top":"Y","hot":"N"}]
     * total : 1
     */

    @SerializedName("code")
    private int code;
    @SerializedName("msg")
    private String msg;
    @SerializedName("total")
    private int total;
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

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
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
         * id : 5
         * cover : /dev-api/profile/upload/image/2021/04/01/c1eb74b2-e96 4-4388-830a-1b606fc9699f.png
         * title : 测试新闻标题
         * subTitle : 测试新闻子标题
         * content : <p>内容<img src="/dev-api/profile/upload/image/202 1/04/07/a9434ccf-5acf-4bf5-a06e-c3457c6762e9.png"></p>
         * status : Y
         * publishDate : 2021-04-01
         * tags : null
         * commentNum : 1
         * likeNum : 2
         * readNum : 10
         * type : 2
         * top : Y
         * hot : N
         */

        @SerializedName("id")
        private int id;
        @SerializedName("cover")
        private String cover;
        @SerializedName("title")
        private String title;
        @SerializedName("subTitle")
        private String subTitle;
        @SerializedName("content")
        private String content;
        @SerializedName("status")
        private String status;
        @SerializedName("publishDate")
        private String publishDate;
        @SerializedName("tags")
        private Object tags;
        @SerializedName("commentNum")
        private int commentNum;
        @SerializedName("likeNum")
        private int likeNum;
        @SerializedName("readNum")
        private int readNum;
        @SerializedName("type")
        private String type;
        @SerializedName("top")
        private String top;
        @SerializedName("hot")
        private String hot;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSubTitle() {
            return subTitle;
        }

        public void setSubTitle(String subTitle) {
            this.subTitle = subTitle;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getPublishDate() {
            return publishDate;
        }

        public void setPublishDate(String publishDate) {
            this.publishDate = publishDate;
        }

        public Object getTags() {
            return tags;
        }

        public void setTags(Object tags) {
            this.tags = tags;
        }

        public int getCommentNum() {
            return commentNum;
        }

        public void setCommentNum(int commentNum) {
            this.commentNum = commentNum;
        }

        public int getLikeNum() {
            return likeNum;
        }

        public void setLikeNum(int likeNum) {
            this.likeNum = likeNum;
        }

        public int getReadNum() {
            return readNum;
        }

        public void setReadNum(int readNum) {
            this.readNum = readNum;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTop() {
            return top;
        }

        public void setTop(String top) {
            this.top = top;
        }

        public String getHot() {
            return hot;
        }

        public void setHot(String hot) {
            this.hot = hot;
        }
    }
}
