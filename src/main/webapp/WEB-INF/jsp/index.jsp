<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib tagdir="/WEB-INF/tags/widget" prefix="widget" %>
<!DOCTYPE html>
<html>
<head>
    <title>Todooz</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap -->
    <link href="/css/bootstrap.min.css" rel="stylesheet" media="screen">

    <style type="text/css">
        body {
            margin: 5px 0 50px 0;
        }
    </style>
</head>
<body>
<div class="container">
    <widget:navbar />
    <div class="row">
        <div class="col-lg-7 col-lg-offset-1">
            <legend>All tasks</legend>

            <c:forEach var="task" items="${tasks}">
                <widget:task task="${task}"/>
            </c:forEach>
        </div>
        <div class="col-lg-3">
            <div class="panel panel-default">
                <div class="panel-heading">Quick links</div>
                <div class="panel-body">
                    <ul>
                        <li><a href="/today">Today's</a></li>
                        <li><a href="/tomorrow">Tomorrow's</a></li>
                    </ul>
                </div>
            </div>

            <div class="panel panel-default">
                <div class="panel-heading">Tags</div>
                <div class="panel-body">
                    <c:forEach var="tag" items="${tagCloud.tags}">
                        <a href="/tag/${tag}" style="font-size:14px">${fn:escapeXml(tag)}</a>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>