package interviewtest.todo.repository;

import interviewtest.todo.entity.Todo;
import interviewtest.todo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TodoRepository extends JpaRepository<Todo, String> {
    List<Todo> findAllByUser(User user);

    Optional<Todo> findFirstById(String id);
}
