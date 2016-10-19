var uploadSuccessCount = 0;

function checkFile(obj){
	var fileSize = 0;
    var isIE = /msie/i.test(navigator.userAgent) && !window.opera;            
    if (isIE && !obj.files) {          
         var filePath = obj.value;            
         var fileSystem = new ActiveXObject("Scripting.FileSystemObject");   
         var file = fileSystem.GetFile (filePath);               
         fileSize = file.Size;         
    }else {  
         fileSize = obj.files[0].size;     
    }
	if (fileSize > 5242880){
		obj.value = "";
		alert("上传的文件应小于5M");
		return;
	}
	var fileName = obj.files[0].name;
	var dotPosition = fileName.lastIndexOf(".");
	var extendName = fileName.substr(dotPosition);
	if (extendName != ".bmp" && extendName != ".png" && extendName != ".gif" && extendName != ".jpeg" && extendName != ".jpg"
			&& extendName != ".BMP" && extendName != ".PNG" && extendName != ".GIF" && extendName != ".JPEG" && extendName != ".JPG"){
		obj.value = "";
		alert("上传的图片格式不正确。支持图片格式为bmp, png, gif, jpeg, jpg");
		return;
	}
}

var imgIconId = 'acceptance_bill_img1';
var sequence = 1;

function uploadAcceptanceBillImg(poNo, formIdStr, uploadIdStr, imgIconIdStr, seq){
	if (document.getElementById(uploadIdStr).value == ""){
		return;
	}
	imgIconId = imgIconIdStr;
	var options ={   
		url:"AcceptanceBillAjax_uploadFile.action?poNo=" + poNo + "&seq=" + seq,  
		type:'post',                    
		data:null,
		success:fillUploadCallback,
		error:errorCallback
    };
	var form = $("#" + formIdStr);
	form.ajaxSubmit(options);
}

function fillUploadCallback(data){
	var img1 = document.getElementById(imgIconId);
	img1.setAttribute("src", data);
	uploadSuccessCount++;
}

function errorCallback(){
	
}

function continue_upload(acceptancebillUploaddivId){
	document.getElementById(acceptancebillUploaddivId).className="pay_pad_acceptancebill_item";
}

function remove_upload(poNo, formIdStr, seq){
	var options ={   
		url:"AcceptanceBillAjax_removeUpload.action?poNo=" + poNo + "&seq=" + seq,  
		type:'post',                    
		data:null,
		success:removeUploadCallback,
		error:errorRemoveCallback
    };
	sequence = seq;
	var form = $("#" + formIdStr);
	form.ajaxSubmit(options);
}

function removeUploadCallback(data){
	document.getElementById("acceptancebill_uploaddiv" + sequence).className="pay_pad_acceptancebill_item_hidden";
	var img1 = document.getElementById("acceptance_bill_img_icon" + sequence);
	img1.setAttribute("src", "");
	uploadSuccessCount--;
}

function errorRemoveCallback(data){
	
}


function submitToCheck(poNo){
	if (uploadSuccessCount < 1){
		alert("请先上传银行承兑汇票扫描件");
		return;
	}
	var billFileUploadForm = document.getElementById("pay_pad_acceptancebill_submit_form");
	billFileUploadForm.method="post";
	billFileUploadForm.action="AcceptanceBillPaySubmit.action?poNo=" + poNo;
	billFileUploadForm.submit();
}