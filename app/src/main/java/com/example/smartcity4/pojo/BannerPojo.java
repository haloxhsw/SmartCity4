package com.example.smartcity4.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BannerPojo {

    /**
     * total : 3
     * rows : [{"searchValue":null,"createBy":"admin","createTime":"2021-05-06 15:40:17","updateBy":"1","updateTime":"2021-06-25 11:02:40","remark":null,"params":{},"id":25,"appType":"smart_city","status":"1","sort":2,"advTitle":"首页轮播","advImg":"/prod-api/profile/upload/image/2021/05/06/b9d9f081-8a76-41dc-8199-23bcb3a64fcc.png","servModule":"新闻详情","targetId":28,"type":"2"},{"searchValue":null,"createBy":"admin","createTime":"2021-05-06 15:40:59","updateBy":"1111129","updateTime":"2021-06-25 11:02:51","remark":null,"params":{},"id":26,"appType":"smart_city","status":"1","sort":3,"advTitle":"首页轮播","advImg":"/prod-api/profile/upload/image/2021/05/06/e614cb7f-63b0-4cda-bf47-db286ea1b074.png","servModule":"新闻详情","targetId":29,"type":"2"},{"searchValue":null,"createBy":"admin","createTime":"2021-05-06 15:41:20","updateBy":"1111129","updateTime":"2021-06-25 11:03:10","remark":null,"params":{},"id":27,"appType":"smart_city","status":"1","sort":4,"advTitle":"首页轮播","advImg":"/prod-api/profile/upload/image/2021/05/06/242e06f7-9fb0-4e16-b197-206f999c98f2.png","servModule":"新闻详情","targetId":30,"type":"2"}]
     * code : 200
     * msg : 查询成功
     */

    @SerializedName("total")
    private int total;
    @SerializedName("code")
    private int code;
    @SerializedName("msg")
    private String msg;
    @SerializedName("rows")
    private List<RowsDTO> rows;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

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

    public List<RowsDTO> getRows() {
        return rows;
    }

    public void setRows(List<RowsDTO> rows) {
        this.rows = rows;
    }

    public static class RowsDTO {
        /**
         * searchValue : null
         * createBy : admin
         * createTime : 2021-05-06 15:40:17
         * updateBy : 1
         * updateTime : 2021-06-25 11:02:40
         * remark : null
         * params : {}
         * id : 25
         * appType : smart_city
         * status : 1
         * sort : 2
         * advTitle : 首页轮播
         * advImg : /prod-api/profile/upload/image/2021/05/06/b9d9f081-8a76-41dc-8199-23bcb3a64fcc.png
         * servModule : 新闻详情
         * targetId : 28
         * type : 2
         */

        @SerializedName("searchValue")
        private Object searchValue;
        @SerializedName("createBy")
        private String createBy;
        @SerializedName("createTime")
        private String createTime;
        @SerializedName("updateBy")
        private String updateBy;
        @SerializedName("updateTime")
        private String updateTime;
        @SerializedName("remark")
        private Object remark;
        @SerializedName("params")
        private ParamsDTO params;
        @SerializedName("id")
        private int id;
        @SerializedName("appType")
        private String appType;
        @SerializedName("status")
        private String status;
        @SerializedName("sort")
        private int sort;
        @SerializedName("advTitle")
        private String advTitle;
        @SerializedName("advImg")
        private String advImg;
        @SerializedName("servModule")
        private String servModule;
        @SerializedName("targetId")
        private int targetId;
        @SerializedName("type")
        private String type;

        public Object getSearchValue() {
            return searchValue;
        }

        public void setSearchValue(Object searchValue) {
            this.searchValue = searchValue;
        }

        public String getCreateBy() {
            return createBy;
        }

        public void setCreateBy(String createBy) {
            this.createBy = createBy;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(String updateBy) {
            this.updateBy = updateBy;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public Object getRemark() {
            return remark;
        }

        public void setRemark(Object remark) {
            this.remark = remark;
        }

        public ParamsDTO getParams() {
            return params;
        }

        public void setParams(ParamsDTO params) {
            this.params = params;
        }

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

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public String getAdvTitle() {
            return advTitle;
        }

        public void setAdvTitle(String advTitle) {
            this.advTitle = advTitle;
        }

        public String getAdvImg() {
            return advImg;
        }

        public void setAdvImg(String advImg) {
            this.advImg = advImg;
        }

        public String getServModule() {
            return servModule;
        }

        public void setServModule(String servModule) {
            this.servModule = servModule;
        }

        public int getTargetId() {
            return targetId;
        }

        public void setTargetId(int targetId) {
            this.targetId = targetId;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public static class ParamsDTO {
        }
    }
}
