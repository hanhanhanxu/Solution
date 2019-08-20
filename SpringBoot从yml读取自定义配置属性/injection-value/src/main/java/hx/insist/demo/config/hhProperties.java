package hx.insist.demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @ClassName hhProperties
 * @Description //TODO
 * @Author 123
 * @Date 2019/8/12 10:24
 * @Version 1.0
 **/
@Data
@ConfigurationProperties(prefix = "hh")
public class hhProperties {
    private List hlist;
    private hhXXXProperties xxx;
}
