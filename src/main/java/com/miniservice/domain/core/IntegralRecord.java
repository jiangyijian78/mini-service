package com.miniservice.domain.core;

import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name="integral_record")
public class IntegralRecord {
	public IntegralRecord() {}
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long userId;
    /**
     * 积分增量
     */
    @Column
    private Long integralIncrease;

    @Column
    private String reason;

    @LastModifiedDate
    @Column
    private Date updateTime;

    @Column
    private Integer type;

    public static final class Builder {
        private Long id;
        private Long userId;
        private Long integralIncrease;
        private String reason;
        private Date updateTime;
        private Integer type;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setUserId(Long userId) {
            this.userId = userId;
            return this;
        }

        public Builder setIntegralIncrease(Long integralIncrease) {
            this.integralIncrease = integralIncrease;
            return this;
        }

        public Builder setReason(String reason) {
            this.reason = reason;
            return this;
        }

        public Builder setUpdateTime(Date updateTime) {
            this.updateTime = updateTime;
            return this;
        }

        public Builder setType(Integer type) {
            this.type = type;
            return this;
        }

        public IntegralRecord build() {
            return new IntegralRecord(this);
        }
    }

    private IntegralRecord(Builder builder) {
        this.id = builder.id;
        this.userId = builder.userId;
        this.integralIncrease = builder.integralIncrease;
        this.reason = builder.reason;
        this.updateTime = builder.updateTime;
        this.type = builder.type;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getIntegralIncrease() {
        return integralIncrease;
    }

    public String getReason() {
        return reason;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public Integer getType() {
        return type;
    }

    @Override
    public String toString() {
        return "IntegralRecord{" +
                "id=" + id +
                ", userId=" + userId +
                ", integralIncrease=" + integralIncrease +
                ", reason='" + reason + '\'' +
                ", updateTime=" + updateTime +
                ", type=" + type +
                '}';
    }
}
