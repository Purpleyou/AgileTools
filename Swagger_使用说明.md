Swagger 是一套围绕 OpenAPI 规格说明建立的开源工具，可以帮助开发人员设计，构建，撰写文档以及使用
REST APIs. 主要的 Swagger 工具有三种：
* Swagger Editor – 基于浏览器的用于编辑 OpenAPI specs 的编辑器。
* Swagger UI – 以交互式API文档的形式呈现 OpenAPI specs.
* Swagger Codegen – 从 OpenAPI spec 生成服务器存根以及客户端库。

OpenAPI Specification 是一种 REST APIs 的 API 描述格式。一个OpenAPI文件能够完整地描述一个API，
包括：
* 可用的endpoints (/users) 以及每个endpoint上的操作(GET /users, POST /users)
* 操作参数，每次操作的输入输出
* 认证方式
* 联系信息，license, 用户条款等等

API specifications 可以用YAML or JSON 格式书写.这样人与机器都方便阅读。
完整的 [OpenAPI Specification 参见此处](https://github.com/OAI/OpenAPI-Specification/blob/master/versions/3.0.0.md)

使用OpenAPI的好处：
* 使用 Swagger Codegen 为API生成服务器存根。接下来只要实现服务器逻辑就好，API随时可用。
* 使用 Swagger Codegen 可以用超过40种语言为API生成客户端库。
* 使用 Swagger UI 生成交互式API文档，让用户可以直接在浏览器中试用API调用。
* 使用 spec 将与API相关的工具与API连接起来。例如，将spec导入SoapUI, 为API创建自动化测试。

这里我们将Swagger与Spring Boot 相结合进行demo, 在开发的过程中添加进说明内容， 同时构建RESTful API 及用于记录所有接口细节的文档。
使用要点如下：

1. 在POM文件中添加Swagger2依赖：
    ```
    <!--SpringFox dependencies -->
    <dependency>
        <groupId>io.springfox</groupId>
        <artifactId>springfox-swagger2</artifactId>
        <version>2.7.0</version>
    </dependency>
    <dependency>
        <groupId>io.springfox</groupId>
        <artifactId>springfox-swagger-ui</artifactId>
        <version>2.7.0</version>
    </dependency>
    <dependency>
        <groupId>com.github.joschi.jackson</groupId>
        <artifactId>jackson-datatype-threetenbp</artifactId>
        <version>2.6.4</version>
    </dependency>
    <!-- Bean Validation API support -->
    <dependency>
        <groupId>javax.validation</groupId>
        <artifactId>validation-api</artifactId>
        <version>1.1.0.Final</version>
        <scope>provided</scope>
    </dependency>
    ```

   注意： 由于Swagger不同版本的使用方法各有不同，请参考[Swagger Github 的官方样例](https://github.com/swagger-api/swagger-codegen/tree/master/samples/server/petstore/springboot)

   2. 创建Swagger2配置类：
   ```
    ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("Swagger API Documentation")
            .description("This spec is mainly for testing the Swagger SpringBoot Plugin.")
            .license("Apache-2.0")
            .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
            .termsOfServiceUrl("")
            .version("1.0.0")
            .contact(new Contact("","", "apiteam@swagger.io"))
            .build();
    }

    @Bean
    public Docket customImplementation(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                    .apis(RequestHandlerSelectors.basePackage("com.clec.agile.toolsdemo"))
                    .build()
                .directModelSubstitute(org.threeten.bp.LocalDate.class, java.sql.Date.class)
                .directModelSubstitute(org.threeten.bp.OffsetDateTime.class, java.util.Date.class)
                .apiInfo(apiInfo());
    }
    ```
    
   使用SpringBoot中的@Configuration注解，让Spring来加载该类配置，以及@EnableSwagger2来启用Swagger2。
   apiInfo()可用于添加基本信息。
   RequestHandlerSelectors.basePackage()用于指定暴露给Swagger来展示的接口，Swagger会为该包下所有Controller定义的API生产文档。

3. 添加文档内容：
@ApiOperation注解给API增加说明
@ApiImplicitParams注解给参数增加说明

4. API文档的访问及调试：
启动Spring Boot程序，访问：http://localhost:8080/swagger-ui.html