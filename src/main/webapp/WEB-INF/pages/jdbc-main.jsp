<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: VMakarenko
  Date: 6/26/14
  Time: 11:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<div class="form">
    ${result}<br>
    <form:form method="post" action="tryPausedScheduleAdd" modelAttribute="cabinetRequest">

        Instructor:
        <form:select path="instructor">
            <c:forEach items="${instructors}" var="instructor">
                <form:option value="${instructor.person.name} ${instructor.person.surname}"/>
            </c:forEach>
        </form:select>
        <br>
        Plane:
        <form:select path="plane">
            <c:forEach items="${planes}" var="plane">
                <form:option value="${plane.manufacturer} ${plane.model}"/>
            </c:forEach>
        </form:select>
        <br>
        <div class="mono_width">
            From:
            <form:input  path="date_from" onkeyup="dateOnChange"/><form:input path="time_from" onkeyup="timeOnChange"/>
            <br>
            To:
            <form:input path="date_to"  onkeyup="dateOnChange"/><form:input path="time_to" onkeyup="timeOnChange"/>
        </div>
        Duration: <form:input path="${duration}"></form:input><br/>
        <form:button path="submit" value="Submit!">Try paused schedule add</form:button>
    </form:form>
</div>
<div><a href="tryDeleteInstructor">Try to delete instructor,while requesting.</a></div>

</body>
</html>
