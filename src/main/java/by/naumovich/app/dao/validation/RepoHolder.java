package by.naumovich.app.dao.validation;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import by.naumovich.app.dao.jpa.BrandRepo;
import by.naumovich.app.dao.jpa.ModelRepo;

@Component
public class RepoHolder implements ApplicationContextAware {

    private static BrandRepo brandRepo;
    private static ModelRepo modelRepo;

    public static BrandRepo brandRepo() {
        return brandRepo;
    }

    public static ModelRepo modelRepo() {
        return modelRepo;
    }

    @Override
    public void setApplicationContext(ApplicationContext arg0) throws BeansException {
        brandRepo = arg0.getBean(BrandRepo.class);
        modelRepo = arg0.getBean(ModelRepo.class);

    }

}
