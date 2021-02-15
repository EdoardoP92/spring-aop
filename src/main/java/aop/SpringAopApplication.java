package aop;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import aop.service.FooService;

@SpringBootApplication
@EnableAspectJAutoProxy
public class SpringAopApplication {

	@Autowired
	private FooService fs;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringAopApplication.class, args);
	}
	
	@PostConstruct
	public void init() {
		this.fs.voidAspectPoc("");
		this.fs.voidAspectPoc(null);
//		this.fs.getProtoBean().printReference();
//		this.fs.getProtoBean().printReference();
	}

}
