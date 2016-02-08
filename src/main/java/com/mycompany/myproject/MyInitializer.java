package com.mycompany.myproject;

import com.google.inject.Inject;
import com.google.inject.persist.PersistService;

/**
 * <p>Description: </p>
 * Date: 2/8/16 - 1:03 PM
 *
 * @author Ruslan Balkin <a href="mailto:baron@baron.su">baron@baron.su</a>
 * @version 1.0.0.0
 */
public class MyInitializer {
	@Inject
	MyInitializer(PersistService service) {
		service.start();
	}
}
