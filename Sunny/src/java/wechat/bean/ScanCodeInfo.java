package wechat.bean;

import javax.xml.bind.annotation.XmlElement;


public class ScanCodeInfo{
	private String scanType;//  扫描类型，一般是qrcode  
	private String scanResult ; 
	@XmlElement(name="ScanType")
	public String getScanType() {
		return scanType;
	}
	public void setScanType(String scanType) {
		this.scanType = scanType;
	}
	@XmlElement(name="ScanResult")
	public String getScanResult() {
		return scanResult;
	}
	public void setScanResult(String scanResult) {
		this.scanResult = scanResult;
	}


}
