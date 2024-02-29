package interviewtest.todo.service;

import interviewtest.todo.entity.Todo;
import interviewtest.todo.entity.User;
import interviewtest.todo.model.request.CreateTodoRequest;
import interviewtest.todo.model.request.UpdateTodoRequest;
import interviewtest.todo.model.response.TodoResponse;
import interviewtest.todo.repository.TodoRepository;
import jakarta.transaction.Transactional;
import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private ValidationService validationService;

    @Transactional
    public void create(User user, CreateTodoRequest request) {
        validationService.validate(request);
        Todo todo = new Todo();
        todo.setDescription(request.getDescription());
        todo.setName(request.getName());
        todo.setUser(user);
        todo.setStatus(false);
        todoRepository.save(todo);
    }

    private TodoResponse toTodoResponse(Todo todo){
        return TodoResponse.builder()
                .id(todo.getId())
                .name(todo.getName())
                .description(todo.getDescription())
                .status(todo.getStatus())
                .build();
    }

    @Transactional
    public List<TodoResponse> findByUser(User user){
        List<Todo> todos = todoRepository.findAllByUser(user);
        return todos.stream().map(this::toTodoResponse).toList();
    }

    @Transactional
    public void update(User user, UpdateTodoRequest request, String todoId){
        validationService.validate(request);
        Todo todo = todoRepository.findFirstById(todoId)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Todo Not Found")) ;
        todo.setName(request.getName());
        todo.setDescription(request.getDescription());
        todoRepository.save(todo);
    }

    @Transactional
    public void updateStatus(User user, String todoId){
        Todo todo = todoRepository.findFirstById(todoId)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Todo Not Found")) ;
        todo.setStatus(!todo.getStatus());

        todoRepository.save(todo);
    }

}