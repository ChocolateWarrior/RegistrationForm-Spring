<#import "/spring.ftl" as spring/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Registration Form</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body ng-app="registrationApp" ng-controller="RegistrationCtrl">


<div class="container" style="margin-top: 60px">
    <div class="row">
        <div class="col-md-8 col-md-offset-2">
            <h2 class="page-header">Registration Form</h2>
            <h3 id="registrationMessage">{{message}}</h3>
            <form style="margin-bottom: 30px" name="form" autocomplete="off" novalidate ng-submit="form.$valid && sendForm(auth)">
                <div class="form-group">
                    <label id="firsNameInputLabel" for="firstNameExampleElement">First name</label>
                    <input type="text"
                           class="form-control"
                           id="firstNameExampleElement"
                           placeholder="Enter first name"
                           required
                           ng-model="auth.firstName">
                </div>
                <div class="form-group">
                    <label id="lastNameInputLabel" for="lastNameExampleElement">Last name</label>
                    <input type="text"
                           class="form-control"
                           id="lastNameExampleElement"
                           placeholder="Enter last name"
                           required
                           ng-model="auth.lastName">
                </div>
                <div class="form-group">
                    <label id="loginInputLabel" for="loginExampleElement">Login</label>
                    <input type="text"
                           class="form-control"
                           id="loginExampleElement"
                           placeholder="Enter login"
                           required
                           ng-model="auth.login">
                </div>
                <div class="form-group">
                    <label id="passwordInputLabel" for="passwordExampleElement">Password</label>
                    <input type="text"
                           class="form-control"
                           id="passwordExampleElement"
                           placeholder="Enter password"
                           required
                           ng-model="auth.password">
                </div>
                <button type="submit" class="btn btn-success" style="margin-top:30px" ng-disabled="form.$invalid">
                    Register
                </button>
            </form>
        </div>
    </div>
</div>

<script type="text/javascript" src="http://code.jquery.com/jquery-1.8.3.js"></script>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/angularjs/1.7.8/angular.min.js"></script>
<script type="text/javascript" src="js/main.js"></script>

</body>
</html>