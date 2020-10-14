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
 * @Description: 菜单 sys_menu
 * @auther: xrq
 * @date: 2020/10/9 9:55
 */
@Data
@TableName("sys_menu")
public class SysMenu {

    /**
     * 主键 id
     */
    @Id
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 父级ID
     */
    @TableField("parent_id")
    private Integer parentId;

    /**
     * 创建时间
     */
    @TableField("create_time")
    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    /**
     * 菜单名称
     */
    @TableField("menu_name")
    private String menuName;

    /**
     * 菜单路径
     */
    private String path;

    /**
     * 菜单级数
     */
    private Integer level;

    /**
     * 菜单排序
     */
    private Integer sort;

    /**
     * 前端隐藏 (1-否 2-是)
     */
    private Integer hidden;
}
