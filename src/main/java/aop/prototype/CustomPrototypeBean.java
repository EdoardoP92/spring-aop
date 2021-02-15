package aop.prototype;

import java.lang.reflect.Method;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.cglib.beans.BeanGenerator;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
/*
 * nel caso si abbia la necessità di iniettare un prototype in un singleton
 * bisogna istruire spring che non dovrà tornare un'istanza del prototype
 * ma un proxy che lo wrappa (CGLIB)
 * Each time the method on the proxy object is called, the proxy decides itself whether 
 * to create a new instance of the real object or reuse the existing one.
 */

public class CustomPrototypeBean {

	private String proxyName;
	
	public String printReference() {
		
		System.out.println("prototype reference: "+this);
		this.createBeanWithCGLIB();
		return this.toString();
	}
	
	private void createBeanWithCGLIB() {
		
		BeanGenerator beanGenerator = new BeanGenerator();
		beanGenerator.addProperty("name", String.class);
		
		Object bean = beanGenerator.create();
		Method setter;
		Method getter;
		try {
			setter = bean.getClass().getMethod("setName", String.class);
			setter.invoke(bean, this+" cglib-name");
			getter = bean.getClass().getMethod("getName");
			String name = (String) getter.invoke(bean);
			System.out.println("NAME BY CGLIB: "+name);
			this.proxyName = name;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getProxyName() {
		return this.proxyName;
	}
}
