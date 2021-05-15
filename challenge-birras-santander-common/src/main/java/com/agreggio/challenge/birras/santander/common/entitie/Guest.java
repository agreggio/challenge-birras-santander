package com.agreggio.challenge.birras.santander.common.entitie;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="GUEST",uniqueConstraints={
        @UniqueConstraint(columnNames = {"MEET_UP_ID", "USER_ID"})
})
@EntityListeners(AuditingEntityListener.class)
public class Guest extends AuditableEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="ID")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "MEET_UP_ID")
    private MeetUp meetUp;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "USER_ID")
    private User user;


    @Column(name="CHECK_IN")
    boolean checkIn;

    public MeetUp getMeetUp() {
        return meetUp;
    }

    public void setMeetUp(MeetUp meetUp) {
        this.meetUp = meetUp;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isCheckIn() {
        return checkIn;
    }

    public void setCheckIn(boolean checkIn) {
        this.checkIn = checkIn;
    }
}
