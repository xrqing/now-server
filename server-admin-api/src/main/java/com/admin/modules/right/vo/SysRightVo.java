package com.admin.modules.right.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @Description: 权限资源 vo
 * @auther: xrq
 * @date: 2020/10/14 11:46
 */
@Data
public class SysRightVo {

    /**
     * 主键 id
     */
    private Integer id;

    /**
     * 创建时间
     */
    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    /**
     * 名称
     */
    private String name;

    /**
     * 资源URL
     */
    private String url;

    /**
     * 描述
     */
    private String description;

    /**
     * 权限资源分类ID
     */
    private Integer categoryId;

    /**
     * 上级名称
     */
    private String categoryName;
}
