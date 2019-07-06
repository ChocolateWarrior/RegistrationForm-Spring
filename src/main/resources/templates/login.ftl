<#import "/spring.ftl" as spring/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Registration Form</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

<#--    <link rel="stylesheet"-->
<#--          type="text/css" href="<@spring.url '/css/style.css'/>"/>-->

</head>

<body ng-app="loggingApp" ng-controller="LoggingCtrl">
<div class="container" style="margin-top: 60px">
    <div class="card">
        <div class="card-header">Logging in</div>
<#--        <h3 id="loggingMessageElement" style="margin-left: 20px">{{message}}</h3>-->
        <div class="card-body">

            <#if logout>
                <div class = "alert alert-info" role = "alert">You've logged out!</div>
            </#if>

            <#if error>
                <div class = "alert alert-danger" role = "alert">Wrong authentication parameters!</div>
            </#if>

            <#if message??>
                <h2>${message}</h2>
            </#if>

            <form action="/login" method="post">
                <input name="${_csrf.parameterName}" value="${_csrf.token}" type="hidden">
                <div class="form-group">
                    <label id="exampleLoginLabel" for="exampleLoginElement">Login</label>
                    <input type="text"
                           name="login"
                           id="exampleLoginElement"
                           placeholder="Enter login"
                           required
                           class="form-control"
                           ng-model="data.login"/>
                </div>
                <div class="form-group">
                    <label id="examplePasswordLabel" for="examplePasswordElement">Password</label>
                    <input type="password"
                           name="password"
                           id="examplePasswordElement"
                           placeholder="Enter password"
                           required
                           class="form-control"
                           ng-model="data.password">
                </div>
                <button type="submit" class="btn btn-primary">Sign in</button>
            </form>
        </div>
    </div>
    <div class = "navigation">
        <ul class="nav nav-tabs nav-fill bg-light">
            <li class="nav-item">
                <a class="nav-link" href="/registration">Register here</a>
            </li>
        </ul>
    </div>
</div>

<#--<script type="text/javascript" src="http://code.jquery.com/jquery-1.8.3.js"></script>-->
<#--<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/angularjs/1.7.8/angular.min.js"></script>-->

</body>
</html>