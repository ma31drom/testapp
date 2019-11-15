package by.naumovich.app.dao.validation;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import by.naumovich.app.dao.jpa.BrandRepo;
import by.naumovich.app.dao.jpa.CarRepo;
import by.naumovich.app.dao.jpa.LicenceRepo;
import by.naumovich.app.dao.jpa.ModelRepo;
import by.naumovich.app.dao.jpa.UserRepo;

@Component
public class RepoHolder implements ApplicationContextAware {

    private static BrandRepo brandRepo;
    private static ModelRepo modelRepo;
    private static CarRepo carRepo;
    private static UserRepo userRepo;
    private static LicenceRepo userLicenceRepo;

    public static BrandRepo brandRepo() {
        return brandRepo;
    }

    public static LicenceRepo userLicenceRepo() {
        return userLicenceRepo;
    }

    public static ModelRepo modelRepo() {
        return modelRepo;
    }

    public static CarRepo carRepo() {
        return carRepo;
    }

    public static UserRepo userRepo() {
        return userRepo;
    }

    @Override
    public void setApplicationContext(ApplicationContext arg0) throws BeansException {
        brandRepo = arg0.getBean(BrandRepo.class);
        modelRepo = arg0.getBean(ModelRepo.class);
        carRepo = arg0.getBean(CarRepo.class);
        userRepo = arg0.getBean(UserRepo.class);
        userLicenceRepo = arg0.getBean(LicenceRepo.class);
    }

}
