<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script type="application/javascript">
    function checkField(inp, str){
        if(inp.value == ""){
            alert(str);
            inp.focus();
            return false;
        }
        return true;
    }
    function checkFields(){
        res = true;
        if(!checkField(form.name,"please, enter your name!")){
            return false;
        }
        if(!checkField(form.surname,"please, enter your surname!")){
            return false;
        }
        if(!checkField(form.lastname,"please, enter your last name!")){
            return false;
        }
        if(!checkField(form.passport,"please, enter your passport!")){
            return false;
        }
        if(!checkField(form.login,"please, enter your login!")){
            return false;
        }
        if(!checkField(form.password,"please, enter your password!")){
            return false;
        }
        if(!checkField(form.repeat_password,"please, repeat your password!")){
            return false;
        }
        if(form.password.value != form.repeat_password.value){
            alert("Passwords are not similar");
            return false;
        }
        submit.disabled=true;
        return true;
    }

</script>
<t:cabinet-template >
    <jsp:attribute name="body_fragment">
    <form:form id="form" action="register" commandName="registerBean" onsubmit="return checkFields()">
        Name: <form:input id="name" path="name" name="name"/></br>
        Surname: <form:input id="surname" path="surname" name="surname"/></br>
        Last name: <form:input id="lastname" path="lastname" name="lastname"/></br>
        Passport: <form:input id="passport" path="passport" name="passport"/></br>
        Login: <form:input id="login" path="login" name="login"/></br>
        Password: <form:input id="password" path="pass_hash" name="password" /></br>
        Repeat password: <input id="repeat_password" name="repeat_password"/></br>
        <form:button id="submit">Submit</form:button>
    </form:form>
    </jsp:attribute>
</t:cabinet-template>