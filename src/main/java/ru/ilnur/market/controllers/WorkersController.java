package ru.ilnur.market.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.ilnur.market.dao.WorkerDAO;
import ru.ilnur.market.models.Worker;


@Controller
@RequestMapping("/workers")
public class WorkersController {
    private final WorkerDAO workerDAO;

    @Autowired
    public WorkersController(WorkerDAO workerDAO) {
        this.workerDAO = workerDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("workers", workerDAO.index());
        return "workers/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("worker", workerDAO.show(id));
        return "workers/show";
    }

    @GetMapping("/new")
    public String newWorker(@ModelAttribute("worker") Worker worker) {
        return "workers/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("worker") Worker worker) {
        workerDAO.save(worker);
        return "redirect:/workers";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("worker", workerDAO.show(id));
        return "workers/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("worker") Worker worker,
                         @PathVariable("id") int id) {
        workerDAO.update(worker, id);
        return "redirect:/workers";
    }

    @GetMapping("/{id}/searchCompany")
    public String search(Model model, @PathVariable("id") int id) {
        model.addAttribute("companies", workerDAO.search(id));
        return "companies/search";
    }
}
