package com.li.app.gateway.filter;

import com.li.app.gateway.utils.AppJwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.RequestPath;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Objects;

/**
 * @Author: liyuelian
 * @Date: 2024/7/11 00:16
 * @Description:
 **/
@Component
@Slf4j
public class AuthorizeFilter implements Ordered, GlobalFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //1.判断是否为登录请求
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        if (request.getURI().getPath() == null) {
            //返回错误
            response.setStatusCode(HttpStatus.NOT_FOUND);
            //结束请求
            return response.setComplete();
        }

        //是否为登录请求
        if (request.getURI().getPath().contains("/login")) {
            //直接放行
            return chain.filter(exchange);
        }

        //2.非登录请求
        String token = request.getHeaders().getFirst("token");

        //token是否为空
        if (StringUtils.isBlank(token)) {
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            //结束请求
            return response.setComplete();
        }

        //获取token
        try {
            Claims claimsBody = AppJwtUtil.getClaimsBody(token);
            //是否过期(-1：有效，0：有效，1：过期，2：过期)
            int result = AppJwtUtil.verifyToken(claimsBody);
            if (result == 1 || result == 2) {
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                return response.setComplete();
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }

        return chain.filter(exchange);
    }

    /**
     * 指定过滤器的执行顺序，值越小，执行顺序越靠前
     *
     * @return
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
