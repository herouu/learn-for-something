package com.github.herouu.trainhigh.common.domian;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
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

  @Override
  public String toString() {
    return "KdsChapterPoint{" +
        "id=" + id +
        ", parentId=" + parentId +
        ", chapterId=" + chapterId +
        ", pointNo='" + pointNo + '\'' +
        ", pointName='" + pointName + '\'' +
        ", status=" + status +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        ", isDelete=" + isDelete +
        ", pointDesc='" + pointDesc + '\'' +
        '}';
  }
}
