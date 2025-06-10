package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 * 论文选题：(ThesisTopicSelection)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "ThesisTopicSelection")
public class ThesisTopicSelection implements Serializable {

    // ThesisTopicSelection编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "thesis_topic_selection_id")
    private Integer thesis_topic_selection_id;

    // 论文题目
    @Basic
    private String thesis_title;
    // 指导教师
    @Basic
    private Integer instructor;
    // 封面图片
    @Basic
    private String cover_photo;
    // 教师姓名
    @Basic
    private String teachers_name;
    // 发布时间
    @Basic
    private Timestamp release_time;
    // 题目类型
    @Basic
    private String question_type;
    // 题目附件
    @Basic
    private String title_attachment;
    // 论文详情
    @Basic
    private String paper_details;

    // 点击数
    @Basic
    private Integer hits;

    // 点赞数
    @Basic
    private Integer praise_len;












    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
