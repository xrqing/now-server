package com.entity.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * @Description: 管理员角色关系表 sys_admin_role_relation
 * @auther: xrq
 * @date: 2020/9/12 21:12
 */
@Data
@TableName("sys_admin_role_relation")
public class SysAdminRoleRelation {

    /**
     * 主键
     */
    @Id
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 管理员id
     */
    @TableField("admin_id")
    private Integer adminId;

    /**
     * 角色id
     */
    @TableField("role_id")
    private Integer roleId;
}
