package com.miniservice.domain.core;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="check_in")
public class CheckIn {
	public CheckIn() {}
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long userId;

    /**
     * 登录的小程序id
     */
    @Column
    private String formId;
    @Column
    private Date checkInTime;
    @Column
    private Date createTime;

    private CheckIn(Builder builder) {
        this.checkInTime = builder.checkInTime;
        this.formId = builder.formId;
        this.createTime = new Date();
        this.userId = builder.userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(Date checkInTime) {
        this.checkInTime = checkInTime;
    }

    public static class Builder {
        private Long userId;

        private String formId;
        private Date checkInTime;

        public Builder userId(Long userId) {
            this.userId = userId;
            return this;
        }

        public Builder formId(String formId) {
            this.formId = formId;
            return this;
        }

        public Builder checkInTime(Date checkInTime) {
            this.checkInTime = checkInTime;
            return this;
        }

        public CheckIn build() {
            return new CheckIn(this);
        }
    }

}
