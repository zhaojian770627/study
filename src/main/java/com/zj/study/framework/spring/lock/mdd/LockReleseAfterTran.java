package com.zj.study.framework.spring.lock.mdd;

import org.springframework.transaction.support.TransactionSynchronizationAdapter;

/**
 * 事务完成后不管成功与失败，释放锁
 * 
 * @author zj
 *
 */
public class LockReleseAfterTran extends TransactionSynchronizationAdapter {

	private LockContext lockContext;

	public LockReleseAfterTran(LockContext lockContext) {
		this.lockContext = lockContext;
	}

	@Override
	public void afterCompletion(int status) {
		super.afterCompletion(status);
		lockContext.unlock();
	}
}
