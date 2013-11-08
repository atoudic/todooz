package fr.todooz.web.controller;


import fr.todooz.domain.Task;
import fr.todooz.service.TagCloudService;
import fr.todooz.service.TaskService;
import fr.todooz.util.TagCloud;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;
import javax.persistence.Column;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class AdminController {

    @Inject
    private TagCloudService tagCloudService;

    @Inject
    private TaskService taskService;

    @ModelAttribute
    public TagCloud tagCloud() {
        return tagCloudService.buildTagCloud();
    }

    @InitBinder
    public void initBinder(DataBinder binder) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormatter, true));
    }

    @RequestMapping("/add")
    public String add(Model model) {
        // on injecte une Task vierge dans le modèle
        model.addAttribute("task", new Task());

        return "edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String post(@ModelAttribute @Valid Task task, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "edit";
        }

        taskService.save(task);

        redirectAttributes.addFlashAttribute("flashMessage", "La sauvegarde a réussi");

        return "redirect:/";
    }

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("task", taskService.findById(id));

        return "edit";
    }
}
