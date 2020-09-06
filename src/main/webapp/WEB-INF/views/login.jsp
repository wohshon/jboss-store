<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
   
<c:set var="PRODUCTS" value="${sessionScope.SESS_PRODUCTS}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Sample Not Coolstore</title>

    
    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">


    <!-- Custom CSS -->
    <link href="css/shop-homepage.css" rel="stylesheet">

    <link href="css/style.css" rel="stylesheet">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

    <!--main container-->
    <div class="container text-center">
        <div class="row">
            <div class="col">${msg}</div>
        </div>
        <form class="form-signin" id="loginForm" method="POST">
            <img class="mb-4" src="https://getbootstrap.com/docs/4.0/assets/brand/bootstrap-solid.svg" alt="" width="72" height="72">
            <h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>
            <label for="inputEmail" class="sr-only">Email address</label>
            <input type="email" name="inputEmail" id="inputEmail" class="form-control" placeholder="Email address" required autofocus value="johnd@gmail.com">
            <label for="inputPassword" class="sr-only">Password</label>
            <input type="password" name="inputPassword" id="inputPassword" class="form-control" placeholder="Password" required value="123&83730434Dfg">
            <div class="checkbox mb-3">
              <label>
                <input type="checkbox" value="remember-me"> Remember me
              </label>
            </div>
            <button class="btn btn-lg btn-primary btn-block" type="button" id="btn-login">Sign in</button>
            <p class="mt-5 mb-3 text-muted">&copy; 2020-2021</p>
          </form>        
    </div>
    <!-- jQuery -->
    <script src="js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.bundle.min.js"></script>
    <!-- Custom JavaScript -->
    <script src="js/app.js"></script>
    <script type="text/javascript">


        function btnClick(event){
            $('#infoArea').hide();
            var targetId=event.target.id;
            App.log('clicked '+targetId);
            App.handleEvent(targetId);
        }

            
        function readyFn( jQuery ) {
                
            $( ".btn" ).bind("click", function(e) {
                btnClick(e);
                }); 
        }
        
        $( document ).ready( readyFn );
    </script>        
</body>
