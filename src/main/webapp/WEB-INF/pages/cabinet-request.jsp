<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<t:cabinet-template >
<jsp:attribute name="body_fragment">

    <p>
        Please select lesson to plan.
    </p>
    <div class="form">
        ${result}<br>
        <form:form method="post" action="request" modelAttribute="cabinetRequest">

            Instructor:
            <form:select path="instructor">
                <form:option value="any"/>
                <c:forEach items="${instructors}" var="instructor">
                    <form:option value="${instructor.person.name} ${instructor.person.surname}"/>
                </c:forEach>
            </form:select>
            <br>
            Plane:
            <form:select path="plane">
                <form:option value="any"/>
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
            <form:button path="submit" value="Submit!">Submit!</form:button>
        </form:form>
    </div>

</jsp:attribute>
</t:cabinet-template>