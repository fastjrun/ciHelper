package com.fastjrun.thirdparty.util;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fastjrun.thirdparty.helper.HttpHelper;

public class EasyMobUtil {

    protected final Log log = LogFactory.getLog(this.getClass());

    private static final String GRANT_TYPE = "client_credentials";

    private Map<String, String> headerMap = new HashMap<String, String>();

    private String registerUrl;

    private String updateNickNameUrl;

    private String refreshTokenUrl;

    private String createChatRoomUrl;

    private String apiServer;

    private String org_name;

    public String getApiServer() {
        return apiServer;
    }

    public void setApiServer(String apiServer) {
        this.apiServer = apiServer;
    }

    public String getOrg_name() {
        return org_name;
    }

    public void setOrg_name(String org_name) {
        this.org_name = org_name;
    }

    public String getApp_name() {
        return app_name;
    }

    public void setApp_name(String app_name) {
        this.app_name = app_name;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getClient_secret() {
        return client_secret;
    }

    public void setClient_secret(String client_secret) {
        this.client_secret = client_secret;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public long getExpireAt() {
        return expireAt;
    }

    public void setExpireAt(long expireAt) {
        this.expireAt = expireAt;
    }

    private String app_name;

    private String client_id;

    private String client_secret;

    // 当前Token的accessToken 有效期是60天
    private String access_token;

    // Token过期时间点
    private long expireAt;

    public void init() {
        this.registerUrl = this.apiServer + "/" + org_name + "/" + app_name + "/user";
        this.refreshTokenUrl = this.apiServer + "/" + org_name + "/" + app_name + "/token";
        this.updateNickNameUrl = this.apiServer + "/" + org_name + "/" + app_name + "/users";
        this.createChatRoomUrl = this.apiServer + "/" + org_name + "/" + app_name + "/chatrooms";
        this.headerMap.put("Content-Type", "application/json");
        String authorization = "Bearer " + this.access_token;
        this.headerMap.put("Authorization", authorization);
    }

    public JSONObject registerUser(String userName, String password) {

        JSONObject request = new JSONObject();
        request.put("username", userName);
        request.put("password", password);
        log.debug(request.toString());
        String responseStr = HttpHelper.requestJsonPost(request.toString(), this.registerUrl, this.headerMap);
        log.debug(responseStr);
        return JSONObject.fromObject(responseStr);

    }

    public JSONObject createChatRoomMaxUser2(String name, String description, String owner) {

        JSONObject request = new JSONObject();
        request.put("name", name);
        request.put("description", description);
        request.put("maxusers", 2);
        request.put("owner", owner);
        log.debug(request.toString());
        String responseStr = HttpHelper.requestJsonPost(request.toString(), this.createChatRoomUrl, this.headerMap);
        log.debug(responseStr);
        return JSONObject.fromObject(responseStr);

    }

    public JSONObject getChatRoomById(String chatSessionId) {

        String responseStr = HttpHelper.sendGet(this.createChatRoomUrl + "/" + chatSessionId, this.headerMap);
        log.debug(responseStr);
        return JSONObject.fromObject(responseStr);

    }

    public JSONObject updateNickName(String userName, String nickNameNew) {

        JSONObject request = new JSONObject();
        request.put("nickname", nickNameNew);
        log.debug(request.toString());
        String responseStr = HttpHelper.requestJsonPost(request.toString(), this.updateNickNameUrl + "/" + userName,
                this.headerMap);
        log.debug(responseStr);
        return JSONObject.fromObject(responseStr);

    }

    public void refreshToken() {
        if (System.currentTimeMillis() > this.expireAt) {
            JSONObject request = new JSONObject();
            request.put("grant_type", GRANT_TYPE);
            request.put("client_id", this.client_id);
            request.put("client_secret", this.client_secret);
            Map<String, String> headerMapToken = new HashMap<String, String>();
            headerMapToken.put("Content-Type", "application/json");
            log.debug(request.toString());
            String responseStr = HttpHelper.requestJsonPost(request.toString(), this.refreshTokenUrl, headerMapToken);

            JSONObject reponse = JSONObject.fromObject(responseStr);
            log.debug(responseStr);
            this.access_token = reponse.getString("access_token");
            this.expireAt = System.currentTimeMillis() + reponse.getLong("expires_in");
        }

    }
}
