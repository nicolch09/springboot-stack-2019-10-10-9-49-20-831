package com.tw.apistackbase.controller;
import com.tw.apistackbase.model.EmployeeModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private List<EmployeeModel> employeeList = new ArrayList<>();

    @PostMapping(path = "/add", consumes = "application/json", produces = "application/json")
    public List<EmployeeModel> add(@RequestBody EmployeeModel employee) {
        employeeList.add(employee);
        return employeeList;
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<List<EmployeeModel>> delete(@PathVariable Integer id){
        EmployeeModel emp = this.employeeList.stream().filter(element -> element.getId().equals(id)).findFirst().orElse(null);
        employeeList.remove(emp);
        return ResponseEntity.ok(employeeList);
    }

    @PutMapping(path = "/change/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<List<EmployeeModel>> change(@RequestBody EmployeeModel employee, @PathVariable Integer id){
        EmployeeModel emp = this.employeeList.stream().filter(element -> element.getId().equals(id)).findFirst().orElse(null);

        emp.setGender(employee.getGender());
        emp.setName(employee.getName());
        emp.setAge(employee.getAge());

        return ResponseEntity.ok(employeeList);
    }

    @GetMapping(path = "/search", produces = {"application/json"})
    public ResponseEntity<List> search() {
        return ResponseEntity.ok(employeeList);
    }

    @GetMapping(path = "/search/{id}", produces = {"application/json"})
    public ResponseEntity<Stream<EmployeeModel>> search(@PathVariable Integer id){
        return ResponseEntity.ok(employeeList.stream().filter(element -> element.getId().equals(id)));
    }
}