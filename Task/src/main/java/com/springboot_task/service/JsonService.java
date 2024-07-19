package com.springboot_task.service;

import com.springboot_task.entity.*;
import com.springboot_task.repository.JsonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.HashMap;
import java.util.Map;

@Service
public class JsonService {

    @Autowired
    private JsonRepository jsonRepository;
    private static final Logger logger = LoggerFactory.getLogger(JsonService.class);

    public JsonModel modifyJson(JsonModel jsonModel, String inputs) {
        logger.info("Received JSON Model: {}", jsonModel);
        logger.info("Received Inputs: {}", inputs);

        if (jsonModel == null || jsonModel.getMenu() == null) {
            logger.error("JsonModel or its menu is null");
            throw new IllegalArgumentException("JsonModel or its menu is null");
        }
        // Parse inputs into a map
        Map<String, String> replacements = parseInputs(inputs);

        // Perform replacements
        replaceValue(jsonModel, replacements);

        // Save to DB
        JsonEntity jsonEntity = new JsonEntity();
        jsonEntity.setJsonModel(jsonModel.toString());
        jsonRepository.save(jsonEntity);

        return jsonModel;
    }

    private Map<String, String> parseInputs(String inputs) {
        Map<String, String> replacements = new HashMap<>();
        String[] pairs = inputs.split(",");
        for (String pair : pairs) {
            String[] keyValue = pair.split(":::");
            if (keyValue.length == 2) {
                replacements.put(keyValue[0], keyValue[1]);
            }
        }
        return replacements;
    }

    private void replaceValue(JsonModel jsonModel, Map<String, String> replacements) {
        Menu menu = jsonModel.getMenu();
        if (menu != null) {
            // Example of replacing values in menu
            String menuValue = menu.getValue();
            for (Map.Entry<String, String> entry : replacements.entrySet()) {
                if (menuValue.equals(entry.getKey())) {
                    menu.setValue(entry.getValue());
                }
            }

            Popup popup = menu.getPopup();
            if (popup != null) {
                for (MenuItem item : popup.getMenuitem()) {
                    String itemValue = item.getValue();
                    if (replacements.containsKey(itemValue)) {
                        item.setValue(replacements.get(itemValue));
                    }
                }
            }
        }
    }
}
