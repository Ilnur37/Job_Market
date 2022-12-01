package ru.ilnur.market.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.ilnur.market.dao.CompaniesDAO;
import ru.ilnur.market.models.Company;

@RequestMapping("/companies")
@Controller
public class CompaniesController {
    private final CompaniesDAO companiesDAO;

    @Autowired
    public CompaniesController(CompaniesDAO companiesDAO) {
        this.companiesDAO = companiesDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("companies", companiesDAO.index());
        return "companies/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("company", companiesDAO.show(id));
        return "companies/show";
    }

    @GetMapping("/new")
    public String newCompany(@ModelAttribute("company") Company company) {
        return "companies/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("company") Company company,
                         BindingResult bindingResult) {
        if(bindingResult.hasErrors())
            return "companies/new";

        companiesDAO.save(company);
        return "redirect:/companies";
    }

}
