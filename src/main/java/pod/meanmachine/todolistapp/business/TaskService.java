package pod.meanmachine.todolistapp.business;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import pod.meanmachine.todolistapp.data.TaskEntity;
import pod.meanmachine.todolistapp.data.TaskRepository;
import pod.meanmachine.todolistapp.exception.ConstraintViolationException;
import pod.meanmachine.todolistapp.transfer.TaskCMD;
import pod.meanmachine.todolistapp.transfer.TaskTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class TaskService {
    private final TaskRepository taskRepository;
    private final ModelMapper modelMapper;

    public TaskTO getTaskById(Integer id) {
        validateMandatoryTaskId(id);
        TaskEntity task = taskRepository.findById(id).orElseThrow(() -> ConstraintViolationException.builder()
                                                                            .status(HttpStatus.NOT_FOUND)
                                                                            .message("Task Not Found")
                                                                            .build());
        TaskTO taskTO = modelMapper.map(task, TaskTO.class);
        log.info("Found Task with id {} -> {}", id, taskTO);
        return taskTO;
    }

    public List<TaskTO> findAll() {
        List<TaskTO> tasks = new ArrayList<>();
        for (TaskEntity task : taskRepository.findAll()) {
            tasks.add(modelMapper.map(task, TaskTO.class));
        }
        log.info("Tasks to return -> {}", tasks);
        return tasks;
    }

    public TaskTO update(int id, TaskCMD request) {
        validateMandatoryTaskId(id);
        TaskEntity taskToUpdate = taskRepository.findById(id)
                .orElseThrow(() -> new ConstraintViolationException(HttpStatus.NOT_FOUND, "Task Not Found"));
        if(request.getCompleted() != null){
            taskToUpdate.setCompleted(request.getCompleted());
            if(Boolean.TRUE.equals(request.getCompleted())){
                taskToUpdate.setCompletionDate(new Date());
            }
        }

        if(StringUtils.hasText(request.getDescription())){
            taskToUpdate.setDescription(request.getDescription());
        }

        taskToUpdate = taskRepository.saveAndFlush(taskToUpdate);
        log.info("Updated task -> {}", taskToUpdate);
        return modelMapper.map(taskToUpdate, TaskTO.class);
    }

    public TaskTO create(TaskCMD request) {
        TaskEntity taskToCreate = modelMapper.map(request, TaskEntity.class);
        taskToCreate.setCreationDate(new Date());

        if(request.getCompleted() != null){
            taskToCreate.setCompleted(request.getCompleted());
            if(Boolean.TRUE.equals(request.getCompleted())){
                taskToCreate.setCompletionDate(new Date());
            }
        }

        if(StringUtils.hasText(request.getDescription())){
            taskToCreate.setDescription(request.getDescription());
        }

        taskToCreate = taskRepository.save(taskToCreate);
        log.info("Created task -> {}", taskToCreate);
        return modelMapper.map(taskToCreate, TaskTO.class);
    }

    public void delete(int id) {
        validateMandatoryTaskId(id);
        taskRepository.deleteById(id);
        log.info("Task {} successfully deleted", id);
    }

    private void validateMandatoryTaskId(Integer id) {
        if (id == null || id.equals(0)) {
            throw new ConstraintViolationException(HttpStatus.BAD_REQUEST, "The Task id is required");
        }
    }

}
