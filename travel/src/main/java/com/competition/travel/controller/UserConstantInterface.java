package com.competition.travel.controller;
public interface UserConstantInterface {
    // 请求的网址
    public static final String WX_LOGIN_URL = "https://api.weixin.qq.com/sns/jscode2session";
    // 你的appid
    public static final String WX_LOGIN_APPID = "wx2eddb44a43c21717";
    // 你的密匙
    public static final String WX_LOGIN_SECRET = "3cf029e0c1971fbf7c917ebb9a62e92c";
    // 固定参数
    public static final String WX_LOGIN_GRANT_TYPE = "authorization_code";
}
