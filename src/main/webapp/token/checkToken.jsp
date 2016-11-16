<%@ page import="com.peopleyuqing.bean.Token" %>
<%@ page import="com.peopleyuqing.token.impl.RedisTokenManager" %>
<%--
  Created by IntelliJ IDEA.
  User: zhuxt
  Date: 2016/11/11
  Time: 13:23
  To change this template use File | Settings | File Templates.
  检查token是否存在
--%>
<%@ include file="/head.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String username = request.getParameter("username");
    String prokey = request.getParameter("prokey");
    String token = request.getParameter("token");
    jsonResult.element("token", token);
    Token tokenObject = new Token(username, prokey, token);
    RedisTokenManager manager = new RedisTokenManager();
    boolean exist = false;
    try {
        exist = manager.checkToken(tokenObject);
        if (!exist) {
            jsonResult.element("success", false);
        }
    } catch (Exception e) {
        e.printStackTrace();
        jsonResult.element("success", false);
        jsonResult.element("error", e.getMessage());
    }
    responseResult = jsonResult.toString();
    pw.print(responseResult);
%>
