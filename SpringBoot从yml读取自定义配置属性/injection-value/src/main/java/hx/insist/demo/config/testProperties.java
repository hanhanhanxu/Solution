package hx.insist.demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @ClassName testProperties
 * @Description //TODO 使用application.yml文件注入属性
 * @Author 123
 * @Date 2019/8/12 10:05
 * @Version 1.0
 **/
@Data
@ConfigurationProperties(prefix = "hx.ctj")
public class testProperties {
    private String name;
    private String pro;
    private List hlist;
    private String hstr;
}
