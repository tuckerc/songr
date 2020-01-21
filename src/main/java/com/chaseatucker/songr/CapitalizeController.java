package com.chaseatucker.songr;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CapitalizeController {
  @RequestMapping(value = "/capitalize/{word}", method = RequestMethod.GET)
  public String capitalize(@PathVariable String word, Model m) {
    m.addAttribute("word", word);
    m.addAttribute("capitalizedWord", word.toUpperCase());
    return "capitalize";
  }
}
