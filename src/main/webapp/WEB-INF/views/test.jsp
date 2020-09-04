<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
   
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

	<jsp:include page="nav.jsp"/>

    <!--main container-->
    <div class="container">

        <div class="row">
            <div class="col-md-4 test">
                One of three columns
            </div>
            <div class="col-md-4 test">
                One of three columns
            </div>
            <div class="col-md-4 test">
                One of three columns
            </div>
        </div>

        <div class="row">
            <div class="col-lg-3 test">
                One of four columns
            </div>
            <div class="col-lg-3 test">
                One of four columns
            </div>
            <div class="col-lg-3 test">
                One of four columns
            </div>
            <div class="col-lg-3 test">
                One of four columns
            </div>
        </div>

        <div class="row">
            <div class="col-lg-6 test">
                One of two columns
            </div>
            <div class="col-lg-6 test">
                One of two columns
            </div>
        </div>

        <div class="row">
            <div class="col-lg-12 test">
                One of one columns
            </div>
        </div>

    </div>
    <!--end main container-->
    <!-- jQuery -->
    <script src="js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

<script type="text/javascript">


	function btnClick(event){
		$('#infoArea').hide();
		var targetId=event.target.id;
		//alert('clicked '+targetId);
	}

		
	function readyFn( jQuery ) {
			
		$( ".btn" ).bind("click", function(e) {
			btnClick(e);
			}); 
	}
	
	$( document ).ready( readyFn );
</script>
</body>

</html>
