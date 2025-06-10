package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 * 答辩信息：(DefenseInformation)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "DefenseInformation")
public class DefenseInformation implements Serializable {

    // DefenseInformation编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "defense_information_id")
    private Integer defense_information_id;

    // 论文题目
    @Basic
    private String thesis_title;
    // 论文类型
    @Basic
    private String paper_type;
    // 答辩学生
    @Basic
    private Integer defense_student;
    // 答辩时间
    @Basic
    private Timestamp defense_time;
    // 答辩地点
    @Basic
    private String venue_of_defense;
    // 答辩须知
    @Basic
    private String defense_notice;
    // 答辩状态
    @Basic
    private String defense_status;
    // 答辩结果
    @Basic
    private String defense_results;














    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
