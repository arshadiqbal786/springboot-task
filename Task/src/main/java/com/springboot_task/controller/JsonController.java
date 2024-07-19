package com.springboot_task.controller;

import com.springboot_task.entity.JsonModel;
import com.springboot_task.service.JsonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class JsonController {

    @Autowired
    private JsonService jsonService;

    @PostMapping("/modify")
    public JsonModel modifyJson(@RequestBody JsonModel jsonModel, @RequestParam String inputs) {
        return jsonService.modifyJson(jsonModel, inputs);
    }

    private JsonModel getPredefinedJsonModel() {
        // Initialize or retrieve your JSON model here
        JsonModel jsonModel = new JsonModel();
        // Setup the model with the default values
        return jsonModel;
    }
}
