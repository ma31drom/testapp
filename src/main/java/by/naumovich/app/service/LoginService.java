package by.naumovich.app.service;

import java.nio.charset.StandardCharsets;

import com.google.common.hash.Hashing;

import by.naumovich.app.dao.model.Credentials;
import by.naumovich.app.dao.model.CredsDTO;
import by.naumovich.app.dao.model.IdAwareObject;

public interface LoginService {

	String login(CredsDTO creds);

	IdAwareObject save(Credentials creds);

	static String toHash(String password) {
		return Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();
	}

}
