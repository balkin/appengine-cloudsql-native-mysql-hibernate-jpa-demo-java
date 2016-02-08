package com.google.appengine.demos;

import com.google.inject.Inject;
import com.google.inject.persist.PersistService;
import com.google.inject.servlet.ServletModule;

/**
 * <p>Description: </p>
 * Date: 2/8/16 - 12:55 PM
 *
 * @author Ruslan Balkin <a href="mailto:baron@baron.su">baron@baron.su</a>
 * @version 1.0.0.0
 */
public class MyServletModule extends ServletModule {

	@Override protected void configureServlets() {
		serve("/*").with(HibernateJpaServlet.class);
	}
}