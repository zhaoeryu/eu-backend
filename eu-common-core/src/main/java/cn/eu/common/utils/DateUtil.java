package cn.eu.common.utils;

/**
 * @author zhaoeryu
 * @since 2023/6/5
 */
public class DateUtil {

    /**
     * 60*60*3 转换成 3小时20分10秒
     * @param seconds 秒数
     * @return 秒数转换成时分秒后的字符串
     */
    public static String secondsToTime(int seconds) {
        // 天
        int d = seconds / (3600 * 24);
        //小时
        int h = (seconds % (3600 * 24)) / 3600;
        //分钟
        int m = (seconds % 3600) / 60;
        //秒
        int s = seconds % 60;
        StringBuilder sb = new StringBuilder();
        if (d > 0) {
            sb.append(d).append("天");
        }
        if (h > 0) {
            sb.append(h).append("小时");
        }
        if (m > 0) {
            sb.append(m).append("分");
        }
        if (s > 0) {
            sb.append(s).append("秒");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(DateUtil.secondsToTime(5));
        System.out.println(DateUtil.secondsToTime(60));
        System.out.println(DateUtil.secondsToTime(60 * 60));
        System.out.println(DateUtil.secondsToTime(60 * 60 * 24));
        System.out.println(DateUtil.secondsToTime(60 * 60 * 24 + 60 * 60 + 60 + 1));
        System.out.println(DateUtil.secondsToTime(3500));
    }
}
