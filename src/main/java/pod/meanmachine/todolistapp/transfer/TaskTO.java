package pod.meanmachine.todolistapp.transfer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(value = "Task TO Model", description = "Transfer Object for Task Entity")
public class TaskTO {

    @ApiModelProperty(value = "Task Identifier", example = "1")
    private Integer id;

    @ApiModelProperty(value = "Task Description", example = "Buy groceries")
    private String description;

    @ApiModelProperty(value = "Task completed indicator", example = "false")
    private Boolean completed;

    @ApiModelProperty(value = "Task creation date")
    private Date creationDate;

    @ApiModelProperty(value = "Task completion date")
    private Date completionDate;
}
