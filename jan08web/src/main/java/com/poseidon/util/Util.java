package com.poseidon.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Util {
	// String값이 들어오면 int타입인지 확인해보는 메소드
	// 127 -> true
	// 2d3454c => false
	public static boolean intCheck(String str) {
		boolean result = true;

//		for (int i = 0; i < str.length(); i++) {
//			char ch = str.charAt(i);
//			
//			if (ch >= 48 && ch <= 59) {
//				result = true;
//			} else {
//				result = false;
//				break;
//			}
//		}

		for (int i = 0; i < str.length(); i++) {
			if (!Character.isDigit(str.charAt(i))) {
				result = false;
				break;
			}
		}

		return result;
	}

	// 방법 2
	public static boolean intCheck2(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	// 숫자 문자 섞인걸 숫자로 변환
	// A59 -> 59
	public static int str2Int(String str) {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < str.length(); i++) {
			if (Character.isDigit(str.charAt(i))) {
				sb.append(str.charAt(i));
			}
		}
		// return Integer.parseInt(sb.toString());
		// 얘는 이제 detail.java에서 사용
		// 한계 - 누가 aa 처럼 숫자 없이 문자만 적으면 500에러가 남 (sb가 텅 빔)

		// sb의 길이가 0일때를 처리하는 방법으로 도모
		int number = 0;
		if (sb.length() > 0) {
			number = Integer.parseInt(sb.toString());
		}
		// sb의 길이가 0보다 크면 sb의 숫자를 number에 담고
		// 문자만 들어왔다면 0을 반환
		// 확인
		// System.out.println("변환된 숫자 " + number);

		return number;
	}

	// 참고
	public static int str2Int2(String str) {
		String numberOnly = str.replaceAll("[^0-9]", "");
		return Integer.parseInt(numberOnly);
	}

// ip가져오기

	// IP얻어오기
	public static String getIP(HttpServletRequest request) {
		String ip = request.getHeader("X-FORWARDED-FOR");
		if (ip == null) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null) {
			ip = request.getRemoteAddr();
		}

		return ip;
	}

	// html태그를 특수기호로 변경하기 < &lt > &gt
	public static String removeTag(String str) {

		str = str.replaceAll("<", "&lt");
		str = str.replaceAll(">", "&gt");

		return str;
	}

	//엔터 누르면 줄바꿈 처리
	public static String addBR(String str) {
		return str.replaceAll("(\r\n|\r|\n|\n\r)", "<br>");
	}
	
	
	//ip중간을 😒로 가리기
	public static String ipMasking(String ip) {
		if(ip.indexOf('.') != -1) {
			StringBuffer sb = new StringBuffer(); //멀티스레드 환경에서도 동기화 지원
			sb.append(ip.substring(0, ip.indexOf('.')));
			sb.append(".😒.");
			sb.append(ip.substring(ip.indexOf('.', 6) +1));
			return sb.toString();
		} else {
			return ip;			
		}
		
	}
	

}
