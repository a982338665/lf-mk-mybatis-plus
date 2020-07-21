
## 来源：

    作者：程序员糖糖
    链接：https://zhuanlan.zhihu.com/p/161947638
    来源：知乎
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

## 一、pom文件中引入Swagger3依赖
   
    <dependency>
         <groupId>io.springfox</groupId>
          <artifactId>springfox-boot-starter</artifactId>
          <version>3.0.0</version>
   
    </dependency>
## 二、Application上面加入@EnableOpenApi注解@EnableOpenApi

    @SpringBootApplication
    @MapperScan(basePackages = {"cn.ruiyeclub.dao"})
    public class Swagger3Application {
        public static void main(String[] args) {
            SpringApplication.run(Swagger3Application.class, args);
        }
    }

## 三、Swagger3Config的配置@Configuration

    public class Swagger3Config {
        @Bean
        public Docket createRestApi() {
            return new Docket(DocumentationType.OAS_30)
                    .apiInfo(apiInfo())
                    .select()
                    .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                    .paths(PathSelectors.any())
                    .build();
        }
    
        private ApiInfo apiInfo() {
            return new ApiInfoBuilder()
                    .title("Swagger3接口文档")
                    .description("更多请咨询服务开发者Ray。")
                    .contact(new Contact("Ray。", "http://www.ruiyeclub.cn", "ruiyeclub@foxmail.com"))
                    .version("1.0")
                    .build();
        }
    }
    
## 四、Swagger注解的使用说明@Api：用在请求的类上，表示对类的说明
   
    tags="说明该类的作用，可以在UI界面上看到的注解"
    value="该参数没什么意义，在UI界面上也看到，所以不需要配置"

    @ApiOperation：用在请求的方法上，说明方法的用途、作用
        value="说明方法的用途、作用"
        notes="方法的备注说明"
    
    @ApiImplicitParams：用在请求的方法上，表示一组参数说明
        @ApiImplicitParam：用在@ApiImplicitParams注解中，指定一个请求参数的各个方面
            name：参数名
            value：参数的汉字说明、解释
            required：参数是否必须传
            paramType：参数放在哪个地方
                · header --> 请求参数的获取：@RequestHeader
                · query --> 请求参数的获取：@RequestParam
                · path（用于restful接口）--> 请求参数的获取：@PathVariable
                · div（不常用）
                · form（不常用）    
            dataType：参数类型，默认String，其它值dataType="Integer"       
            defaultValue：参数的默认值
    
    @ApiResponses：用在请求的方法上，表示一组响应
        @ApiResponse：用在@ApiResponses中，一般用于表达一个错误的响应信息
            code：数字，例如400
            message：信息，例如"请求参数没填好"
            response：抛出异常的类
    
    @ApiModel：用于响应类上，表示一个返回响应数据的信息
                （这种一般用在post创建的时候，使用@RequestBody这样的场景，
                请求参数无法使用@ApiImplicitParam注解进行描述的时候）
        @ApiModelProperty：用在属性上，描述响应类的属性
    Controller层的配置：@Api(tags = "用户信息管理")
    @RestController
    @RequestMapping("userRecord")
    public class UserRecordController extends ApiController {
        /**
         * 服务对象
         */
        @Resource
        private UserRecordService userRecordService;
    
        /**
         * 分页查询所有数据
         * @param page       分页对象
         * @param userRecord 查询实体
         * @return 所有数据
         */
        @ApiOperation("分页查询所有数据")
        @GetMapping("page")
        public R selectAll(Page<UserRecord> page, UserRecord userRecord) {
            return success(this.userRecordService.page(page, new QueryWrapper<>(userRecord)));
        }
    
        /**
         * 通过主键查询单条数据
         * @param id 主键
         * @return 单条数据
         */
        @ApiOperation("通过主键查询单条数据")
        @GetMapping("{id}")
        public R selectOne(@PathVariable Serializable id) {
            return success(this.userRecordService.getById(id));
        }
    
        /**
         * 新增数据
         * @param userRecord 实体对象
         * @return 新增结果
         */
        @ApiOperation("新增数据")
        @PostMapping("insert")
        public R insert(@RequestBody UserRecord userRecord) {
            return success(this.userRecordService.save(userRecord));
        }
    
        /**
         * 修改数据
         * @param userRecord 实体对象
         * @return 修改结果
         */
        @ApiOperation("修改数据")
        @PutMapping("update")
        public R update(@RequestBody UserRecord userRecord) {
            return success(this.userRecordService.updateById(userRecord));
        }
    
        /**
         * 删除数据
         * @param idList 主键结合
         * @return 删除结果
         */
        @ApiOperation("删除数据")
        @DeleteMapping("delete")
        public R delete(@RequestParam("idList") List<Long> idList) {
            return success(this.userRecordService.removeByIds(idList));
        }
    }

## 五、Swagger界面效果Swagger的访问路径由port/swagger-ui.html改成了 port/swagger-ui/ 或port/swagger-ui/index.html ，项目演示代码在 springboot-swagger
