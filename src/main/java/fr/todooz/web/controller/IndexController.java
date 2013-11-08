package fr.todooz.web.controller;

import fr.todooz.domain.Task;
import fr.todooz.web.servlet.DummyData;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.Date;

@Controller
public class IndexController {
    @Inject
    private fr.todooz.service.TaskService taskService;

    @Inject
    private SessionFactory sessionFactory;

    @RequestMapping({ "/", "/index" })
    public String index(Model model) {
        model.addAttribute("tasks", taskService.findAll());

        return "index";
    }

    @PostConstruct
    public void bootstrap() {
        if (taskService.count() == 0){
            taskService.save(buildTask());
            taskService.save(buildTask());
            taskService.save(buildTask());
        }
    }

    private Task buildTask() {
        Task task = new Task();

        task.setDate(new Date());
        task.setTitle("Read Effective Java");
        task.setText("Read Effective Java before it's too late");
        task.setTags("java, java");

        return task;
    }
}