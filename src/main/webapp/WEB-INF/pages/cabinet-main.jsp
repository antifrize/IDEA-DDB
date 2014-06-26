<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<t:cabinet-template>
<jsp:attribute name="body_fragment">
<script>
    function showHide(id){
        var elem = $('#'+id);
        if(elem.is(":visible")){
            elem.hide();
        }else{
            elem.show();
        }
    }

    function sendInstr(username){
        $.ajax({
            url: 'http://localhost:8080/rest/instructor?login='+username+'&license='+$('#instr_license').val(),
            type: 'PUT',
            dataType: 'json'
        }).done(function(result){
                    if(result==true){
                        alert("Request sent. Wait for approvance.");
                    }else{
                        alert("Try again later.");
                    }
                    location.reload();
                });
    }

    function sendStudent(username){
        var str = 'http://localhost:8080/rest/student?login='+username;
        $.ajax({
            url: str,
            type: 'PUT',
            dataType: 'json'
        }).done(function(result){
                    if(result==true){
                        alert("Request sent. Wait for approvance.");
                    }else{
                        alert("Try again later.");
                    }
                    location.reload();
                });
    }
</script>

<div style="width:200px">
    <div class="cool_text">Welcome, ${person.name} ${person.surname}.</div>
    <sec:authentication property="principal.username" var="username" />
    <sec:authorize access="!(hasRole('ROLE_STUDENT')||hasRole('ROLE_STUDENT_UNAPPROVED'))">
        <div>
            <button id="become_student" class="button" onclick="showHide('become_student_send')">Want to become a student?</button>
        </div>
        <div class="bordered">
            <button style="display:none" id="become_student_send" class="button" onclick="sendStudent('${username}')">Send request.</button>
        </div>
    </sec:authorize>
    <sec:authorize access="!(hasRole('ROLE_INSTRUCTOR')||hasRole('ROLE_INSTRUCTOR_UNAPPROVED'))"><sec:authentication property="principal.username" var="username" />
        <div>
            <button id="become_instructor" class="button" onclick="showHide('become_instructor_send_div');">Want to become an instructor?</button>
        </div>
        <div class="bordered" id="become_instructor_send_div" style="display:none;border-radius: 5px;">
            License: <input style="width: 70%;" id="instr_license"/></br>
            <button  id="become_instructor_send" class="button"  onclick="sendInstr('${username}')">Send request.</button>
        </div>
    </sec:authorize>
</div>

</jsp:attribute>
</t:cabinet-template>