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
var imgIconId;
var is1Success = false;
var is2Success = false;
var is3Success = false;
function uploadCompanyLicense(formIdStr, seq){
	var options ={   
		url:"AcceptanceBillAjax_uploadCompanyLicenseFile.action?seq=" + seq,  
		type:'post',                    
		data:null,
		success:fillUploadCallback,
		error:errorCallback
    };
	imgIconId = 'register_pad_img' + seq;
	var form = $("#" + formIdStr);
	form.ajaxSubmit(options);
}

function fillUploadCallback(data){
	var img1 = document.getElementById(imgIconId);
	img1.setAttribute("src", data);
	if (imgIconId == 'register_pad_img1'){
		is1Success = true;
	}
	else if (imgIconId == 'register_pad_img2'){
		is2Success = true;
	}
	else if (imgIconId == 'register_pad_img3'){
		is3Success = true;
	}
}

function errorCallback(){
	if (imgIconId == 'register_pad_img1'){
		is1Success = false;
	}
	else if (imgIconId == 'register_pad_img2'){
		is2Success = false;
	}
	else if (imgIconId == 'register_pad_img3'){
		is3Success = false;
	}
}

function submit_upload(){
	if (!is1Success && !is2Success && !is3Success){
		alert("请上传至少一个扫描件");
		return;
	}
	var subForm = document.getElementById("register_pad_upload_submit_form");
	subForm.method="post";
	subForm.action="Register1Submit.action";
	subForm.submit();
}