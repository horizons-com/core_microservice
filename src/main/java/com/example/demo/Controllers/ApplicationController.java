package com.example.demo.Controllers;

import com.example.demo.Services.MessageService;
import com.example.demo.Services.StudentService;
import org.codehaus.jettison.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ApplicationController {
    @Autowired
    protected StudentService studentService;

    @Autowired
    protected MessageService messageService;

    public ApplicationController(StudentService studentService, MessageService messageService) {
        this.studentService = studentService;
        this.messageService = messageService;
    }

    @RequestMapping("/students")
    public String getAllStudents() {
        return studentService.getAll();
    }

    @RequestMapping(value = "/students/{id}", method = RequestMethod.GET)
    public String getStudentById(@PathVariable("id") String id) throws JSONException {
        return studentService.getStudentById(id);
    }

    @RequestMapping("/messages")
    public String getAllMessages() {
        return messageService.getAll();
    }

}
