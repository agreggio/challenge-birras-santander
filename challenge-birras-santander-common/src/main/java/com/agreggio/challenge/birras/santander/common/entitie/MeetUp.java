package com.agreggio.challenge.birras.santander.common.entitie;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="MEET_UP")
@EntityListeners(AuditingEntityListener.class)
public class MeetUp extends AuditableEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="ID")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name="DIRECTION", nullable = false)
    private String direction;

    @Column(name = "EVENT_DATE", unique=true, nullable = false)
    @Temporal(TemporalType.DATE)
    private Date eventDate;

    @Column(name="AMOUNT_BEER", nullable = false)
    private Long amountBeer;

    @OneToMany(mappedBy = "meetUp")
    Set<Guest> guestSet;

    public Set<Guest> getGuestSet() {
        return guestSet;
    }

    public void setGuestSet(Set<Guest> guestSet) {
        this.guestSet = guestSet;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public Long getAmountBeer() {
        return amountBeer;
    }

    public void setAmountBeer(Long amountBeer) {
        this.amountBeer = amountBeer;
    }
}
