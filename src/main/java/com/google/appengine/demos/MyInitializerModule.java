package com.google.appengine.demos;

import com.google.inject.Inject;
import com.google.inject.persist.PersistService;

/**
 * <p>Description: </p>
 * Date: 2/8/16 - 12:50 PM
 *
 * @author Ruslan Balkin <a href="mailto:baron@baron.su">baron@baron.su</a>
 * @version 1.0.0.0
 */
public class MyInitializerModule {

	@Inject
	public MyInitializerModule(PersistService service) {
		service.start();
	}
}
