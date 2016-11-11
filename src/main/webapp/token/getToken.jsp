<%-- added by zhuxt 2016-11-11 获取token--%>
<%@ page import="com.peopleyuqong.token.impl.RedisTokenManager" %>
<%@ page import="com.peopleyuqong.bean.Token" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/head.jsp"%>
<%
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