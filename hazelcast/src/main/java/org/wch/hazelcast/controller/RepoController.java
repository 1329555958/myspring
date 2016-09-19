package org.wch.hazelcast.controller;

import com.hazelcast.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wch.hazelcast.repository.Speaker;
import org.wch.hazelcast.repository.SpeakerRepository;
import org.wch.util.JSONUtil;

/**
 * @author weichunhe
 *         Created on 2016/9/19.
 * @version 1.0
 */
@RestController
@RequestMapping("/repo")
public class RepoController {
    @Autowired
    private SpeakerRepository repository;

    @RequestMapping("/save")
    public Object save(String name) {
        Speaker speaker = new Speaker();
        speaker.setName(name);
        speaker.setId(System.currentTimeMillis());
        System.out.println(JSONUtil.toJson(repository.save(speaker)));
        return speaker;
    }

    @RequestMapping("/speakers")
    public Object all() {
        return repository.findAll();
    }
}
