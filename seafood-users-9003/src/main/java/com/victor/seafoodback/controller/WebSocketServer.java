package com.victor.seafoodback.controller;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.victor.seafoodback.dao.UserDao;
import com.victor.seafoodback.entity.Message;
import com.victor.seafoodback.entity.User;
import com.victor.seafoodback.vo.UserInfoVo;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.SetUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.awt.dnd.DropTargetEvent;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/messageServer/{userId}/{userName}")
@Component
@Slf4j
public class WebSocketServer {

    private static final Integer SEND_USER_LIST = 1;
    private static final Integer SEND_COMMON_MESSAGE = 2;

    /**
     * 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
     */
    private static int onlineCount = 0;
    /**
     * concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
     */
    private static ConcurrentHashMap<String, WebSocketServer> webSocketMap = new ConcurrentHashMap<>();
    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    private Session session;
    private Integer userId;
    private String userName;

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("userName") String userName, @PathParam("userId") Integer userId) {
        this.session = session;
        this.userName = userName;
        this.userId = userId;
        if (webSocketMap.containsKey(userName)) {
            webSocketMap.remove(userName);
            webSocketMap.put(userName, this);
        } else {
            webSocketMap.put(userName, this);
            addOnlineCount();
        }

        Set<String> userNameList = webSocketMap.keySet();

        System.out.println(userNameList);

        for (Object username : userNameList) {
            List<String> userNameList2 = new ArrayList<>(userNameList);
            userNameList2.remove(username);
            HashMap<String, Object> map = new HashMap<>();
            map.put("messageType", SEND_USER_LIST);
            map.put("userList", userNameList2);
            try {
                webSocketMap.get(username).session.getBasicRemote().sendText(JSON.toJSONString(map));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        log.info("用户连接:" + userName + ",当前在线人数为:" + getOnlineCount());

        try {
            sendMessage("连接成功");
        } catch (IOException e) {
            log.error("用户:" + userName + ",网络异常!!!!!!");
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        if (webSocketMap.containsKey(userName)) {
            webSocketMap.remove(userName);
            Set<String> userNameList = webSocketMap.keySet();

            for (Object username : userNameList) {
                List<String> userNameList2 = new ArrayList<>(userNameList);
                userNameList2.remove(username);
                HashMap<String, Object> map = new HashMap<>();
                map.put("messageType", SEND_USER_LIST);
                map.put("userList", userNameList2);
                try {
                    webSocketMap.get(username).session.getBasicRemote().sendText(JSON.toJSONString(map));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            subOnlineCount();
        }
        log.info("用户退出:" + userName + ",当前在线人数为:" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("用户消息:" + userName + ",报文:" + message);
        if (StringUtils.isNotBlank(message)) {
            try {
                //解析发送的报文
                JSONObject messageJSON = JSON.parseObject(message);
                //追加发送人(防止串改)
                String toUserName = messageJSON.getString("toUserName");
                String messageOnly = messageJSON.getString("message");
                //传送给对应toUserId用户的websocket
                if (StringUtils.isNotBlank(toUserName) && webSocketMap.containsKey(toUserName)) {
                    HashMap<String, Object> map = new HashMap<>();
                    map.put("messageType", SEND_COMMON_MESSAGE);
                    map.put("fromUserName", this.userName);
                    map.put("message", messageOnly);
                    webSocketMap.get(toUserName).sendMessage(JSON.toJSONString(map));
                } else {
                    log.error("请求的用户: " + toUserName + " 不在线");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("用户错误: " + this.userName + " ,原因:" + error.getMessage());
        error.printStackTrace();
    }

    /**
     * 实现服务器主动推送
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }


    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }
}
