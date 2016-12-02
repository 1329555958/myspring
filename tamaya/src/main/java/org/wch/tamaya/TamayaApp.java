package org.wch.tamaya;

import org.apache.tamaya.Configuration;
import org.apache.tamaya.ConfigurationProvider;
import org.apache.tamaya.inject.ConfigurationInjection;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.wch.tamaya.bean.User;

/**
 * @author weichunhe
 *         Created on 2016/11/28.
 * @version 1.0
 */
@SpringBootApplication
public class TamayaApp {
    public static void main(String[] args) {
        SpringApplication.run(TamayaApp.class, args);
        main2(null);
    }

    public static void main2(String[] args) {
        Configuration configuration = ConfigurationProvider.getConfiguration();
        System.out.println(configuration.get("User.age"));

        User user = new User();

        user = ConfigurationInjection.getConfigurationInjector().createTemplate(User.class);
        System.out.println(user.getName());
    }
}
