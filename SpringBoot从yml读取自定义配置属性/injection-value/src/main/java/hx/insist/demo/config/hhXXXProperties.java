package hx.insist.demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @ClassName hhXXXProperties
 * @Description //TODO
 * @Author 123
 * @Date 2019/8/12 10:44
 * @Version 1.0
 **/
@Data
@ConfigurationProperties(prefix = "hh.xxx")
public class hhXXXProperties {
    private String name;
    private Integer sg;
    private Integer tz;
    private Integer jj;
}
