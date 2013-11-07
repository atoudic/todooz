<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ attribute name="task" required="true" type="fr.todooz.domain.Task" %>

<div>
    <p><fmt:formatDate value="${task.date}" pattern="dd MMM yyyy"/></p>
    <span class="lead">${fn:escapeXml(task.title)}</span>
    <code>${fn:escapeXml(task.tags)}</code>
    <p>${fn:escapeXml(task.text)}</p>
</div>