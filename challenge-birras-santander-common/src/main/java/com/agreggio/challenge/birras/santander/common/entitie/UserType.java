package com.agreggio.challenge.birras.santander.common.entitie;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="USER_TYPE")
@EntityListeners(AuditingEntityListener.class)
public class UserType extends AuditableEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="ID")
    private Long id;

    @Column(name="VALUE", nullable = false)
    private String value;


    public UserType() {

    }

    public UserType(Long id, String value) {
        this.id = id;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String type) {
        this.value = type;
    }
}
