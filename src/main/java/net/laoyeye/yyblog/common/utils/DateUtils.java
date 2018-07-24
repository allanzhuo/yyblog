package net.laoyeye.yyblog.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * DateUtil 类主要是提供了对日期操作的一些方法，主要是指日期类型与字符型的转换；<br>
 * 日期的比较、日期的输入格式化等。
 * @author 小卖铺的老爷爷
 * @date 2015年5月8日
 * @website www.laoyeye.net
 */
public class DateUtils {

    private static final String DATE_FORMAT_yyyyMMdd = "yyyyMMdd";
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final String DATETIME_FORMAT_yyyyMMddHHmmss = "yyyyMMddHHmmss";

    public DateUtils() {
    }

    public static boolean isDate(String s) {
        return parseDate(s) != null;
    }

    /**
     * 获得当前日期天内的毫秒值
     * 
     * @param currDate
     * @return 毫秒值
     */
    public static long getCurrDayTimeMillis(Date currDate) {
        long currTime = 0;
        if (currDate == null) {
            return currTime;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(currDate);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minu = cal.get(Calendar.MINUTE);
        int sec = cal.get(Calendar.SECOND);
        currTime = (hour * 60 * 60 + minu * 60 + sec) * 1000;
        return currTime;
    }

    /**
     * 仅显示年月日的时间
     * 
     * @param date
     * @return 日期字符串
     */
    public static String showSimpleDate(String date) {
        if (date == null || date.trim().equals("")) {
            return "";
        }
        if (date.trim().length() > 10) {
            return date.trim().substring(0, 10);
        }
        return date.trim();
    }

    /**
     * 根据字符型的年、月、日参数，转换成相应的日期，<br>
     * 如果年、月、日 三个参数当中有一个是无效的，则返回空值，<br>
     * 需要注意的是，这里无效是指输入的字符串无法转换成整型数值。
     */
    public static Date parseDate(String year, String month, String day) {
        int intYear = 0;
        int intMonth = 0;
        int intDay = 0;
        try {
            intYear = Integer.parseInt(year);
            intMonth = Integer.parseInt(month);
            intDay = Integer.parseInt(day);
        } catch (Exception ex) {
            return null;
        }
        return parseDate(intYear, intMonth, intDay);
    }

    /**
     * 根据字符型的年、月、日参数，转换成相应的日期，<br>
     * 此方法对于 int month , int day 参数范围不做限制，<br>
     * 比如设置month为15，day为34等，系统可以直接将超出的部分累加到下一年或下一月。
     */
    public static Date parseDate(int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, day);
        return cal.getTime();
    }

    /**
     * 与Date parseDate(int year,int month,int day)方法类似，只是多了时、分、秒三个参数
     */
    public static Date parseDate(int year, int month, int day, int hour,
            int min, int sec) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, day, hour, min, sec);
        return cal.getTime();
    }

    /**
     * 将指定字符串按固定格式转换为日期格式，当前兼容的格式如下：<br>
     * 1、eg. 1978-12-21 14:21:25<br>
     * 2、eg. 12/21/1978 14:21:35<br>
     * 如果当前字符串格式违例，则返回null。
     */

    public static Date parseDate(String strDate, String format) {
        try {
            return getDate(strDate, format);
        } catch (Exception ex) {
            return null;
        }
    }

    private static Date getDate(String strDate, String format) throws Exception {
        SimpleDateFormat formator = new SimpleDateFormat(format);
        return formator.parse(strDate);
    }

    public static Date parseDate(String strDate) {
        Date now = null;
        try {
            now = getDate(strDate, DATETIME_FORMAT);
        } catch (Exception ex) {
            now = null;
        }
        if (now == null) {
            try {
                now = getDate(strDate, DATE_FORMAT);
            } catch (Exception e) {
                now = null;
            }
        }
        if (now == null) {
            try {
                now = getDate(strDate, DATETIME_FORMAT_yyyyMMddHHmmss);
            } catch (Exception e) {
                now = null;
            }
        }
        if (now == null) {
            try {
                now = getDate(strDate, DATE_FORMAT_yyyyMMdd);
            } catch (Exception e) {
                now = null;
            }
        }
        return now;
    }

    /**
     * 判断两个日期之间差了多少天，不足一天，则按一天计算，即20.01天也算21天
     */
    public static int dateDiff(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return 0;
        }
        long baseNum = 3600 * 1000 * 24;
        long absNum = Math.abs(date1.getTime() - date2.getTime());
        long mod = absNum % baseNum;
        int num = (int) (absNum / baseNum);
        if (mod > 0) {
            num++;
        }
        return num;
    }

    /**
     * 判断两个时间之间相差多少小时，不足一个小时，按照0小时计算
     */
    public static int hourDiff(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return 0;
        }
        long baseNum = 3600 * 1000;
        long absNum = date1.getTime() - date2.getTime();
        // long mod = absNum % baseNum;
        int num = (int) (absNum / baseNum);
        return num;
    }

    /**
     * 判断两个日期是否相等
     * 
     * @param date1
     * @param date2
     * @return 0:相等 1:date1 > date2 -1:date1 < date2
     */
    public static int dateCompare(Date date1, Date date2) {
        if (date1 == date2) {
            return 0;
        }
        if (date1 == null && date2 == null) {
            return 0;
        }
        long time1 = 0;
        long time2 = 0;
        if (date1 != null) {
            time1 = date1.getTime();
        }
        if (date2 != null) {
            time2 = date2.getTime();
        }
        if (time1 == time2) {
            return 0;
        }
        if (time1 > time2) {
            return 1;
        }
        return -1;
    }

    /**
     * 将指定日期增量后得到一个新的日期
     * 
     * @param type
     *            增量类型，Calendar.DAY_OF_MONTH、Calendar.MONTH等
     * @param num
     *            增量的数量值
     */
    public static Date dateAdd(Date date, int type, int num) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(type, num);
        return cal.getTime();
    }

    public static String shortDateForChina(Date date) {
        String r = "";
        SimpleDateFormat formator = new SimpleDateFormat("yyyy 年 MM 月 dd 日");
        try {
            r = formator.format(date);
        } catch (Exception ex) {
            r = formator.format(new Date());
        }
        return r;
    }

    /**
     * 将日期按无格式方式输出，即：按yyyyMMddHHmmss这样的格式输出，此方法很少用到
     */
    public static String fullTimeNoFormat(Date date) {
        if (date == null) {
            return "";
        }
        String r = "";
        SimpleDateFormat formator = new SimpleDateFormat("yyyyMMddHHmmss");
        try {
            r = formator.format(date);
        } catch (Exception ex) {
            r = "";
        }
        return r;
    }

    /**
     * 将日期按"yyyy-MM-dd HH:mm:ss"格式输出<br>
     * 如果日期的时间部分全为0，则不显示
     */
    public static String fullTime(Date date) {
        if (date == null) {
            return "";
        }
        String format = DATETIME_FORMAT;
        String s = "";
        SimpleDateFormat formator = new SimpleDateFormat(format);
        try {
            s = formator.format(date);
        } catch (Exception ex) {
            s = "";
        }
        if (s != null && s.length() > 11) {
            String sTime = s.substring(11);
            if (sTime.equals("00:00:00")) {
                return s.substring(0, 10);
            }
        }
        return s;
    }
    /**
     * 将日期按"yyyyMMdd"格式输出<br>
     */
    public static String fullDate(Date date) {
        if (date == null) {
            return "";
        }
        String format = "yyyyMMdd";
        String s = "";
        SimpleDateFormat formator = new SimpleDateFormat(format);
        try {
            s = formator.format(date);
        } catch (Exception ex) {
            s = "";
        }
        return s;
    }
    /**
     * 将日期按"yyyyMM"格式输出<br>
     */
    public static String fullDate4Month(Date date) {
        if (date == null) {
            return "";
        }
        String format = "yyyyMM";
        String s = "";
        SimpleDateFormat formator = new SimpleDateFormat(format);
        try {
            s = formator.format(date);
        } catch (Exception ex) {
            s = "";
        }
        return s;
    }
    /**
     * 将日期按"yyyy"格式输出<br>
     */
    public static String fullDate4Year(Date date) {
        if (date == null) {
            return "";
        }
        String format = "yyyy";
        String s = "";
        SimpleDateFormat formator = new SimpleDateFormat(format);
        try {
            s = formator.format(date);
        } catch (Exception ex) {
            s = "";
        }
        return s;
    }
    /**
     * 获取指定日期所在周的第一天<br>
     */
    public static String getFirstDayOfWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int d = 0;
        if (cal.get(Calendar.DAY_OF_WEEK) == 1) {
            d = -6;
        } else {
            d = 2 - cal.get(Calendar.DAY_OF_WEEK);
        }
        cal.add(Calendar.DAY_OF_WEEK, d);
        // 所在周开始日期
        String data = new SimpleDateFormat("yyyyMMdd").format(cal.getTime());
        return data;
    }
    /**
     * 获取指定日期所在周的最后一天<br>
     */
    public static String getLastDayOfWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int d = 0;
        if (cal.get(Calendar.DAY_OF_WEEK) == 1) {
            d = 0;
        } else {
            d = 8 - cal.get(Calendar.DAY_OF_WEEK);
        }
        cal.add(Calendar.DAY_OF_WEEK, d);
        // 所在周结束日期
        String data = new SimpleDateFormat("yyyyMMdd").format(cal.getTime());
        return data;
    }

    public static String fullTime() {
        return fullTime(new Date());
    }

    
    public static String fullDate(){
        return fullDate(new Date());
    }
    
    public static String fullTime(long date) {
        return fullTime(new Date(date));
    }

    /**
     * 将日期按指定格式输出，但仅输出日期部分，不显示时间，其他规则与fullTime(Date date , Locale area)一致
     */
    public static String shortDate(Date date) {
        String s = fullTime(date);
        if (s == null || s.equals("")) {
            return s;
        }
        return s.substring(0, 10);
    }

    /**
     * 获得当前日期的短日期格式。
     * 
     * @return 日期字符串
     */
    public static String shortDate() {
        return shortDate(new Date());
    }

    public static String shortDate(long date) {
        return shortDate(new Date(date));
    }

    /**
     * 显示日期的时间部分
     */
    public static String shortTime(Date date) {
        if (date == null) {
            return "";
        }
        String s = "";
        SimpleDateFormat formator = new SimpleDateFormat("HH:mm:ss");
        try {
            s = formator.format(date);
        } catch (Exception ex) {
            s = "";
        }
        return s;
    }

    public static String shortTime() {
        return shortTime(new Date());
    }

    public static String shortTime(long date) {
        return shortTime(new Date(date));
    }

    /**
     * 取得当前系统时间
     * 
     * @return
     */
    public static Date getCurrentDateTime() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime();
    }

    /**
     * 转换当前时间
     * 
     * @param format
     * @指定格式
     * @return 日期
     */
    public static Date parseCurrentDateTime(String format) {
        Calendar calendar = Calendar.getInstance();
        Date d = null;
        try {
            d = parseDateTime(calendar.getTime(), format);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return d;
    }

    /**
     * 格式化当前时间
     * 
     * @param format
     * @指定格式
     * @return 日期字符串形式
     */
    public static String formatCurrentDateTime(String format) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(calendar.getTime());
    }

    /**
     * 取得指定时间固定格式的字符串形式
     * 
     * @param date
     * @时间
     * @param format
     * @格式
     * @return
     */
    public static String formatDateTime(Date date, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }

    /**
     * 转换指定时间为固定格式
     * 
     * @param dateStr
     * @时间
     * @param format
     * @格式
     * @return
     * @throws ParseException
     */
    public static Date parseDateTime(String dateStr, String format)
            throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.parse(dateStr);
    }

    /**
     * 转换指定时间为固定格式
     * 
     * @param date
     * @时间
     * @param format
     * @格式
     * @return
     * @throws ParseException
     */
    public static Date parseDateTime(Date date, String format)
            throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.parse(dateFormat.format(date));
    }

    public static String getLunarString() {
        Calendar calendar = Calendar.getInstance();
        Lunar lunar = new Lunar(calendar);
        return "农历" + lunar.getLunarString();
    }

    /**
     * 取得指定时间固定格式的字符串形式,date为空时返回null
     * 
     * @param date
     * @时间
     * @param format
     * @格式
     * @return
     */
    public static String formatDate(Date date, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        if (date == null) {
            return null;
        } else {
            return dateFormat.format(date);
        }
    }

    /**
     * 获取时间
     * 
     * @param date
     *            指定日期
     * @param n
     *            与date相差天数
     * @return
     */
    public static Date getDate(Date date, int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, n);
        return cal.getTime();
    }
    
    /**
     * 获取当前年
     */
    public static String getCurrentDateYear(){
        return formatCurrentDateTime("yyyy");
    }
    /**
     * 获取当前月
     */
    public static String getCurrentDateMonth(){
        return formatCurrentDateTime("MM");
    }
    /**
     * 获取当前日
     */
    public static String getCurrentDateDay(){
        return formatCurrentDateTime("dd");
    }
    
    /**
     * 某天的开始——00:00:00
     * @param date
     */
    public static Date calculateDayStartTime(Date date) {
        SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat yyyyMMddHHmmss = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
        try {
            return yyyyMMddHHmmss.parse(yyyyMMdd.format(date) + "00:00:00");
        } catch (ParseException e) {
            return null;
        }
    }

    public static Date calculateDayMidTime(Date date) {
        SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat yyyyMMddHHmmss = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
        try {
            return yyyyMMddHHmmss.parse(yyyyMMdd.format(date) + "12:00:00");
        } catch (ParseException e) {
            return null;
        }
    }

    public static Date calculateDayEndTime(Date date) {
        SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat yyyyMMddHHmmss = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
        try {
            return yyyyMMddHHmmss.parse(yyyyMMdd.format(date) + "23:59:59");
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 获取当天日期 前、后某天
     * @param i 正数为之后某天 负数为之前某天
     */
    public static Date getDay(int i) {
        return getDay(new Date(), i);
    }

    /**
     * 获取date日期 前、后某天
     * @param i 正数为之后某天 负数为之前某天
     */
    public static Date getDay(Date date, int i) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, i);
        return cal.getTime();
    }

    /**
     * 计算两个日期相隔的天数  :secondDay晚于firstDay 返回正整数，否则返回负整数，同一天返回0
     */
    public static int countDaysBetweenTwoDate(Date firstDay, Date secondDay) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            firstDay = sdf.parse(sdf.format(firstDay));//丢掉时间信息，变成00:00:00
            secondDay = sdf.parse(sdf.format(secondDay));
        } catch (Exception e) {
              throw new RuntimeException(e);
        }
        int nDay = (int) ((secondDay.getTime() - firstDay.getTime()) / (24 * 60 * 60 * 1000));
        return nDay;
    }

    public static int getDayValueOfDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_MONTH);
    }

    public static Date calculateMonthStartTime(Date day) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(day);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        return cal.getTime();
    }

    public static Date constructDay(String day) {
        SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return yyyyMMdd.parse(day);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * 把yyyymmdd转成yyyy-MM-dd格式
     * (这里用一句话描述这个方法的作用)
     * 方法名：formatDate
     */
    public static String formatDate(String str) {
        SimpleDateFormat sf1 = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat sf2 = new SimpleDateFormat("yyyy-MM-dd");
        String sfstr = "";
        try {
            sfstr = sf2.format(sf1.parse(str));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return sfstr;
    }
}
