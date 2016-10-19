/**
 * 
 */
package com.smartchemical.common.fileupload;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringBufferInputStream;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.smartchemical.common.constant.PathConstant;
import com.smartchemical.common.util.PoNoGenerator;
import com.smartchemical.entities.frame.user.ScUser;

/**
 * @author Jephy
 *
 */
@SuppressWarnings("deprecation")
public class FileUploadAjaxAction extends ActionSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private File fileUploadSc;
	
	private String fileUploadScContentType;
	
	private String fileUploadScFileName;

	private int seq;
	
	private InputStream inputStream;

	public File getFileUploadSc() {
		return fileUploadSc;
	}

	public void setFileUploadSc(File fileUploadSc) {
		this.fileUploadSc = fileUploadSc;
	}

	public String getFileUploadScContentType() {
		return fileUploadScContentType;
	}

	public void setFileUploadScContentType(String fileUploadScContentType) {
		this.fileUploadScContentType = fileUploadScContentType;
	}

	public String getFileUploadScFileName() {
		return fileUploadScFileName;
	}

	public void setFileUploadScFileName(String fileUploadScFileName) {
		this.fileUploadScFileName = fileUploadScFileName;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	
	public String uploadFile() throws Exception {
		ActionContext ctx = ActionContext.getContext();
		ScUser user = (ScUser) ctx.getSession().get("user");
		
		String rootTemp = ServletActionContext.getServletContext().getRealPath("/upload/company-license-temp");
		String rootSave = ServletActionContext.getServletContext().getRealPath(PathConstant.COMPANY_LICENSE_SAVE_FOLDER + user.getUserId());
		
		File rootDir = new File(rootSave);
		if (!rootDir.exists() || !rootDir.isDirectory()){
			rootDir.mkdir();
		}
		
		InputStream is = new FileInputStream(fileUploadSc);
		String licensePicNameTemp = user.getUserId() + "_" + System.currentTimeMillis() + 
				fileUploadScFileName.substring(fileUploadScFileName.lastIndexOf("."));
		String billPicNameSave = PoNoGenerator.makeCompanyLicensePicName(user.getUserId(), seq) + 
				fileUploadScFileName.substring(fileUploadScFileName.lastIndexOf("."));
        File destFileTemp = new File(rootTemp, licensePicNameTemp);
        File destFileSave = new File(rootSave, billPicNameSave);
        
        OutputStream osTemp = new FileOutputStream(destFileTemp);
        OutputStream osSave = new FileOutputStream(destFileSave);
        byte[] buffer = new byte[400];
        int length  = 0 ;
        while((length = is.read(buffer))>0){
        	osTemp.write(buffer, 0, length);
        	osSave.write(buffer, 0, length);
        }
        is.close();
        osTemp.close();
        osSave.close();
		setInputStream(new StringBufferInputStream("/smartchemical-web/upload/company-license-temp/" + destFileTemp.getName()));
		return SUCCESS;
	}

	public String execute() throws Exception {
		return SUCCESS;
	}

}
