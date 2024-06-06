package com.target.shipments.service;

import com.target.shipments.model.TestTable;
import com.target.shipments.repository.TestTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TestTableService {

    @Autowired
    private TestTableRepository testTableRepository;

    public List<TestTable> getAll() {
        return testTableRepository.findAll();
    }

    public Optional<TestTable> getById(String id) {
        return testTableRepository.findById(id);
    }

    public TestTable save(TestTable testTable) {
        return testTableRepository.save(testTable);
    }

    public void deleteById(String id) {
        testTableRepository.deleteById(id);
    }
}
