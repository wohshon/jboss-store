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

    <title>OpenShift v3 Demo</title>

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

    <!-- Page Content -->
    <div class="container">

        <div class="row">
		<!-- cart lo -->
            <div class="col-md-4">
                <p class="lead">Your Cart</p>
                <c:set var="cart" value="${sessionScope.CART}"/>
                <c:set var="cartItems" value="${cart.items}"/>
                <c:choose>
                	<c:when test="${fn:length(cartItems)==0}">
                		<div id="cartDisplay">
                			<div class="row" style="padding-bottom: 5px;">
                				<div class="col-md-1"><label>#</label></div>
                				<div class="col-md-3"><label>Item</label></div>
                				<div class="col-md-2"><label>Price</label></div>
                				<div class="col-md-1"><label>Qty</label></div>
                				<div class="col-md-2"><label>Total</label></div>
                				<div class="col-md-1"><label>&nbsp;</label></div>                				
                			</div>
                			<div id="cartItemsDiv"></div>
							<div>&nbsp;</div>
                			<div id="cartSummaryDisplay" class="row">Your Cart is empty</div>                		
                		</div>
                	</c:when>
                	<c:otherwise>
                		<div id="cartDisplay">
                			<div class="row" style="padding-bottom: 5px;">
                				<div class="col-md-1"><label>#</label></div>
                				<div class="col-md-3"><label>Item</label></div>
                				<div class="col-md-2"><label>Price</label></div>
                				<div class="col-md-1"><label>Qty</label></div>
                				<div class="col-md-2"><label>Total</label></div>
                				<div class="col-md-1"><label>&nbsp;</label></div>                				
                			</div>
                			<div id="cartItemsDiv">
							                			
	                		<c:forEach items="${cartItems}" var="cartItem" varStatus="loop">
	                			<c:set var="itemCount" value="${loop.index+1}"/>
	                			<div class="row" id="cartItemsDisplay">
	                				<div class="col-md-1" id="itemCount_<c:out value="${itemCount}"/>"><c:out value="${itemCount}"/>.</div>
	                				<div class="col-md-3"><c:out value="${cartItem.itemName}"/></div>
	                				<div class="col-md-2"><fmt:formatNumber type="currency" value="${cartItem.itemPrice}"/></div>
	                				<div class="col-md-1"><c:out value="${cartItem.qty}"/></div>
	                				<div class="col-md-2"><fmt:formatNumber type="currency" value="${cartItem.linePrice}"/></div>
	                				<div class="col-md-1" id="itemDelete_<c:out value='${itemCount}'/>"><a class="btn btn-sm" href="#" id="itemDeleteBtn_<c:out value='${itemCount}'/>">Remove</a></div>
	                			</div>
	                		</c:forEach>
	                		</div>
								<div>&nbsp;</div>
	      						<div class="row" id="cartSummaryDisplay">
	      						<div class="col-md-9"><label>Cart Total : </label><label>&nbsp;<fmt:formatNumber type="currency" value="${cart.totalPrice}"/></label></div>
	      						<div class="col-md-1"><a class="btn btn-primary" href="#" id="checkOutBtn">Check Out</a></div>
	      						</div>          		
                		</div>
                	</c:otherwise>
                </c:choose>	
				            	
            	<div id="loginArea" class="col-md-10" style="display:none;">
            		<div><label>Enter Customer Information</label></div>
            		<label>Name</label>
					<div>
						<input type="text" class="form-control" id="custName" placeholder="Enter Name"/>
					</div>            	
            		<label>Email (Customer Id)</label>
					<div>
						<input type="email" class="form-control" id="custEmail" placeholder="Enter Email"/>
					</div>
					<div>&nbsp;</div>
					<!-- pull-left interfered with mobile browser click event -->
					<div><a class="btn btn-primary" href="#" id="submitCustBtn">Submit</a></div>            	
 				</div>
 			</div>
			<div id="infoArea" class="col-md-7" style="display:none;">
				
			</div>
			
		<!-- end cart area -->
			
            <div class="col-md-8">

                <div class="row carousel-holder">

<!--                     <div class="col-md-12">
                        <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
                            <ol class="carousel-indicators">
                                <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                                <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                                <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                            </ol>
                            <div class="carousel-inner">
                                <div class="item active">
                                
                                    <img class="slide-image" src="http://placehold.it/800x300" alt="">
                                
                                    <img class="slide-image" src="img/red_fedora.png" alt="">
                                </div>
                                <div class="item">
                                    <img class="slide-image" src="img/tux.png" alt="">
                                </div>
                                <div class="item">
                                    <img class="slide-image" src="img/fabric.png" alt="">
                                </div>
                            </div>
                            <a class="left carousel-control" href="#carousel-example-generic" data-slide="prev">
                                <span class="glyphicon glyphicon-chevron-left"></span>
                            </a>
                            <a class="right carousel-control" href="#carousel-example-generic" data-slide="next">
                                <span class="glyphicon glyphicon-chevron-right"></span>
                            </a>
                        </div>
                    </div>
 -->
                </div>

                <div class="row">

                    <div class="col-sm-4 col-lg-4 col-md-4">
                        <div class="thumbnail">
                        	<div id="prodId_${prodId_1}" style="display: none;">${prodId_1}</div>
                            <img src="${img_1}" alt="">
                            <div class="caption">
                                <h4 class="pull-right">$<label id="prodPrice_${prodId_1}">${prodPrice_1}</label></h4>                                
                                <h4><a href="#" id="prodName_${prodId_1}">${prod_1}</a></h4>
                                <p id="prodShortDesc_${prodId_1}">${shortDesc_1}</p>
                            </div>
                            <div class="cartBtn">
							 	<div>
							    	<input type="number" min="1" data-bind="value:cartQty_${prodId_1}" step="1" class="form-control" id="cartQty_${prodId_1}" placeholder="Enter Qty" style="width: 50%;float:left;margin-right:5px;margin-left:5px;">
							    	<a class="btn btn-primary" href="#" id="cartBtn_${prodId_1}">Update</a>
							    </div>
                            </div>
                            
                            <div class="ratings">
                                <p class="pull-right">15 reviews</p>
                                <p>
                                    <span class="glyphicon glyphicon-star"></span>
                                    <span class="glyphicon glyphicon-star"></span>
                                    <span class="glyphicon glyphicon-star"></span>
                                    <span class="glyphicon glyphicon-star"></span>
                                    <span class="glyphicon glyphicon-star"></span>
                                </p>
                            </div>
                        </div>
                    </div>

                    <div class="col-sm-4 col-lg-4 col-md-4">
                        <div class="thumbnail">
                        	<div id="prodId_${prodId_2}" style="display: none;">${prodId_2}</div>
                            <img src="${img_2}" alt="">
                            <div class="caption">
                                <h4 class="pull-right">$<label id="prodPrice_${prodId_2}">${prodPrice_2}</label></h4>
                                <h4><a href="#" id="prodName_${prodId_2}">${prod_2}</a>
                                </h4>
                                <p id="prodShortDesc_${prodId_2}">${shortDesc_2}</p>
                            </div>
                            <div class="cartBtn">
							 	<div>
							    	<input type="number" min="1" data-bind="value:cartQty_${prodId_2}" step="1" class="form-control" id="cartQty_${prodId_2}" placeholder="Enter Qty" style="width: 50%;float:left;margin-right:5px;margin-left:5px;">
							    	<a class="btn btn-primary" href="#" id="cartBtn_${prodId_2}">Update</a>
							    </div>
                            </div>
                            
                            <div class="ratings">
                                <p class="pull-right">12 reviews</p>
                                <p>
                                    <span class="glyphicon glyphicon-star"></span>
                                    <span class="glyphicon glyphicon-star"></span>
                                    <span class="glyphicon glyphicon-star"></span>
                                    <span class="glyphicon glyphicon-star"></span>
                                    <span class="glyphicon glyphicon-star-empty"></span>
                                </p>
                            </div>
                        </div>
                    </div>

                    <div class="col-sm-4 col-lg-4 col-md-4">
                        <div class="thumbnail">
                            <div id="prodId_${prodId_3}" style="display: none;">${prodId_3}</div>
                            <img src="${img_3}" alt="">
                            <div class="caption">
                                <h4 class="pull-right">$<label id="prodPrice_${prodId_3}">${prodPrice_3}</label></h4>
                                <h4><a href="#" id="prodName_${prodId_3}">${prod_3}</a>
                                </h4>
                                <p id="prodShortDesc_${prodId_3}">${shortDesc_3}.</p>
                            </div>
                            <div class="cartBtn">
							 	<div>
							    	<input type="number" min="1" data-bind="value:cartQty_${prodId_3}" step="1" class="form-control" id="cartQty_${prodId_3}" placeholder="Enter Qty" style="width: 50%;float:left;margin-right:5px;margin-left:5px;">
							    	<a class="btn btn-primary" href="#" id="cartBtn_${prodId_3}">Update</a>
							    </div>
                            </div>
                            
                            <div class="ratings">
                                <p class="pull-right">31 reviews</p>
                                <p>
                                    <span class="glyphicon glyphicon-star"></span>
                                    <span class="glyphicon glyphicon-star"></span>
                                    <span class="glyphicon glyphicon-star"></span>
                                    <span class="glyphicon glyphicon-star"></span>
                                    <span class="glyphicon glyphicon-star-empty"></span>
                                </p>
                            </div>
                        </div>
                    </div>

                    <div class="col-sm-4 col-lg-4 col-md-4">
                        <div class="thumbnail">
                        	<div id="prodId_${prodId_4}" style="display: none;">${prodId_4}</div>                        
                            <img src="${img_4}" alt="">
                            <div class="caption">
                                <h4 class="pull-right">$<label id="prodPrice_${prodId_4}">${prodPrice_4}</label></h4>
                                <h4><a href="#" id="prodName_${prodId_4}">${prod_4}</a>
                                </h4>
                                <p id="prodShortDesc_${prodId_4}">${shortDesc_4}.</p>
                            </div>
                            <div class="cartBtn">
							 	<div>
							    	<input type="number" min="1" data-bind="value:cartQty_${prodId_4}" step="1" class="form-control" id="cartQty_${prodId_4}" placeholder="Enter Qty" style="width: 50%;float:left;margin-right:5px;margin-left:5px;">
							    	<a class="btn btn-primary" href="#" id="cartBtn_${prodId_4}">Update</a>
							    </div>
                            </div>
                            
                            <div class="ratings">
                                <p class="pull-right">6 reviews</p>
                                <p>
                                    <span class="glyphicon glyphicon-star"></span>
                                    <span class="glyphicon glyphicon-star"></span>
                                    <span class="glyphicon glyphicon-star"></span>
                                    <span class="glyphicon glyphicon-star-empty"></span>
                                    <span class="glyphicon glyphicon-star-empty"></span>
                                </p>
                            </div>
                        </div>
                    </div>

                    <div class="col-sm-4 col-lg-4 col-md-4">
                        <div class="thumbnail">
                        	<div id="prodId_${prodId_5}" style="display: none;">${prodId_5}</div>                        
                            <img src="${img_5}" alt="">
                            <div class="caption">
                                <h4 class="pull-right">$<label id="prodPrice_${prodId_5}">${prodPrice_5}</label></h4>
                                <h4><a href="#" id="prodName_${prodId_5}">${prod_5}</a>
                                </h4>
                                <p id="shortProdDesc_${prodId_5}">${shortDesc_5}</p>
                            </div>
                            <div class="cartBtn">
							 	<div>
							    	<input type="number" min="1" data-bind="value:cartQty_${prodId_5}" step="1" class="form-control" id="cartQty_${prodId_5}" placeholder="Enter Qty" style="width: 50%;float:left;margin-right:5px;margin-left:5px;">
							    	<a class="btn btn-primary" href="#" id="cartBtn_${prodId_5}">Update</a>
							    </div>
                            </div>
                            
                            <div class="ratings">
                                <p class="pull-right">18 reviews</p>
                                <p>
                                    <span class="glyphicon glyphicon-star"></span>
                                    <span class="glyphicon glyphicon-star"></span>
                                    <span class="glyphicon glyphicon-star"></span>
                                    <span class="glyphicon glyphicon-star"></span>
                                    <span class="glyphicon glyphicon-star-empty"></span>
                                </p>
                            </div>
                        </div>
                    </div>
<!-- 
                    <div class="col-sm-4 col-lg-4 col-md-4">
                        <h4><a href="#">Like this template?</a>
                        </h4>
                        <p>If you like this template, then check out <a target="_blank" href="http://maxoffsky.com/code-blog/laravel-shop-tutorial-1-building-a-review-system/">this tutorial</a> on how to build a working review system for your online store!</p>
                        <a class="btn btn-primary" target="_blank" href="http://maxoffsky.com/code-blog/laravel-shop-tutorial-1-building-a-review-system/">View Tutorial</a>
                    </div>
 -->
<!--                     <div class="col-sm-4 col-lg-4 col-md-4">
                        <h4><a href="#">Your Shopping Cart</a>
                        </h4>
                        <p>If you like this template, then check out <a target="_blank" href="http://maxoffsky.com/code-blog/laravel-shop-tutorial-1-building-a-review-system/">this tutorial</a> on how to build a working review system for your online store!</p>
                        <a class="btn btn-primary" target="_blank" href="http://maxoffsky.com/code-blog/laravel-shop-tutorial-1-building-a-review-system/">Check Out</a>
                    </div>
 -->                </div>

            </div>

        </div>

    </div>
    <!-- /.container -->

    <div class="container">

        <hr>

        <!-- Footer -->
        <footer>
            <div class="row">
                <div class="col-lg-12">
                    <p>Copyright &copy; Your Website 2014</p>
                </div>
            </div>
        </footer>

    </div>
    <!-- /.container -->

    <!-- jQuery -->
    <script src="js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

<script type="text/javascript">
/* function CItem(name) {
	this.name="";
}
 */

function CartItem(itemId, name, qty, price,linePrice)  {
	 	 this.itemId=itemId;
		 this.itemName=name;
		 this.qty=qty;
		 this.itemPrice=price;
		 this.linePrice=linePrice;
 }

 function Customer(name, email)  {
 	 this.name=name;
	 this.email=email;
}

 
function removeCartItem(prodId) {
	   $.ajax({
		      type: "POST",
		      //contentType : 'application/json; charset=utf-8',
		      dataType : 'json',
	 	      headers: { 
		          'Accept': 'application/json',
		          'Content-Type': 'application/json' 
		      },	      
		      url: "deleteItem.page",
		      data: prodId, // Note it is important
		      success :function(result) {
			      //alert(result.items[0].itemName);
			      var displayResult="";
			      if (result.items.length==0) {
			    	  $("#cartItemsDiv").html("Your Cart is Empty");
				      $("#cartSummaryDisplay").html("");
				  }
			      for (i=0; i<result.items.length;i++) {
				      //alert($("#cartDisplay").html());
				      //displayResult+=result.items[i].itemName+"\n";
				      displayResult+="<div class='row' id='cartItemsDisplay'> <div class='col-md-1' id='itemCount_"+(i+1)+"'>"+(i+1)+"</div>";
				      displayResult+="<div class='col-md-3'>"+result.items[i].itemName+"</div>";	
				      displayResult+="<div class='col-md-2'>$"+result.items[i].itemPrice+"</div>";
				      displayResult+="<div class='col-md-1'>"+result.items[i].qty+"</div>";				
				      displayResult+="<div class='col-md-2'>$"+result.items[i].linePrice+"</div>";				
				      displayResult+="<div class='col-md-2'><a class='btn btn-sm' href='#' id='itemDeleteBtn_"+(i+1)+"'>Remove</a></div></div>";				
				  }
			      $("#cartItemsDiv").html(displayResult);
			      $('[id^=itemDelete]').bind("click", function(e) {
					  btnClick(e);
					});		      

			      if (result.items.length>0) {
					  
				      displayResult="<div class='col-md-9'><label>Cart Total : $"+result.totalPrice+"</label></div>";
				      displayResult+="<div class='col-md-1'><a class='btn btn-primary' href='#' id='checkOutBtn'>Check Out</a></div>";
				      $("#cartSummaryDisplay").html(displayResult);
			      } else {
			    	  $("#cartSummaryDisplay").html("You Cart is empty");
				  }
			      //readyFn();
				  //alert(displayResult);
			      //alert(result.itemId+" "+result.itemName+" "+result.qty+" "+result.itemPrice+" "+result.linePrice);
			      //alert('success');
			       //alert(Object.keys(result));
			       //alert(result.qty);
		     },
		     error: function(err) {
		    	 alert('fail '+err.status);
		    	 alert(err.responseText);
				alert(Object.keys(err));
				
			     }
		  });	

}
function updateCart(cartItem) {
	   $.ajax({
	      type: "POST",
	      //contentType : 'application/json; charset=utf-8',
	      dataType : 'json',
 	      headers: { 
	          'Accept': 'application/json',
	          'Content-Type': 'application/json' 
	      },	      
	      url: "updateCart.page",
	      data: JSON.stringify(cartItem), // Note it is important
	      success :function(result) {
		      //alert(result.items[0].itemName);
		      var displayResult="";
		      for (i=0; i<result.items.length;i++) {
			      //alert($("#cartDisplay").html());
			      //displayResult+=result.items[i].itemName+"\n";
			      displayResult+="<div class='row' id='cartItemsDisplay'> <div class='col-md-1' id='itemCount_"+(i+1)+"'>"+(i+1)+"</div>";
			      displayResult+="<div class='col-md-3'>"+result.items[i].itemName+"</div>";	
			      displayResult+="<div class='col-md-2'>$"+result.items[i].itemPrice+"</div>";
			      displayResult+="<div class='col-md-1'>"+result.items[i].qty+"</div>";				
			      displayResult+="<div class='col-md-2'>$"+result.items[i].linePrice+"</div>";				
			      displayResult+="<div class='col-md-2'><a class='btn btn-sm' href='#' id='itemDeleteBtn_"+(i+1)+"'>Remove</a></div></div>";				
			  }
		      $("#cartItemsDiv").html(displayResult);
		      $('[id^=itemDelete]').bind("click", function(e) {
				  btnClick(e);
				});		      
			  //alert(displayResult);
		      displayResult="<div class='col-md-9'><label>Cart Total : $"+result.totalPrice+"</label></div>";
		      displayResult+="<div class='col-md-1'><a class='btn btn-primary' href='#' id='checkOutBtn'>Check Out</a></div>";
		      $("#cartSummaryDisplay").html(displayResult);
		  		$( "#checkOutBtn" ).bind("click", function(e) {
				  btnClick(e);
				});
		      //readyFn();
			  //alert(displayResult);
		      //alert(result.itemId+" "+result.itemName+" "+result.qty+" "+result.itemPrice+" "+result.linePrice);
		      //alert('success');
		       //alert(Object.keys(result));
		       //alert(result.qty);
	     },
	     error: function(err) {
	    	 alert('fail '+err.status);
	    	 alert(err.responseText);
			alert(Object.keys(err));
			
		     }
	  });
}

function checkOut(custName, custEmail) {

		var cust=new Customer(custName, custEmail);
		
	   $.ajax({
		      type: "POST",
		      //contentType : 'application/json; charset=utf-8',
		      dataType : 'json',
	 	      headers: { 
		          'Accept': 'application/json',
		          'Content-Type': 'application/json' 
		      },	      
		      url: "checkOut.page",
		      data: JSON.stringify(cust), // Note it is important
		      success :function(result) {
			      //alert('Checkout Successful, please click on Orders to view your purchase history');
			      $('#infoArea').html('<label>Checkout Successful, please click on <a href="viewOrders.page">View Orders</a> to view your purchase history</label>');
			      $('#infoArea').show();
		    	  $("#cartItemsDiv").html("Your Cart is Empty");
			      $("#cartSummaryDisplay").html("");
			      $('#loginArea').hide();
		      },
		      error: function(err) {
			      alert('error');
			  }
	   });
	
}
function btnClick(event){
	$('#infoArea').hide();
	var targetId=event.target.id;
	//alert('clicked '+targetId);
	var cartItem;
	//filter cartBtn
	//alert(targetId.substring(0,8));
	var prodId;
	if (targetId.substring(0,7)=='cartBtn') {
		prodId=targetId.substring(targetId.length-1);
		cartItem=createCartItem(prodId);
		updateCart(cartItem);	
		
	} 
 	else if (targetId.substring(0,10)=='itemDelete') {
		//alert('delete item-'+targetId.substring(14)+'-');
		removeCartItem(targetId.substring(14));
	} 
 	else if (targetId.substring(0,8)=='checkOut') {
		
		$('#checkOutBtn').addClass('disabled');
		$('[id^=itemDelete]').addClass('disabled');
		
		$('#loginArea').show();
		
	}
 	else if (targetId.substring(0,13)=='submitCustBtn') {
 	 	//alert(targetId);
		var custName=$('#custName').val();
		var custEmail=$('#custEmail').val();
		checkOut(custName, custEmail);
 	}
	//alert(targetId.substring(targetId.length-1));
/* 	if (targetId=='cartBtn_1') {
		//alert($("#cartQty_1").val());
		//cartItem.itemId=$("#prodId_1").html();
		//cartItem.qty=$("#cartQty_1").val();
		cartItem=createCartItem(1);
	}	
	updateCart(cartItem);
 */		
}

function createCartItem(prodId) {
	//$("#cartQty_1").val();
	//$("#prodPrice_1").html();
	//$("#prod_1").html();
	var pName=$("#prodName_"+prodId).html();
	var pQty=$("#cartQty_"+prodId).val();
	var pPrice=$("#prodPrice_"+prodId).html();
	var item=new CartItem(prodId,pName,pQty,pPrice,0);
	//alert(item.name+" "+item.qty+" "+item.price);
	return item;
	
}
	
function readyFn( jQuery ) {
	    
	$( ".btn" ).bind("click", function(e) {
		  btnClick(e);
		}); 
/* 
	var c1=new CItem("c1");
	c1.name="c1";
	var c2=new CItem("c2");
	c2.name="c4";
	alert(c1.name);
	alert(JSON.stringify(c2));    */
}
 
$( document ).ready( readyFn );
</script>
</body>

</html>
