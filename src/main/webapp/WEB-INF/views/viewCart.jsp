<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
   
<c:set var="CART_ITEMS" value="${sessionScope.SESS_CART.items}"/>
<c:set var="CART" value="${sessionScope.SESS_CART}"/>

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
<!--    <c:out value="${fn:length(CART_ITEMS)}"/>-->

        <div id="alertMsg" class="alert alert-success alert-dismissible fade show" role="alert">
            <label id="alertMsgContent">123</label>
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
            <span aria-hidden="true">&times;</span>
            </button>
        </div> 
        <c:set var="count" value="0" scope="page" />
        <div class="row">
            <div class="col col-1 bg-dark text-white">
                #
            </div>
            <div class="col col-7 bg-dark text-white">
                Cart Item
            </div>
            <div class="col col-1 bg-dark text-white">
                Price
            </div>
            <div class="col col-2 bg-dark text-white">
                Qty
            </div>
            <div class="col col-1 bg-dark text-white">
                Total
            </div>
        </div>
        <c:if test="${fn:length(CART_ITEMS)==0}">
            <div id="nocart_msg" class="h5">Sorry there are no items in your cart currently.</div>
        </c:if>
        <c:forEach items="${CART_ITEMS}" var="cartItem">
            <div class="row" id="cart_info">
                <div class="col col-1">
                    <c:set var="count" value="${count + 1}" scope="page"/>
                    <c:out value = "${count}"/>                    
                </div>
                <div class="col col-7">
                    ${cartItem.item.name}: ${cartItem.item.shortDescription}
                </div>
                <div class="col col-1">
                    ${cartItem.item.price}
                </div>
                <div class="col col-2">
                    ${cartItem.qty}
                </div>
                <div class="col col-1">
                    ${cartItem.linePrice}
                </div>
            </div>            


        </c:forEach>
        <div class="row">
            <div class="col col-11">
                <div class="float-right">
                    <label>Total </label>
                </div>
            </div>
            <div class="col col-1">
                ${CART.totalPrice}
            </div>
        </div>
        <c:if test="${fn:length(CART_ITEMS) > 0}">

            <div class="row">
                <div class="col">
                    <div class="float-right">
                        <button id="btn-delete-cart" type="button" class="btn btn-outline-primary">X Delete Cart</button>
                        <button id="btn-checkout" type="button" class="btn btn-outline-primary">Check Out</button>
                    </div>
                </div>
            </div>        

            <div class="row">
                <div class="col" style="display: none;" id="checkOutSection">
                    <form class="form-checkout" id="checkoutForm" method="POST">
                        <h1 class="h6 mb-3 font-weight-normal">Hi ${sessionScope.SESS_USER.name}, Please Enter a Order Name for reference, and your  Delivery and Contact Information to confirm order</h1>
                        <div class = "row text-black"> Order Name </div>
                        <label for="orderName" class="sr-only">Order Name</label>
                        <input type="text" name="orderName" id="orderName" class="form-control" placeholder="Contact Info" required value="Order for - ${sessionScope.SESS_USER.name}">

                        <div class = "row text-black"> Delivery Address </div>                        
                        <label for="deliveryAddress" class="sr-only" aria-disabled="true">Delivery Address</label>
                        <input type="text" name="deliveryAddress" id="deliveryAddress" class="form-control" placeholder="Delivery Address" required autofocus value="${sessionScope.SESS_USER.deliveryAddress}" >

                        <div class = "row text-black"> Contact Info </div>
                        <label for="contactInfo" class="sr-only">Contact Info</label>
                        <input type="text" name="contactInfo" id="contactInfo" class="form-control" placeholder="Contact Info" required value="${sessionScope.SESS_USER.contactInfo}">

                        <div class = "row text-black"> Credit Card Number </div>
                        <label for="ccNumber" class="sr-only">Credit Card Number</label>
                        <input type="text" name="ccNumber" id="ccNumber" class="form-control" placeholder="Credit Card Number" required value="1234-5678-1234-5678">

                        <div class = "row text-black"> Card Expiry </div>
                        <label for="ccExpiry" class="sr-only"> Card Expiry </label>
                        <input type="text" name="ccExpiry" id="ccExpiry" class="form-control" placeholder=" Card Expiry " required value="12/26">
                        
                        <button class="btn btn-sm btn-primary btn-block" type="button" id="btn-confirm-checkout">Confirm Checkout</button>                        
                    </form>        
                </div>
            </div>

        </c:if>    
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
