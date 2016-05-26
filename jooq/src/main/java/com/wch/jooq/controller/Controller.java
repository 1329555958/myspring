package com.wch.jooq.controller;

import com.wch.jooq.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by weichunhe on 2016/5/26.
 */
@RestController
@RequestMapping("/")
public class Controller {

    @Autowired
    private MemberRepository memberRepository;

    @RequestMapping("/{memberId}/balance")
    public Object queryBalanceByMemberId(@PathVariable String memberId) {
        return memberRepository.queryBalanceById(memberId);
    }

    @RequestMapping("/{memberId}")
    public Object queryAccountByMemberId(@PathVariable String memberId) {
        return memberRepository.queryAccountById(memberId).intoMap();
    }
}
