package component;

import java.util.Scanner;

public enum BaseTransformer {;
	private static final String cstr = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	public static String convert(long num, int base) {
		check(base, 1, 62);
		String r = "";
		while (num > 0) {
			r = cstr.charAt((int) (num%base)) + r;
			num /= base;
		}
		return r;
	}
	public static long convert(String num, int base) {
		check(base, 1, 62);
		long r = 0;
		int t;
		for (int i = 0; i < num.length(); i++) {
			t = cstr.indexOf(num.charAt(i));
			check(t, 0, base);
			r = r * base + t;
		}
		return r;
	}
	private static void check(int base, int min, int max) {
		if (!(min < base && base <= max)) throw new IndexOutOfBoundsException();
	}
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		Long l = s.nextLong();
		System.out.println(l);
		System.out.println(convert(l,62));
		s.close();
	}
}
