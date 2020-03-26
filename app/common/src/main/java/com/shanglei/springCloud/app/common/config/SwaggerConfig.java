package com.shanglei.springCloud.app.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * Swagger2 配置类
 * @author zhouqi
 * @date  13:31
 * @version v1.0.0
 * @Description
 *
 * Modification History:
 * Date                 Author          Version          Description
 ---------------------------------------------------------------------------------*
 *  13:31     zhouqi          v1.0.0           Created
 *
 */
@EnableSwagger2
@Configuration
public class SwaggerConfig {

    /**
     * Swagger2 配置
     * @return
     */
    @Bean
    public Docket customDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(this.apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.mingbyte.baoneng"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(this.globalOperation());

    }

    private List<Parameter> globalOperation(){
        //添加head参数配置start
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        //第一个token为传参的key，第二个token为swagger页面显示的值
        tokenPar.name("Authorization").description("Authorization").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        pars.add(tokenPar.build());

        return pars;
    }

    /**
     * 设置基础信息
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("宝能")
                .contact(new Contact("zhouqi", "", "zhouqi@mingbyte.com"))
                .version("1.0.0")
                .description("农产品溯源")
                .build();
    }




}
