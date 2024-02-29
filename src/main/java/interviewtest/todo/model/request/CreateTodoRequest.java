package interviewtest.todo.model.request;

import interviewtest.todo.entity.User;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateTodoRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String description;

}
