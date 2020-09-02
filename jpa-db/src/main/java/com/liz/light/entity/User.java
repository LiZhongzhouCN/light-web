package com.liz.light.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author liz
 * @Description:
 * @date: 2020/7/8 15:33
 */
@Data
@Entity
@Table(name = "t_user")
//@DynamicUpdate
public class User implements Serializable {

    @Id
    /**
     * postgres
     */
//    @SequenceGenerator(sequenceName = "t_user_sequence",name = "t_user_seq")
//    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "t_user_seq")
    /**
     * mysql
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private Integer age;

    @CreationTimestamp
    private Timestamp createTime;

    @UpdateTimestamp
    private Timestamp updateTime;
}
