package test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonTest {

	public static ObjectMapper objectMapper = new ObjectMapper();
	
	public static void main(String[] args) throws IOException {
		List<Aaa> aaa = new ArrayList<Aaa>();
		Aaa a = new Aaa();
		a.setA(1);
//		Bbb b = new Bbb();
//		b.setA(2);
//		b.setHehe("hhh");
		aaa.add(a);
//		aaa.add(b);
		String s = objectMapper.writeValueAsString(aaa);
		System.out.println(s);
		
		Ccc c = new Ccc();
		c.setA(a);
		String s2 = objectMapper.writeValueAsString(c);
		System.out.println(s2);
		
//		JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, Aaa.class);
//		List<Aaa> aaa2 = objectMapper.readValue(s, javaType);
//		for (Aaa a2 : aaa2) {
//			System.out.println(a2.getA());
//		}
	}

}
