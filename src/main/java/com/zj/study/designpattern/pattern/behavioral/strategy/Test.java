package com.zj.study.designpattern.pattern.behavioral.strategy;

/**
 * Created by geely
 */
public class Test {
	public static void main(String[] args) {
//        PromotionActivity promotionActivity618 = new PromotionActivity(new LiJianPromotionStrategy());
//        PromotionActivity promotionActivity1111 = new PromotionActivity(new FanXianPromotionStrategy());
//
//        promotionActivity618.executePromotionStrategy();
//        promotionActivity1111.executePromotionStrategy();

		String promotionKey = "LIJIAN";
		PromotionStrategy promotionStrategy = PromotionStrategyFactory.getPromotionStrategy(promotionKey);
		promotionStrategy.doPromotion();
	}
}
