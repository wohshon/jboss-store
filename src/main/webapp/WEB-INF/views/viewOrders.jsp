<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
   
<c:set var="ORDERS" value="${requestScope.REQ_ORDERS}"/>
<c:set var="DELIVERIES" value="${requestScope.REQ_DELVS}"/>

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
<!--     <c:out value="${fn:length(ORDERS)}"/>  -->
 
    <c:set var="count" value="0" scope="page" />
    <div class="row">
        <div class="col col-1 bg-dark text-white">
            #
        </div>
        <div class="col col-5 bg-dark text-white">
            Order
        </div>
        <div class="col col-3 bg-dark text-white">
            Order Timestamp
        </div>
        <div class="col col-2 bg-dark text-white">
            Price
        </div>
        <div class="col col-1 bg-dark text-white">
            Status
        </div>
    </div>    
    <c:if test="${fn:length(ORDERS)==0}">
        <div class="h5">Sorry there are no orders currently.</div>
    </c:if>
    <c:forEach items="${ORDERS}" var="order">
        <div class="row">
            <div class="col col-1">
                <c:set var="count" value="${count + 1}" scope="page"/>
                <c:out value = "${count}"/>                    
            </div>
            <div class="col col-4">
                ${order.name}
            </div>
            <div class="col col-3">
                ${order.orderDate}
            </div>
            <div class="col col-2">
                $ ${order.totalPrice}
            </div>
            <div class="col col-2">
                ${order.status}
            </div>
          
        </div>            


    </c:forEach>



    <c:set var="count1" value="0" scope="page" />
    <div class="row">
        <div class="col col-1 bg-dark text-white">
            #
        </div>
        <div class="col col-4 bg-dark text-white">
            Deliveries for Orders
        </div>
        <div class="col col-3 bg-dark text-white">
            Delivery Date
        </div>
        <div class="col col-2 bg-dark text-white">
            Status
        </div>
        <div class="col col-2 bg-dark text-white">
            Recipient
        </div>          
    </div>    
    <c:forEach items="${DELIVERIES}" var="delivery">
        <div class="row">
            <div class="col col-1">
                <c:set var="count1" value="${count1 + 1}" scope="page"/>
                <c:out value = "${count1}"/>                    
            </div>
            <div class="col col-4">
                ${delivery.order.name}
            </div>
            <div class="col col-3">
                ${delivery.deliveryDate}
            </div>
            <div class="col col-2">
                ${delivery.status}
            </div>
            <div class="col col-2">
                ${delivery.order.customer.name}
            </div>
        </div>            


    </c:forEach>    
    
    </div>
    <!--end main container-->
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
            $("#alertMsg").hide();
        }
        
        $( document ).ready( readyFn );
    </script>
</body>

</html>
