package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 * 论文信息：(PaperInformation)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "PaperInformation")
public class PaperInformation implements Serializable {

    // PaperInformation编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "paper_information_id")
    private Integer paper_information_id;

    // 论文题目
    @Basic
    private String thesis_title;
    // 指导教师
    @Basic
    private Integer instructor;
    // 论文类型
    @Basic
    private String paper_type;
    // 上传学生
    @Basic
    private Integer upload_students;
    // 上传时间
    @Basic
    private Timestamp upload_time;
    // 论文附件
    @Basic
    private String paper_attachment;
    // 论文评优
    @Basic
    private String thesis_evaluation;
    // 老师意见
    @Basic
    private String teachers_opinion;
    // 论文评语
    @Basic
    private String paper_comments;














    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
