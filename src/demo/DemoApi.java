package demo;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;

public class DemoApi {
	
	public static void demoDecimal() {
		System.out.println(BigDecimal.TWO);
	}
	
	public static void demoSleep() {
		try {
			Thread.sleep(500);
			Thread.sleep(Duration.ofSeconds(1));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void demoLocale() {
		System.out.println(Locale.of("ja"));
		System.out.println(Locale.of("ja","jp"));
		//System.out.println(new Locale()); // Deprecated
	}
	
	public static void demoCollectionStaticNewXXX() {
		
		HashMap<String, String> m = HashMap.newHashMap(10);
		HashSet<String> set = HashSet.newHashSet(10);
		
	}
	
	
	public static void demoMath() {
		
		System.out.println(Math.TAU);
		System.out.println(Math.TAU / 2);
		System.out.println(Math.PI);
		
		System.out.println(Integer.toHexString(Integer.compress(0xcafebabe, 0xf0f0f0f0)));
		System.out.println(Integer.toHexString(Integer.expand(0xcafebabe, 0xf0f0f0f0)));
		
		
	}
}
