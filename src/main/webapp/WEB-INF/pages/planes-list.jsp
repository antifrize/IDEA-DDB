<%--
  Created by IntelliJ IDEA.
  User: VMakarenko
  Date: 5/16/14
  Time: 12:10 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:cabinet-template >
<jsp:attribute name="body_fragment">

<html>
<head>
    <title>Planes list</title>
</head>
<body>
<h1>Planes list</h1>
<c:forEach items="${planes}" var="plane">
    [${plane.manufacturer} ${plane.model} ]</br>
</c:forEach>

</body>
</html>
    </jsp:attribute>
</t:cabinet-template>
