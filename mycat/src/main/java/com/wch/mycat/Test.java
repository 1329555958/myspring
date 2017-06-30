package com.wch.mycat;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class Test {

    public static void main(String[] args) {
        BasicDataSource driverds = new BasicDataSource();
        driverds.setUrl("jdbc:mysql://10.65.213.14:8066/gop");
        driverds.setUsername("reader");
        driverds.setPassword("reader");
//        driverds.setUrl("jdbc:mysql://10.5.16.14:8066");
//        driverds.setUsername("user");
//        driverds.setPassword("user");
        driverds.setDriverClassName("com.mysql.jdbc.Driver");
//	        driverds.setMaxActive(JDBC_DEFAULT_MAX_ACTIVE);
//	        driverds.setMaxIdle(JDBC_DEFAULT_MAX_IDLE);
//	        driverds.setMinIdle(JDBC_DEFAULT_MIN_IDLE);

        driverds.setLogAbandoned(true);
        driverds.setRemoveAbandoned(true);
//	        driverds.setRemoveAbandonedTimeout(JDBC_DEFAULT_REMOVE_ABD_TIMEOUT);
        driverds.setTestOnBorrow(true);
//	        driverds.setValidationQuery(" select 1 from ssop.dual");
//	        driverds.setMaxWait(JDBC_DEFAULT_MAX_WAIT);
        JdbcTemplate template = new JdbcTemplate(driverds);

        System.out.println(template);
        SqlRowSet rowSet = template.queryForRowSet
                (" SELECT SOURCE_ORDER_NO 外部订单号 from gop.t_gateway_access where SOURCE_ORDER_NO in ('2017041214390924490812404526')  ");
//               ( "SELECT id 编号 from test13.test ");
//        ("SELECT account_no FROM dpm.t_dpm_inner_account c WHERE c.account_no in( '20030020810101')");
//        ("SELECT NAME FROM cmf.t_sequence");

        while (rowSet.next())
            System.out.println("======" + rowSet.getString(1));

//	        System.out.println(template.queryForList(" SELECT SOURCE_ORDER_NO 外部订单号, VOUCHER_NO 凭证号, case VOUCHER_TYPE when 'trade' then '交易' when 'simpleOrder' then '简单交易' when 'refund' then '退款' when 'control' then '控制' when 'payment' then '支付' when 'payCard' then '付款到卡' when 'discountPay' then '折扣支付' when 'transfer' then '转账' else VOUCHER_TYPE end as 凭证类型, PARTNER_ID 合作方ID, BUYER_MEMBER_ID 买家会员ID, RELATED_INNER_VOUCHER_NO 关联凭证号, GMT_CREATE 创建时间, GMT_MODIFY 修改时间, MEMO 备注 from gop.t_gateway_access where SOURCE_ORDER_NO in ('2017041214390924490812404526')  ")); ;

    }

}