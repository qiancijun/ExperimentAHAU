package com.qiancijun.application.core.chain;

/**
 * @author Xu Chun
 * @Desc
 * @Time 2021/3/17 19:11
 */

public class CalcuteRequest {
    private String type; // 请求类型

    public CalcuteRequest(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
