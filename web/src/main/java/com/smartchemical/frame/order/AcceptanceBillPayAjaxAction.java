/**
 * 
 */
package com.smartchemical.frame.order;

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
import com.smartchemical.common.util.CopyFileUtil;
import com.smartchemical.common.util.PoNoGenerator;
import com.smartchemical.entities.frame.user.ScUser;

/**
 * @author Jephy
 *
 */
@SuppressWarnings("deprecation")
public class AcceptanceBillPayAjaxAction extends ActionSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private File acceptanceBillFile;
	
	private String acceptanceBillFileContentType;
	
	private String acceptanceBillFileFileName;
	
	private String poNo;
	
	private int seq;
	
	private File productPicFile;
	
	private String productPicFileContentType;
	
	private String productPicFileFileName;
	
	private File companyPicFile;
	
	private String companyPicFileContentType;
	
	private String companyPicFileFileName;

	private InputStream inputStream;
	
	public File getAcceptanceBillFile() {
		return acceptanceBillFile;
	}

	public void setAcceptanceBillFile(File acceptanceBillFile) {
		this.acceptanceBillFile = acceptanceBillFile;
	}

	public String getAcceptanceBillFileContentType() {
		return acceptanceBillFileContentType;
	}

	public void setAcceptanceBillFileContentType(
			String acceptanceBillFileContentType) {
		this.acceptanceBillFileContentType = acceptanceBillFileContentType;
	}

	public String getAcceptanceBillFileFileName() {
		return acceptanceBillFileFileName;
	}

	public void setAcceptanceBillFileFileName(String acceptanceBillFileFileName) {
		this.acceptanceBillFileFileName = acceptanceBillFileFileName;
	}

	public String getPoNo() {
		return poNo;
	}

	public void setPoNo(String poNo) {
		this.poNo = poNo;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public File getProductPicFile() {
		return productPicFile;
	}

	public void setProductPicFile(File productPicFile) {
		this.productPicFile = productPicFile;
	}

	public String getProductPicFileContentType() {
		return productPicFileContentType;
	}

	public void setProductPicFileContentType(String productPicFileContentType) {
		this.productPicFileContentType = productPicFileContentType;
	}

	public String getProductPicFileFileName() {
		return productPicFileFileName;
	}

	public void setProductPicFileFileName(String productPicFileFileName) {
		this.productPicFileFileName = productPicFileFileName;
	}

	public File getCompanyPicFile() {
		return companyPicFile;
	}

	public void setCompanyPicFile(File companyPicFile) {
		this.companyPicFile = companyPicFile;
	}

	public String getCompanyPicFileContentType() {
		return companyPicFileContentType;
	}

	public void setCompanyPicFileContentType(String companyPicFileContentType) {
		this.companyPicFileContentType = companyPicFileContentType;
	}

	public String getCompanyPicFileFileName() {
		return companyPicFileFileName;
	}

	public void setCompanyPicFileFileName(String companyPicFileFileName) {
		this.companyPicFileFileName = companyPicFileFileName;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	
	public String uploadCompanyLicenseFile() throws Exception {
		ActionContext ctx = ActionContext.getContext();
		ScUser user = (ScUser) ctx.getSession().get("user");
		
		String rootTemp = ServletActionContext.getServletContext().getRealPath("/upload/company-license-temp");
		String rootSave = ServletActionContext.getServletContext().getRealPath(PathConstant.COMPANY_LICENSE_SAVE_FOLDER + CopyFileUtil.makeCompanyLicensePath(user));
		
		File rootDir = new File(rootSave);
		if (!rootDir.exists() || !rootDir.isDirectory()){
			rootDir.mkdir();
		}
		
		InputStream is = new FileInputStream(acceptanceBillFile);
		String licensePicNameTemp = user.getUserId() + "_" + System.currentTimeMillis() + 
				acceptanceBillFileFileName.substring(acceptanceBillFileFileName.lastIndexOf("."));
		String billPicNameSave = PoNoGenerator.makeCompanyLicensePicName(user.getUserId(), seq) + 
				acceptanceBillFileFileName.substring(acceptanceBillFileFileName.lastIndexOf("."));
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
		setInputStream(new StringBufferInputStream(PathConstant.PROJECTNAME + "/upload/company-license-temp/" + destFileTemp.getName()));
		return SUCCESS;
	}
	
	public String uploadFile() throws Exception {
		ActionContext ctx = ActionContext.getContext();
		ScUser user = (ScUser) ctx.getSession().get("user");
		String rootTemp = ServletActionContext.getServletContext().getRealPath("/upload/acceptance-bill-temp");
		String rootSave = ServletActionContext.getServletContext().getRealPath(PathConstant.ACBILL_SAVE_FOLDER + poNo);
		
		//保存承兑汇票的文件夹，以订单号为文件夹名
		File rootDir = new File(rootSave);
		if (!rootDir.exists() || !rootDir.isDirectory()){
			rootDir.mkdir();
		}
		
		InputStream is = new FileInputStream(acceptanceBillFile);
		String billPicNameTemp = poNo + "_" + System.currentTimeMillis() + 
				acceptanceBillFileFileName.substring(acceptanceBillFileFileName.lastIndexOf("."));
		String billPicNameSave = PoNoGenerator.makeAcceptanceBillPicName(user.getUserId(), poNo, seq) + 
				acceptanceBillFileFileName.substring(acceptanceBillFileFileName.lastIndexOf("."));
        File destFileTemp = new File(rootTemp, billPicNameTemp);
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
		setInputStream(new StringBufferInputStream(PathConstant.PROJECTNAME + "/upload/acceptance-bill-temp/" + destFileTemp.getName()));
		return SUCCESS;
	}
	
	public String uploadRechargeAcFile() throws Exception {
		ActionContext ctx = ActionContext.getContext();
		ScUser user = (ScUser) ctx.getSession().get("user");
		String rootTemp = ServletActionContext.getServletContext().getRealPath("/upload/acceptance-bill-temp");
		String rootSave = ServletActionContext.getServletContext().getRealPath(PathConstant.RECHARGE_ACBILL_SAVE_FOLDER + "recharge-" +poNo);
		
		//保存承兑汇票的文件夹，以订单号为文件夹名
		File rootDir = new File(rootSave);
		if (!rootDir.exists() || !rootDir.isDirectory()){
			rootDir.mkdir();
		}
		
		InputStream is = new FileInputStream(acceptanceBillFile);
		String billPicNameTemp = poNo + "_" + System.currentTimeMillis() + 
				acceptanceBillFileFileName.substring(acceptanceBillFileFileName.lastIndexOf("."));
		String billPicNameSave = PoNoGenerator.makeAcceptanceBillPicName(user.getUserId(), poNo, seq) + 
				acceptanceBillFileFileName.substring(acceptanceBillFileFileName.lastIndexOf("."));
        File destFileTemp = new File(rootTemp, billPicNameTemp);
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
		setInputStream(new StringBufferInputStream(PathConstant.PROJECTNAME + "/upload/acceptance-bill-temp/" + destFileTemp.getName()));
		return SUCCESS;
	}
	
	public String uploadProductPic() throws Exception {
		String rootSave = ServletActionContext.getServletContext().getRealPath(PathConstant.PRODUCT_PIC_TEMP_FOLDER);
		
		File rootDir = new File(rootSave);
		if (!rootDir.exists() || !rootDir.isDirectory()){
			rootDir.mkdir();
		}
		
		InputStream is = new FileInputStream(productPicFile);
		String billPicNameSave = System.currentTimeMillis() + 
				productPicFileFileName.substring(productPicFileFileName.lastIndexOf("."));
        File destFileSave = new File(rootSave, billPicNameSave);
        
        OutputStream osSave = new FileOutputStream(destFileSave);
        byte[] buffer = new byte[400];
        int length  = 0 ;
        while((length = is.read(buffer))>0){
        	osSave.write(buffer, 0, length);
        }
        is.close();
        osSave.close();
		setInputStream(new StringBufferInputStream(PathConstant.PROJECTNAME + PathConstant.PRODUCT_PIC_TEMP_FOLDER + destFileSave.getName()));
		return SUCCESS;
	}
	
	public String uploadCompanyPic() throws Exception {
		String rootSave = ServletActionContext.getServletContext().getRealPath(PathConstant.COMPANY_PIC_TEMP_FOLDER);
		
		File rootDir = new File(rootSave);
		if (!rootDir.exists() || !rootDir.isDirectory()){
			rootDir.mkdir();
		}
		
		InputStream is = new FileInputStream(companyPicFile);
		String billPicNameSave = System.currentTimeMillis() + 
				companyPicFileFileName.substring(companyPicFileFileName.lastIndexOf("."));
        File destFileSave = new File(rootSave, billPicNameSave);
        
        OutputStream osSave = new FileOutputStream(destFileSave);
        byte[] buffer = new byte[400];
        int length  = 0 ;
        while((length = is.read(buffer))>0){
        	osSave.write(buffer, 0, length);
        }
        is.close();
        osSave.close();
		setInputStream(new StringBufferInputStream(PathConstant.PROJECTNAME + PathConstant.COMPANY_PIC_TEMP_FOLDER + destFileSave.getName()));
		return SUCCESS;
	}
	
	public String removeUpload() throws Exception {
		ActionContext ctx = ActionContext.getContext();
		ScUser user = (ScUser) ctx.getSession().get("user");
		String rootSave = ServletActionContext.getServletContext().getRealPath(PathConstant.ACBILL_SAVE_FOLDER + poNo);
		File rootDir = new File(rootSave);
		if (rootDir.isDirectory()){
			File[] files = rootDir.listFiles();
			if (files == null || files.length == 0){
				setInputStream(new StringBufferInputStream("failed"));
				return SUCCESS;
			}
			for (File file : files){
				if (file.isFile() && file.getName().contains(PoNoGenerator.makeAcceptanceBillPicName(user.getUserId(), poNo, seq) + ".")){
					file.delete();
				}
			}
		}
		else {
			setInputStream(new StringBufferInputStream("failed"));
			return SUCCESS;
		}
		setInputStream(new StringBufferInputStream("success"));
		return SUCCESS;
	}
	
	public String removeUploadRechargeAcFile() throws Exception {
		ActionContext ctx = ActionContext.getContext();
		ScUser user = (ScUser) ctx.getSession().get("user");
		String rootSave = ServletActionContext.getServletContext().getRealPath(PathConstant.RECHARGE_ACBILL_SAVE_FOLDER + "recharge-" + poNo);
		File rootDir = new File(rootSave);
		if (rootDir.isDirectory()){
			File[] files = rootDir.listFiles();
			if (files == null || files.length == 0){
				setInputStream(new StringBufferInputStream("failed"));
				return SUCCESS;
			}
			for (File file : files){
				if (file.isFile() && file.getName().contains(PoNoGenerator.makeAcceptanceBillPicName(user.getUserId(), poNo, seq) + ".")){
					file.delete();
				}
			}
		}
		else {
			setInputStream(new StringBufferInputStream("failed"));
			return SUCCESS;
		}
		setInputStream(new StringBufferInputStream("success"));
		return SUCCESS;
	}
	
	public String execute() throws Exception {
		return SUCCESS;
	}
	
}
