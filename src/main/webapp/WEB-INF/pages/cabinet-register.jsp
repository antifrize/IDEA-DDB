<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<t:cabinet-template >
    <jsp:attribute name="body_fragment">
    <form:form action="register" commandName="registerBean">
        Name: <form:input path="name" name="name"/></br>
        Surname: <form:input path="surname" name="surname"/></br>
        Last name: <form:input path="lastname" name="lastname"/></br>
        Login: <form:input path="login" name="login"/></br>
        Password: <form:input path="password" name="password" /></br>
        Repeat password: <form:input path="repeat_password" name="repeat_password"/></br>
    </form:form>
    </jsp:attribute>
</t:cabinet-template>