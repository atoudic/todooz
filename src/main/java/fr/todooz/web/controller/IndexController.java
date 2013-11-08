package fr.todooz.web.controller;

import fr.todooz.domain.Task;
import fr.todooz.service.TagCloudService;
import fr.todooz.service.TaskService;
import fr.todooz.util.TagCloud;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.Date;

@Controller
public class IndexController {
    @Inject
    private TaskService taskService;

    @Inject
    private TagCloudService tagCloudService;

    @ModelAttribute
    public TagCloud tagCloud() {
        return tagCloudService.buildTagCloud();
    }

    @RequestMapping({ "/", "/index" })
    public String index(Model model) {
        model.addAttribute("tasks", taskService.findAll());

        return "index";
    }

    @RequestMapping("/search")
    public String search(String query, Model model) {
        model.addAttribute("tasks", taskService.findByQuery(query));

        return "index";
    }

    @RequestMapping("/tag/{tag}")
    public String tag(@PathVariable String tag, Model model) {
        model.addAttribute("tasks", taskService.findByTag(tag));

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