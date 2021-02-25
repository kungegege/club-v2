## 问题1
> HandlerInterceptor里@Autowired对象为空
```markdown
    # 解决方法
       原因：spring对象注入时机在拦截器之后
    用配置文件注入：
    
    @Configuration
    public class InterceptorConfig implements WebMvcConfigurer {
    
        @Override
        public void addInterceptors(InterceptorRegistry registry) {
            registry.addInterceptor(tokenInterceptor())
                    .addPathPatterns("/user/**");
        }
    
        @Bean
        public TokenInterceptor tokenInterceptor(){
            return  new TokenInterceptor();
        }
    }
```

## 问题2
> idea 打开 markdown 文件 死机
```markdown
    # 解决方法
    下载了一个plugs，禁用了原来的markdown插件 
```

