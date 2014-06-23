<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:cabinet-template >
<jsp:attribute name="body_fragment">
    <script type="application/javascript">
    function reabilate(id){
        $.ajax({
            url: 'http://localhost:8080/rest/approvePerson?id='+id,
            type: 'POST',
            dataType: 'json'
        }).done(function(result){
                    if(result.success==true){
                        alert("Successfully approved.");
                    }else{
                        alert("Error approving person. Try again later.");
                    }
                });
        location.reload();
    }
    function blacklist(id){
        $.ajax({
            url: 'http://localhost:8080/rest/blacklistPerson?id='+id,
            type: 'POST',
            dataType: 'json'
        }).done(function(result){
                    if(result.success==true){
                        alert("Successfully blacklisted.");
                    }else{
                        alert("Error blacklisting person. Try again later.");
                    }
                });
        location.reload();
    }
</script>
<p>All students:</p>
<div>
    <c:forEach items="${students}" var="student">
        <div>
            ${student.person.name} ${student.person.surname}<button value="blacklist" onclick="blacklist(${student.id})">Blacklist</button>
        </div>
    </c:forEach>
</div>
<p>Blacklisted students:</p>
<div>
    <c:forEach items="${blacklistedStudents}" var="student">
        <div>
                ${student.person.name} ${student.person.surname}<button value="reabilate" onclick="reabilate(${student.id})">Reabilate</button>
        </div>
    </c:forEach>
</div>
</jsp:attribute>
</t:cabinet-template>