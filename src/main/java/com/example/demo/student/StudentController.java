package com.example.demo.student;

import com.datadog.api.client.ApiClient;
import com.datadog.api.client.ApiException;
import com.datadog.api.client.v1.api.LogsApi;
import com.datadog.api.client.v1.model.HTTPLogItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.Collections;
import java.util.List;


@RestController
@RequestMapping(path = "api/v1/student")


public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }
    Logger logger = LoggerFactory.getLogger(StudentController.class);
    @GetMapping
    public List<Student> getStudents (){
        ApiClient defClient = ApiClient.getDefaultApiClient();
        LogsApi apiInstance = new LogsApi(defClient);
        String msg = "fetching students";

        List<HTTPLogItem> body = Collections.singletonList(new HTTPLogItem().message(msg));

        try {
            apiInstance.submitLog(body);
        }catch (ApiException e){
            e.printStackTrace();
        }

        logger.info(msg);
        return studentService.getStudents();
    }

    @PostMapping
    public void registerNewStudent(@RequestBody Student student){
        logger.info("posting students");
        studentService.addNewStudent(student);
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudents(@PathVariable("studentId")Long studentId){
        logger.info("deleting students");
        studentService.deleteStudent(studentId);

    }

    @PutMapping(path = "{studentId}")
    public void updateStudent(
            @PathVariable("studentId") Long studentId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email) {
        logger.info("Changing student info");
        studentService.updateStudent(studentId,name,email);
    }



}
