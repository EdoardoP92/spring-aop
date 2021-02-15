package aop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import aop.prototype.CustomPrototypeBean;

@Component //needed for aop
public class FooService {

	private CustomPrototypeBean protoBean;
	
	@Autowired
	public FooService(CustomPrototypeBean protoBean) {
		super();
		this.protoBean = protoBean;
	}

	public void voidAspectPoc(String arg) {
		arg.length();
		System.out.println("voidAspectPoc()");
	}

	public CustomPrototypeBean getProtoBean() {
		return protoBean;
	}

}
