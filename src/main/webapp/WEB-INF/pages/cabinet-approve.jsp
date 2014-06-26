<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:cabinet-template >
<jsp:attribute name="body_fragment">
<!-- TODO maybe should call plain method???? -->
<script type="application/javascript">
    function approve(id,type){
        $.ajax({
            url: 'http://localhost:8080/rest/approve?id='+id+'&type='+type,
            type: 'POST',
            dataType: 'json'
        }).done(function(result){
                if(result==true){
                    alert("Successfully approved.");
                }else{
                    alert("Error approving person. Try again later.");
                }
                location.reload();
            });
    }
    function blacklist(id){
        $.ajax({
            url: 'http://localhost:8080/rest/blacklist?id='+id,
            type: 'POST',
            dataType: 'json'
        }).done(function(result){
                    if(result==true){
                        alert("Successfully blacklisted.");
                    }else{
                        alert("Error blacklisting person. Try again later.");
                    }
                    location.reload();
                });
    }
</script>
<p>Unapproved students:</p>
<div>
    <c:forEach items="${students}" var="student">
        <div>
            ${student.person.name} ${student.person.surname}<button value="approve" onclick="approve(${student.person.id},'student')">Approve</button><button value="blacklist" onsubmit="blacklist(${student.person.id})">Blacklist</button>
        </div>
    </c:forEach>
</div>
<p>Unapproved instructors:</p>
<div>
    <c:forEach items="${instructors}" var="instructor">
        <div>
                ${instructor.person.name} ${instructor.person.surname}<button value="approve" onclick="approve(${instructor.person.id},'instructor')">Approve</button><button value="blacklist" onsubmit="blacklist(${instructor.person.id})">Blacklist</button>
        </div>
    </c:forEach>
</div>
</jsp:attribute>
</t:cabinet-template>