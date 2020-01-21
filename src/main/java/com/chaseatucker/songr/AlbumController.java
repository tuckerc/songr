package com.chaseatucker.songr;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AlbumController {
  @RequestMapping(value = "/albums", method = RequestMethod.GET)
  public String albums(Model m) {
    Album blindfaller = new Album("blindfaller", "Mandolin Orange", 10, 2352, "https://images.squarespace-cdn.com/content/v1/54bd37c4e4b0bb2b01b2cbaf/1538064832529-TYVK2PNF65KGLYI10PT8/ke17ZwdGBToddI8pDm48kODEHMGUBRgRRplOmqRomK1Zw-zPPgdn4jUwVcJE1ZvWhcwhEtWJXoshNdA9f1qD7Rb66VC8y5UmDKESC3gpGyXuuE80dFkeX9PanWgM3V4qRJePadRz66WmX0L5eMAEew/image-asset.png?format=500w");
    Album theWildHunt = new Album("The Wild Hunt", "The Tallest Man on Earth", 10, 2078, "https://upload.wikimedia.org/wikipedia/en/5/5c/Wildhuntcover.jpg");
    Album strangeTrails = new Album("StrangeTrails", "Lord Huron", 14, 3300, "https://upload.wikimedia.org/wikipedia/en/e/e5/Strange_Trails_cover.jpg");
    Album[] albums = {blindfaller, theWildHunt, strangeTrails};
    m.addAttribute("albums", albums);
    return "albums";
  }
}
