package com.zj.study.framework.spring.lock.mdd.redisson;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.redisson.Redisson;
import org.redisson.RedissonMultiLock;
import org.redisson.api.RKeys;
import org.redisson.api.RLock;

import com.zj.study.framework.spring.lock.mdd.AppContext;
import com.zj.study.framework.spring.lock.mdd.LockFacade;

public class RedissonLockUtil {

	final String module;

	public RedissonLockUtil(String module) {
		this.module = module;
	}

	String concat(String lockey) {
		return LockFacade.lockPrex + module + ":" + lockey;
	}

	private Redisson getClient() {
		return (Redisson) AppContext.getBean("redissonClient");
	}

	// ------------------------------------独占锁---------------------------------------------
	/**
	 * 普通的独占锁
	 * 
	 * @param lockkey
	 * @return
	 */
	public LockContext lock(String lockkey) {
		RLock rlock = genRLock(LockType.NORMAL, lockkey);
		rlock.lock();
		LockContext lockContext = new LockContext();
		lockContext.setLock(rlock);
		lockContext.setKey(lockkey);
		lockContext.setSuccess(true);
		return lockContext;
	}

	public LockContext trylock(String lockkey) {
		RLock rlock = genRLock(LockType.NORMAL, lockkey);
		boolean isLocked = rlock.tryLock();
		LockContext lockContext = new LockContext();
		lockContext.setLock(rlock);
		lockContext.setKey(lockkey);
		lockContext.setSuccess(isLocked);
		return lockContext;
	}

	public LockContext trylock(String lockkey, long time, TimeUnit unit) throws InterruptedException {
		RLock rlock = genRLock(LockType.NORMAL, lockkey);
		boolean isLocked = rlock.tryLock(time, unit);
		LockContext lockContext = new LockContext();
		lockContext.setLock(rlock);
		lockContext.setKey(lockkey);
		lockContext.setSuccess(isLocked);
		return lockContext;
	}

	// ------------------------------------读锁---------------------------------------------
	/**
	 * 添加读锁
	 * 
	 * @param lockkey
	 * @return
	 */
	public LockContext readlock(String lockkey) {
		RLock readLock = genRLock(LockType.READ, lockkey);
		readLock.lock();
		LockContext lockContext = new LockContext();
		lockContext.setLock(readLock);
		lockContext.setKey(lockkey);
		lockContext.setSuccess(true);
		return lockContext;
	}

	public LockContext tryReadlock(String lockkey) {
		RLock readLock = genRLock(LockType.READ, lockkey);
		boolean isLocked = readLock.tryLock();
		LockContext lockContext = new LockContext();
		lockContext.setLock(readLock);
		lockContext.setKey(lockkey);
		lockContext.setSuccess(isLocked);
		return lockContext;
	}

	public LockContext tryReadlock(String lockkey, long time, TimeUnit unit) throws InterruptedException {
		RLock readLock = genRLock(LockType.READ, lockkey);
		boolean isLocked = readLock.tryLock(time, unit);
		LockContext lockContext = new LockContext();
		lockContext.setLock(readLock);
		lockContext.setKey(lockkey);
		lockContext.setSuccess(isLocked);
		return lockContext;
	}

	// ------------------------------------写锁---------------------------------------------
	/**
	 * 添加写锁
	 * 
	 * @param lockkey
	 * @return
	 */
	public LockContext writelock(String lockkey) {
		RLock writeLock = genRLock(LockType.WRITE, lockkey);
		writeLock.lock();
		LockContext lockContext = new LockContext();
		lockContext.setLock(writeLock);
		lockContext.setKey(lockkey);
		lockContext.setSuccess(true);
		return lockContext;
	}

	public LockContext tryWritelock(String lockkey) {
		RLock writeLock = genRLock(LockType.WRITE, lockkey);
		boolean isLocked = writeLock.tryLock();
		LockContext lockContext = new LockContext();
		lockContext.setLock(writeLock);
		lockContext.setKey(lockkey);
		lockContext.setSuccess(isLocked);
		return lockContext;
	}

	public LockContext tryWritelock(String lockkey, long time, TimeUnit unit) throws InterruptedException {
		RLock writeLock = genRLock(LockType.WRITE, lockkey);
		boolean isLocked = writeLock.tryLock(time, unit);
		LockContext lockContext = new LockContext();
		lockContext.setLock(writeLock);
		lockContext.setKey(lockkey);
		lockContext.setSuccess(isLocked);
		return lockContext;
	}

	// ------------------------------------联锁---------------------------------------------
	/**
	 * 普通的独占锁
	 * 
	 * 程序里会对数组进行排序
	 * 
	 * @param lockkey
	 * @return
	 */
	public LockContext mlock(String[] lockkeys) {
		RLock rlock = genRLock(LockType.NORMAL, lockkeys);
		rlock.lock();
		LockContext lockContext = new LockContext();
		lockContext.setLock(rlock);
		lockContext.setKeys(lockkeys);
		lockContext.setSuccess(true);
		return lockContext;
	}

	public LockContext mReadlock(String[] lockkeys) {
		RLock rlock = genRLock(LockType.READ, lockkeys);
		rlock.lock();
		LockContext lockContext = new LockContext();
		lockContext.setLock(rlock);
		lockContext.setKeys(lockkeys);
		lockContext.setSuccess(true);
		return lockContext;
	}

	public LockContext mTryReadlock(String[] lockkeys) {
		RLock rlock = genRLock(LockType.READ, lockkeys);
		boolean isLocked = rlock.tryLock();
		LockContext lockContext = new LockContext();
		lockContext.setLock(rlock);
		lockContext.setKeys(lockkeys);
		lockContext.setSuccess(isLocked);
		return lockContext;
	}

	public LockContext mTryReadlock(String[] lockkeys, long time, TimeUnit unit) throws InterruptedException {
		RLock rlock = genRLock(LockType.READ, lockkeys);
		boolean isLocked = rlock.tryLock(time, unit);
		LockContext lockContext = new LockContext();
		lockContext.setLock(rlock);
		lockContext.setKeys(lockkeys);
		lockContext.setSuccess(isLocked);
		return lockContext;
	}

	public LockContext mWritelock(String[] lockkeys) {
		RLock rlock = genRLock(LockType.WRITE, lockkeys);
		rlock.lock();
		LockContext lockContext = new LockContext();
		lockContext.setLock(rlock);
		lockContext.setKeys(lockkeys);
		lockContext.setSuccess(true);
		return lockContext;
	}

	public LockContext mTryWritelock(String[] lockkeys) {
		RLock rlock = genRLock(LockType.WRITE, lockkeys);
		boolean isLocked = rlock.tryLock();
		LockContext lockContext = new LockContext();
		lockContext.setLock(rlock);
		lockContext.setKeys(lockkeys);
		lockContext.setSuccess(isLocked);
		return lockContext;
	}

	public LockContext mTryWritelock(String[] lockkeys, long time, TimeUnit unit) throws InterruptedException {
		RLock rlock = genRLock(LockType.WRITE, lockkeys);
		boolean isLocked = rlock.tryLock(time, unit);
		LockContext lockContext = new LockContext();
		lockContext.setLock(rlock);
		lockContext.setKeys(lockkeys);
		lockContext.setSuccess(isLocked);
		return lockContext;
	}

	private RedissonMultiLock genRLock(LockType lockType, String[] lockkeys) {
		Arrays.sort(lockkeys);
		RLock[] rlocks = new RLock[lockkeys.length];
		for (int pos = 0; pos < lockkeys.length; pos++) {
			RLock vlock = genRLock(lockType, lockkeys[pos]);
			rlocks[pos] = vlock;
		}

		return new RedissonMultiLock(rlocks);
	}

	private RLock genRLock(LockType lockType, String lockKey) {
		concat(lockKey);
		RLock vlock = null;
		String concatKey = concat(lockKey);
		switch (lockType) {
		case NORMAL:
			vlock = getClient().getLock(concatKey);
			break;
		case READ:
			vlock = getClient().getReadWriteLock(concatKey).readLock();
			break;
		case WRITE:
			vlock = getClient().getReadWriteLock(concatKey).writeLock();
			break;
		default:
			throw new UnsupportedOperationException(lockType.toString() + " is unsupported locktype!");
		}

		return vlock;
	}

	public List<String> listKeys() {
		RKeys keys = getClient().getKeys();
		String pattern = LockFacade.lockPrex + module + ":*";
		List<String> foundedKeysLst = new LinkedList<String>();
		Iterable<String> foundedKeys = keys.getKeysByPattern(pattern);
		foundedKeys.forEach(s -> {
			foundedKeysLst.add(s);
		});
		return foundedKeysLst;
	}
}
