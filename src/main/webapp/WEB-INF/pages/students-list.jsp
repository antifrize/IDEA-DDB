<%--
  Created by IntelliJ IDEA.
  User: VMakarenko
  Date: 5/16/14
  Time: 12:10 AM
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:cabinet-template >
<jsp:attribute name="body_fragment">

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Students list</title>
</head>
<body>

<h1>Students list</h1>
<c:forEach items="${students}" var="student">
    [${student.person.surname} ${student.person.name} ]</br>
</c:forEach>

</body>
</html>
</jsp:attribute>
</t:cabinet-template>
