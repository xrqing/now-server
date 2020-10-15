package com.entity.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * @Description: 角色表 sys_role
 * @auther:
 * @date: 2020/9/12 21:29
 */
@Data
@TableName("sys_role")
public class SysRole {

    /**
     * 主键
     */
    @Id
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 管理员数量
     */
    @TableField("admin_count")
    private Integer adminCount;

    /**
     * 创建时间
     */
    @TableField("create_time")
    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    /**
     * 启用状态：0->禁用；1->启用
     */
    private Integer status;
}
