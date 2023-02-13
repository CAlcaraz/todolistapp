package pod.meanmachine.todolistapp.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pod.meanmachine.todolistapp.business.TaskService;
import pod.meanmachine.todolistapp.config.SwaggerConfig;
import pod.meanmachine.todolistapp.transfer.TaskCMD;
import pod.meanmachine.todolistapp.transfer.TaskTO;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/tasks", produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Api(tags = {SwaggerConfig.TASK_TAG})
public class TaskController {

    private final TaskService taskService;

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Get Task By Id", response = TaskTO.class)
    public ResponseEntity<TaskTO> getTaskById(@PathVariable("id") int id) {
        return ResponseEntity.ok(taskService.getTaskById(id));
    }

    @GetMapping
    @ApiOperation(value = "Get all tasks", response = TaskTO.class)
    public ResponseEntity<List<TaskTO>> findAll() {
        return ResponseEntity.ok(taskService.findAll());
    }

    @PostMapping
    @ApiOperation(value = "Create task", response = TaskTO.class)
    public ResponseEntity<TaskTO> create(@RequestBody @ApiParam(name = "request", value = "Command Payload") TaskCMD request) {
        return ResponseEntity.ok(taskService.create(request));
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "Delete task", response = TaskTO.class)
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        taskService.delete(id);
        return ResponseEntity.noContent().build();
    }


    @PutMapping(value = "/{id}")
    @ApiOperation(value = "Update task by Id", response = TaskTO.class)
    public ResponseEntity<TaskTO> update(@PathVariable("id") int id, @ApiParam(name = "request", value = "Command Payload") @RequestBody TaskCMD request) {
        return ResponseEntity.ok(taskService.update(id, request));
    }

}
