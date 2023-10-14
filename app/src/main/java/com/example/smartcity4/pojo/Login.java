package com.example.smartcity4.pojo;

import com.google.gson.annotations.SerializedName;

public class Login {

    /**
     * code : 200
     * msg : 操作成功
     * token : eyJhbGciOiJIUzUxMiJ9.eyJsb2dpbl91c2VyX2tleSI6ImY2NT g5NjlhLWJmMDMtNDM0Mi1iYjFmLTRiNzAxYmQzNGViMCJ9.rsmLIODdu3p7DszpItwEyL XEJc20RejFXtjBITxYDA7q4PKyborAdyAsLGAtR4YEuRiq9XQIGdchtrLovGRpFA
     */

    @SerializedName("code")
    private int code;
    @SerializedName("msg")
    private String msg;
    @SerializedName("token")
    private String token;

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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
