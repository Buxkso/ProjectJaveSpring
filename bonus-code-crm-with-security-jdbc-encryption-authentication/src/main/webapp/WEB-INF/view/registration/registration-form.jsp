<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../system/header.jsp" %>
<!doctype html>
<html lang="en">

<head>

    <title>Register New User Form</title>

</head>

<body>

<div>

    <div id="loginbox" style="margin-top: 50px;"
         class="mainbox col-md-3 col-md-offset-2 col-sm-6 col-sm-offset-2">

        <div class="panel panel-primary">

            <div class="panel-heading">
                <div class="panel-title">Register New User</div>
            </div>

            <div style="padding-top: 30px" class="panel-body">

                <!-- Registration Form -->
                <form:form action="${pageContext.request.contextPath}/register/processRegistrationForm"
                           modelAttribute="crmUser"
                           class="form-horizontal">

                    <!-- Place for messages: error, alert etc ... -->
                    <div class="form-group">
                        <div class="col-xs-15">
                            <div>

                                <!-- Check for registration error -->
                                <c:if test="${registrationError != null}">

                                    <div class="alert alert-danger col-xs-offset-1 col-xs-10">
                                            ${registrationError}
                                    </div>

                                </c:if>

                            </div>
                        </div>
                    </div>

                    <!-- User name -->
                    <div style="margin-bottom: 25px" class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>

                        <form:input path="userName" placeholder="username" class="form-control"/>
                    </div>

                    <!-- Password -->
                    <div style="margin-bottom: 25px" class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>

                        <form:password path="password" placeholder="password" class="form-control"/>
                    </div>

                    <!-- Register Button -->
                    <div style="margin-top: 10px" class="form-group">
                        <div class="col-sm-6 controls">
                            <button type="submit" class="btn btn-primary">Register</button>
                        </div>
                        <div class="col-sm-6 controls">
                            <a href="${pageContext.request.contextPath}/customer/list">Back to List</a>
                        </div>
                    </div>

                </form:form>

            </div>

        </div>

    </div>

</div>

</body>
</html>