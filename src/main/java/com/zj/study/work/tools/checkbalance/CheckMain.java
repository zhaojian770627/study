package com.zj.study.work.tools.checkbalance;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.jdbc.core.JdbcTemplate;

public class CheckMain {

	public static void main(String[] args) throws Exception {

		// 孙宝前
		// String accbody = "2112909038834176";
		// String period = "2021-03";
		// 阚炳垚
		String accbody = "2113050920505856";
		String period = "2021-01";

		AppContext.init();
		Map<String, Item> detailBalance = getIADetails(accbody, period);
		Map<String, Item> balance = getBalance(accbody, period);

//		print(detailBalance);
		System.out.println("-------------------------------------");
//		print(balance);
		compare(detailBalance, balance);
		System.exit(0);
	}

	private static void compare(Map<String, Item> detailBalance, Map<String, Item> balance) {
		Set<String> keys = new HashSet<>();
		detailBalance.keySet().forEach(k -> {
			keys.add(k);
		});

		balance.keySet().forEach(k -> {
			keys.add(k);
		});

		List<CompareItem> listCompareItem = new ArrayList<>();
		keys.forEach(k -> {
			Item detailItem = detailBalance.get(k);
			Item balanceItem = balance.get(k);

			CompareItem compareItem = new CompareItem(detailItem, balanceItem);
			compareItem.Compare();

			listCompareItem.add(compareItem);
		});

		for (CompareItem compareItem : listCompareItem) {
			if (!compareItem.isEqu)
				System.err.println(compareItem);
			else
				System.out.println(compareItem);
		}
	}

	private static Map<String, Item> getBalance(String accbody, String period) throws Exception {
		JdbcTemplate jdbcTemplate = (JdbcTemplate) AppContext.getBean(JdbcTemplate.class);
		List<Map<String, Object>> retList = jdbcTemplate.queryForList(SqlConst.balanceSql, accbody, period);
		Map<String, Item> detailMap = new HashMap<String, Item>();
		if (retList != null && retList.size() > 0) {
			for (Map<String, Object> m : retList) {
				String costreg = m.get("costreg").toString();
				String costobject = m.get("costobject").toString();

				BigDecimal num = m.get("num") != null ? (BigDecimal) m.get("num") : BigDecimal.ZERO;
				BigDecimal money = m.get("money") != null ? (BigDecimal) m.get("money") : BigDecimal.ZERO;

				String key = costreg + "_" + costobject;
				Item item = detailMap.get(key);
				if (item == null) {
					item = new Item(key);
					detailMap.put(key, item);
				} else
					throw new Exception("重复数据!!!!");

				item.addNum(num);
				item.addMoney(money);
			}
		}
		return detailMap;

	}

	private static void print(Map<String, Item> detailBalance) {
		detailBalance.forEach((k, v) -> {
			System.out.println(v.toString());
		});
	}

	private static Map<String, Item> getIADetails(String accbody, String period) {
		JdbcTemplate jdbcTemplate = (JdbcTemplate) AppContext.getBean(JdbcTemplate.class);

		List<Map<String, Object>> retList = jdbcTemplate.queryForList(SqlConst.detailSumSql, accbody, period);
		Map<String, Item> detailMap = new HashMap<String, Item>();
		if (retList != null && retList.size() > 0) {
			for (Map<String, Object> m : retList) {
				String costreg = m.get("costreg").toString();
				String costobject = m.get("costobject").toString();
				String inorout = m.get("inorout").toString();

				BigDecimal num = m.get("num") != null ? (BigDecimal) m.get("num") : BigDecimal.ZERO;
				BigDecimal money = m.get("money") != null ? (BigDecimal) m.get("money") : BigDecimal.ZERO;

				String key = costreg + "_" + costobject;
				Item item = detailMap.get(key);
				if (item == null) {
					item = new Item(key);
					detailMap.put(key, item);
				}

				if (inorout.equals("IN")) {
					item.addNum(num);
					item.addMoney(money);
				} else {
					item.addNum(num.negate());
					item.addMoney(money.negate());
				}
			}
		}
		return detailMap;
	}

	static class Item {
		String key;
		BigDecimal num = BigDecimal.ZERO;
		BigDecimal money = BigDecimal.ZERO;

		public Item(String key) {
			this.key = key;
		}

		public BigDecimal getNum() {
			return num;
		}

		public BigDecimal getMoney() {
			return money;
		}

		public void addNum(BigDecimal num) {
			this.num = this.num.add(num);
		}

		public void addMoney(BigDecimal money) {
			this.money = this.money.add(money);
		}

		@Override
		public String toString() {
			return "Item [key=" + key + ", num=" + num + ", money=" + money + "]";
		}
	}

	static class CompareItem {
		Item detailItem;
		Item balanceItem;

		boolean isEqu = false;

		public CompareItem(Item detailItem, Item balanceItem) {
			this.detailItem = detailItem;
			this.balanceItem = balanceItem;
		}

		public void Compare() {
			if (detailItem == null || balanceItem == null) {
				isEqu = false;
				return;
			}

			if (detailItem.getNum().compareTo(balanceItem.getNum()) == 0
					&& detailItem.getMoney().compareTo(balanceItem.getMoney()) == 0)
				isEqu = true;
		}

		@Override
		public String toString() {
			return "[detailItem=" + detailItem + ", balanceItem=" + balanceItem + ", isEqu=" + isEqu + "]";
		}

	}

}
