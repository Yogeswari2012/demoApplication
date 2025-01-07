package org.demo.demoapplication;

import com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
public class Controllers {

    @Autowired
    private PublicRepository rep;

    @GetMapping("/create-student/{param1}/{param2}")
    public ResponseEntity<Object> createStudent(@PathVariable("param1") String id, @PathVariable("param2") int date) throws CustomException {
        if(date < 0 ) {
            throw new CustomException("Date is not valid", HttpStatus.BAD_REQUEST);
        }
        Student s = new Student("4", 130);
        s.setFirstName( "Do");
       s.setLastName("ka");
        s.setEmail("Doka@gmail.com");
        rep.save(s);
        return ResponseEntity.ok("Resource Created Successfully");
    }

    @PostMapping("/get-student/{param1}/{param2}")
    public void getStudent(@PathVariable("param1") String param1, @PathVariable("param2") int param2) {

        Student s = new Student(param1, param2);
        Student  s1 = rep.findByProperty(s);
    }


    @PostMapping("/get-all-student/{param1}")
    public ResponseEntity<List<Student>> getAllStudent(@PathVariable("param1") String param1) {
        Student s = new Student(param1);
        List<Student> students = rep.query(s, Student.class);
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @PostMapping("/delete-student/{param1}/{param2}")
    public ResponseEntity<Object> deleteStudent(@PathVariable("param1") String param1, @PathVariable("param2") int param2) {
        Student s = new Student(param1, param2);
        try {
            rep.deleteByProperty(s, Student.class);
        }catch(ConditionalCheckFailedException e) {
            return new ResponseEntity<>("Record Not found", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);

    }
}
