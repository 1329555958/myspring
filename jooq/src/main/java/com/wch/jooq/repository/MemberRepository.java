package com.wch.jooq.repository;

import com.wch.jooq.entity.tables.TAccount;
import com.wch.jooq.entity.tables.TMember;
import com.wch.jooq.entity.tables.records.TAccountRecord;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

/**
 * Created by weichunhe on 2016/5/26.
 */
@Repository
public class MemberRepository {
    @Autowired
    DSLContext dsl;

    public BigDecimal queryBalanceById(String id) {
        return dsl.select().from(TMember.T_MEMBER).join(TAccount.T_ACCOUNT).on(TMember.T_MEMBER.ACCOUNT_ID.eq(TAccount.T_ACCOUNT.ACCOUNT_ID).and(TMember.T_MEMBER.MEMBER_ID.eq(id))).fetchOne(TAccount.T_ACCOUNT.BALANCE);
    }

    public TAccountRecord queryAccountById(String id) {
        return dsl.select(TAccount.T_ACCOUNT.fields()).from(TMember.T_MEMBER).join(TAccount.T_ACCOUNT).on(TMember.T_MEMBER.ACCOUNT_ID.eq(TAccount.T_ACCOUNT.ACCOUNT_ID).and(TMember.T_MEMBER.MEMBER_ID.eq(id))).fetchOne().into(TAccountRecord.class);
    }
}
