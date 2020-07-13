package com.miniservice.service.impl;

import com.miniservice.cache.TokenSingleton;
import com.miniservice.constant.ResultEnum;
import com.miniservice.domain.core.Integral;
import com.miniservice.domain.core.IntegralRecord;
import com.miniservice.domain.core.User;
import com.miniservice.domain.dto.IntegralRecordRepository;
import com.miniservice.domain.dto.IntegralRepository;
import com.miniservice.domain.dto.UserRepository;
import com.miniservice.dto.model.IntegralDetail;
import com.miniservice.dto.request.LoginReq;
import com.miniservice.dto.request.RegisteredReq;
import com.miniservice.dto.response.LoginResp;
import com.miniservice.dto.response.PointListResp;
import com.miniservice.dto.response.PointQueryResp;
import com.miniservice.dto.response.RegisteredResp;
import com.miniservice.exception.ApplicationException;
import com.miniservice.exception.ParameterException;
import com.miniservice.service.MiniService;
import com.miniservice.util.DateTimeUtil;
import com.miniservice.util.PasswordUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service("miniService")
public class MiniServiceImpl implements MiniService {
	Logger log = LoggerFactory.getLogger(MiniServiceImpl.class);

	private UserRepository userRepository;
	private IntegralRepository integralRepository;
	private IntegralRecordRepository integralRecordRepository;

	public MiniServiceImpl(UserRepository userRepository, IntegralRepository integralRepository,
			IntegralRecordRepository integralRecordRepository) {
		this.userRepository = userRepository;
		this.integralRepository = integralRepository;
		this.integralRecordRepository = integralRecordRepository;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public RegisteredResp registered(RegisteredReq request) throws ApplicationException {
		if (userRepository.findByPhone(request.getPhone()) != null) {
			throw new ApplicationException(ResultEnum.PHONE_EXISTED);
		}

        User user = new User.Builder()
                .setName(request.getUserName())
                .setPassword(PasswordUtil.passwordHash(request.getPassword()))
                .setPhone(request.getPhone())
                .setOpenId(request.getOpenId())
                .setUnionId(request.getUnionId())
                .build();
        User saved = userRepository.save(user);

        IntegralRecord record = new IntegralRecord.Builder()
                .setUserId(saved.getId())
                .setIntegralIncrease(1000L)
                .setReason("Register")
				.setType(1)
                .build();
        integralRecordRepository.save(record);

        Integral integral = new Integral.Builder()
                .setIntegral(1000L)
                .setUserId(saved.getId())
                .build();
        integralRepository.save(integral);
        return new RegisteredResp();
    }

	@Override
	public LoginResp loginByPassword(LoginReq request) throws ApplicationException {
		String userName = request.getUserName();
		String password = request.getPassword();

		// 查询用户
		User user = userRepository.findByPhone(userName);
		if (null == user) {
			log.error("Can not find User by {}", userName);
			throw new ApplicationException(ResultEnum.INVALID_USER);
		}

		// 验证密码
		String passwordHash = PasswordUtil.passwordHash(password);
		if (!passwordHash.equalsIgnoreCase(user.getPassword())) {
			log.error("password invalid");
			throw new ApplicationException(ResultEnum.INVALID_USER);
		}
		// 生成token
		String token = UUID.randomUUID().toString().replace("-", "");
		// 缓存token
		TokenSingleton.getInstance().cacheByToken(token, userName);

		LoginResp response = new LoginResp();
		response.setAuthToken(token);
		return response;
	}

	@Override
	public PointQueryResp queryPoint(String token) throws ApplicationException {
		Optional<String> value = TokenSingleton.getInstance().findByToken(token);
		if (!value.isPresent()) {
			log.error("Token expired.");
			throw new ParameterException(ResultEnum.INVALID_TOKEN);
		}

		Optional<User> user = Optional.ofNullable(userRepository.findByPhone(value.get()));
		if (!user.isPresent()) {
			log.error("Can not find User by {}", user);
			throw new ApplicationException(ResultEnum.INVALID_USER);
		}
		Integral integral = integralRepository.findByUserId(user.get().getId());
		if (integral == null) {
			throw new ApplicationException("User not found.");
		}
		return new PointQueryResp(integral.getIntegral());
	}

	@Override
	public PointListResp queryPointList(String token) throws ApplicationException {
		Optional<String> value = TokenSingleton.getInstance().findByToken(token);
		if (!value.isPresent()) {
			log.error("Token expired.");
			throw new ParameterException(ResultEnum.INVALID_TOKEN);
		}

		Optional<User> user = Optional.ofNullable(userRepository.findByPhone(value.get()));
		if (!user.isPresent()) {
			log.error("Can not find User by {}", user);
			throw new ApplicationException(ResultEnum.INVALID_USER);
		}

		// 查询积分列表，根据指定参数
		List<IntegralRecord> list = integralRecordRepository.findByUserId(user.get().getId());

		List<IntegralDetail> data = new ArrayList<>();
		list.stream().sorted((s1, s2) -> {
			return s1.getUpdateTime().compareTo(s2.getUpdateTime());
		}).forEach(t -> {
			IntegralDetail detail = new IntegralDetail();
			detail.setIntegral(t.getIntegralIncrease());
			detail.setReason(t.getReason());
			detail.setTime(DateTimeUtil.formatDateTime2String(t.getUpdateTime(), "yyyy-MM-dd HH:mm:ss"));
			detail.setType(t.getType());
			data.add(detail);
		});

		PointListResp response = new PointListResp();
		response.setData(data);
		return response;
	}
}
