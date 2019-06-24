package by.naumovich.app.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import by.naumovich.app.dao.jpa.CredsRepo;
import by.naumovich.app.dao.model.Credentials;
import by.naumovich.app.dao.model.CredsDTO;
import by.naumovich.app.dao.model.IdAwareObject;
import by.naumovich.app.excep.Unauthorized;
import by.naumovich.app.excep.UserNotFoundException;
import by.naumovich.app.filter.TokenRegFilter;
import by.naumovich.app.service.LoginService;

@Service
public class CredsServiceImpl implements LoginService {
	@Autowired
	CredsRepo repo;

	public static Map<String, UserAndDate> lastActionMap = new HashMap<String, CredsServiceImpl.UserAndDate>();

	public CredsServiceImpl(@Value("${token.tllSeconds}") Integer ttl) {
		CredsServiceImpl.ttl = ttl;
	}

	public static Integer ttl;

	@Override
	public String login(CredsDTO creds) {

		Credentials credentials = new Credentials();
		credentials.setPassHash(creds.getPassHash());
		credentials.setUserName(creds.getUserName());

		Example<Credentials> ex = Example.of(credentials);

		Optional<Credentials> findOne = repo.findOne(ex);
		if (findOne.isPresent()) {

			String string = UUID.randomUUID().toString();
			lastActionMap.put(string, new UserAndDate(findOne.get().getUserId(), new Date()));
			return string;
		}
		throw new UserNotFoundException();
	}

	public static Integer getUserId() {
		String token = TokenRegFilter.tokenLocal.get();
		if (token == null) {
			throw new Unauthorized();
		}

		UserAndDate userAndDate = lastActionMap.get(token);
		if (userAndDate == null) {
			throw new Unauthorized();
		}
		if ((new Date().getTime() - userAndDate.date.getTime()) / 1000 > ttl) {
			lastActionMap.remove(token);
			throw new Unauthorized();
		}
		userAndDate.setDate(new Date());

		return userAndDate.userId;
	}

	public static class UserAndDate {
		private Integer userId;
		private Date date;

		public UserAndDate(Integer userId, Date date) {
			super();
			this.userId = userId;
			this.date = date;
		}

		public Integer getUserId() {
			return userId;
		}

		public void setUserId(Integer userId) {
			this.userId = userId;
		}

		public Date getDate() {
			return date;
		}

		public void setDate(Date date) {
			this.date = date;
		}

	}

	@Override
	public IdAwareObject save(Credentials creds) {
		return repo.save(creds);
	}

}
