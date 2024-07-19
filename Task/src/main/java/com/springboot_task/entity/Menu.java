package com.springboot_task.entity;

import lombok.Data;
import java.util.List;

@Data
public class Menu {
    private String id;
    private String value;
    private Popup popup;
}
