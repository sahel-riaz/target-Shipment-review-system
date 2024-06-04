package com.target.shipments.controller;

import com.target.shipments.model.TestTable;
import com.target.shipments.service.TestTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/test")
public class TestTableController {

    @Autowired
    private TestTableService testTableService;

    @GetMapping
    public List<TestTable> getAll() {
        return testTableService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TestTable> getById(@PathVariable String id) {
        Optional<TestTable> testTable = testTableService.getById(id);
        if (testTable.isPresent()) {
            return ResponseEntity.ok(testTable.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public TestTable create(@RequestBody TestTable testTable) {
        return testTableService.save(testTable);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TestTable> update(@PathVariable String id, @RequestBody TestTable testTable) {
        if (testTableService.getById(id).isPresent()) {
            testTable.setTestId(id);
            return ResponseEntity.ok(testTableService.save(testTable));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        if (testTableService.getById(id).isPresent()) {
            testTableService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
