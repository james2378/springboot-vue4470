package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 * 选题信息：(TopicInformation)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "TopicInformation")
public class TopicInformation implements Serializable {

    // TopicInformation编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "topic_information_id")
    private Integer topic_information_id;

    // 论文题目
    @Basic
    private String thesis_title;
    // 指导教师
    @Basic
    private Integer instructor;
    // 题目类型
    @Basic
    private String question_type;
    // 选题学生
    @Basic
    private Integer selected_students;
    // 选题时间
    @Basic
    private Timestamp topic_selection_time;
    // 题目状态
    @Basic
    private String question_status;
    // 选题状态
    @Basic
    private String topic_status;
    // 选题备注
    @Basic
    private String topic_notes;














    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
