//立即执行加载模式框
$(function() {
    $("#left-error-dialog").dialog({
        title: "错误提示",
        modal: "true", //对话框外可操作，true 对话框会遮罩一层灰纱，无法操作。
        show: "clip", //动画效果
        hide: "drop",
        autoOpen: false, //隐藏
        resizable: false, //大小不可改变
        draggable: false, //位置不可变
    });
    $("#right-error-dialog").dialog({
        title: "错误提示",
        modal: "true", //对话框外可操作，true 对话框会遮罩一层灰纱，无法操作。
        show: "clip", //动画效果
        hide: "drop",
        autoOpen: false, //隐藏
        resizable: false, //大小不可改变
        draggable: false, //位置不可变
    });
    $("#right-insert-error-dialog").dialog({
        title: "错误提示",
        modal: "true", //对话框外可操作，true 对话框会遮罩一层灰纱，无法操作。
        show: "clip", //动画效果
        hide: "drop",
        autoOpen: false, //隐藏
        resizable: false, //大小不可改变
        draggable: false, //位置不可变
    });
    $("#repeat-error-dialog").dialog({
        title: "错误提示",
        modal: "true", //对话框外可操作，true 对话框会遮罩一层灰纱，无法操作。
        show: "clip", //动画效果
        hide: "drop",
        autoOpen: false, //隐藏
        resizable: false, //大小不可改变
        draggable: false, //位置不可变
    });
});
//插入新服务
$("#add-submit").click(function(){
	let service_name = $("#add-name").val();
	if(service_name=="" || $.trim(service_name).length==0){
		alert("输入为空");
	}else{
		$.post("PCHservlet",{s_name:service_name},function(pchr){
			if(pchr == "你插入的服务已存在"){
				alert("你插入的服务已存在");
			}else{
				console.log(pchr);
				var result =JSON.parse(pchr);
				var res="";
				   for(var i=0,l=result.length;i<l;i++){
			           res+=" <div class=\"a-service\"><div class=\"a-service-name\" onclick=\"click_chick(this)\">";
			           res+=result[i]['s_name'];
			           res+="<span style=\"display: none;\" class=\"delete-it\">";
			           res+=result[i]['s_id'];
			           res+="</span></div>";
			           if(result[i]['s_choice']==0){
			        	   res+="<button class=\"a-service-btn\" onclick=\"delete_service(this)\">删除<span style=\"display: none;\" class=\"delete-it\">";
			        	   res+=result[i]['s_id'];
			        	   res+="</span></button>";
			           }else{
			        	   res+="<button class=\"a-service-btnno\"></button>";
			           }
			           res+="</div>";
				   }
				   $("#service-lists").html(res);
			}
			
  		  });
	}
});
//删除服务

function delete_service(obj){
	let service_id = $(obj).children(".delete-it").text();
	console.log(service_id);
	$.post("PCHdelete",{s_id:service_id},function(pchr){
	   console.log(pchr);
		var result =JSON.parse(pchr);
	   var res="";
	   for(var i=0,l=result.length;i<l;i++){
           res+=" <div class=\"a-service\"><div class=\"a-service-name\" onclick=\"click_chick(this)\">";
           res+=result[i]['s_name'];
           res+="<span style=\"display: none;\" class=\"delete-it\">";
           res+=result[i]['s_id'];
           res+="</span></div>";
           if(result[i]['s_choice']==0){
        	   res+="<button class=\"a-service-btn\" onclick=\"delete_service(this)\">删除<span style=\"display: none;\" class=\"delete-it\">";
        	   res+=result[i]['s_id'];
        	   res+="</span></button>";
           }else{
        	   res+="<button class=\"a-service-btnno\"></button>";
           }
           res+="</div>";
	   }
	   $("#service-lists").html(res);

		  });
}

//选择左边的服务
function click_chick(obj) {
    if ($(obj).hasClass('a-service-chicked')) {
        $(obj).removeClass("a-service-chicked");
    } else {
        $(obj).addClass("a-service-chicked");
    }
}
//添加按钮事件
$("#add-btn").click(function() {
    // $("#left-error-dialog").dialog("open")
    let s_array = new Array();
    let t_array = new Array();
    let starray = new Array();
    $(".a-service").children(".a-service-chicked").each(function(index, domEle) {
        // domEle == this 
        var insert_s_id = $(domEle).children(".delete-it").text();
        s_array.push(insert_s_id);
        // $(domEle).css("backgroundColor", "yellow");
    });
    $(".the-oneservice").each(function(index, domEle) {
        if ($(domEle)[0].checked == true) {
            var insert_t_id = $(domEle)[0].value;
            t_array.push(insert_t_id);
        } else {}
    });
    if (s_array.length == 0) {
        $("#left-error-dialog").dialog("open")
    } else if (t_array.length == 0) {
    	 $("#right-insert-error-dialog").dialog("open")
    }else{
    	//得到二维数组starray  starray[1][0]=5 表示服务器1 与 5 有联系
        for (var i = 0; i < t_array.length; i++) {
            let tid = t_array[i];
            starray[tid] = new Array();
            for (var j = 0; j < s_array.length; j++) {
                starray[tid].push(s_array[j]);
            }
        }
        $.post("PCHrelationInsert",{relation_array:JSON.stringify(starray)},function(pchr){
        	var flag = pchr.substr(0,5);
        	if(flag=="你所添加的"){
        		$("#repeat-error-dialog").text(pchr);
        		$("#repeat-error-dialog").dialog("open");
        	}else{
        		var result =JSON.parse(pchr);
        		var container = result.container;
        		var service = result.service;
//        		console.log(container);
//        		console.log(service);
//        		console.log(container.length);
//        		console.log(service.length);
        		var res="";
        		   for(var i=0,l=service.length;i<l;i++){
        	           res+=" <div class=\"a-service\"><div class=\"a-service-name\" onclick=\"click_chick(this)\">";
        	           res+=service[i]['s_name'];
        	           res+="<span style=\"display: none;\" class=\"delete-it\">";
        	           res+=service[i]['s_id'];
        	           res+="</span></div>";
        	           if(service[i]['s_choice']==0){
        	        	   res+="<button class=\"a-service-btn\" onclick=\"delete_service(this)\">删除<span style=\"display: none;\" class=\"delete-it\">";
        	        	   res+=service[i]['s_id'];
        	        	   res+="</span></button>";
        	           }else{
        	        	   res+="<button class=\"a-service-btnno\"></button>";
        	           }
        	           res+="</div>";
        		   }
        		   $("#service-lists").html(res);
        		   //服务器
        		   var res="";
        		   for (var k =0;k<container.length;k++) {
        			   var temp = container[k];
//        			   console.log(temp);
        			   console.log(temp.sonServices);
        			    res+="<div class=\"a-the-service\"><input type=\"checkbox\" name=\"one-service\" class=\"the-oneservice\" value=\"";
        			    res+=temp.t_id;
        			    res+="\">";
        			    res+=temp.t_name;
        			    for(var i=-1;i<temp.sonServices.length;i++){
        			    	if(i==-1){
        			    		res+="<div class=\"one-service-name\">原始服务1</div><div class=\"one-service-name\">原始服务2</div>";
        			    	}else{
        			    		res+="<div class=\"one-service-name\" onclick=\"click_chick(this)\">";
        			    		res+=temp.sonServices[i].s_name;
        			    		res+="<span style=\"display: none;\" class=\"delete-it\"";
        			    		res+=temp.sonServices[i].s_id;
        			    		res+="</span><span style=\"display: none;\" class=\"delete-it2\">";
        			    		res+=temp.t_id;
        			    		res+="</span></div>";
        			    	}
        			    }
        			    res+="</div>";
        			}
        		   $("#content-right").html(res);
        	}
        })
    }
    
})
//卸载按钮事件
$("#delete-btn").click(function() {
	let st_array = new Array();
	for(var i=0;i<2;i++){
		st_array[i] = new Array();
	}
	//被选中了颜色
    $(".a-the-service").children(".a-service-chicked").each(function(index, domEle) {
        // domEle == this 
        var delete_s_id = $(domEle).children(".delete-it").text();
        var delete_t_id = $(domEle).children(".delete-it2").text();
        if(delete_t_id == 1){
        	st_array[0].push(delete_s_id);
        }else{
        	st_array[1].push(delete_s_id);
        }
        
    });
    if(st_array[0].length == 0&&st_array[1].length == 0){
    	 $("#right-error-dialog").dialog("open");
    }else{
    	$.post("PCHrelationSelete",{relation_array:JSON.stringify(st_array)},function(pchr){
    		var result =JSON.parse(pchr);
    		var container = result.container;
    		var service = result.service;
    		var res="";
    		   for(var i=0,l=service.length;i<l;i++){
    	           res+=" <div class=\"a-service\"><div class=\"a-service-name\" onclick=\"click_chick(this)\">";
    	           res+=service[i]['s_name'];
    	           res+="<span style=\"display: none;\" class=\"delete-it\">";
    	           res+=service[i]['s_id'];
    	           res+="</span></div>";
    	           if(service[i]['s_choice']==0){
    	        	   res+="<button class=\"a-service-btn\" onclick=\"delete_service(this)\">删除<span style=\"display: none;\" class=\"delete-it\">";
    	        	   res+=service[i]['s_id'];
    	        	   res+="</span></button>";
    	           }else{
    	        	   res+="<button class=\"a-service-btnno\"></button>";
    	           }
    	           res+="</div>";
    		   }
    		   $("#service-lists").html(res);
    		   //服务器
    		   var res="";
    		   for (var k =0;k<container.length;k++) {
    			   var temp = container[k];
//    			   console.log(temp.sonServices);
    			    res+="<div class=\"a-the-service\"><input type=\"checkbox\" name=\"one-service\" class=\"the-oneservice\" value=\"";
    			    res+=temp.t_id;
    			    res+="\">";
    			    res+=temp.t_name;
    			    for(var i=-1;i<temp.sonServices.length;i++){
    			    	if(i==-1){
    			    		res+="<div class=\"one-service-name\">原始服务1</div><div class=\"one-service-name\">原始服务2</div>";
    			    	}else{
    			    		res+="<div class=\"one-service-name\" onclick=\"click_chick(this)\">";
    			    		res+=temp.sonServices[i].s_name;
    			    		res+="<span style=\"display: none;\" class=\"delete-it\">";
    			    		res+=temp.sonServices[i].s_id;
    			    		res+="</span><span style=\"display: none;\" class=\"delete-it2\">";
    			    		res+=temp.t_id;
    			    		res+="</span></div>";
    			    	}
    			    }
    			    res+="</div>";
    			}
    		   $("#content-right").html(res);
    	});
    }
   
})