package com.fastjrun.helper;

import com.fastjrun.packet.BaseRestDefaultResponseBody;
import com.fastjrun.packet.BaseRestResponse;
import com.fastjrun.packet.BaseRestResponseHead;

public class RestResponseHelper {

    /**
     * 
     * @Title: getSuccessResult @Description: 创建处理成功的响应体 @param @return @return
     *         BaseRestResponse <BaseRestDefaultResponseBody> @throws
     */

    public static BaseRestResponse<BaseRestDefaultResponseBody> getSuccessResult() {
        BaseRestResponse<BaseRestDefaultResponseBody> response = new BaseRestResponse<BaseRestDefaultResponseBody>();
        BaseRestResponseHead responseHead = new BaseRestResponseHead();
        responseHead.setCode("0000");
        responseHead.setMsg("ok");
        response.setHead(responseHead);
        BaseRestDefaultResponseBody responseBody = new BaseRestDefaultResponseBody();
        response.setBody(responseBody);
        return response;
    }

    public static BaseRestResponse<BaseRestDefaultResponseBody> getFailResult(String code, String msg) {
        BaseRestResponse<BaseRestDefaultResponseBody> response = new BaseRestResponse<BaseRestDefaultResponseBody>();
        BaseRestResponseHead responseHead = new BaseRestResponseHead();
        responseHead.setCode(code);
        responseHead.setMsg(msg);
        response.setHead(responseHead);
        BaseRestDefaultResponseBody responseBody = new BaseRestDefaultResponseBody();
        response.setBody(responseBody);
        return response;
    }

}
