$(document).ready(function(e){

	$('#menu-toggle').click(function(e) {
		e.preventDefault();
		$('#wrapper').toggleClass('toggled');
	});

	$('#create_btn').on('click',function(e){

		e.preventDefault();
		var name = $('#create_name').val();
		var pass = $('#create_pass').val();

		$.post("Create_class",{name:name,pass:pass},function(data){
			$('#create_form').prepend('<p id="create_pre" >'+data+'</p>');
		});      

	});

	$('#join_btn').on('click',function(e){

		e.preventDefault();

		var name = $('#join_name').val();
		var pass = $('#join_pass').val();

		$.post("Join_class",{name:name,pass:pass},function(data){	
			$('#join_form').prepend('<p id="join_pre">'+data+'</p>');
			
		});

	});

	$('#create').on('hidden.bs.modal',function(){
		$('#create_pre').remove();
		$('#create_name').val("");
		$('#create_pass').val("");

	});

	$('#join').on('hidden.bs.modal',function(){
		$('#join_pre').remove();
		$('#join_name').val("");
		$('#join_pass').val("");

	});
	
	$('#join_name').focus(function(){
		$('#join_pre').remove();
	});
	$('#join_pass').focus(function(){
		$('#join_pre').remove();
	});
	$('#create_name').focus(function(){
		$('#create_pre').remove();
	});
	$('#create_pass').focus(function(){
		$('#create_pre').remove();
	});
	

});