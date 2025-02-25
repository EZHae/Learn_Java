package com.itwll.jsp2.junit;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JUnitTest {
	private static final Logger log = LoggerFactory.getLogger(JUnitTest.class);
	
	@Test
	public void test1() {
		log.debug("JUnit1 테스트 입니다...");
	}
	
	@Test
	public void test2() {
		log.debug("JUnit2 테스트 입니다...");
	}
	
	@Test
	public void test3() {
		int arr[];
		int i = 0;
		arr = new int[10];
		arr[0]=0;
		arr[1]=1;
		while(i<8) {
			arr[i+2] = arr[i+1] + arr[i];
			i++;
		}
		for (int s : arr) {
			System.out.println(s);
		}
		System.out.println(arr);
	}
}
