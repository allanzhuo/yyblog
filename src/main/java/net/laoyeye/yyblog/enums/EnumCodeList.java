package net.laoyeye.yyblog.enums;

import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 枚举类型转换成CodeList的工具类
 * 
 * <br>
 * 
 *
 */
public class EnumCodeList implements CodeList {


    public static interface CodeListItem {

        String getCode();

        String getValue();
    }


    private final Map<String, String> codeListMap;


    public EnumCodeList(Class<? extends Enum<?>> enumClass) {
        Assert.isTrue(CodeListItem.class.isAssignableFrom(enumClass),
                "the given enumClass must implement " + CodeListItem.class);
        Map<String, String> codeList = new LinkedHashMap<String, String>();
        Method method = ReflectionUtils.findMethod(enumClass, "values");

        Enum<?>[] result = (Enum<?>[]) ReflectionUtils.invokeMethod(method,
                enumClass);
        for (Enum<?> e : result) {
            CodeListItem item = (CodeListItem) e;
            codeList.put(item.getCode(), item.getValue());
        }

        this.codeListMap = Collections.unmodifiableMap(codeList);
    }


    @Override
    public Map<String, String> toMap() {
        return this.codeListMap;
    }

	@Override
	public Map<String, String> getMap(String bizType) {
		// TODO Auto-generated method stub
		return null;
	}

}
