package pl.edu.wszib.http2.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.edu.wszib.http2.service.ToDoService;
import pl.edu.wszib.http2.service.model.ToDo;
import pl.edu.wszib.http2.service.model.ToDoStatus;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/todos")
public class TodoController {

    @Autowired
    private ToDoService toDoService;

    @PostConstruct
    public void init(){
        ToDo newToDo = new ToDo();
        newToDo.setStatus(ToDoStatus.NEW);
        newToDo.setTermin("2023-04-26");
        newToDo.setZadanie("Kup prezent na urodziny X");

        toDoService.create(newToDo);

        ToDo inProgressToDo = new ToDo();
        inProgressToDo.setStatus(ToDoStatus.IN_PROGRESS);
        inProgressToDo.setTermin("2023-04-09");
        inProgressToDo.setZadanie("Skok ze spadochronem");

        toDoService.create(inProgressToDo);

        ToDo doneToDo = new ToDo();
        doneToDo.setStatus(ToDoStatus.DONE);
        doneToDo.setTermin("2023-04-20");
        doneToDo.setZadanie("Zrób zakupy");

        toDoService.create(doneToDo);
    }

    @GetMapping
    public String listView(Model model){
        List<ToDo> todos = toDoService.list();
        model.addAttribute("todos", todos);
        return "todos/list";
    }

    @GetMapping("/create")
    public String createView(Model model) {
        ToDo newToDo = new ToDo();
        model.addAttribute("newToDo", newToDo);
        return "todos/create";
    }

    @PostMapping("/create")
    public String createAction(ToDo newToDo){
        newToDo.setStatus(ToDoStatus.NEW);
        toDoService.create(newToDo);
        return "redirect:/todos";
    }

    @GetMapping("/update")
    public String updateSatus(@RequestParam Integer id, @RequestParam ToDoStatus status){
        ToDo existing = toDoService.get(id);
        existing.setStatus(status);
        toDoService.update(existing);
        return "redirect:/todos";
    }
}
