package com.hjt.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.hjt.node.INode;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jerry <br/>
 * @Time： 2021-10-18 <br/>
 */
@Data
public class DeptUserVO implements INode<DeptUserVO> {
    private static final long serialVersionUID = 1L;
    /**
     * 主键ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 父节点ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long parentId;

    /**
     * 子孙节点
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<DeptUserVO> children;

    /**
     * 是否有子孙节点
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Boolean hasChildren;


    @Override
    public List<DeptUserVO> getChildren() {
        if (this.children == null) {
            this.children = new ArrayList<>();
        }
        return this.children;
    }


    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 父节点ID
     */
    private Integer deptId;

    /**
     * 上级部门ID
     */
    private Integer parentDeptId;

    /**
     * 部门名称
     */
    private String deptName;


    /**
     * 上级部门ID
     */
    private String parentDeptName;

    /**
     * 用户别名
     */
    private String nickName;

    /**
     * 岗位名称
     */
    private String postName;


    private String postCode;
}
