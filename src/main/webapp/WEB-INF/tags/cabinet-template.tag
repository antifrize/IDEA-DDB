<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@attribute name="body_fragment" fragment="true" %>

<html>

<head>
    <title>Personal cabinet</title>
    <link rel="stylesheet" type="text/css" href="../css/cabinet.css"/>
    <script type="application/javascript" src="../js/jquery-2.1.1.min.js"></script>
</head>
<body>
<div class="main">
    <div class="header"><%@include file="../pages/fragments/header.jspf" %> </div>
    <div class="menu" ><%@include file="../pages/fragments/menu.jspf"%></div>
    <div class="body" ><jsp:invoke fragment="body_fragment"/></div>
    <div class="footer" ><%@include file="../pages/fragments/footer.jspf"%></div>
</div>



</body>
</html>