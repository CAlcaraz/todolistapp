package pod.meanmachine.todolistapp.data;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Task")
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class TaskEntity {

    @Id
    @Column(name = "TASK_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "TASK_DESCRIPTION")
    private String description;

    @Column(name = "TASK_IS_COMPLETED")
    private Boolean completed;

    @Column(name = "TASK_CREATION_DT")
    private Date creationDate;

    @Column(name = "TASK_COMPLETION_DT")
    private Date completionDate;
}
