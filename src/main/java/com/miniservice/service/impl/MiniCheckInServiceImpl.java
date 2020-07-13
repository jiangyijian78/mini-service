package com.miniservice.service.impl;

import com.google.common.collect.Lists;
import com.miniservice.domain.core.Integral;
import com.miniservice.domain.core.IntegralRecord;
import com.miniservice.domain.core.CheckIn;
import com.miniservice.domain.core.User;
import com.miniservice.domain.dto.IntegralRecordRepository;
import com.miniservice.domain.dto.IntegralRepository;
import com.miniservice.domain.dto.CheckInRepository;
import com.miniservice.domain.dto.UserRepository;
import com.miniservice.dto.request.CheckinReq;
import com.miniservice.dto.response.CheckinResp;
import com.miniservice.exception.ApplicationException;
import com.miniservice.exception.ParameterException;
import com.miniservice.service.MiniCheckInService;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service("miniCheckInService")
public class MiniCheckInServiceImpl implements MiniCheckInService {
	Logger log = LoggerFactory.getLogger(MiniCheckInServiceImpl.class);

	private CheckInRepository checkInRepository;
	private UserRepository userRepository;
	private IntegralRepository integralRepository;
	private IntegralRecordRepository integralRecordRepository;

	public MiniCheckInServiceImpl(CheckInRepository checkInRepository, UserRepository userRepository,
								  IntegralRepository integralRepository, IntegralRecordRepository integralRecordRepository) {
		this.checkInRepository = checkInRepository;
		this.userRepository = userRepository;
		this.integralRepository = integralRepository;
		this.integralRecordRepository = integralRecordRepository;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public CheckinResp checkin(CheckinReq request) throws ApplicationException {
		CheckinResp checkinResp = new CheckinResp();
		try {
			saveCheckRequest(request);
			checkinResp.setCheckInFlag(true);
			checkinResp.setMessage("success");
		} catch (ApplicationException e) {
			checkinResp.setCheckInFlag(false);
			checkinResp.setMessage(e.getMessage());
		} catch (Exception e) {
			log.error("system error: ", e);
			throw new ApplicationException("system error");
		}

		return checkinResp;
	}

	/**
	 * 为了防止重复提交，添加方法锁 如果有分布式架构， 请使用分布式锁
	 */
	private synchronized void saveCheckRequest(CheckinReq request) throws ApplicationException {
        User user = findByPhone(request.getPhone());
		Integral integral = findByUser(user.getId());
		List<CheckIn> lastCheckIns = findLastSignInToday(user.getId());
		if (!CollectionUtils.isEmpty(lastCheckIns)) {
			if (lastCheckIns.size() >= 3) {
				throw new ApplicationException("check in max 3 times per day");
			} else if (lastCheckIns.get(0).getCheckInTime().getTime() - request.getCheckInTime() <= 4 * 60 * 60 * 1000l
					* 1000l) {
				throw new ApplicationException("must over 4 hours between two check in");
			}
		}
		/**
		 * 签到
		 */
		checkInRepository.save(convertSignin(request, user));
		int num = integralRepository.addIntegral(user.getId(), integral.getIntegral());
		if (num != 1) {
		    throw new ApplicationException("update integral error, please try agin later ");
        }
		integralRecordRepository.save(generateIntegralRecord(user.getId()));
	}

	private IntegralRecord generateIntegralRecord(Long userId) {
		return new IntegralRecord.Builder()
				.setIntegralIncrease(10L)
				.setReason("sign in")
				.setType(1)
				.setUserId(userId)
				.build();
	}

	private CheckIn convertSignin(CheckinReq request, User user) {
		return new CheckIn.Builder().userId(user.getId())
                .formId(request.getFormId())
                .checkInTime(new Date(request.getCheckInTime()))
                .build();
	}

	private Integral findByUser(Long userId) throws ParameterException {
		if (!Optional.ofNullable(userId).isPresent()) {
			throw new ParameterException("userid can not be null");
		}
		Integral integral = new Integral.Builder()
				.setUserId(userId)
				.build();
		Example<Integral> example = Example.of(integral);
		Optional<Integral> integralRepositoryOne = integralRepository.findOne(example);
		return integralRepositoryOne.get();
	}

	private User findByPhone(String phone) throws ApplicationException {
		if (!Optional.ofNullable(phone).isPresent()) {
			throw new ParameterException("phone can not be null");
		}
		User user = new User.Builder()
				.setPhone(phone)
				.build();
		Example<User> example = Example.of(user);
		Optional<User> userOptional = userRepository.findOne(example);
        if (!userOptional.isPresent()) {
            throw new ApplicationException("user not exists");
        }
		return userOptional.get();
	}

	private List<CheckIn> findLastSignInToday(Long userId) {
		List<CheckIn> checkIns = checkInRepository.findByUserId(userId, getDay(new Date()),
				getDay(DateUtils.addDays(new Date(), 1)));
		if (CollectionUtils.isEmpty(checkIns)) {
			return Lists.newArrayListWithExpectedSize(0);
		}
		return checkIns;
	}

	private Date getDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}
}
