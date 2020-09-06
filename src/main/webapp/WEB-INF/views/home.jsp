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

	<jsp:include page="nav.jsp"/>

    <!--main container-->
    <div class="container">
        <!--c:out value="${fn:length(PRODUCTS)}"/-->
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
            <div class="col col-8 bg-dark text-white">
                Item
            </div>
            <div class="col col-1 bg-dark text-white">
                Price
            </div>
            <div class="col col-2 bg-dark text-white">
                Qty
            </div>
        </div>
        <c:forEach items="${PRODUCTS}" var="product">

            <div class="row">
                <div class="col col-1">
                    <c:set var="count" value="${count + 1}" scope="page"/>
                    <c:out value = "${count}"/>
                </div>
                <div class="col col-8">
                    <div class="row">
                        <div class="col col-sm-9">
                            <div class="h5">${product.name}: ${product.shortDescription}</div> 
                            <p class="text-justify text-primary"> ${product.description}</p>
                        </div>
                        <div class="col col-sm-3">
                            <img src="${product.img}" class="img-thumbnail"/>
                        </div>
                    </div>
                </div>
                <div class="col col-1">
                    <div class="h5">${product.price}</div>
                </div>
                <div class="col col-2">
                    <div>
                        <input type="number" value="0" min="0" max="50" step="1" id="qty_${product.productId}" class="inputQty"/>
                    </div>
                </div>
            </div>      
        </c:forEach>
        
        <div class="row">
            <div class="col">
                <div class="float-right">
                    <button id="btn-submit" type="button" class="btn btn-outline-primary">Add To Cart</button>
                </div>
            </div>
        </div>
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
