<%-- added by zhuxt 2016-11-11 获取token--%>
<%@ page import="com.peopleyuqing.token.impl.RedisTokenManager" %>
<%@ page import="com.peopleyuqing.bean.Token" %>
<%@ page import="org.springframework.context.support.ClassPathXmlApplicationContext" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/head.jsp"%>
<%
  ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
  context.start();
  String username = request.getParameter("username");
  String prokey = request.getParameter("prokey");
  try {
    RedisTokenManager manager = new RedisTokenManager();
    Token token = manager.createToken(username, prokey);
    jsonResult.element("token", token.getToken());
  } catch (Exception e) {
    e.printStackTrace();
    jsonResult.element("success", false);
    jsonResult.element("error", e.getMessage());
  }
  responseResult = jsonResult.toString();
  System.out.println(responseResult);
  pw.print(responseResult);
%>