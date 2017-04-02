package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.launchcode.models.JobData.findByColumnAndValue;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }

    // TODO #1 - Create handler to process search request and display results
    @RequestMapping(value = "results")
    public String results(Model model, @RequestParam String searchType, @RequestParam String searchTerm) {

        List<HashMap<String, String>> jobs = new ArrayList<>();
        if (searchType.equals("all")) {
            jobs = JobData.findByValue(searchTerm);
        } else {
            jobs = JobData.findByColumnAndValue(searchType, searchTerm);
        }
        int numResults = jobs.size();
        model.addAttribute("columns", ListController.columnChoices);
        model.addAttribute("jobs", jobs);
        model.addAttribute("numResults", numResults);
        return "search";
    }
}
