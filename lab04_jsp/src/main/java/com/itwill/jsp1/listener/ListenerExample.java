package com.itwill.jsp1.listener;

import java.util.Enumeration;

import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletRequestAttributeEvent;
import jakarta.servlet.ServletRequestAttributeListener;
import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.ServletRequestListener;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Application Lifecycle Listener implementation class ListenerExample
 *
 */
// @WebListener
/* WAS가 시작될 때 리스너 객체를 생성하고 관리할 수 있도록 설정
 	(1) web.xml 파일에서 <listener> 태그로 설정
 	(2) @WebListener 애너테이션으로 설정.
 	(*주의*) 한 개의 리스너 클래스는 위의 방법 중 하나로 설정해야 함 */ 
public class ListenerExample implements ServletRequestListener, ServletRequestAttributeListener {
	/* ServletRequestListener: 요청(request) 객체가 생성/소멸 이벤트를 처리.
	 * ServletRequestAttributeListener: 요청 객체의 attribute가 추가/삭제/변경 이벤트를 처리.
	 */
	
    /**
     * Default constructor. 
     */
    public ListenerExample() {
        System.out.println("==== ListenerExample() 생성자 호출");
    }

	/**
     * @see ServletRequestListener#requestInitialized(ServletRequestEvent)
     */
    @Override
    public void requestInitialized(ServletRequestEvent sre)  { 
         System.out.println("요청이 초기화됨.");
         ServletRequest req = sre.getServletRequest();
         if (req instanceof HttpServletRequest) { // type checking
        	 HttpServletRequest httpReq = (HttpServletRequest) req; // casting
        	 String uri = httpReq.getRequestURI();
        	 System.out.println("[Request URI]: " + uri);
        	 System.out.println("[Request URL]: " + httpReq.getRequestURL());
         }
    }

	/**
     * @see ServletRequestListener#requestDestroyed(ServletRequestEvent)
     */
    @Override
    public void requestDestroyed(ServletRequestEvent sre)  { 
         System.out.println("요청이 소멸됨.");
    }
	
    // ServletRequestAttributeLisetener 인터페이스의 default 메서드(들)을 재정의.
    /**
     * @see ServletRequestAttributeListener#attributeAdded(ServletRequestAttributeEvent)
     */
    @Override
    public void attributeAdded(ServletRequestAttributeEvent srae) {
    	// 상위 타입에서 구현된(override 되기 전의) 메서드를 호출.
    	ServletRequestAttributeListener.super.attributeAdded(srae);
    	// 인터페이스는 클래스와 다르게 다중 상속이 가능하기 때문에 무엇이 super 인터페이스인지 모름.
    	// 그렇기 때문에 인터페이스의 이름을 명시해줘야 함.
    	
    	Enumeration<String> attrNames = srae.getServletRequest().getAttributeNames();
    	while (attrNames.hasMoreElements()) {
    		System.out.println("Request Attr Name: " + attrNames.nextElement() + "이(가) 추가됨.");
    	}
    }
}
