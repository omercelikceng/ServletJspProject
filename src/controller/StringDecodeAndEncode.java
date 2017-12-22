package controller;

import java.util.HashMap;
public class StringDecodeAndEncode {
	
	private static final HashMap<String, String> encodeMap = new HashMap<>();
	static {
		encodeMap.put("<", "#60;");
		encodeMap.put(">", "#62;");
		encodeMap.put("!", "#33;");
		encodeMap.put("%", "#37;");
	}
	// Kullanıcıdan bir veri girişi aldığımda req.getParameter fonksiyonu çağrıldığı an encoding işlemi yapıyorum.
	// Bu sınıfın amacı ise ajax ile bu verileri çektiğim de o fonksiyonu çağırmamış oluyorum.
	// Burada encoding işlemi yapıyorum.
	public String encodeOperation(String value) {
		for (String key : encodeMap.keySet()) {
			value = value.replaceAll(key, encodeMap.get(key));
		}
		return value;
	}

}
