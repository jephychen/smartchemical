function goCart(productId, quantity){
	var cartForm = document.getElementById("cart_form");
	cartForm.method="post";
	cartForm.action="Cart.action?productId=" + productId + "&quantity=" + quantity;
	cartForm.submit();
}