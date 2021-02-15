package aop;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import aop.service.FooService;

@SpringBootTest
class SpringAopApplicationTests {
	
	@Autowired
	private FooService fs;

	@Test
	void contextLoads() {
		String actual = this.fs.getProtoBean().printReference();
		String expected = this.prototypeReference();
		
		assertNotNull(actual);
		assertNotNull(expected);
		assertNotEquals(actual, expected);
	}
	
	private String prototypeReference() {
		return fs.getProtoBean().printReference();
	}

}
