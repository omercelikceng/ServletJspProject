package filter;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class XSSRequestWrapper extends HttpServletRequestWrapper {

	public XSSRequestWrapper(HttpServletRequest request) {
		super(request);
	}

	@Override
	public String getParameter(String incomingValue) {
		String value = super.getParameter(incomingValue);
		value = encodeOperation(value);
		return value;
	}

	public String encodeOperation(String value) {
		for (String key : encodeMap.keySet()) {
			value = value.replaceAll(key, encodeMap.get(key));
		}
		return value;
	}

	private static final HashMap<String, String> encodeMap = new HashMap<>();
	static {
		encodeMap.put("<", "#60;");
		encodeMap.put(">", "#62;");
		encodeMap.put("!", "#33;");
		encodeMap.put("%", "#37;");
	}
}
