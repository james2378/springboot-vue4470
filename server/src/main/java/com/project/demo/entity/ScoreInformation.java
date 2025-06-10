package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 * 成绩信息：(ScoreInformation)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "ScoreInformation")
public class ScoreInformation implements Serializable {

    // ScoreInformation编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "score_information_id")
    private Integer score_information_id;

    // 论文题目
    @Basic
    private String thesis_title;
    // 题目类型
    @Basic
    private String question_type;
    // 答辩学生
    @Basic
    private Integer defense_student;
    // 发布时间
    @Basic
    private Timestamp release_time;
    // 评奖评优
    @Basic
    private String award_and_excellence_evaluation;
    // 成绩详情
    @Basic
    private String score_details;














    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
