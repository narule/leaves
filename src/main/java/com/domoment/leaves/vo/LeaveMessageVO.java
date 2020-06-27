package com.domoment.leaves.vo;

/**
 * description 留言实体类
 */
public class LeaveMessageVO extends BaseVO {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//标题
	private String title;
	
	//类别
	private String category;
	
	//父id
	private Integer parentId;
	
	//附件
	private String attachment;
	
	//简述
	private String description;
	
	//内容
	private String content;
	
	//给谁
	private String toWhom;
	
	// 预留信息
	private String reservedInformation;
	
	//查看凭证
	private String certificate;

	//留名
	private String leaveName;

	//是否公开
	private Integer _public;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getToWhom() {
		return toWhom;
	}

	public void setToWhom(String toWhom) {
		this.toWhom = toWhom;
	}

	public String getReservedInformation() {
		return reservedInformation;
	}

	public void setReservedInformation(String reservedInformation) {
		this.reservedInformation = reservedInformation;
	}

	public String getCertificate() {
		return certificate;
	}

	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}

	public String getLeaveName() {
		return leaveName;
	}

	public void setLeaveName(String leaveName) {
		this.leaveName = leaveName;
	}

	public Integer get_public() {
		return _public;
	}

	public void set_public(Integer _public) {
		this._public = _public;
	}

	

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	
}
