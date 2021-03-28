package com.victor.seafoodback.controller;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.victor.seafoodback.entity.CommonResult;
import com.victor.seafoodback.entity.Seafood;
import com.victor.seafoodback.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/admin")
public class ApiController {
    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2021000117626392";

    // 商户私钥，您的PKCS8格式RSA2私钥    （自己生成的私钥，记得去除空格，换行符）
    public static String merchant_private_key = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCLZR1k9IYOtqfFcp41iKguiln0Q77aTbNGvkwZIt59v7Kxhot0RnQLkRMHA4SAWN2op3sdiL8/7vIjFZE4UxDEX+mQRnlnjYE5tg+nzgTD2pYz5uMYigDJqWtuLs6V2OBz+IJwILzCOwXOR2LZ9KB4JMC7pkO5P5Y+U1OTthpTPjKIx7OdplDJyRQuPRyCGyOAKiYx5OvuLnhAVkDxdVDXpoW8ltFMSZqhVh4MMbu96qBgdmnFET1AyImlN2HGG+JJeOUibhJ9P/mmQvjvaWwcD0we5/9R0dqvUPUqvOr2XZLvYPsiQipE8Br2W8L8uGjCX/AtwKjykQQ9ocqHHcZVAgMBAAECggEAAg8USBF0iXMZN0liHNhP46AXhAwcHY1YQIs/BIAHWUQjl4utM6J2xFT4OvpKUrr9SyhuIRmL+Z3F5c7Dvlk7virMgF0KHgVxCzEtOgVI/LuVzF50rEAdktz1wULfukbhydS52H4jS5sjktBrHV0Hob6pOhxSk8Hkrjb83Qmrid8zm8fSpq6fwcxjbGSSBSrRC5PEqYxVdSe7fk6YTc3Wana2zqeFCguLKagl3sNJldW4TVIzfUq9DAeNliH3LFUiVnaSHnWWoUu15JIEGDYoIDRNZQ1XYlDSEIkux4szXvHYPcx7p2p1vZq6xuAVNFHiFH+1tMLYn1rYE6sMdn+0AQKBgQDo9obwEn/idX0g2pXCmoxY2MWoO323vpw0wZeDoGWGO9wfG9jHBJn0KTm8b/NnTFBMO+CQpR3sXUsC7murb+IAqcsYDMhMaezFcneL08a+OYJ0E/ZjTu8dtyWF9FIdz5BRqBRak9qMmP4Z03HrEa92qTJjvKcr1yKl9BiKtnjpIQKBgQCZLehRsOVBya4B7hoE0R8h0oyghgHpvhEIxiBt304lgQumy3+1PPQXV3/xxCK70P5Cab3VBwfavDDu/tkedNKQRpRCO8696GRYGFCKI4C9INmk6F5z51PT4idiU4NrQwrTWzT4YnlLJGp/p3JJAG7sLdyRm0R+pAj3AuQwmIiytQKBgQCx3Y+W+jjjuRpJHDrdcswXBaKb2Jga0xBGg0xgNfVtsIRWyBcRlGTK8zLeuYbYZn6kRc8mALtA/NXJrilMokamhPzIJvJCzVj2Q3gcrRt/nz8HyzoCBoEwaeXfSXPsyM4CWlb39Q0W7bSMGVFPzZfu/C250o68PSx6Ek0ukzTroQKBgQCVlQzu6S2XRtkTrW3fEk+yeac2AmbP2PlNdhyo7o2jdhbotIRrebBrMtkQQJYijn4cYKIt6wWyhl/Kozdm1HHizeltnHXRYaTLmkLcArtu9StULE5AJL/pg0kubZtCHV/u8XwSqicER/Vv3xZtT20CWe6wic0jVhAjFsG66tlimQKBgQCnmHf40PhlwT0s1dQm+7oIWP1OCiJKwAmhFWOKeuSV6fObdE+kAA751jKUr1r4IA4a+0IbSbbeT4SRVb7si4mDja6rDdVWqwZiRUGCo5nTB8UARqRuqx90Ow67KlgzYq81dGB8t7C9/YGiiDZPlu3fTqxTPFHlFZMR9IVwM2M4pw==";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAi2UdZPSGDranxXKeNYioLopZ9EO+2k2zRr5MGSLefb+ysYaLdEZ0C5ETBwOEgFjdqKd7HYi/P+7yIxWROFMQxF/pkEZ5Z42BObYPp84Ew9qWM+bjGIoAyalrbi7Oldjgc/iCcCC8wjsFzkdi2fSgeCTAu6ZDuT+WPlNTk7YaUz4yiMeznaZQyckULj0cghsjgComMeTr7i54QFZA8XVQ16aFvJbRTEmaoVYeDDG7veqgYHZpxRE9QMiJpTdhxhviSXjlIm4SfT/5pkL472lsHA9MHuf/UdHar1D1Krzq9l2S72D7IkIqRPAa9lvC/Lhowl/wLcCo8pEEPaHKhx3GVQIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://192.168.43,118:9527/admin/alipay/return_url";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    //public static String return_url = "http://127.0.0.1:8080/order";
    public static String return_url = "http://127.0.0.1:8080/order";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    @Autowired
    private OrderService orderService;



    @RequestMapping("/toPay")
    public void toPay(HttpServletRequest request, HttpServletResponse response, String name, String info,
                      Integer userId, Integer seafoodId, Integer addrId) throws Exception {
        System.out.println(name);
        System.out.println(info);
        System.out.println(userId);
        System.out.println(seafoodId);
        System.out.println(addrId);
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(gatewayUrl, app_id, merchant_private_key, "json", charset, alipay_public_key, sign_type);
        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(return_url);
        alipayRequest.setNotifyUrl(notify_url);
        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = UUID.randomUUID().toString();
        System.out.println("out_trade_no=" + out_trade_no);

        CommonResult commonResult = orderService.orderGetSeafood(seafoodId);
        JSONObject jsonObject = new JSONObject(commonResult.getData());
        jsonObject.get("outPrice");


        //付款金额，必填
        String total_amount = jsonObject.get("outPrice").toString();;
        System.out.println(total_amount);
        //订单名称，必填
        String subject = name;
        //商品描述，可空
        String body = info;

        alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\","
                + "\"total_amount\":\"" + total_amount + "\","
                + "\"subject\":\"" + subject + "\","
                + "\"body\":\"" + body + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");


        orderService.addOrder1(userId, seafoodId, addrId, out_trade_no);

        //请求
        String result = "";
        try {
            result = alipayClient.pageExecute(alipayRequest).getBody();
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

        //输出
        response.setContentType("text/html;charset=" + charset);
        response.getWriter().write(result);// 直接将完整的表单html输出到页面
        response.getWriter().flush();
        response.getWriter().close();
    }

    //回调验证.验证成功后可以返回自己想要跳转的页面
    @RequestMapping("/alipay/return_url")
    public void returnUrl(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, AlipayApiException {
        System.out.println("----------------------------notify_url------------------------");
        // 商户订单号
        String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
        //付款金额，必填
        String total_amount = new String(request.getParameter("WIDtotal_amount").getBytes("ISO-8859-1"), "UTF-8");
        //支付宝交易号
        String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");
        //交易状态
        String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");

        System.out.println("商户订单号:" + out_trade_no);
        System.out.println("付款金额:" + total_amount);
        System.out.println("支付宝交易号:" + trade_no);
        System.out.println("交易状态:" + trade_status);

        if (trade_status.equals("TRADE_SUCCESS")) {
            System.out.println("交易成功,进行其他业务逻辑处理........");
            try {
                response.getWriter().write("交易成功");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            System.out.println("交易失败");
        }
    }

}
