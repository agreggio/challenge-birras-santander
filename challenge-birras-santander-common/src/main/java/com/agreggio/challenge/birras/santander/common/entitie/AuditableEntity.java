package com.agreggio.challenge.birras.santander.common.entitie;


import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.sql.Timestamp;

@MappedSuperclass
public class AuditableEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @CreatedDate
    @Column(name = "CREATE_DATE")
    private Timestamp createDate;

    @Column(name = "DELETE_DATE")
    private Timestamp deleteDate;

    @LastModifiedDate
    @Column(name = "LAST_UPDATE")
    private Timestamp lastUpdate;


    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public Timestamp getDeleteDate() {
        return deleteDate;
    }

    public void setDeleteDate(Timestamp deleteDate) {
        this.deleteDate = deleteDate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

}