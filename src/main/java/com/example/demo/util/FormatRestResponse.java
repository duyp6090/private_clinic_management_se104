package com.example.demo.util;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.example.demo.dto.RestResponse;

import jakarta.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class FormatRestResponse implements ResponseBodyAdvice<Object> {

    @Override
    @Nullable
    public Object beforeBodyWrite(
            @Nullable Object body,
            MethodParameter returnType,
            MediaType selectedContentType,
            Class selectedConverterType,
            ServerHttpRequest request, ServerHttpResponse response) {
        HttpServletResponse servletResponse = ((ServletServerHttpResponse) response).getServletResponse();

        if (body instanceof RestResponse) {
            return body;
        }

        RestResponse<Object> restResponse = new RestResponse<>();

        // Set status code
        int status = servletResponse.getStatus();
        restResponse.setStatusCode(status);

        // Case error
        if (status >= 400) {
            restResponse.setError("CALL API FAILED");
            restResponse.setData(body);
        } else {
            // Case success
            restResponse.setData(body);
            restResponse.setMessage("CALL API SUCCESS");
        }

        return restResponse;
    }

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

}
