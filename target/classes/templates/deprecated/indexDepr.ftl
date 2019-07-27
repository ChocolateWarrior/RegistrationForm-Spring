<#import "/spring.ftl" as spring/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login form's Main</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

</head>
<body>
<div class="container" style="margin-top: 50px">
    <div class = "navigation">
        <h2 class="h2">Easy Repairs</h2>
        <ul class="nav nav-tabs nav-fill bg-light">
            <li class="nav-item">
             <a class="nav-link disabled" href="/main">Main page</a>
            </li>
            <li class="nav-item">
               <a class="nav-link" href="/registration">Sign up</a>
            </li>
            <li class="nav-item">
               <a class="nav-link" href="/login">Sign in</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="/display">Display users</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/logout">Sign out</a>
            </li>
        </ul>
<#--        <div>-->
<#--            <form action="/logout" method = "post">-->
<#--                <button type="submit" class="btn btn-primary">Sign Out</button>-->
<#--            </form>-->
<#--        </div>-->
    </div>
    <div class = "userInfo">
        <h2>You are logged in as: ${username} and with roles ${roles}</h2>
    </div>

</div>

</body>
</html>