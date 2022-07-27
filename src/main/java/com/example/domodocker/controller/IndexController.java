package com.example.domodocker.controller;

import com.example.domodocker.controller.entity.Account;
import com.example.domodocker.controller.repo.AccountRepository;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

@RestController
public class IndexController {

    @Resource
    StringRedisTemplate template;

    @Resource
    AccountRepository repository;

    @GetMapping("/")
    public String index() {
        return "<h1>Hello Docker!</h1>" + new Date();
    }

    @GetMapping("/get")
    public Account get(@RequestParam("id") long id){
        return repository.findById(id).orElse(null);
    }

    @PostMapping("/post")
    public Account get(@RequestParam("id") long id,
                       @RequestParam("name") String name,
                       @RequestParam("password") String password){
        return repository.save(new Account(id, name, password));
    }

    @GetMapping("/take")
    public String take(@RequestParam("key") String key){
        return template.opsForValue().get(key);
    }

    @PostMapping("/put")
    public String put(@RequestParam("key") String key,
                       @RequestParam("value") String value){
        template.opsForValue().set(key, value);
        return "操作成功！";
    }
}
