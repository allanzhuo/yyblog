package net.laoyeye.yyblog.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * 去除富文本编辑器格式工具
 * @author 小卖铺的老爷爷
 * @date 2018年4月24日
 * @website www.laoyeye.net
 */
public class FilterHtml {

	public static String filterHtml(String str)
	{
		Pattern pattern = Pattern.compile("<([^>]*)>");
		Matcher matcher = pattern.matcher(str);
		StringBuffer sb = new StringBuffer();
		boolean result1 = matcher.find();
		while (result1)
		{
			matcher.appendReplacement(sb, "");
			result1 = matcher.find();
		}
		matcher.appendTail(sb);
		return sb.toString();
	}

	public static String fiterHtmlTag(String str, String tag)
	{
		String regexp = "<\\s*" + tag + "\\s+([^>]*)\\s*>";
		Pattern pattern = Pattern.compile(regexp);
		Matcher matcher = pattern.matcher(str);
		StringBuffer sb = new StringBuffer();
		boolean result1 = matcher.find();
		while (result1)
		{
			matcher.appendReplacement(sb, "");
			result1 = matcher.find();
		}
		matcher.appendTail(sb);
		return sb.toString();
	}

	public static String replaceHtmlTag(String str, String beforeTag, String tagAttribute, String startTag, String endTag)
	{
		String regexpForTag = "<\\s*" + beforeTag + "\\s+([^>]*)\\s*>";
		String regexpForTagAttribute = tagAttribute + "=\"([^\"]+)\"";
		Pattern patternForTag = Pattern.compile(regexpForTag);
		Pattern patternForAttribute = Pattern.compile(regexpForTagAttribute);
		Matcher matcherForTag = patternForTag.matcher(str);
		StringBuffer sb = new StringBuffer();
		boolean result = matcherForTag.find();
		while (result)
		{
			StringBuffer sbReplace = new StringBuffer();
			Matcher matcherForAttribute = patternForAttribute.matcher(matcherForTag.group(1));
			if (matcherForAttribute.find()) {
				matcherForAttribute.appendReplacement(sbReplace, startTag + matcherForAttribute.group(1) + endTag);
			}
			matcherForTag.appendReplacement(sb, sbReplace.toString());
			result = matcherForTag.find();
		}
		matcherForTag.appendTail(sb);
		return sb.toString();
	}

	public static int chineseWords(CharSequence charSequence)
	{
		if (charSequence == null) {
			return 0;
		}
		char[] t1 = charSequence.toString().toCharArray();
		int count = 0;
		for (char aT1 : t1) {
			if (Character.toString(aT1).matches("[\\u4E00-\\u9FA5]+")) {
				count++;
			}
		}
		return count;
	}
}
