<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:cabinet-template >
<jsp:attribute name="body_fragment">
<!-- TODO maybe should call plain method???? -->
<script type="application/javascript">
    function approve(id){
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
<p>Unapproved students:</p>
<div>
    <c:forEach items="${students}" var="student">
        <div>
            ${student.person.name} ${student.person.surname}<button value="approve">Approve</button><button value="blacklist">Blacklist</button>
        </div>
    </c:forEach>
</div>
<p>Unapproved instructors:</p>
<div>
    <c:forEach items="${instructors}" var="instructor">
        <div>
                ${instructor.person.name} ${instructor.person.surname}<button value="approve" onclick="approveInstructor(${instructor.id})">Approve</button><button value="blacklist">Blacklist</button>
        </div>
    </c:forEach>
</div>
</jsp:attribute>
</t:cabinet-template>