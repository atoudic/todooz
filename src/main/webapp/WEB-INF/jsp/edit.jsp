<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

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
    <div class="navbar navbar-default">
        <div class="navbar-header">
            <a class="navbar-brand" href="/">Todooz</a>
        </div>
        <div class="collapse navbar-collapse navbar-ex1-collapse">
            <form class="navbar-form navbar-left" role="search">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="Search">
                </div>
            </form>
            <a href="/add" class="btn btn-default navbar-btn navbar-right">
                <span class="glyphicon glyphicon-plus"></span>
            </a>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-7 col-lg-offset-1">

            <legend>Edit</legend>
            <form:form cssClass="form-horizontal" commandName="task" action="/edit" method="post">
                <form:hidden path="id" />
                <div class="form-group">
                    <label for="title" class="col-lg-2 control-label">Titre</label>
                    <div class="col-lg-8">
                        <form:input cssClass="form-control" path="title" id="title" placeholder="Titre" />
                    </div>
                </div>
                <div class="form-group">
                    <label for="date" class="col-lg-2 control-label">Date</label>
                    <div class="col-lg-4">
                        <c:set var="error"><form:errors path="date"><span style="color:#B94A48" class="help-block">La date n'est pas correcte</span></form:errors></c:set>
                        <form:input cssClass="form-control" path="date" id="date" placeholder="dd/MM/yyyy" />
                        ${error}
                    </div>
                </div>
                <div class="form-group">
                    <label for="tags" class="col-lg-2 control-label">Tags</label>
                    <div class="col-lg-6">
                        <form:input cssClass="form-control" path="tags" id="tags" placeholder="Tags" />
                    </div>
                </div>
                <div class="form-group">
                    <label for="text" class="col-lg-2 control-label">Texte</label>
                    <div class="col-lg-10">
                        <textarea class="form-control" name="text" id="text" placeholder="Texte" rows="5"></textarea>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-lg-offset-2 col-lg-10">
                        <button type="submit" class="btn btn-primary">${empty task.id ? 'Add' : 'Update'}</button>
                    </div>
                </div>
            </form:form>
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