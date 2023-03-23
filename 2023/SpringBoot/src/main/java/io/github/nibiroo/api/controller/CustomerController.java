package io.github.nibiroo.api.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @GetMapping(value = {"/hello/{name}", "/helloworld/{name}"})
    @ResponseBody
    public String HelloCustomer (@PathVariable("name") String nameCustomer) {
        return String.format("Hello %s", nameCustomer);
    }

}
