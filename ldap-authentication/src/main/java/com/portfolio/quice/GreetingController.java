package com.portfolio.quice;

import org.springframework.web.bind.annotation.RestController;
import java.security.Principal;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class GreetingController {
  @GetMapping("/greeting")
  String greeting(Principal principal) {
    return "hello, " + principal.getName() + "!";
  }

}
