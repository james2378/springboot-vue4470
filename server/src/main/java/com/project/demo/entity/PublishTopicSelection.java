package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 * 发布选题：(PublishTopicSelection)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "PublishTopicSelection")
public class PublishTopicSelection implements Serializable {

    // PublishTopicSelection编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "publish_topic_selection_id")
    private Integer publish_topic_selection_id;

    // 发布学生
    @Basic
    private Integer release_students;
    // 学生姓名
    @Basic
    private String student_name;
    // 发布时间
    @Basic
    private Timestamp release_time;
    // 论文题目
    @Basic
    private String thesis_title;
    // 题目类型
    @Basic
    private String question_type;
    // 题目附件
    @Basic
    private String title_attachment;
    // 题目详情
    @Basic
    private String question_details;
    // 选题状态
    @Basic
    private String topic_status;
    // 题目状态
    @Basic
    private String question_status;














    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
