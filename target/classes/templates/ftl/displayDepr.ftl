<#import "/spring.ftl" as spring/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Registration Form</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body ng-app="displayApp" ng-controller="DisplayAppCtrl">


<div class = "container" style="margin-top: 60px">
    <div class = "row">
        <div class = "col-md-12">
            <div class = "panel panel-default">
                <div class = "panel-heading">Users</div>
                <div class = "panel-body">
                    <table class = "table table-striped">
                        <thead>
                        <tr>
                            <th>id</th>
                            <th>first name</th>
                            <th>last name</th>
                            <th>login</th>
                            <th>password</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr ng-repeat="item in users">
                            <td>{{item.id}}</td>
                            <td>{{item.firstName}}</td>
                            <td>{{item.lastName}}</td>
                            <td>{{item.login}}</td>
                            <td>{{item.password}}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript" src="http://code.jquery.com/jquery-1.8.3.js"></script>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/angularjs/1.7.8/angular.min.js"></script>
<script type="text/javascript" src="js/main.js"></script>

</body>
</html>