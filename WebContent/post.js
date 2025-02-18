$(document).ready(function(e){

	$('#post').on('shown.bs.modal',function(e){

		var x = e.relatedTarget.id;
		var title=$('#'+x).html();

		$.ajax({

			type: "GET",
			url: "Post?id="+e.relatedTarget.id,
			success: function(data){
				$('#post .modal-title').html(title);
				$('#post .modal-body').html(data.answer);
			}

		});

	});

	$('#btn_post').on('click',function(e){
	
		e.preventDefault();
		var title,post,class_id;
		title = $('#title_box').val();
		post = $('#post_box').val();
		class_id = $('#topic_box').val();

		$.post("Newpost",{title:title,post:post,class_id:class_id},function(data){	
			$('#post_form').prepend('<p id="post_pre">'+data+'</p>');
	
		});

	});

	$('#new_post').on('hidden.bs.modal',function(data){

		$('#post_pre').remove();
		$('#title').val("");
		$('#post').val("");
		$('#topic').val("");

	});

	$('#title').focus(function(){
		$('#post_pre').remove();
	});
	$('#post').focus(function(){
		$('#post_pre').remove();
	});
	$('#topic').focus(function(){
		$('#post_pre').remove();
	});

});

