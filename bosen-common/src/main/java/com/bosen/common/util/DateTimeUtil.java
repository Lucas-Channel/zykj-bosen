package com.bosen.common.util;

import org.springframework.util.StringUtils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Objects;

/**
 * 日期时间工具类
 */
public class DateTimeUtil {

    /**
     * 获取当天凌晨零点时间
     * @return LocalDateTime
     */
    public static LocalDateTime getToday() {
        return LocalDateTime.parse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).concat(" 00:00:00"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    /**
     * 两个LocalDateTime相差的年数
     * @param first 第一个
     * @param second 第二个
     * @return 相差的年数，如果 first < second （即first的公元年小于second的），返回正数
     */
    public static Integer diffYears(LocalDateTime first, LocalDateTime second) {
        Period period = Period.between(first.toLocalDate(), second.toLocalDate());
        return period.getYears();
    }

    /**
     * 两个LocalDateTime相差的分钟数
     * @param first 第一个
     * @param second 第二个
     * @return 相差的分钟数，如果 first < second （即first的时间小于second的），返回正数
     */
    public static long diffMinutes(LocalDateTime first, LocalDateTime second) {
        Duration dur = Duration.between(first, second);
        return dur.toMinutes();
    }

    /**
     * 两个LocalDateTime相差的天数
     * @param first 第一个
     * @param second 第二个
     * @return 相差的天数，如果 first < second （即first的时间小于second的），返回正数
     */
    public static long diffDays(LocalDateTime first, LocalDateTime second) {
        Duration dur = Duration.between(first, second);
        return dur.toDays();
    }

    /**
     * 获取当前时间到今日凌晨的秒数
     * @return seconds
     */
    public static Long getNowToNextDaySeconds() {
        LocalDateTime currentTime = LocalDateTime.now(ZoneId.systemDefault());
        LocalDateTime midNight = LocalDateTime.of(LocalDate.now(ZoneId.systemDefault()).plusDays(1), LocalTime.MIN);
        return Duration.between(currentTime, midNight).getSeconds();
    }

    /**
     * 格式化LocalDateTime, 返回格式为yyyy-MM-dd HH:mm:ss
     * @param dateTime 时间
     * @return 传入空值, 则返回空字符串
     */
    public static String formatDateTime(LocalDateTime dateTime) {
        if (Objects.isNull(dateTime)) {
            return "";
        }
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    /**
     * 格式化LocalDateTime, 返回格式为yyyy-MM-dd HH:mm:ss
     * @param localDateTime 时间
     */
    public static String formatLocalDateTimeToString(LocalDateTime localDateTime, String format) {
        if (localDateTime == null) {
            return null;
        }
        if (format.isEmpty()) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(format);
        return localDateTime.format(dtf);
    }

    /**
     * 格式化LocalDateTime, 返回格式为yyyy-MM-dd HH:mm
     * @param dateTime 时间
     * @return 传入空值, 则返回空字符串
     */
    public static String formatMinuteTime(LocalDateTime dateTime) {
        if (Objects.isNull(dateTime)) {
            return "";
        }
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    /**
     * 格式化LocalDateTime, 返回格式为yyyy-MM-dd
     * @param dateTime 时间
     * @return 传入空值, 则返回空字符串
     */
    public static String formatDate(LocalDateTime dateTime) {
        if (Objects.isNull(dateTime)) {
            return "";
        }
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

    }


    /**
     * 格式化LocalDate, 返回格式为yyyy-MM-dd
     * @param date 时间
     * @return 传入空值, 则返回空字符串
     */
    public static String formatDate(LocalDate date) {
        if (Objects.isNull(date)) {
            return "";
        }
        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

    }


    /**
     * 解析yyyy-MM-dd格式字符串, 并填充00:00:00, 返回LocalDateTime
     * @param str 日期字符串
     * @return 传入空值, 则返回值
     */
    public static LocalDateTime parseDateStart(String str) {
        return parseConcatDateTime(str);
    }

    /**
     * 解析yyyy-MM-dd格式字符串, 并填充00:00:00, 返回LocalDateTime
     * @param str 日期字符串
     * @return 传入空值, 则返回值
     */
    public static LocalDateTime parseConcatDateTime(String str) {
        if (!StringUtils.hasLength(str)) {
            return null;
        }
        return LocalDateTime.parse(str.concat(" 00:00:00"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    /**
     * 解析yyyy-MM-dd格式字符串, 并填充00:00:00, 返回LocalDateTime
     * @param str 日期字符串
     * @return 传入空值, 则返回值
     */
    public static LocalDateTime parseDateEnd(String str) {
        if (!StringUtils.hasLength(str)) {
            return null;
        }
        return LocalDateTime.parse(str.concat(" 23:59:59"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    /**
     * 解析yyyy-MM-dd HH:mm:ss格式字符串, 返回LocalDateTime
     * @param str 时间字符串
     * @return 传入空值, 则返回值
     */
    public static LocalDateTime parseDateTime(String str) {
        if (!StringUtils.hasLength(str)) {
            return null;
        }
        return LocalDateTime.parse(str, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    /**
     * 解析指定格式字符串, 返回LocalDateTime
     * @param str 时间字符串
     * @param dateTimeFormatter 解析格式
     * @return 传入空值, 则返回值
     */
    public static LocalDateTime parseDateTime(String str,DateTimeFormatter dateTimeFormatter) {
        if (!StringUtils.hasLength(str)) {
            return null;
        }
        return LocalDateTime.parse(str, dateTimeFormatter);
    }
    /**
     * 解析yyyy-MM-dd格式字符串, 返回LocalDate
     * @param str 时间字符串
     * @return 传入空值, 则返回值
     */
    public static LocalDate parseDate(String str) {
        if (!StringUtils.hasLength(str)) {
            return null;
        }
        return LocalDate.parse(str, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * 获取的毫秒值(系统时区)
     * @param dateTime 时间
     * @return 返回结果
     */
    public static Long toMilliSecond(LocalDateTime dateTime) {
        if (Objects.isNull(dateTime)) {
            return null;
        }
        return dateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * 获取的毫秒值(系统时区)
     * @param milliSecond 时间戳
     * @return 返回结果
     */
    public static LocalDateTime toLocalDateTime(long milliSecond) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(milliSecond), ZoneId.systemDefault());
    }

    /**
     * 获取当前时间秒值(不含年月日)
     * @return 返回结果
     */
    public static int getNowSecOfHHmmss() {
        return getNowSecOfHHmmss(null);
    }

    /**
     * 获取时间戳中的时间秒值(不含年月日)
     * @return 返回结果
     */
    public static int getNowSecOfHHmmss(Long time) {
        Calendar instance = Calendar.getInstance();
        if(time!=null) {
            instance.setTimeInMillis(time);
        }
        return instance.get(Calendar.HOUR_OF_DAY)*3600+instance.get(Calendar.MINUTE)*60+instance.get(Calendar.SECOND);
    }

    /**
     * 获得目标时间戳中（时分秒）与当前时间（时分秒）的秒差
     * endTime=xxxx-xx-xx 16:00:00 ,假设当前时间：xxxx-xx-xx 15:00:00,返回1*60*60=3600秒
     * @param endTime 日期
     * @return 结果
     */
    public static int offsetNowSecond(Long endTime) {
        Calendar instance = Calendar.getInstance();
        if(endTime!=null) {
            instance.setTimeInMillis(endTime);
        }
        return getNowSecOfHHmmss(endTime)-getNowSecOfHHmmss();
    }

    /**
     * 判断当前时间的时分秒，是否在指定时段的时分秒之间，
     * @param startTime 开始日期
     * @param endTime 结束日期
     * @return 结果
     */
    public static boolean isBelongPeriodOfNow(Long startTime,Long endTime){
        int nowSec=DateTimeUtil.getNowSecOfHHmmss();
        return getNowSecOfHHmmss(startTime)<nowSec&&getNowSecOfHHmmss(endTime)>nowSec;
    }


    /**
     * 复制指定日期的时分秒
     * @param targetTime 目标时间
     * @param hhmmssTime 被复制时分秒的时间戳
     * @return 返回结果
     */
    public static Long copyHHmmss(Long targetTime,Long hhmmssTime) {
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(targetTime);
        Calendar hhmmssCal = Calendar.getInstance();
        hhmmssCal.setTimeInMillis(hhmmssTime);
        instance.set(Calendar.HOUR_OF_DAY,hhmmssCal.get(Calendar.HOUR_OF_DAY));
        instance.set(Calendar.MINUTE,hhmmssCal.get(Calendar.MINUTE));
        instance.set(Calendar.SECOND,hhmmssCal.get(Calendar.SECOND));
        return instance.getTimeInMillis();
    }

    /**
     * 时间转换 yyyy-MM-dd HH:mm:ss
     * @param dateTime 入参
     * @return 返回结果
     */
    public static String toDateString(Long dateTime) {
        if (dateTime == null) {
            return "";
        }

        return DateTimeUtil.formatDateTime(DateTimeUtil.toLocalDateTime(dateTime));
    }

    /**
     * 时间转换 yyyy-MM-dd
     * @param dateTime 入参
     * @return 返回结果
     */
    public static String toStrDate(Long dateTime) {
        if (dateTime == null) {
            return "";
        }

        return DateTimeUtil.formatDate(DateTimeUtil.toLocalDateTime(dateTime));
    }
    /**
     * 时间转换 yyyy-MM-dd HH:mm
     * @param dateTime 入参
     * @return 返回结果
     */
    public static String toDateStr(Long dateTime) {
        if (dateTime == null) {
            return "";
        }

        return DateTimeUtil.formatMinuteTime(DateTimeUtil.toLocalDateTime(dateTime));
    }
    public static Long toDateLong(String dateTime) {
        if (Objects.isNull(dateTime)) {
            return null;
        }

        return DateTimeUtil.toMilliSecond(DateTimeUtil.parseDateTime(dateTime));
    }

    /**
     * LocalDateTime转换为Unix时间戳
     * @param localDateTime LocalDateTime实例
     * @return Unix时间戳（精确到毫秒）
     */
    public static long localDateTimeToTimestamp(LocalDateTime localDateTime) {
        if (Objects.isNull(localDateTime)) {
            return 0L;
        }

        return localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

}
