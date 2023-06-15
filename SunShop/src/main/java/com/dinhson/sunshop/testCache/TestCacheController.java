package com.dinhson.sunshop.testCache;

import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@EnableCaching
@AllArgsConstructor
public class TestCacheController {

    private final TestService testService;

    @GetMapping("getAll")
    public List<Student> getAll() {
        return testService.getAll();
    }

    @GetMapping("findById/{id}")
    public Student findById(@PathVariable Integer id) {
        return testService.getById(id);
    }

    @GetMapping("update/{id}/{name}")
    public Student update(@PathVariable Integer id,
                          @PathVariable String name) {
        return testService.updateById(id, name);
    }

    @GetMapping("add/{id}/{name}")
    public Student add(@PathVariable Integer id,
                       @PathVariable String name) {
        return testService.addNewStudent(id, name);
    }
}
