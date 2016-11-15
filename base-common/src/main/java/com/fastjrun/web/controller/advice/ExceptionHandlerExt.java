package com.fastjrun.web.controller.advice;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fastjrun.common.MediaTypes;
import com.fastjrun.common.RestException;
import com.fastjrun.common.ServiceException;
import com.fastjrun.helper.RestResponseHelper;
import com.fastjrun.packet.BaseRestDefaultResponseBody;
import com.fastjrun.packet.BaseRestResponse;

import net.sf.json.JSONObject;

@ControllerAdvice
public class ExceptionHandlerExt extends ResponseEntityExceptionHandler {

    protected final Log log = LogFactory.getLog(this.getClass());

    /**
     * 
     * @Title: handleException @Description: 处理RestException. @param @param ex @param @param
     *         request @param @return @return ResponseEntity <?> @throws
     */
    @ExceptionHandler(value = { RestException.class })
    public final ResponseEntity<?> handleException(RestException ex,
            WebRequest request) {

        HttpHeaders headers = new HttpHeaders();

        String reqContentType = request.getHeader("Content-Type");

        headers.setContentType(MediaType.parseMediaType(reqContentType));

        if (MediaTypes.JSON_UTF_8.equals(reqContentType)) {
            BaseRestResponse<BaseRestDefaultResponseBody> result = RestResponseHelper
                    .getFailResult(ex.errorCode, ex.getMessage());

            JSONObject jsonObject = JSONObject.fromObject(result);

            return handleExceptionInternal(ex, jsonObject, headers,
                    HttpStatus.OK, request);
        } else {
            return super.handleException(ex, request);
        }

    }

    /**
     * 
     * @Title: handleException @Description: 处理RestException. @param @param ex @param @param
     *         request @param @return @return ResponseEntity <?> @throws
     */
    @ExceptionHandler(value = { ServiceException.class })
    public final ResponseEntity<?> handleException(ServiceException ex,
            WebRequest request) {

        HttpHeaders headers = new HttpHeaders();

        String reqContentType = request.getHeader("Content-Type");

        headers.setContentType(MediaType.parseMediaType(reqContentType));
        if (MediaTypes.JSON_UTF_8.equals(reqContentType)) {
            BaseRestResponse<BaseRestDefaultResponseBody> result = RestResponseHelper
                    .getFailResult(ex.getCode(), ex.getMsg());

            JSONObject jsonObject = JSONObject.fromObject(result);

            return handleExceptionInternal(ex, jsonObject, headers,
                    HttpStatus.OK, request);
        } else {
            return super.handleException(ex, request);
        }

    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<?> handleSysException(Exception ex,
            WebRequest request) {

        HttpHeaders headers = new HttpHeaders();

        String reqContentType = request.getHeader("Content-Type");

        headers.setContentType(MediaType.parseMediaType(reqContentType));

        if (MediaTypes.JSON_UTF_8.equals(reqContentType)) {
            BaseRestResponse<BaseRestDefaultResponseBody> result = RestResponseHelper
                    .getFailResult("500", "系统错误，请稍后重试");

            JSONObject jsonObject = JSONObject.fromObject(result);

            String body = jsonObject.toString();

            log.info(body);

            log.error("异常", ex);

            return handleExceptionInternal(ex, jsonObject, headers,
                    HttpStatus.INTERNAL_SERVER_ERROR, request);
        } else {
            return super.handleException(ex, request);
        }

    }

}
