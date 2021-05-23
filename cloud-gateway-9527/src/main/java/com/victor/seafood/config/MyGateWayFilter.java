package com.victor.seafood.config;

import com.alibaba.fastjson.JSONObject;
import com.victor.seafood.util.JwtUtils;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

@Component
public class MyGateWayFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println("----------------全局过滤器------------------------");
        System.out.println(exchange.getRequest().getURI());
        String path = exchange.getRequest().getURI().toString();
        if (path.contains("login") || path.contains("register") || path.contains("alipay") || path.contains("getCode")){
            return chain.filter(exchange);//放行
        }
        String token = exchange.getRequest().getHeaders().getFirst("Authorization");

//        if (name == null) {
//            System.out.println("非法用户");
//            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
//            return exchange.getResponse().setComplete();  //拒绝进入
//        }
        if (token == null || "".equals(token) || "null".equals(token)){
            return catchMyException(exchange,"请先登录",401);
        }
        try{
            Map parse = JwtUtils.Parse(token);
            Date lastTime = (Date) parse.get("lastTime");
            Date now = new Date();
            if(now.after(lastTime)){
                return catchMyException(exchange,"登录过期，请重新登录",401);
            }

            System.out.println(parse);
        }catch (ExpiredJwtException e){
            return catchMyException(exchange,"登录过期，请重新登录",401);
        }
        return chain.filter(exchange);//放行
    }

    @Override
    public int getOrder() {
        return 0;
    }


    //处理验证不通过的情况
    public Mono<Void> catchMyException(ServerWebExchange exchange,String msg,Integer code){
        ServerHttpResponse response = exchange.getResponse();
        JSONObject message = new JSONObject();
        message.put("code", code);
        message.put("message", msg);
        byte[] bits = message.toJSONString().getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = response.bufferFactory().wrap(bits);
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        //指定编码，否则在浏览器中会中文乱码
        response.getHeaders().add("Content-Type", "text/plain;charset=UTF-8");
        return response.writeWith(Mono.just(buffer));
    }
}