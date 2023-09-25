package no.hvl.dat250.rest.todos;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Rest-Endpoint for todos.
 */
@RestController
public class TodoController {
  private Long id = 0L;
  private final List<Todo> todos = new ArrayList<>();
  public static final String TODO_WITH_THE_ID_X_NOT_FOUND = "Todo with the id %s not found!";

  public Todo todoNotFound(Long id){
    Todo notFound = new Todo();
    notFound.setDescription(String.format(TODO_WITH_THE_ID_X_NOT_FOUND,id));
    return notFound;
  }

  @PutMapping("/todos/{id}")
  public Todo updateTodo(@PathVariable Long id, @RequestBody Todo newTodo){
    for (int i = 0; i< todos.size(); i++){
      if (Objects.equals(todos.get(i).getId(), id)){
        todos.remove(i);
        todos.add(i,newTodo);

        return newTodo;
      }
    }
    return null;
  }

  @GetMapping("/todos/{id}")
  public Todo getTodos(@PathVariable Long id){
    for (Todo todo : todos){
      if (todo.getId().equals(id)){
        return todo;
      }
    }
    return todoNotFound(id);
  }

  @GetMapping("/todos")
  public List<Todo> getTodos() {
    return todos;
  }

  @DeleteMapping("/todos/{id}")
  public Todo deleteTodo(@PathVariable Long id){
    for (Todo todo: todos){
      if (todo.getId().equals(id)){
        todos.remove(todo);
        return todo;
      }
    }
    return todoNotFound(id);
  }

  @PostMapping("/todos")
  public Todo createTodo(@RequestBody Todo newTodo){
    newTodo.setId(id);
    id++;
    todos.add(newTodo);
    return newTodo;
  }
}
