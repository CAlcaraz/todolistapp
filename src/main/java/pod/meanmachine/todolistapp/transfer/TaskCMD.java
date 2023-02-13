package pod.meanmachine.todolistapp.transfer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "Task Command", description = "Task Command object to perform CRUD operations")
public class TaskCMD {

    @ApiModelProperty(value = "Task Description", example = "Buy groceries")
    private String description;

    @ApiModelProperty(value = "Task completed indicator", example = "false")
    private Boolean completed;
}
