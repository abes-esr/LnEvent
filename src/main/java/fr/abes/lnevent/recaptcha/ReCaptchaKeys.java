package fr.abes.lnevent.recaptcha;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Getter @Setter
@ConfigurationProperties(prefix = "google.recaptcha.key")
public class ReCaptchaKeys {

    private String site;
    private String secret;
    private float threshold;
}
