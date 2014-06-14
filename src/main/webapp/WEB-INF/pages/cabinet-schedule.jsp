<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<t:cabinet-template >
<jsp:attribute name="body_fragment">
<script type="application/javascript">
    function createRequest() {
        var result = null;
        if (window.XMLHttpRequest) {
            // FireFox, Safari, etc.
            result = new XMLHttpRequest();
            if (typeof xmlhttp.overrideMimeType != 'undefined') {
                result.overrideMimeType('text/xml'); // Or anything else
            }
        }
        else if (window.ActiveXObject) {
            // MSIE
            result = new ActiveXObject("Microsoft.XMLHTTP");
        }
        else {
            // No known mechanism -- consider aborting the application
        }
        return result;
    }
    function takeThat(id){

    }

    function cancelThat(id){
        $.ajax({
            url: 'http://localhost:8080/rest/lesson?id='+id,
            type: 'DELETE',
            dataType: 'json'
        }).done(function(result){
                    if(result.success==true){
                        alert("Successfully cancelled.");
                        $("#sched_"+id).remove();
                    }else{
                        alert("Error cancelling lesson. Try again later.");
                        location.reload();
                    }
        });
    }
</script>
<div class="schedule">
    <div class="scheduleHeader">
        <c:forEach items="${scheduler.headerItems}" var="headerItem">
            <div class="scheduleContainer">
                <div class="scheduleHeaderItem">
                        ${headerItem.toString()}
                </div>
                <c:forEach items="${headerItem.scheduleList}" var="scheduleItem">
                    <div class="scheduleItems">

                        <div id="sched_${scheduleItem.schedule.id}" class="scheduleItem_${scheduleItem.status}">
                            <!-- TODO Calendar in JSP? -->
                            Time: ${scheduleItem.schedule.converter.from.hours}:${scheduleItem.schedule.converter.from.minutes} - ${scheduleItem.schedule.to.time.hours}:${scheduleItem.schedule.to.time.minutes} <br>
                            Instructor: ${scheduleItem.schedule.instructor.person.name} ${scheduleItem.schedule.instructor.person.surname}<br>
                            Student: ${scheduleItem.schedule.student.person.name} ${scheduleItem.schedule.student.person.surname}<br>
                            Plane: ${scheduleItem.schedule.plane.manufacturer} ${scheduleItem.schedule.plane.model}<br>
                            Approved: ${scheduleItem.schedule.approved==true?"true":"false"}
                            <!--TODO check user role -->

                            <div class="scheduleItemButtons">
                                <c:if test="${scheduleItem.status=='new'}">
                                    <button name="take${scheduleItem.schedule.id}" onclick="fu">
                                        Take that
                                    </button>

                                </c:if>
                                <c:if test="${scheduleItem.status=='my'}">
                                    <button name="cancel${scheduleItem.schedule.id}" onclick="cancelThat(${scheduleItem.schedule.id})">
                                        Cancel that
                                    </button>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </c:forEach>

    </div>


</div>

</jsp:attribute>
</t:cabinet-template>