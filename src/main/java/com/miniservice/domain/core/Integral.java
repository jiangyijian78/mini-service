package com.miniservice.domain.core;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name="integral")
public class Integral {
	public Integral() {}
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long userId;
    /**
     * 积分
     */
    @Column
    private Long integral;

    @CreatedDate
    @Column
    private Date createTime;

    @LastModifiedDate
    @Column
    private Date updateTime;

    public static final class Builder {
        private Long id;
        private Long userId;
        private Long integral;
        private Date createTime;
        private Date updateTime;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setUserId(Long userId) {
            this.userId = userId;
            return this;
        }

        public Builder setIntegral(Long integral) {
            this.integral = integral;
            return this;
        }

        public Builder setCreateTime(Date createTime) {
            this.createTime = createTime;
            return this;
        }

        public Builder setUpdateTime(Date updateTime) {
            this.updateTime = updateTime;
            return this;
        }

        public Integral build() {
            return new Integral(this);
        }
    }

    private Integral(Builder builder) {
        this.id = builder.id;
        this.userId = builder.userId;
        this.integral = builder.integral;
        this.createTime = builder.createTime;
        this.updateTime = builder.updateTime;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getIntegral() {
        return integral;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    @Override
    public String toString() {
        return "Integral{" +
                "id=" + id +
                ", userId=" + userId +
                ", integral=" + integral +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
