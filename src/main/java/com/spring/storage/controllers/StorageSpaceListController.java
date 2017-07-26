package com.spring.storage.controllers;

import com.spring.storage.StorageService;
import java.security.Principal;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class StorageSpaceListController {

  @Autowired
  private StorageService storageService;

  @GetMapping("storage/all/list")
  public String listStorages(Model model) {
    model.addAttribute("module","listAllStorageSpaces");
    model.addAttribute("storages", storageService.getAllStorages());

    return "storage/all/list";
  }

  @GetMapping("storage/user/list")
  public String listUserStorages(Model model, Principal principal) {
    Assert.notNull(principal);

    model.addAttribute("module","listUserStorageSpaces");
    model.addAttribute("storages", storageService.getAllUserStorages());
    return "storage/user/list";
  }
}
