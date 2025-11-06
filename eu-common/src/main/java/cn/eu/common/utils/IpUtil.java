package cn.eu.common.utils;

import cn.dev33.satoken.spring.SpringMVCUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.lionsoul.ip2region.xdb.Searcher;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import jakarta.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zhaoeryu
 * @since 2023/6/4
 */
@Slf4j
public class IpUtil {

    private static final String UNKNOWN = "unknown";
    private static final String REGX_INNER_IP = "^(127\\.0\\.0\\.1)|(localhost)|(10\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3})|(172\\.((1[6-9])|(2\\d)|(3[01]))\\.\\d{1,3}\\.\\d{1,3})|(192\\.168\\.\\d{1,3}\\.\\d{1,3})$";
    private static final String REGX_REGION= "(.*?\\|)0\\|(.*?)\\|";

    public static String getClientIp() {
        return getClientIp(SpringMVCUtil.getRequest());
    }
    public static String getClientIp(HttpServletRequest request) {
        String ip = null;
        try {
            // k8s将真实的客户端IP，放到了x-Original-Forwarded-For。而将WAF的回源地址放到了 x-Forwarded-For了。
            ip = request.getHeader("X-Original-Forwarded-For");
            if (StrUtil.isBlank(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getHeader("X-Forwarded-For");
            }
            // 通过nginx获取ip
            if (StrUtil.isBlank(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getHeader("x-forwarded-for");
            }
            // 通过Apache代理获取ip
            if (StrUtil.isBlank(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            // 通过WebLogic代理获取ip
            if (StrUtil.isBlank(ip) || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            // 通过负载均衡获取IP地址（HTTP_CLIENT_IP、HTTP_X_FORWARDED_FOR）
            if (StrUtil.isBlank(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
            }
            // 通过Nginx获取ip（Nginx中的另一个变量，内容就是请求中X-Forwarded-For的信息）
            if (StrUtil.isBlank(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            }
            //兼容集群获取ip
            if (StrUtil.isBlank(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
                // 客户端和服务器为同一台机器时，获取的地址为IPV6格式："0:0:0:0:0:0:0:1"
                if ("0:0:0:0:0:0:0:1".equalsIgnoreCase(ip)) {
                    ip = "127.0.0.1";
                }
                if ("127.0.0.1".equalsIgnoreCase(ip)) {
                    //根据网卡取本机配置的IP
                    InetAddress iNet = null;
                    try {
                        iNet = InetAddress.getLocalHost();
                        ip = iNet.getHostAddress();
                    } catch (UnknownHostException e) {
                        log.error("根据网卡获取IP地址异常: ", e);
                    }
                }
            }
        } catch (Exception e) {
            log.error("获取IP地址异常 ", e);
        }
        //使用代理，则获取第一个IP地址
        if (!StrUtil.isBlank(ip) && ip.indexOf(",") > 0) {
            ip = ip.substring(0, ip.indexOf(","));
        }
        return ip;
    }

    public static boolean isInnerIp(String ip) {
        Pattern reg = Pattern.compile(REGX_INNER_IP);
        Matcher match = reg.matcher(ip);
        return match.find();
    }


    public static String getIpRegion(String ip) {
        try {
            if (isInnerIp(ip)) {
                return "内网IP";
            }
            // 从类路径中加载资源文件
            Resource resource = new ClassPathResource("ip2region/ip2region.xdb");
            byte[] cBuff = IoUtil.readBytes(resource.getInputStream());
            Searcher searcher = Searcher.newWithBuffer(cBuff);
            return searcher.search(ip).replaceAll(REGX_REGION, "$1$2|");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return UNKNOWN;
    }

    public static void main(String[] args) {
        // 中国|0|上海|上海市|电信
        // 美国|0|俄克拉何马|俄克拉何马城|谷歌
        System.out.println(getIpRegion("101.229.237.33"));
        System.out.println(getIpRegion("66.249.65.123"));
        System.out.println(getIpRegion("58.34.86.50"));
        System.out.println(isInnerIp("127.0.0.1"));
        System.out.println(isInnerIp("localhost"));
        System.out.println(isInnerIp("66.249.65.123"));
        System.out.println(isInnerIp("0:0:0:0:0:0:0:1"));
    }

}
