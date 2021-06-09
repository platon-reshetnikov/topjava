<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ tag description="Simple Wrapper Tag" pageEncoding="UTF-8" %>
<%@ attribute name="title" fragment="true" %>
<html>
<head>
     <title><jsp:invoke fragment="title"/></title>
     <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous"/>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
     <a class="navbar-brand" href="https://github.com/JavaWebinar/topjava">Java Enterprise (Topjava)</a>
     <ul class="navbar-nav mr-auto">
     <li class="nav-item active">
    <a class="nav-link" href="<c:url value="/"/>">Home</a>
     </li>
     <li class="nav-item">
     <a class="nav-link" href="users">Users</a>
     </li>
     <li class="nav-item"><a class="nav-link" href="meals">Meals</a>
   </li>
    </ul>
</nav>
<div class="container">
    <jsp:doBody/>
</div>
</body>
</html>