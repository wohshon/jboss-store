<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">


<title>Orders</title>
    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="css/shop-homepage.css" rel="stylesheet">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<jsp:include page="nav.jsp"/>
<div class="container">
	<div id="orderArea">
		<c:if test="${sessionScope.CUST!=null }">
			<h2>Orders for <c:out value="${sessionScope.CUST.name }"/></h2>
		</c:if>	
		<table class="table table-striped">
                <c:set var="orders" value="${requestScope.ORDERS}"/>
			    <thead>
			    	<tr>
			    		<th>#</th>
			    		<th>Order Id</th>
			    		<th>Name</th>
			    		<th>Date</th>
			    		<th>Total Price</th>
			    	</tr>
				</thead>
				<tbody>
           		<c:forEach items="${orders}" var="order" varStatus="loop">
           			<c:set var="orderItems" value="${order.items}"/>
           			<tr>
	           			<c:set var="itemCount" value="${loop.index+1}"/>
	           			<td><c:out value="${itemCount}"/></td>
	           			<td><c:out value="${order.id}"/></td>
	           			<td><c:out value="${order.name}"/></td>
	           			<td><fmt:formatDate type="both" value="${order.orderDate}" /></td>
	           			<td><fmt:formatNumber type="currency" value="${order.totalPrice}"/></td>
	           		</tr>
					<tr>
						<td colspan="4">
							<table class="table table-condensed">
								<tr><td colspan="4">Order Details</td></tr>							
		           				<c:forEach items="${orderItems}" var="item" varStatus="itemLoop">
								<tr>	
									<td><c:out value="${itemLoop.index+1}"/>.</td>           					
									<td><c:out value="${item.product.name}"/></td>           					
									<td><c:out value="${item.qty}"/></td>
									<td><fmt:formatNumber type="currency" value="${item.lineCost}"/></td>           					
		           				</tr>
		           				</c:forEach>
							</table>
						</td>
					</tr>					           			
           		</c:forEach>
				</tbody>
		</table>
	</div>
</div>
                
                
</body>
    <!-- jQuery -->
    <script src="js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

	<script type="text/javascript">
	function readyFn( jQuery ) {
		$( ".btn" ).bind("click", function(e) {
			  btnClick(e);
			}); 
	}
	 
	$( document ).ready( readyFn );
	</script>
</html>