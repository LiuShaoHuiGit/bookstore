package com.book.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author：
 * @date：2019/6/13
 * @version：1.0.0
 * @description：日期时间工具类
 */
public class DateUtils {

	private static final Logger logger = LoggerFactory.getLogger(DateUtils.class);

	/**
	 * Date转String
	 * @param format 格式
	 * @return
	 */
	public static String parseDate(String format) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(new Date());
	}

	/**
	 * 现在到明年所剩秒数
	 * @return 秒
	 */
	public static int secondDistanceYear() {
		Calendar c = Calendar.getInstance();
		long now = c.getTimeInMillis();
		c.set(c.get(Calendar.YEAR) + 1, 0, 1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		return parseSecond(c, now);
	}

	/**
	 * 现在到下月所剩秒数
	 * @return 秒
	 */
	public static int secondDistanceMonth() {
		Calendar c = Calendar.getInstance();
		int d = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		int now = c.get(Calendar.DAY_OF_MONTH);
		return ((d - now) * 60 * 60 * 24) + secondDistanceDay();
	}

	/**
	 * 现在到次日凌晨所剩秒数
	 * @return 秒
	 */
	public static int secondDistanceDay() {
		Calendar c = Calendar.getInstance();
		long now = c.getTimeInMillis();
		c.add(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		return parseSecond(c, now);
	}

	/**
	 * 现在到下小时所剩秒数
	 * @return 秒
	 */
	public static int secondDistanceHour() {
		Calendar c = Calendar.getInstance();
		long now = c.getTimeInMillis();
		c.set(Calendar.HOUR_OF_DAY, c.get(Calendar.HOUR_OF_DAY) + 1);
		c.set(Calendar.MINUTE, 0);
		return parseSecond(c, now);
	}

	/**
	 * 现在到下分钟所剩秒数
	 * @return 秒
	 */
	public static int secondDistanceMinute() {
		Calendar c = Calendar.getInstance();
		long now = c.getTimeInMillis();
		c.set(Calendar.MINUTE, c.get(Calendar.MINUTE) + 1);
		return parseSecond(c, now);
	}

	private static int parseSecond(Calendar calendar, long now) {
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		Long millisecond = calendar.getTimeInMillis() - now;
		millisecond = millisecond / 1000;
		return millisecond.intValue();
	}

	/**
	 * 获取多少秒后的时间
	 * @param second 秒
	 * @return
	 */
	public static Date getTimeBySecond(int second) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.SECOND, c.get(Calendar.SECOND) + second);
		return c.getTime();
	}

    /**
     * 获取下分钟的时间
     * @return
     */
    public static Date getNextMinute() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.MINUTE, c.get(Calendar.MINUTE) + 1);
        return c.getTime();
    }

	/**
	 * 获取下小时的时间
	 * @return
	 */
	public static Date getNextHour() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, c.get(Calendar.HOUR_OF_DAY) + 1);
		return c.getTime();
	}

	/**
	 * 获取下一天的时间
	 * @return
	 */
	public static Date getNextDay() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, 1);
		return c.getTime();
	}

}
