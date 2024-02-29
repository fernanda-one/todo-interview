package interviewtest.todo.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindAllTodoRequest {
    @NotBlank
    private String page;

    @NotBlank
    private String limit;
}
