package by.naumovich.app.web;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import by.naumovich.app.dao.model.Credentials;
import by.naumovich.app.dao.model.CredsDTO;
import by.naumovich.app.dao.model.IdAwareObject;
import by.naumovich.app.dao.validation.UserExists;
import by.naumovich.app.service.LoginService;

@RestController
public class CredsController extends ErrorHandlingController {

    @Autowired
    private LoginService serv;

    @PostMapping("/login")
    @ResponseBody
    public String login(@RequestParam String userName, @RequestParam String password) {
        String password2 = LoginService.toHash(password);
        return serv.login(new CredsDTO(userName, password2));
    }

    @PostMapping("/users/{id}/creds")
    @ResponseBody
    public IdAwareObject save(@PathVariable("id") @UserExists Integer userId, @RequestBody CredsPlainDTO creds) {

        String password2 = LoginService.toHash(creds.getPassword());

        Credentials credsToSave = new Credentials();
        credsToSave.setPassHash(password2);
        credsToSave.setUserName(creds.userName);
        credsToSave.setUserId(userId);

        IdAwareObject target = new IdAwareObject();
        BeanUtils.copyProperties(serv.save(credsToSave), target);

        return target;
    }

    public static class CredsPlainDTO {

        private String userName;

        private String password;

        public CredsPlainDTO() {
            super();
        }

        public CredsPlainDTO(String userName, String password) {
            super();
            this.userName = userName;
            this.password = password;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

    }

}
