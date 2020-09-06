var App={
    ProductDTO: function(productId, name) {
        this.productId = productId  ;
        this.name = name;
    },
    CartItemDTO: function(productId,qty)  {
       this.product = new App.ProductDTO(productId);
       this.qty = qty;
    },
    log: function(msg) {
        console.log("App: "+msg);
    },
    addToCart: function() {
        var inputQtyList = $('.inputQty').toArray();
        var items = [];
        for (var i = 0; i < inputQtyList.length; i++) {
            var eleId = inputQtyList[i].id;
            var qty = $('#'+eleId).val();
            //get id
            if (qty > 0 ) {
                var productId = eleId.substr(eleId.indexOf('_')+1, eleId.length-1);
                var item = new App.CartItemDTO(productId,qty);
                items.push(item);
                this.log('product id '+ productId + ' => '+ qty);
    
            }
        }
        var data = JSON.stringify(items);
        this.log(data);
        //test
        //var p = new App.ProductDTO("p0001") ;
        $.ajax({
            type: "POST",
            //contentType : 'application/json; charset=utf-8',
            dataType : 'json',
             headers: { 
                'Accept': 'application/json',
                'Content-Type': 'application/json' 
            },	      
            url: "updateCart.page",
            data: data,
            success :function(result) {
                App.log('Success '+result);
                $("#alertMsgContent").html("Items Added to Cart");
                $('.inputQty').val(0);
                $('#viewCartLink').html('View Cart['+result.length+']');
                $("#alertMsg").show();

            },
            error: function(error) {
                App.log('Error '+error);
            }
        })        

    },
    doLogin: function() {
        this.log($('#inputEmail').val()+ ' '+ $('#inputPassword').val());
        $('#loginForm').attr('action', '/home.page');
        $('#loginForm').submit();

    },
    doCheckout: function() {
        this.log("Checkout");
        $('#checkOutSection').show();
    },
    doConfirmCheckout: function() {
        this.log("Confirm Checkout");
        this.log($('#deliveryAddress').val()+ ' '+ $('#contactInfo').val());
        $('#checkoutForm').attr('action', '/checkout.page');
        $('#checkoutForm').submit();
    },
    deleteCart: function() {
        this.log("calling delete cart ");
        $.ajax({
            type: "GET",	      
            url: "viewCart.page",
            dataType: "text",
            data: "deleteCart=true",
            success :function(result) {
                App.log('Success '+result);
                $("#alertMsgContent").html("Cart Deleted, please refresh the page");
                $("#alertMsg").show();
                $('#cart_info').hide();
                $('#nocart_msg').show();

            },
            error: function(error) {
                App.log('Error '+error);
            }
        })            
    },
    handleEvent(targetId) {
        if (targetId == 'btn-submit') {
            this.addToCart();
        } else if (targetId == 'btn-login') {
            this.doLogin();
        } else if (targetId == 'btn-checkout') {
            this.doCheckout();
        } else if (targetId == 'btn-confirm-checkout') {
            this.doConfirmCheckout();
        } else if (targetId == 'btn-delete-cart') {
            this.deleteCart();
        }
    }
};
/**
 *         
 inputQtyList.array.forEach(element, index => {
    var i = index + 1;
    this.log($('#qty_'+i).id +':'+$('#qty_'+i).val());            
});
* 
 * 
 */