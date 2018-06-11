$(document).ready(function() {
    InitOffCanvasNavigation();
});



function InitOffCanvasNavigation() {
	$('#btn-nav').on({
		click: function() {
			$('body').toggleClass('nav-open');
		}
	})
}





















