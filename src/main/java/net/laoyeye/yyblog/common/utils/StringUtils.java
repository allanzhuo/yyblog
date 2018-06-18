package net.laoyeye.yyblog.common.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

public class StringUtils {

	private static final String DEFAULT_IS_NULL_EX_MESSAGE = "The validated object is null";

	private static final String DEFAULT_NOT_BLANK_EX_MESSAGE = "The validated character sequence is blank";

	/**
	 * 判断字符串是否为空
	 * 
	 * @param string
	 *            设置字符串
	 * @return boolean 返回是否为空
	 */
	public static boolean isEmpty(String string) {
		return string == null || string.length() == 0;
	}

	/**
	 * 判断List集合是否为空的
	 * 
	 * @param string
	 *            设置字符串
	 * @return boolean 返回是否为空
	 */
	public static boolean listIsEmpty(List<?> list) {
		return list == null || list.isEmpty();
	}

	/**
	 * 判断List集合是否为空的
	 * 
	 * @param string
	 *            设置字符串
	 * @return boolean 返回是否为空
	 */
	public static boolean listIsNotEmpty(List<?> list) {
		return !listIsEmpty(list);
	}


	/**
	 * 判断字符串是不为空
	 * 
	 * @param string
	 *            设置字符串
	 * @return boolean 返回是否为空
	 */
	public static boolean isNotEmpty(String string) {
		return !isEmpty(string);
	}

	/**
	 * 判断两个字符串是否值相等，忽略大小写
	 * 
	 * @param a
	 *            设置第一个字符串
	 * @param b
	 *            设置第二个字符串
	 * @return boolean 返回比较的结果
	 */
	public static boolean compareIgnoreCase(String a, String b) {
		if (isEmpty(a) && isEmpty(b))
			return true;
		if (!isEmpty(a) && !isEmpty(b))
			return a.equalsIgnoreCase(b);
		else
			return false;
	}

	/**
	 * 追加指定的字符串到原字符串中
	 * 
	 * @param src
	 *            设置原字符串
	 * @param appendStr
	 *            设置需要追加的字符串
	 * @return String 返回结果
	 */
	public static String append(String src, String appendStr) {
		if (src == null || isEmpty(appendStr)) {
			return src;
		} else {
			StringBuffer buffer = new StringBuffer(src);
			buffer.append(appendStr);
			return buffer.toString();
		}
	}

	/**
	 * 追加指定的字符串到原字符串中,原字符串不做限定 如果原字符串为null或者为空串则原字符串按空串处理
	 * 
	 * @param src
	 *            设置原字符串
	 * @param appendStr
	 *            设置需要追加的字符串
	 * @return String 返回结果
	 */
	public static String appendStr(String src, String appendStr) {
		if (isEmpty(appendStr)) {
			return src;
		} else if (src == null) {
			return appendStr;
		} else {
			StringBuffer buffer = new StringBuffer(src);
			buffer.append(appendStr);
			return buffer.toString();
		}
	}

	/**
	 * 去除字符串左边空格
	 * 
	 * @param str
	 *            设置原字符串
	 * @return String 返回结果
	 */
	public static String trimLeft(String str) {
		if (str == null)
			return null;
		int length = str.length();
		if (length == 0)
			return "";
		StringBuffer buffer = new StringBuffer(str);
		int index;
		for (index = 0; index < length && buffer.charAt(index) == ' '; index++)
			;
		if (index == length)
			return "";
		else
			return buffer.substring(index);
	}

	/**
	 * 去除字符串右边空格
	 * 
	 * @param str
	 *            设置原字符串
	 * @return String 返回结果
	 */
	public static String trimRight(String str) {
		if (str == null)
			return null;
		int length = str.length();
		if (length == 0)
			return "";
		StringBuffer buffer = new StringBuffer(str);
		int index;
		for (index = length - 1; index >= 0 && buffer.charAt(index) == ' '; index--)
			;
		if (index < 0 && buffer.charAt(0) == ' ')
			return "";
		else
			return buffer.substring(0, index + 1);
	}

	/**
	 * 字符数组转换为字符串,用逗号隔开
	 * 
	 * @param str
	 *            字符数组
	 * @return String
	 */
	public static String arrayToString(String[] str) {
		if (str == null)
			return "";
		StringBuffer rStr = new StringBuffer("");
		for (int i = 0; i < str.length; i++) {
			rStr.append(str[i]);
			rStr.append(",");
		}
		// 截取逗号
		if (rStr.toString().length() > 0) {
			rStr.setLength(rStr.length() - 1);
		}
		return rStr.toString();
	}

	/**
	 * 字符串“，”隔开的 转换成数组 去空格
	 * 
	 * @param str
	 *            字符数组
	 * @return String
	 */
	public static String[] stringToArray(String str) {
		String[] temp = null;
		if (str != null) {
			temp = str.split(",");
			if (temp != null && temp.length > 0) {
				for (int i = 0; i < temp.length; i++) {
					if (temp[i] != null) {
						temp[i] = temp[i].trim();
					}
				}
			}
		}
		return temp;
	}

	/**
	 * 字符数组转换为字符串,用逗号隔开
	 * 
	 * @param str
	 *            字符数组
	 * @return String
	 */
	@SuppressWarnings("rawtypes")
	public static String listToString(List list) {
		if (list == null)
			return "";
		StringBuffer rStr = new StringBuffer("");
		for (Object str_temp : list) {
			if (str_temp != null) {
				if (isEmpty(rStr.toString())) {
					rStr.append(str_temp.toString());
				} else {
					rStr.append(",");
					rStr.append(str_temp.toString());
				}
			}

		}
		return rStr.toString();
	}

	/**
	 * 字符数组转换为字符串,用逗号隔开
	 * 
	 * @param str
	 *            字符数组
	 * @return String
	 */
	public static String arrayToString(Object[] objArry) {
		if (objArry == null || objArry.length == 0)
			return "";
		StringBuffer rStr = new StringBuffer("");
		for (int i = 0; i < objArry.length; i++) {
			rStr.append(objArry[i]);
			rStr.append(",");
		}
		// 截取逗号
		if (rStr.toString().length() > 0) {
			rStr.setLength(rStr.length() - 1);
		}
		// 如果是String型的添加“'”
		String result = rStr.toString();
		if (objArry[0] instanceof String) {
			result = result.replaceAll("([^,]+)", "'$1'");
		}
		return result;
	}

	/**
	 * 转换小写开头字符串
	 * 
	 * @param str
	 *            设置原字符串
	 * @return String 返回字符串
	 */
	public static String deCapitalize(String str) {
		if (StringUtils.isEmpty(str)) {
			return str;
		} else {
			StringBuffer buffer = new StringBuffer(str);
			buffer.setCharAt(0, str.substring(0, 1).toLowerCase().charAt(0));
			return buffer.toString();
		}
	}

	/**
	 * 给传入的字符串前后加双引号
	 * 
	 * @param str
	 *            设置原字符串
	 * @return String 返回结果
	 */
	public static String quote(String str) {
		if (isEmpty(str))
			return "\"\"";
		StringBuffer buffer = new StringBuffer(str);
		if (!str.startsWith("\""))
			buffer.insert(0, "\"");
		if (!str.endsWith("\""))
			buffer.append("\"");
		return buffer.toString();
	}

	/**
	 * 去除字符串中的双引号
	 * 
	 * @param str
	 *            设置原字符串
	 * @return String 返回结果
	 */
	public static String extractQuotedStr(String str) {
		if (isEmpty(str))
			return str;
		StringBuffer buffer = new StringBuffer(str);
		int index = str.length();
		while (buffer.charAt(buffer.length() - 1) == '"') {
			buffer.deleteCharAt(buffer.length() - 1);
			index = buffer.length();
			if (index <= 0)
				break;
		}
		if (buffer.length() == 0)
			return "";
		while (buffer.charAt(0) == '"') {
			buffer.deleteCharAt(0);
			index = buffer.length();
			if (index <= 0)
				break;
		}
		return buffer.toString();
	}

	/***
	 * 判断给定的字符串是否是有效数字。整数，小数
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumber(String str) {
		return str == null ? false : str.matches("^[0-9]+(\\.[0-9]+)?$");
	}

	/**
	 * 判断是否是正整数
	 * (这里用一句话描述这个方法的作用)
	 * 方法名：isInteger
	 * 创建人：liuw 
	 * 时间：2017年4月5日-下午2:00:31
	 * @param str
	 * @return boolean
	 * @exception 
	 * @since  1.0.0
	 */
	public static boolean isInteger(String str) {
		return str == null ? false : str.matches("^[0-9]+$");
	}

	/***
	 * 验证字符串是否为 null,如果是null返回""
	 * 
	 * @param str
	 * @return
	 */
	public static String valueOf(Object str) {
		return str == null ? "" : str.toString();
	}

	/**
	 * 打印参数信息
	 * 
	 * @param map
	 */
	public static void printMap(Map<String, Object> map) {
		System.out
		.println("*******************************************************************");
		System.out
		.println("********************************输出参数内容***************************");
		if (map != null) {
			for (Map.Entry<String, Object> entry : map.entrySet()) {
				System.out.printf("Key=%s\t\t,\t\tValue= %s\r\n",
						entry.getKey(), entry.getValue());
			}
		} else {
			System.out.printf("Map is null!");
		}
		System.out
		.println("*******************************************************************");
	}

	/*	public static void main(String[] arg) {

		// String key = "KEY";
		// String result = encrypt(key,"123456");
		// System.out.println(result);
		// String eresult = decrypt(key,result);
		// System.out.println(eresult);

		System.out.println(chinaToUnicode("生产"));

	}*/

	/***
	 * 简单加密方法
	 * 
	 * @param key
	 * @param string
	 */
	public static String encrypt(String key, String string) {
		double d_key = getKeyDouble(key);
		int seed = (int) (d_key * 100);
		char[] seed_key = String.valueOf(seed).toCharArray();
		char[] s_arry = string.toCharArray();
		for (int i = 0; i < s_arry.length; i++) {
			int key_index = i >= seed_key.length ? (i % seed_key.length) : i;
			char key_char = seed_key[key_index];
			s_arry[i] ^= key_char;
		}
		return string2hex(new String(s_arry));
	}

	/***
	 * 简单加密方法
	 * 
	 * @param key
	 * @param string
	 */
	public static String decrypt(String key, String string) {
		double d_key = getKeyDouble(key);
		int seed = (int) (d_key * 100);
		char[] seed_key = String.valueOf(seed).toCharArray();
		char[] s_arry = hex2String(string).toCharArray();
		for (int i = 0; i < s_arry.length; i++) {
			int key_index = i >= seed_key.length ? (i % seed_key.length) : i;
			char key_char = seed_key[key_index];
			key_char ^= s_arry[i];
			s_arry[i] ^= key_char;
			s_arry[i] = key_char;
		}
		return new String(s_arry);
	}

	/**
	 * 获得加密数据
	 * 
	 * @param key
	 * @return
	 */
	private static double getKeyDouble(String key) {
		if (key == null || key.length() == 0)
			return 0.9D;
		double result = 0.9D;
		char[] keyArray = key.toCharArray();
		for (int i = 0; i < keyArray.length; i++) {
			result += Math.sqrt(((int) keyArray[i])) * (i + 1) * 10;
		}
		return Math.sqrt(result);
	}

	/**
	 * 字符串转换成十六进制值
	 * 
	 * @param string
	 *            String 我们看到的要转换成十六进制的字符串
	 * @return
	 */
	public static String string2hex(String string) {
		if (isEmpty(string)) {
			string = "";
		}
		char[] digital = "0123456789ABCDEF".toCharArray();
		StringBuffer sb = new StringBuffer("");
		byte[] bs = string.getBytes();
		int bit;
		for (int i = 0; i < bs.length; i++) {
			bit = (bs[i] & 0x0f0) >> 4;
		sb.append(digital[bit]);
		bit = bs[i] & 0x0f;
		sb.append(digital[bit]);
		}
		return sb.toString();
	}

	/**
	 * 十六进制转换字符串
	 * 
	 * @param hex
	 *            String 十六进制
	 * @return String 转换后的字符串
	 */
	public static String hex2String(String string) {
		if (isEmpty(string)) {
			string = "";
		}
		String digital = "0123456789ABCDEF";
		char[] hex2char = string.toCharArray();
		byte[] bytes = new byte[string.length() / 2];
		int temp;
		for (int i = 0; i < bytes.length; i++) {
			temp = digital.indexOf(hex2char[2 * i]) * 16;
			temp += digital.indexOf(hex2char[2 * i + 1]);
			bytes[i] = (byte) (temp & 0xff);
		}
		return new String(bytes);
	}

	/**
	 * java字节码转字符串
	 * 
	 * @param b
	 * @return
	 */
	public static String byte2hex(byte[] b) { // 一个字节的数，
		// 转成16进制字符串
		String hs = "";
		String tmp = "";
		for (int n = 0; n < b.length; n++) {
			// 整数转成十六进制表示
			tmp = (Integer.toHexString(b[n] & 0XFF));
			if (tmp.length() == 1) {
				hs = hs + "0" + tmp;
			} else {
				hs = hs + tmp;
			}
		}
		tmp = null;
		return hs.toUpperCase(); // 转成大写
	}

	/**
	 * 字符串转java字节码
	 *
	 * @param b
	 * @return
	 */
	public static byte[] hex2byte(byte[] b) {
		if ((b.length % 2) != 0) {
			throw new IllegalArgumentException("长度不是偶数");
		}
		byte[] b2 = new byte[b.length / 2];
		for (int n = 0; n < b.length; n += 2) {
			String item = new String(b, n, 2);
			// 两位一组，表示一个字节,把这样表示的16进制字符串，还原成一个进制字节
			b2[n / 2] = (byte) Integer.parseInt(item, 16);
		}
		b = null;
		return b2;
	}

	public static String chinaToUnicode(String str) {
		String result = "";
		for (int i = 0; i < str.length(); i++) {
			int chr1 = (char) str.charAt(i);
			if (chr1 >= 19968 && chr1 <= 171941) {// 汉字范围 \u4e00-\u9fa5 (中文)
				result += "\\u" + Integer.toHexString(chr1);
			} else {
				result += str.charAt(i);
			}
		}
		return result;
	}

	public static String round2String(BigDecimal value, int scale) {

		String result = "";
		String roundres = round(value, scale);

		int pointPos = roundres.indexOf('.');
		if (pointPos == -1) {
			result = roundres;
		} else {
			String intPartString = roundres.substring(0, pointPos);
			String decPartString = roundres.substring(pointPos + 1);
			int decnum = Integer.parseInt(decPartString);
			// int intpartnum = Integer.parseInt(intPartString);
			long intpartnum = Long.parseLong(intPartString);
			if (decnum == 0 && intpartnum == 0) {
				result = "0";
			}
		}
		if ("0".equals(result)) {
			result = "";
		} else {
			result = roundres;
		}
		return result;
	}

	public static String round(BigDecimal value, int scale) {
		String result = "";
		if (value != null) {
			BigDecimal one = new BigDecimal("1");
			if (scale == 0) {
				long numvalue = value.divide(one, scale,
						BigDecimal.ROUND_HALF_UP).longValue();
				result = String.valueOf(numvalue);
			} else if (scale > 0) {
				// double numvalue = value.divide(one,
				// scale,BigDecimal.ROUND_HALF_UP).doubleValue();
				// result = String.valueOf(numvalue);
				BigDecimal numvalue = value.divide(one, scale,
						BigDecimal.ROUND_HALF_UP);
				String format = "#." + "###############".substring(0, scale);
				DecimalFormat df = new DecimalFormat(format);
				result = df.format(numvalue);
			} else {
				result = String.valueOf(value);
			}
		}

		return result;
	}

	public static String formatDecimal(BigDecimal value) {
		String result = "";
		DecimalFormat int9dec3 = new DecimalFormat("########0.###");
		DecimalFormat int10dec5 = new DecimalFormat("#########0.#####");
		if (value != null) {
			String doublevalue = String.valueOf(value);
			int pointPos = doublevalue.indexOf('.');
			if (pointPos == -1) {
				result = doublevalue;
			} else {
				String decPartString = doublevalue.substring(pointPos + 1);
				if (decPartString.length() == 3) {
					result = int9dec3.format(value);
				} else if (decPartString.length() == 5) {
					result = int10dec5.format(value);
				} else {
					result = doublevalue;
				}
			}
		}
		return result;
	}

	public static Date stringToYyyyMMddHHmmss(String date) {
		SimpleDateFormat fdt;
		Date dt = null;

		if ((date == null) || (date.trim().equals(""))) {
			return null;
		}
		try {
			fdt = new SimpleDateFormat("yyyyMMddHHmmss");
			dt = new Date(fdt.parse(date).getTime());
		} catch (ParseException e) {
			//
		}

		return (dt);
	}

	public static String formatDate(String date, String format) {
		SimpleDateFormat fdt;
		String dt = null;

		if ((date == null) || (date.trim().equals(""))) {
			return "";
		}
		if (format == null || "".equals(format)) {
			format = "yyyy/MM/dd";
		}
		format = format.replace('Y', 'y');
		int year = 0;
		int month = 0;
		int day = 0;
		int hour = 0;
		int minute = 0;
		int second = 0;
		if (date.length() == 8) {
			year = Integer.parseInt(date.substring(0, 4));
			month = Integer.parseInt(date.substring(4, 6));
			day = Integer.parseInt(date.substring(6, 8));
		} else if (date.length() == 10) {
			year = Integer.parseInt(date.substring(0, 4));
			month = Integer.parseInt(date.substring(5, 7));
			day = Integer.parseInt(date.substring(8, 10));
		} else if (date.length() == 6) {
			hour = Integer.parseInt(date.substring(0, 2));
			minute = Integer.parseInt(date.substring(2, 4));
			second = Integer.parseInt(date.substring(4, 6));
		} else if (date.length() == 14) {
			year = Integer.parseInt(date.substring(0, 4));
			month = Integer.parseInt(date.substring(4, 6));
			day = Integer.parseInt(date.substring(6, 8));
			hour = Integer.parseInt(date.substring(8, 10));
			minute = Integer.parseInt(date.substring(10, 12));
			second = Integer.parseInt(date.substring(12, 14));
		}
		Calendar gmtlocal = new GregorianCalendar();
		gmtlocal.set(Calendar.YEAR, year);
		gmtlocal.set(Calendar.MONTH, month - 1);
		gmtlocal.set(Calendar.DAY_OF_MONTH, day);
		gmtlocal.set(Calendar.HOUR, hour);
		gmtlocal.set(Calendar.MINUTE, minute);
		gmtlocal.set(Calendar.SECOND, second);
		try {
			fdt = new SimpleDateFormat(format);
			dt = fdt.format(gmtlocal.getTime());
		} catch (Exception e) {
			//
		}

		return dt;
	}

	public static Date stringToDate(String date, String fmt) {
		SimpleDateFormat fdt;
		Date dt = null;

		if ((date == null) || (date.trim().equals(""))) {
			return null;
		}

		if (fmt == null) {
			fmt = "yyyy/MM/dd";
		}

		fmt = fmt.replace('Y', 'y');

		try {
			fdt = new SimpleDateFormat(fmt);
			dt = new Date(fdt.parse(date).getTime());
		} catch (ParseException e) {
			//
		}

		return (dt);
	}

	public static boolean validateInputDate(String date, String format) {

		if (date.length() != 8) {
			return false;
		}

		SimpleDateFormat ft = new SimpleDateFormat(format);
		java.util.Date after = null;
		try {
			after = ft.parse(date);
		} catch (ParseException e) {
			return false;
		}
		if (!ft.format(after).equals(date)) {
			return false;
		}

		return true;
	}

	public static Date stringToDate(String date) {
		if (date == null || "".equals(date.trim())) {
			return null;
		}
		if (!validateInputDate(date)) {
			return null;
		}
		String yy = date.substring(0, 4);
		String mm = date.substring(5, 7);
		String dd = date.substring(8, 10);

		return stringToDate(yy, mm, dd);
	}

	public static boolean validateInputDate(String date) {

		if (date.length() != 10) {
			return false;
		}

		if (date.charAt(4) != '/') {
			return false;
		}
		if (date.charAt(7) != '/') {
			return false;
		}

		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		java.util.Date after = null;
		try {
			after = format.parse(date);
		} catch (ParseException e) {
			return false;
		}
		if (!format.format(after).equals(date)) {
			return false;
		}

		return true;
	}

	public static String dateFormat(String date) {

		if (date == null || date.trim().getBytes().length != 8) {
			return "";
		}
		String yy = date.substring(0, 4);
		String mm = date.substring(4, 6);
		String dd = date.substring(6, 8);
		String dateFormat = yy + "/" + mm + "/" + dd;
		return dateFormat;
	}

	public static String dateToString(String date) {

		if (date == null) {
			return "";
		}
		if (date.length() != 10) {
			return "";
		}

		if (date.charAt(4) != '/') {
			return "";
		}
		if (date.charAt(7) != '/') {
			return "";
		}

		String yy = date.substring(0, 4);
		String mm = date.substring(5, 7);
		String dd = date.substring(8, 10);

		return yy + mm + dd;
	}

	public static String formatDate(java.util.Date date) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		return format.format(date);
	}

	public static String formatDate(java.util.Date date,String formats) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat format = new SimpleDateFormat(formats);
		return format.format(date);
	}

	public static Date stringToDate(String year, String month, String date) {

		if ((year != null) && (!year.equals("")) && (month != null)
				&& (!month.equals("")) && (date != null) && (!date.equals(""))) {

			if (month.length() < 2) {
				month = "0" + month;
			}
			if (date.length() < 2) {
				date = "0" + date;
			}

			String string = year + "-" + month + "-" + date;

			return Date.valueOf(string);
		}
		return null;
	}

	/**
	 * Date Arithmetic function. Adds the specified (signed) amount of time to
	 * the given time field, based on the calendar's rules. For example, to
	 * subtract 5 days from the current time of the calendar, you can achieve it
	 * by calling:
	 * <p>
	 * add(Calendar.DATE, -5).
	 *
	 * @param field
	 *            the time field.
	 * @param date
	 *            date.
	 * @param amount
	 *            the amount of date or time to be added to the field.
	 * @return Date
	 */
	public static Date dateAdd(Date date, char field, int amount) {
		Calendar worldTour = Calendar.getInstance();
		worldTour.setTimeInMillis(date.getTime());
		if (field == 'y') {
			worldTour.add(GregorianCalendar.YEAR, amount);
		} else if (field == 'm') {
			worldTour.add(GregorianCalendar.MONTH, amount);
		} else if (field == 'd') {
			worldTour.add(GregorianCalendar.DATE, amount);
		} else {
			return null;
		}
		Date returnDate = new Date(worldTour.getTimeInMillis());
		return returnDate;

	}

	public static String date2String(Date dt, String fmt) {
		if (dt == null) {
			return null;
		}
		SimpleDateFormat fdt;
		String tmp = null;

		if (fmt == null) {
			fmt = "yyyy/MM/dd";
		}

		fmt = fmt.replace('Y', 'y');
		fdt = new SimpleDateFormat(fmt);
		tmp = fdt.format(dt);
		return tmp;
	}

	public static String date2String(Date dt) {
		if (dt == null) {
			return null;
		}
		String tmp = null;
		String fmt = "yyyy/MM/dd";
		tmp = date2String(dt, fmt);

		return tmp;
	}

	public static String converHhmm(String hhmm) {
		if (hhmm == null || "".equals(hhmm.trim())) {
			return "";
		}
		if (hhmm.trim().getBytes().length < 2) {
			return "";
		} else if (hhmm.trim().getBytes().length == 2) {
			return hhmm + ":" + "00";
		}
		String hour = hhmm.substring(0, 2);
		String min = hhmm.substring(2, 4);
		return hour + ":" + min;
	}

	public static Date getCurrentDate() {
		Calendar cal = Calendar.getInstance();
		cal.setLenient(false);
		Date date = new Date(cal.getTimeInMillis());
		return date;
	}

	public static Timestamp getCurrentTimestamp() {
		Calendar cal = Calendar.getInstance();
		cal.setLenient(false);
		Timestamp date = new Timestamp(cal.getTimeInMillis());
		return date;
	}

	public static String getCurrentDate(String fmt) {
		Calendar cal = Calendar.getInstance();
		cal.setLenient(false);
		Date date = new Date(cal.getTimeInMillis());

		SimpleDateFormat fdt;
		String tmp = null;

		if (fmt == null) {
			fmt = "yyyy/MM/dd";
		}

		fmt = fmt.replace('Y', 'y');
		fdt = new SimpleDateFormat(fmt);
		tmp = fdt.format(date);
		return tmp;
	}

	public static String filterToHtml(String input) {
		if (input == null || input.equals("")) {
			return input;
		}
		StringBuffer filtered = new StringBuffer(input.length());
		char c;
		for (int i = 0; i <= input.length() - 1; i++) {
			c = input.charAt(i);
			switch (c) {
			case '"':
				filtered.append("&quot;");
				break;
			case '&':
				filtered.append("&amp;");
				break;
			case '>':
				filtered.append("&lt;");
				break;
			case '<':
				filtered.append("&gt;");
				break;
			case ' ':
				filtered.append("&nbsp;");
				break;
			case '|':
				filtered.append("&brvbar;");
				break;
			default:
				filtered.append(c);
			}
		}
		return (filtered.toString());
	}

	/**
	 * filtertoDB
	 *
	 * @param input
	 *            String
	 * @return input String
	 */
	public static String filterToDB(String input) {
		if (input == null || input.equals("")) {
			return input;
		}
		input = input.replaceAll("&quot;", "\"");
		input = input.replaceAll("&amp;", "&");
		input = input.replaceAll("&lt;", ">");
		input = input.replaceAll("&gt;", "<");
		input = input.replaceAll("&nbsp;", " ");
		input = input.replaceAll("&brvbar;", "|");
		return input;
	}

	/**
	 * @param input
	 *            String
	 * @param strLen
	 *            int
	 * @return padding String
	 */
	public static String stringPadding(String input, int strLen) {
		String temp = input;
		if (input == null || "".equals(input)) {
			return "";
		}
		if (input.length() < strLen) {
			int padLen = strLen - input.trim().length();
			for (int i = 0; i < padLen; i++) {
				temp = "0" + temp.trim();
			}
		}
		return temp;
	}

	/**
	 * @param str
	 *            String
	 * @return upper String
	 */
	public static String toUpperCase(String str) {
		String temp = str;
		if (str != null && !"".equals(str)) {
			temp = str.trim().toUpperCase();
		}
		return temp;
	}

	public static int getByteLength(String str) {
		if (str == null || "".equals(str)) {
			return 0;
		}
		int length = str.getBytes().length;
		return length;
	}

	public static String subString(String str, int start, int end) {
		if (str == null || "".equals(str) || str.length() <= start) {
			return "";
		}
		if (str.length() < end) {
			return str.substring(start);
		}
		return str.substring(start, end);
	}

	public static String convertHalf(String s) {
		char[] c = s.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if ((c[i] >= 0x0030 && c[i] <= 0x0039)
					|| (c[i] >= 0x0041 && c[i] <= 0x005A)
					|| (c[i] >= 0x0061 && c[i] <= 0x007A)) {
				c[i] = (char) (c[i] + 0xFEE0);
			}
		}
		return new String(c);
	}

	public static String convertNull(String s) {
		if (s == null) {
			s = "";
		}
		return s;
	}

	public static String convertNull(Object s) {
		if (s == null) {
			s = "";
		}
		return s.toString();
	}

	public static String convertNull(BigDecimal b) {
		String s = "";
		if (b != null) {
			s = String.valueOf(b);
		}
		return s;
	}

	public static String convertNull(Timestamp t) {
		String s = "";
		if (t != null) {
			s = String.valueOf(t);
		}
		return s;
	}

	public static String convertNull(Integer num) {
		String s = "";
		if (num != null) {
			s = num.toString();
		}
		return s;
	}

	public static String dbReplaceChar(String in) {
		in = in.trim();
		return in.replaceAll("'", "''");
	}

	public static boolean isBlank(String _baseSt) {

		boolean b = false;

		if (_baseSt == null) {
			b = true;
		} else {

			String s = _baseSt.trim();

			if (s.length() > 0) {
				b = false;
			} else {
				b = true;
			}
		}
		return b;

	}

	public static boolean isBlank(Object _baseSt) {

		boolean b = false;

		if (_baseSt == null) {
			b = true;
		} else {

			String s = _baseSt.toString().trim();

			if (s.length() > 0) {
				b = false;
			} else {
				b = true;
			}
		}
		return b;

	}

	/**
	 * 判断两个字符串是否相等
	 *
	 * @author HuangDongyang
	 *
	 * @param String
	 *            str1 第一个字符串
	 * @param String
	 *            str2 第二个字符串
	 *
	 *            return boolean 判断结果
	 */
	public static boolean equals(String str1, String str2) {

		boolean ret = false;

		if (str1 != null && !str1.trim().isEmpty() && str2 != null
				&& !str2.trim().isEmpty()) {
			if (str1.equals(str2)) {
				ret = true;
			}
		}
		return ret;
	}

	public static String TimestampToString(Timestamp dbTimestamp) {

		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");

		String strTimestamp = df.format(dbTimestamp);

		return strTimestamp;
	}

	public static String TimestampToString2(Timestamp dbTimestamp) {

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String strTimestamp = df.format(dbTimestamp);

		return strTimestamp;
	}

	public static String zerofill(final int value, final int length) {
		String fillMark = "";
		for (int i = 0; i < length; i++) {
			fillMark += "0";
		}
		fillMark += value;
		return fillMark
				.substring(fillMark.length() - length, fillMark.length());
	}

	public static String cutdownString(String str, int size) {
		String ret = "";
		int len = 0;

		if (str == null) {
			return "";
		}

		len = str.length();
		if (len <= size) {
			return str;
		}

		ret = str.substring(0, size);

		return ret;
	}

	public static String trim(String str) {
		if (str == null) {
			return "";
		}
		return str.trim();
	}

	public static boolean isEmpty(CharSequence cs) {
		return cs == null || cs.length() == 0;
	}

	public static boolean isNotEmpty(CharSequence cs) {
		return !StringUtils.isEmpty(cs);
	}

	/**
	 * <p>
	 * Capitalizes a String changing the first letter to title case as per
	 * {@link Character#toTitleCase(char)}. No other letters are changed.
	 * </p>
	 *
	 * <p>
	 * For a word based algorithm, see
	 * {@link org.apache.commons.lang3.text.WordUtils#capitalize(String)}. A
	 * {@code null} input String returns {@code null}.
	 * </p>
	 *
	 * <pre>
	 * StringUtils.capitalize(null) = null StringUtils.capitalize("") = ""
	 * StringUtils.capitalize("cat") = "Cat" StringUtils.capitalize("cAt") =
	 * "CAt"
	 * </pre>
	 *
	 * @param str
	 *            the String to capitalize, may be null
	 * @return the capitalized String, {@code null} if null String input
	 * @see org.apache.commons.lang3.text.WordUtils#capitalize(String)
	 * @see #uncapitalize(String)
	 * @since 2.0
	 */
	public static String capitalize(String str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0) {
			return str;
		}
		return new StringBuilder(strLen)
				.append(Character.toTitleCase(str.charAt(0)))
				.append(str.substring(1)).toString();
	}

	/**
	 * <p>
	 * Uncapitalizes a String changing the first letter to title case as per
	 * {@link Character#toLowerCase(char)}. No other letters are changed.
	 * </p>
	 *
	 * <p>
	 * For a word based algorithm, see
	 * {@link org.apache.commons.lang3.text.WordUtils#uncapitalize(String)}. A
	 * {@code null} input String returns {@code null}.
	 * </p>
	 *
	 * <pre>
	 * StringUtils.uncapitalize(null) = null StringUtils.uncapitalize("")
	 * = "" StringUtils.uncapitalize("Cat") = "cat"
	 * StringUtils.uncapitalize("CAT") = "cAT"
	 * </pre>
	 *
	 * @param str
	 *            the String to uncapitalize, may be null
	 * @return the uncapitalized String, {@code null} if null String input
	 * @see org.apache.commons.lang3.text.WordUtils#uncapitalize(String)
	 * @see #capitalize(String)
	 * @since 2.0
	 */
	public static String uncapitalize(String str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0) {
			return str;
		}
		return new StringBuilder(strLen)
				.append(Character.toLowerCase(str.charAt(0)))
				.append(str.substring(1)).toString();
	}

	/**
	 * <p>
	 * Validate that the specified argument is not {@code null}; otherwise
	 * throwing an exception.
	 *
	 * <pre>
	 * Validate.notNull(myObject, &quot;The object must not be null&quot;);
	 * </pre>
	 *
	 * <p>
	 * The message of the exception is &quot;The validated object is null&quot;.
	 * </p>
	 *
	 * @param <T>
	 *            the object type
	 * @param object
	 *            the object to check
	 * @return the validated object (never {@code null} for method chaining)
	 * @throws NullPointerException
	 *             if the object is {@code null}
	 * @see #notNull(Object, String, Object...)
	 */
	public static <T> T notNull(T object) {
		return notNull(object, DEFAULT_IS_NULL_EX_MESSAGE);
	}

	/**
	 * <p>
	 * Validate that the specified argument is not {@code null}; otherwise
	 * throwing an exception with the specified message.
	 *
	 * <pre>
	 * Validate.notNull(myObject, &quot;The object must not be null&quot;);
	 * </pre>
	 *
	 * @param <T>
	 *            the object type
	 * @param object
	 *            the object to check
	 * @param message
	 *            the {@link String#format(String, Object...)} exception message
	 *            if invalid, not null
	 * @param values
	 *            the optional values for the formatted exception message
	 * @return the validated object (never {@code null} for method chaining)
	 * @throws NullPointerException
	 *             if the object is {@code null}
	 * @see #notNull(Object)
	 */
	public static <T> T notNull(T object, String message, Object... values) {
		if (object == null) {
			throw new NullPointerException(String.format(message, values));
		}
		return object;
	}

	/**
	 * <p>
	 * Validate that the specified argument character sequence is neither
	 * {@code null}, a length of zero (no characters), empty nor whitespace;
	 * otherwise throwing an exception with the specified message.
	 *
	 * <pre>
	 * Validate.notBlank(myString, &quot;The string must not be blank&quot;);
	 * </pre>
	 *
	 * @param <T>
	 *            the character sequence type
	 * @param chars
	 *            the character sequence to check, validated not null by this
	 *            method
	 * @param message
	 *            the {@link String#format(String, Object...)} exception message
	 *            if invalid, not null
	 * @param values
	 *            the optional values for the formatted exception message, null
	 *            array not recommended
	 * @return the validated character sequence (never {@code null} method for
	 *         chaining)
	 * @throws NullPointerException
	 *             if the character sequence is {@code null}
	 * @throws IllegalArgumentException
	 *             if the character sequence is blank
	 * @see #notBlank(CharSequence)
	 *
	 * @since 3.0
	 */
	public static <T extends CharSequence> T notBlank(T chars, String message,
			Object... values) {
		if (chars == null) {
			throw new NullPointerException(String.format(message, values));
		}
		if (StringUtils.isBlank(chars)) {
			throw new IllegalArgumentException(String.format(message, values));
		}
		return chars;
	}

	/**
	 * <p>
	 * Validate that the specified argument character sequence is neither
	 * {@code null}, a length of zero (no characters), empty nor whitespace;
	 * otherwise throwing an exception.
	 *
	 * <pre>
	 * Validate.notBlank(myString);
	 * </pre>
	 *
	 * <p>
	 * The message in the exception is &quot;The validated character sequence is
	 * blank&quot;.
	 * </p>
	 *
	 * @param <T>
	 *            the character sequence type
	 * @param chars
	 *            the character sequence to check, validated not null by this
	 *            method
	 * @return the validated character sequence (never {@code null} method for
	 *         chaining)
	 * @throws NullPointerException
	 *             if the character sequence is {@code null}
	 * @throws IllegalArgumentException
	 *             if the character sequence is blank
	 * @see #notBlank(CharSequence, String, Object...)
	 *
	 * @since 3.0
	 */
	public static <T extends CharSequence> T notBlank(T chars) {
		return notBlank(chars, DEFAULT_NOT_BLANK_EX_MESSAGE);
	}

	public static String generateGUID() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}

	/**
	 * 使用MD5算法加密
	 * 
	 * @param str
	 * @param charset
	 * @return
	 * @throws Exception
	 */
	public String encodeByMD5(String str, String charset) throws Exception {
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.reset();
			messageDigest.update(str.getBytes(charset));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new Exception("不能调用MD5加密算法。");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new Exception("字符串不支持" + charset + "的转换。");
		}
		return byteArrayToString(messageDigest.digest());
	}

	/**
	 * 使用SHA算法加密
	 * 
	 * @param str
	 * @param charset
	 * @return
	 * @throws Exception
	 */
	public String encodeBySHA(String str, String charset) throws Exception {
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("SHA");
			messageDigest.reset();
			messageDigest.update(str.getBytes(charset));

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new Exception("不能调用指定的加密算法。");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new Exception("字符串不支持" + charset + "的转换。");
		}
		return byteArrayToString(messageDigest.digest());
	}

	/**
	 * 将byte数组转化为16进制字符串
	 * 
	 * @param byteArray
	 * @return
	 */
	private String byteArrayToString(byte[] byteArray) {
		StringBuilder strBuilder = new StringBuilder();
		for (int i = 0; i < byteArray.length; i++) {
			if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
				strBuilder.append("0").append(
						Integer.toHexString(0xFF & byteArray[i]));
			else
				strBuilder.append(Integer.toHexString(0xFF & byteArray[i]));
		}
		return strBuilder.toString();
	}

	/**
	 * 加密密码
	 * 
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public String getEencryptPassword(String password) throws Exception {
		String str = encodeByMD5(password, "utf-8");
		return encodeBySHA(str, "utf-8");
	}

	/*
	 * 字符串数组转成用,分隔的字符串
	 */
	public String ConvertListToStr(String[] sources) {
		String result = "";
		for (int i = 0; i < sources.length; i++) {
			result += "'" + sources[i] + "',";
		}
		if (result.length() > 0) {
			result = result.substring(0, result.length() - 1);
		}

		return result;
	}

	/*
	 * 字符串数组转成用,分隔的字符串
	 */
	public String ConvertListToNumStr(String[] sources) {
		String result = "";
		for (int i = 0; i < sources.length; i++) {
			result += sources[i] + ",";
		}
		if (result.length() > 0) {
			result = result.substring(0, result.length() - 1);
		}

		return result;
	}

	/**
	 * 获取随机UUID字符串
	 * 
	 * @return UUID
	 */
	public static String getUUID() {
		String str = UUID.randomUUID().toString().replaceAll("-", "");
		return str;
	}

	/**
	 * 判断对象是否为空
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean objectNull(Object obj) {

		return obj == null || isEmpty(String.valueOf(obj));
	}

	/**
	 * 判断两个字符串是否值相等
	 * 
	 * @param a
	 *            设置第一个字符串
	 * @param b
	 *            设置第二个字符串
	 * @return boolean 返回比较的结果
	 */
	public static boolean compare(String a, String b) {

		if (isEmpty(a) && isEmpty(b))
			return true;
		if (!isEmpty(a) && !isEmpty(b))
			return a.equals(b);
		else
			return false;
	}

	/**
	 * 获取升序字符串
	 * @param sourceStr
	 * @return
	 */
	public static String getCode(String sourceStr) {
		StringBuffer out = new StringBuffer();
		int a = Integer.parseInt(sourceStr) + 1;
		String suffix = "000000" + a;
		out.append(suffix.substring(suffix.length() - 4));
		return out.toString();
	}

	public static String getRandomSalt() {
		StringBuffer buffer = new StringBuffer(
				"0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
		StringBuffer sb = new StringBuffer();
		Random r = new Random();
		int range = buffer.length();
		for (int i = 0; i < range; i++) {
			sb.append(buffer.charAt(r.nextInt(range)));
		}
		return sb.toString();
	}


	/**
	 * <p>日期型YYYYMMDD转换成MM-DD</p>.<br>
	 * author：zhoukai<br>
	 *===================================
	 * @param strDate 转换Q前的值
	 * @return 转换后的值
	 */
	public static String convertDate(String strDate){
		if (StringUtils.isEmpty(strDate) && strDate.length() < 8) {
			return null;
		} 
		StringBuilder sbDate =new StringBuilder();
		sbDate.append(strDate.substring(4, 6));
		sbDate.append("-");
		sbDate.append(strDate.substring(6));
		return sbDate.toString();
	}

	/**
	 * 解析出url参数中的键值对 如 "Action=del&id=123"，解析出Action:del,id:123存入map中
	 * 
	 * @param URL url地址
	 * @return url请求参数部分
	 */
	public static Map<String,String> urlRequest(String strUrlParam){

		Map<String,String> mapRequest = new HashMap<String,String>();

		String[] arrSplit = null;

		// 每个键值为一组
		arrSplit = strUrlParam.split("[&]");
		for(String strSplit : arrSplit){
			String[] arrSplitEqual = null;
			arrSplitEqual = strSplit.split("[=]");

			// 解析出键值
			if(arrSplitEqual.length > 1){
				// 正确解析
				mapRequest.put(arrSplitEqual[0],arrSplitEqual[1]);

			} else{
				if(arrSplitEqual[0] != ""){
					// 只有参数没有值，不加入
					mapRequest.put(arrSplitEqual[0],"");
				}
			}
		}
		return mapRequest;
	}

	/**
	 * 把yyyymmdd转成yyyy-MM-dd格式
	 * (这里用一句话描述这个方法的作用)
	 * 方法名：formatDate
	 * 创建人：liuw 
	 * 时间：2017年3月25日-下午3:36:00
	 * @param str
	 * @return String
	 * @exception 
	 * @since  1.0.0
	 */
	public static String formatDate(String str) {
		SimpleDateFormat sf1 = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat sf2 = new SimpleDateFormat("yyyy-MM-dd");
		String sfstr = "";
		try {
			sfstr = sf2.format(sf1.parse(str));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return sfstr;
	}

	/**
	 * 翻转字符串
	 * @param str
	 * @return
	 */
	public static String swap(String str) {
		return new StringBuilder(str).reverse().toString();
	}

	public static String addStrings(String str , int n){
		String tmpStr = "";
		for(int i=0;i<n;i++){
			tmpStr += str;
		}
		return tmpStr;
	}

}
