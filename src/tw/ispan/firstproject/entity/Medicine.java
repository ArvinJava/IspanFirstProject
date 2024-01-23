package tw.ispan.firstproject.entity;

import java.util.Date;

public class Medicine {
	private int id;
	private Date date;
	private String name;
	private String licenseCode;
	private String supplyStatus;
	
	public Medicine() {
		
	}
	
	public Medicine( int id, String name) {
		this.id = id;
		this.name = name;
		
	}
	
	public Medicine(int id, Date date, String name, String licenseCode, String supplyStatus) {
		
		this.id = id;
		this.date = date;
		this.name = name;
		this.licenseCode = licenseCode;
		this.supplyStatus = supplyStatus;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLicenseCode() {
		return licenseCode;
	}
	public void setLicenseCode(String licenseCode) {
		this.licenseCode = licenseCode;
	}
	public String getSupplyStatus() {
		return supplyStatus;
	}
	public void setSupplyStatus(String supplyStatus) {
		this.supplyStatus = supplyStatus;
	}
	

}
