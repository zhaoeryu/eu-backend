package cn.eu.common.utils;

import cn.hutool.core.util.StrUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 获取公网IP
 * @see <a href="https://www.jianshu.com/p/d985711629da">参考文章<a/>
 * @author zhaoeryu
 * @since 2023/6/6
 */
public class PublicIpUtil {
    // 方法1
    private static String getNowIP1() throws IOException {
        String ip = null;
        String chinaz = "http://ip.chinaz.com";
        StringBuilder inputLine = new StringBuilder();
        String read = "";
        URL url = null;
        HttpURLConnection urlConnection = null;
        BufferedReader in = null;
        try {
            url = new URL(chinaz);
            urlConnection = (HttpURLConnection) url.openConnection();
            in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
            while ((read = in.readLine()) != null) {
                inputLine.append(read + "\r\n");
            }
            Pattern p = Pattern.compile("\\<dd class\\=\"fz24\">(.*?)\\<\\/dd>");
            Matcher m = p.matcher(inputLine.toString());
            if (m.find()) {
                String ipstr = m.group(1);
                ip = ipstr;
            }
        } finally {
            if (in != null) {
                in.close();
            }
        }
        if (StrUtil.isBlank(ip)) {
            throw new RuntimeException();
        }
        return ip;
    }
    // 方法2
    private static String getNowIP2() throws IOException {
        String ip = null;
        BufferedReader br = null;
        try {
            URL url = new URL("https://v6r.ipip.net/?format=callback");
            br = new BufferedReader(new InputStreamReader(url.openStream()));
            String s = "";
            StringBuffer sb = new StringBuffer("");
            String webContent = "";
            while ((s = br.readLine()) != null) {
                sb.append(s + "\r\n");
            }
            webContent = sb.toString();
            int start = webContent.indexOf("(") + 2;
            int end = webContent.indexOf(")") - 1;
            webContent = webContent.substring(start, end);
            ip = webContent;
        } finally {
            if (br != null)
                br.close();
        }
        if (StrUtil.isBlank(ip)) {
            throw new RuntimeException();
        }
        return ip;
    }
    // 方法3
    private static String getNowIP3() throws IOException {
        String ip = null;
        String objWebURL = "https://ip.900cha.com/";
        BufferedReader br = null;
        try {
            URL url = new URL(objWebURL);
            br = new BufferedReader(new InputStreamReader(url.openStream()));
            String s = "";
            String webContent = "";
            while ((s = br.readLine()) != null) {
                if (s.indexOf("我的IP:") != -1) {
                    ip = s.substring(s.indexOf(":") + 1);
                    break;
                }
            }
        } finally {
            if (br != null)
                br.close();
        }
        if (StrUtil.isBlank(ip)) {
            throw new RuntimeException();
        }
        return ip;
    }
    // 方法4
    private static String getNowIP4() throws IOException {
        String ip = null;
        String objWebURL = "https://bajiu.cn/ip/";
        BufferedReader br = null;
        try {
            URL url = new URL(objWebURL);
            br = new BufferedReader(new InputStreamReader(url.openStream()));
            String s = "";
            String webContent = "";
            while ((s = br.readLine()) != null) {
                if (s.indexOf("互联网IP") != -1) {
                    ip = s.substring(s.indexOf("'") + 1, s.lastIndexOf("'"));
                    break;
                }
            }
        } finally {
            if (br != null)
                br.close();
        }
        if (StrUtil.isBlank(ip)) {
            throw new RuntimeException();
        }
        return ip;
    }
    public static String getPublicIP() {
        String ip = null;
        // 第一种方式
        try {
            ip = getNowIP1();
            ip.trim();
        } catch (Exception e) {
            System.out.println("getPublicIP - getNowIP1 failed ~ ");
        }
        if (!StrUtil.isBlank(ip))
            return ip;
        // 第二种方式
        try {
            ip = getNowIP2();
            ip.trim();
        } catch (Exception e) {
            System.out.println("getPublicIP - getNowIP2 failed ~ ");
        }
        if (!StrUtil.isBlank(ip))
            return ip;
        // 第三种方式
        try {
            ip = getNowIP3();
            ip.trim();
        } catch (Exception e) {
            System.out.println("getPublicIP - getNowIP3 failed ~ ");
        }
        if (!StrUtil.isBlank(ip))
            return ip;
        // 第四种方式
        try {
            ip = getNowIP4();
            ip.trim();
        } catch (Exception e) {
            System.out.println("getPublicIP - getNowIP4 failed ~ ");
        }
        if (!StrUtil.isBlank(ip))
            return ip;
        return ip;
    }

    public static void main(String[] args) {
        System.out.println(getPublicIP());
    }
}
