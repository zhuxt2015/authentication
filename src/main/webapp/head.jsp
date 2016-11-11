<%@ page import="java.io.PrintWriter" %>
<%@ page import="net.sf.json.JSONObject" %>
<%--
  Created by IntelliJ IDEA.
  User: zhuxt
  Date: 2016/11/11
  Time: 13:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    PrintWriter pw = response.getWriter();
    response.setContentType("text/html;charset=UTF-8");
    JSONObject jsonResult = new JSONObject();
    jsonResult.element("success", true);
    jsonResult.element("error", "");
    jsonResult.element("token", "");
    String responseResult = "";
%>
