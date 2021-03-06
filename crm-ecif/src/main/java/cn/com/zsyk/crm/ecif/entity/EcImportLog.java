package cn.com.zsyk.crm.ecif.entity;

import java.io.Serializable;
import java.util.Date;

public class EcImportLog implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ec_import_log.import_cd
     *
     * @mbggenerated
     */
    private String importCd;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ec_import_log.import_time
     *
     * @mbggenerated
     */
    private Date importTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ec_import_log.import_obj_typ
     *
     * @mbggenerated
     */
    private String importObjTyp;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ec_import_log.file_nam
     *
     * @mbggenerated
     */
    private String fileNam;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ec_import_log.file_typ
     *
     * @mbggenerated
     */
    private String fileTyp;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ec_import_log.import_sts
     *
     * @mbggenerated
     */
    private String importSts;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ec_import_log.file_ttl_row
     *
     * @mbggenerated
     */
    private Integer fileTtlRow;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ec_import_log.succeed_qty
     *
     * @mbggenerated
     */
    private Integer succeedQty;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ec_import_log.failure_qty
     *
     * @mbggenerated
     */
    private Integer failureQty;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ec_import_log.create_date
     *
     * @mbggenerated
     */
    private String createDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ec_import_log.create_time
     *
     * @mbggenerated
     */
    private String createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ec_import_log.create_user
     *
     * @mbggenerated
     */
    private String createUser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ec_import_log.update_date
     *
     * @mbggenerated
     */
    private String updateDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ec_import_log.update_time
     *
     * @mbggenerated
     */
    private String updateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ec_import_log.update_user
     *
     * @mbggenerated
     */
    private String updateUser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ec_import_log.time_stamp
     *
     * @mbggenerated
     */
    private Date timeStamp;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ec_import_log.rec_stat
     *
     * @mbggenerated
     */
    private String recStat;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table ec_import_log
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ec_import_log.import_cd
     *
     * @return the value of ec_import_log.import_cd
     *
     * @mbggenerated
     */
    public String getImportCd() {
        return importCd;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ec_import_log.import_cd
     *
     * @param importCd the value for ec_import_log.import_cd
     *
     * @mbggenerated
     */
    public void setImportCd(String importCd) {
        this.importCd = importCd;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ec_import_log.import_time
     *
     * @return the value of ec_import_log.import_time
     *
     * @mbggenerated
     */
    public Date getImportTime() {
        return importTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ec_import_log.import_time
     *
     * @param importTime the value for ec_import_log.import_time
     *
     * @mbggenerated
     */
    public void setImportTime(Date importTime) {
        this.importTime = importTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ec_import_log.import_obj_typ
     *
     * @return the value of ec_import_log.import_obj_typ
     *
     * @mbggenerated
     */
    public String getImportObjTyp() {
        return importObjTyp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ec_import_log.import_obj_typ
     *
     * @param importObjTyp the value for ec_import_log.import_obj_typ
     *
     * @mbggenerated
     */
    public void setImportObjTyp(String importObjTyp) {
        this.importObjTyp = importObjTyp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ec_import_log.file_nam
     *
     * @return the value of ec_import_log.file_nam
     *
     * @mbggenerated
     */
    public String getFileNam() {
        return fileNam;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ec_import_log.file_nam
     *
     * @param fileNam the value for ec_import_log.file_nam
     *
     * @mbggenerated
     */
    public void setFileNam(String fileNam) {
        this.fileNam = fileNam;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ec_import_log.file_typ
     *
     * @return the value of ec_import_log.file_typ
     *
     * @mbggenerated
     */
    public String getFileTyp() {
        return fileTyp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ec_import_log.file_typ
     *
     * @param fileTyp the value for ec_import_log.file_typ
     *
     * @mbggenerated
     */
    public void setFileTyp(String fileTyp) {
        this.fileTyp = fileTyp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ec_import_log.import_sts
     *
     * @return the value of ec_import_log.import_sts
     *
     * @mbggenerated
     */
    public String getImportSts() {
        return importSts;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ec_import_log.import_sts
     *
     * @param importSts the value for ec_import_log.import_sts
     *
     * @mbggenerated
     */
    public void setImportSts(String importSts) {
        this.importSts = importSts;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ec_import_log.file_ttl_row
     *
     * @return the value of ec_import_log.file_ttl_row
     *
     * @mbggenerated
     */
    public Integer getFileTtlRow() {
        return fileTtlRow;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ec_import_log.file_ttl_row
     *
     * @param fileTtlRow the value for ec_import_log.file_ttl_row
     *
     * @mbggenerated
     */
    public void setFileTtlRow(Integer fileTtlRow) {
        this.fileTtlRow = fileTtlRow;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ec_import_log.succeed_qty
     *
     * @return the value of ec_import_log.succeed_qty
     *
     * @mbggenerated
     */
    public Integer getSucceedQty() {
        return succeedQty;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ec_import_log.succeed_qty
     *
     * @param succeedQty the value for ec_import_log.succeed_qty
     *
     * @mbggenerated
     */
    public void setSucceedQty(Integer succeedQty) {
        this.succeedQty = succeedQty;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ec_import_log.failure_qty
     *
     * @return the value of ec_import_log.failure_qty
     *
     * @mbggenerated
     */
    public Integer getFailureQty() {
        return failureQty;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ec_import_log.failure_qty
     *
     * @param failureQty the value for ec_import_log.failure_qty
     *
     * @mbggenerated
     */
    public void setFailureQty(Integer failureQty) {
        this.failureQty = failureQty;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ec_import_log.create_date
     *
     * @return the value of ec_import_log.create_date
     *
     * @mbggenerated
     */
    public String getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ec_import_log.create_date
     *
     * @param createDate the value for ec_import_log.create_date
     *
     * @mbggenerated
     */
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ec_import_log.create_time
     *
     * @return the value of ec_import_log.create_time
     *
     * @mbggenerated
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ec_import_log.create_time
     *
     * @param createTime the value for ec_import_log.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ec_import_log.create_user
     *
     * @return the value of ec_import_log.create_user
     *
     * @mbggenerated
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ec_import_log.create_user
     *
     * @param createUser the value for ec_import_log.create_user
     *
     * @mbggenerated
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ec_import_log.update_date
     *
     * @return the value of ec_import_log.update_date
     *
     * @mbggenerated
     */
    public String getUpdateDate() {
        return updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ec_import_log.update_date
     *
     * @param updateDate the value for ec_import_log.update_date
     *
     * @mbggenerated
     */
    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ec_import_log.update_time
     *
     * @return the value of ec_import_log.update_time
     *
     * @mbggenerated
     */
    public String getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ec_import_log.update_time
     *
     * @param updateTime the value for ec_import_log.update_time
     *
     * @mbggenerated
     */
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ec_import_log.update_user
     *
     * @return the value of ec_import_log.update_user
     *
     * @mbggenerated
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ec_import_log.update_user
     *
     * @param updateUser the value for ec_import_log.update_user
     *
     * @mbggenerated
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ec_import_log.time_stamp
     *
     * @return the value of ec_import_log.time_stamp
     *
     * @mbggenerated
     */
    public Date getTimeStamp() {
        return timeStamp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ec_import_log.time_stamp
     *
     * @param timeStamp the value for ec_import_log.time_stamp
     *
     * @mbggenerated
     */
    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ec_import_log.rec_stat
     *
     * @return the value of ec_import_log.rec_stat
     *
     * @mbggenerated
     */
    public String getRecStat() {
        return recStat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ec_import_log.rec_stat
     *
     * @param recStat the value for ec_import_log.rec_stat
     *
     * @mbggenerated
     */
    public void setRecStat(String recStat) {
        this.recStat = recStat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ec_import_log
     *
     * @mbggenerated
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        EcImportLog other = (EcImportLog) that;
        return (this.getImportCd() == null ? other.getImportCd() == null : this.getImportCd().equals(other.getImportCd()))
            && (this.getImportTime() == null ? other.getImportTime() == null : this.getImportTime().equals(other.getImportTime()))
            && (this.getImportObjTyp() == null ? other.getImportObjTyp() == null : this.getImportObjTyp().equals(other.getImportObjTyp()))
            && (this.getFileNam() == null ? other.getFileNam() == null : this.getFileNam().equals(other.getFileNam()))
            && (this.getFileTyp() == null ? other.getFileTyp() == null : this.getFileTyp().equals(other.getFileTyp()))
            && (this.getImportSts() == null ? other.getImportSts() == null : this.getImportSts().equals(other.getImportSts()))
            && (this.getFileTtlRow() == null ? other.getFileTtlRow() == null : this.getFileTtlRow().equals(other.getFileTtlRow()))
            && (this.getSucceedQty() == null ? other.getSucceedQty() == null : this.getSucceedQty().equals(other.getSucceedQty()))
            && (this.getFailureQty() == null ? other.getFailureQty() == null : this.getFailureQty().equals(other.getFailureQty()))
            && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser()))
            && (this.getUpdateDate() == null ? other.getUpdateDate() == null : this.getUpdateDate().equals(other.getUpdateDate()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getUpdateUser() == null ? other.getUpdateUser() == null : this.getUpdateUser().equals(other.getUpdateUser()))
            && (this.getTimeStamp() == null ? other.getTimeStamp() == null : this.getTimeStamp().equals(other.getTimeStamp()))
            && (this.getRecStat() == null ? other.getRecStat() == null : this.getRecStat().equals(other.getRecStat()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ec_import_log
     *
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getImportCd() == null) ? 0 : getImportCd().hashCode());
        result = prime * result + ((getImportTime() == null) ? 0 : getImportTime().hashCode());
        result = prime * result + ((getImportObjTyp() == null) ? 0 : getImportObjTyp().hashCode());
        result = prime * result + ((getFileNam() == null) ? 0 : getFileNam().hashCode());
        result = prime * result + ((getFileTyp() == null) ? 0 : getFileTyp().hashCode());
        result = prime * result + ((getImportSts() == null) ? 0 : getImportSts().hashCode());
        result = prime * result + ((getFileTtlRow() == null) ? 0 : getFileTtlRow().hashCode());
        result = prime * result + ((getSucceedQty() == null) ? 0 : getSucceedQty().hashCode());
        result = prime * result + ((getFailureQty() == null) ? 0 : getFailureQty().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
        result = prime * result + ((getUpdateDate() == null) ? 0 : getUpdateDate().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getUpdateUser() == null) ? 0 : getUpdateUser().hashCode());
        result = prime * result + ((getTimeStamp() == null) ? 0 : getTimeStamp().hashCode());
        result = prime * result + ((getRecStat() == null) ? 0 : getRecStat().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ec_import_log
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", importCd=").append(importCd);
        sb.append(", importTime=").append(importTime);
        sb.append(", importObjTyp=").append(importObjTyp);
        sb.append(", fileNam=").append(fileNam);
        sb.append(", fileTyp=").append(fileTyp);
        sb.append(", importSts=").append(importSts);
        sb.append(", fileTtlRow=").append(fileTtlRow);
        sb.append(", succeedQty=").append(succeedQty);
        sb.append(", failureQty=").append(failureQty);
        sb.append(", createDate=").append(createDate);
        sb.append(", createTime=").append(createTime);
        sb.append(", createUser=").append(createUser);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateUser=").append(updateUser);
        sb.append(", timeStamp=").append(timeStamp);
        sb.append(", recStat=").append(recStat);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}