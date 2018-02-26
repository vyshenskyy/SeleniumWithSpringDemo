package sample.framework.spring.selenium;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.support.SimpleThreadScope;
import org.springframework.stereotype.Component;

@Component
public class ThreadScopeRegisteringBeanFactoryPostProcessor implements BeanFactoryPostProcessor, AutoCloseable {
	private Set<Runnable> threadScopeDisposableBeans = new HashSet<Runnable>();

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		beanFactory.registerScope("thread", new SimpleThreadScope() {
			@Override
			public void registerDestructionCallback(String name, Runnable callback) {
				threadScopeDisposableBeans.add(callback);
			}
		});
	}

	@Override
	public void close() throws Exception {
		threadScopeDisposableBeans.forEach(Runnable::run);
	}
}
