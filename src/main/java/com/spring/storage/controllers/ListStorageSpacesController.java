package com.spring.storage.controllers;

import com.spring.storage.StorageService;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class ListStorageSpacesController {

  @ModelAttribute("module")
  String module() {
    return "listStorageSpaces";
  }

  @Autowired
  private StorageService storageService;

  @GetMapping("storage/list")
  public String listStorages(Model model, Principal principal) {
    Assert.notNull(principal);
    model.addAttribute(storageService.getAllStorages());

    return "storage/list";
  }
}
