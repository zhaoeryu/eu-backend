package cn.eu.system.model.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author zhaoeryu
 * @since 2023/8/1
 */
@Data
public class AssignRoleDto {

    @NotEmpty(message = "用户id不能为空")
    private List<String> userIds;
    @NotNull(message = "角色id不能为空")
    private Integer roleId;

}
