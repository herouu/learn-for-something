package top.alertcode.trainhigh.common.domian;

import java.io.Serializable;
import java.util.Date;

public class KdsChapterPoint implements Serializable {

  private Long id;

  private Long parentId;

  private Long chapterId;

  private String pointNo;

  private String pointName;

  private Integer status;

  private Date createTime;

  private Date updateTime;

  private Byte isDelete;

  private String pointDesc;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getParentId() {
    return parentId;
  }

  public void setParentId(Long parentId) {
    this.parentId = parentId;
  }

  public Long getChapterId() {
    return chapterId;
  }

  public void setChapterId(Long chapterId) {
    this.chapterId = chapterId;
  }

  public String getPointNo() {
    return pointNo;
  }

  public void setPointNo(String pointNo) {
    this.pointNo = pointNo == null ? null : pointNo.trim();
  }

  public String getPointName() {
    return pointName;
  }

  public void setPointName(String pointName) {
    this.pointName = pointName == null ? null : pointName.trim();
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public Date getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(Date updateTime) {
    this.updateTime = updateTime;
  }

  public Byte getIsDelete() {
    return isDelete;
  }

  public void setIsDelete(Byte isDelete) {
    this.isDelete = isDelete;
  }

  public String getPointDesc() {
    return pointDesc;
  }

  public void setPointDesc(String pointDesc) {
    this.pointDesc = pointDesc == null ? null : pointDesc.trim();
  }
}