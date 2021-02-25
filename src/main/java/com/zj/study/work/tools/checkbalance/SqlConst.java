package com.zj.study.work.tools.checkbalance;

public class SqlConst {
	public static final String detailSumSql="SELECT\r\n" + 
			"    SUM(num) as num,\r\n" + 
			"    SUM(MONEY) as money,\r\n" + 
			"    costreg,\r\n" + 
			"    costobject,\r\n" + 
			"    inorout\r\n" + 
			"FROM\r\n" + 
			"    ia_detailledger\r\n" + 
			"WHERE\r\n" + 
			"    accbody=?\r\n" + 
			"AND period<=?\r\n" + 
			"GROUP BY\r\n" + 
			"    costreg,\r\n" + 
			"    costobject,\r\n" + 
			"    inorout";
	
	public static final String balanceSql="SELECT\r\n" + 
			"    num,\r\n" + 
			"    MONEY,\r\n" + 
			"    money,\r\n" + 
			"    costreg,\r\n" + 
			"    costobject\r\n" + 
			"FROM\r\n" + 
			"    ia_accountbalance\r\n" + 
			"WHERE\r\n" + 
			"    accbody=?\r\n" + 
			"AND ia_accountbalance.period=?";
}
