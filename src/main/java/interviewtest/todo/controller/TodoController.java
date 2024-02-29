package interviewtest.todo.controller;

import interviewtest.todo.entity.Todo;
import interviewtest.todo.entity.User;
import interviewtest.todo.model.WebResponse;
import interviewtest.todo.model.request.CreateTodoRequest;
import interviewtest.todo.model.request.UpdateTodoRequest;
import interviewtest.todo.model.response.TodoResponse;
import interviewtest.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TodoController {

    @Autowired
    TodoService todoService;

    @PostMapping(
            path = "api/todos",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<String> create(User user, @RequestBody CreateTodoRequest request) {
        todoService.create(user, request);
        return WebResponse.<String>builder().data("OK").build();
    }

    @GetMapping(
            path = "api/todos"
    )
    public WebResponse<List<TodoResponse>> findByUser(User user){
        List<TodoResponse> todos = todoService.findByUser(user);
        return  WebResponse.<List<TodoResponse>>builder().data(todos).build();
    }

    @PutMapping(
            path = "api/todos/{todoId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<String> update(User user, @RequestBody UpdateTodoRequest request, @PathVariable("todoId") String todoId){
        todoService.update(user, request, todoId);
        return  WebResponse.<String>builder().data("OK").build();
    }
    @PutMapping(
            path = "api/todos/status/{todoId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<String> updateStatus(User user, @PathVariable("todoId") String todoId){
        todoService.updateStatus(user, todoId);
        return  WebResponse.<String>builder().data("OK").build();
    }
}
