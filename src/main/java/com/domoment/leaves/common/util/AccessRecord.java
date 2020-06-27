package com.domoment.leaves.common.util;

import java.io.Serializable;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * service access record 服务器访问记录
 * @author Narule
 *
 */
public class AccessRecord implements Serializable {

	private static Logger logger = LoggerFactory.getLogger(AccessRecord.class);
	
	private static final ConcurrentHashMap<String, AccessRecord> ACCESS_RECORDS = new ConcurrentHashMap<>(0);
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	// 访问IP
	private String ipAddress;
	// 访问总次数
	private int totalAccess;
	// 平均访问间隔
	private long averageAccessTimeGap;

	// 总访问间隔
	private long accessTimeGap;

	// 第一次访问时间
	private long firstAccessTime;

	// 最后访问时间
	private long lastAccessTime;

	// 拒绝访问时间
	private static long _lowDeniedGap = 1000 * 60; // 1minute

	private static long _highDeniedGap = _lowDeniedGap * 30; // 30hours

	// 高危拒绝访问时间
	private static long _dangerGap = _highDeniedGap * 2 * 12; // 30hours

	// 下一次允许访问时间
	private Long nextAccessTime;

	// 是否拒绝
	private boolean denied;

	// 是否高危
	private boolean danger;

	private Integer level;
	
	
	
	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public int getTotalAccess() {
		return totalAccess;
	}

	public void setTotalAccess(int totalAccess) {
		this.totalAccess = totalAccess;
	}

	public long getAverageAccessTimeGap() {
		return averageAccessTimeGap;
	}

	public void setAverageAccessTimeGap(long averageAccessTimeGap) {
		this.averageAccessTimeGap = averageAccessTimeGap;
	}

	public long getAccessTimeGap() {
		return accessTimeGap;
	}

	public void setAccessTimeGap(long accessTimeGap) {
		this.accessTimeGap = accessTimeGap;
	}

	public long getFirstAccessTime() {
		return firstAccessTime;
	}

	public void setFirstAccessTime(long firstAccessTime) {
		this.firstAccessTime = firstAccessTime;
	}

	public long getLastAccessTime() {
		return lastAccessTime;
	}

	public void setLastAccessTime(long lastAccessTime) {
		this.lastAccessTime = lastAccessTime;
	}

	public Long getNextAccessTime() {
		return nextAccessTime;
	}

	public void setNextAccessTime(Long nextAccessTime) {
		this.nextAccessTime = nextAccessTime;
	}

	public boolean isDenied() {
		return denied;
	}

	public void setDenied(boolean denied) {
		this.denied = denied;
	}

	public boolean isDanger() {
		return danger;
	}

	public void setDanger(boolean danger) {
		this.danger = danger;
	}

	private static boolean access(AccessRecord accessRecord) {
		
		Long currentAccessTime = System.currentTimeMillis();
		accessRecord.setLastAccessTime(currentAccessTime);;
		int totalAccess = accessRecord.getTotalAccess();
		totalAccess ++;
		accessRecord.setTotalAccess(totalAccess);
		int levelValue = accessRecord.getLevel();
		
		if(totalAccess > 5) {
				//如果不是被屏蔽
				if(!accessRecord.isDenied()) {
					long accessTimeGap =  currentAccessTime - accessRecord.getFirstAccessTime();
					long averageAccessTimeGap = (accessTimeGap)/(totalAccess - 1);
					accessRecord.setAccessTimeGap(accessTimeGap);
					accessRecord.setAverageAccessTimeGap(averageAccessTimeGap);
					//访问没5秒一次以上
					if(averageAccessTimeGap > 5000) {
					}
					
					//访问没1秒一次以上并且总访问次数不超过100
					else if(averageAccessTimeGap > 1000 && totalAccess < 100) {
						
						
					}else if(averageAccessTimeGap <= 1000) {
							if(totalAccess > 3000) {
								if(averageAccessTimeGap < 400) {
									levelValue = 8;
								}else if(averageAccessTimeGap < 800){
									levelValue = 3;
								}
							} else if(totalAccess > 1000) {
								if(averageAccessTimeGap < 300) {
									levelValue = 12;
								}else if(averageAccessTimeGap < 500){
									levelValue = 8;
								}
							} else if(totalAccess > 200) {
								if(averageAccessTimeGap < 100) {
									levelValue = 15;
								}else if(averageAccessTimeGap < 200){
									levelValue = 8;
								}
							} else if(totalAccess > 20 && averageAccessTimeGap < 100) {
								levelValue = 15;
							}
					}else if(totalAccess > 10000) {
						if(averageAccessTimeGap < 2000) {
							levelValue = 1;
						}else if(averageAccessTimeGap < 1500){
							levelValue = 7;
						}else if(averageAccessTimeGap < 1200) {
							levelValue = 12;
						}
					}
					
				}else {
					// 是否到期
					if(currentAccessTime > accessRecord.getNextAccessTime()) {
						levelValue = 0;
					}
				}
		}
		accessRecord.setLevel(levelValue);
		accessRecord.dealAccessResult(currentAccessTime);
		return accessRecord.isDenied();
	}

	private void dealAccessResult(long currentAccessTime) {
		if(this.level <= 0) {
			this.free();
		}else{
			if(!this.denied) {
				if(this.level > 10) {
					this.accessIsDander(currentAccessTime);
				}else if(this.level > 5){
					this.accessHighDenied(currentAccessTime);
				}else {
					this.accessLowDenied(currentAccessTime);
				}
				logger.info("access denied IP -- " + this.ipAddress 
						+ " | level: " + this.level + " isDanger: " + this.danger );
				
				logger.info("details totalAccess:" + this.totalAccess + " nextAccessTime:" + new Date(this.nextAccessTime).toString()
						+ "averageAccessTimeGap: " + this.averageAccessTimeGap
						);
			}
		}
	}
	
	private void free() {
		this.denied = false;
		this.danger = false;
	}
	
	
	
	private void accessIsDander(long currentAccessTime) {
		this.denied = true;
		this.danger = true;
		this.nextAccessTime = currentAccessTime + _dangerGap;
	}

	private void accessLowDenied(long currentAccessTime) {
		this.denied = true;
		this.nextAccessTime = currentAccessTime + _lowDeniedGap;
	}

	private void accessHighDenied(long currentAccessTime) {
		this.denied = true;
		this.nextAccessTime = currentAccessTime + _highDeniedGap;
	}

	private AccessRecord(String IP) {
		this.totalAccess = 0;
		this.ipAddress = IP;
		this.denied = false;
		this.danger = false;
		this.level = 0;
		this.firstAccessTime = System.currentTimeMillis();
	}

	public static boolean access(String IP) {
		AccessRecord record = ACCESS_RECORDS.get(IP);
		if(record == null) {
			record = new AccessRecord(IP);
			ACCESS_RECORDS.put(IP, record);
		}
		return access(record);
	}
	
	public static String view_access_records() {
		return JsonUtil.objectToJson(ACCESS_RECORDS);
	}

}
