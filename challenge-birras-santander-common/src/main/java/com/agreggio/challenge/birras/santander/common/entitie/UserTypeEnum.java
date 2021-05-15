package com.agreggio.challenge.birras.santander.common.entitie;

public enum UserTypeEnum {

    ADMIN(1l),
    USER(2l);

    private Long userTypeId;

    UserTypeEnum(Long userTypeId) {
        this.userTypeId = userTypeId;
    }

    public Long getUserTypeId() {
        return userTypeId;
    }


}
