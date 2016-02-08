/**
 * Copyright 2012 Google Inc. All Rights Reserved.
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.appengine.demos;

import com.google.appengine.api.utils.SystemProperty;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.persist.PersistService;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Singleton
public class HibernateJpaServlet extends HttpServlet {

	@Inject
	EntityManager entityManager;

	@Inject
	PersistService persistService;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		res.setContentType("text/plain");

		Map<String, String> properties = new HashMap<>();
		if (SystemProperty.environment.value() ==
				SystemProperty.Environment.Value.Production) {
			properties.put("javax.persistence.jdbc.driver",
					"com.mysql.jdbc.GoogleDriver");
			properties.put("javax.persistence.jdbc.url",
					System.getProperty("cloudsql.url"));
		} else {
			properties.put("javax.persistence.jdbc.driver",
					"com.mysql.jdbc.Driver");
			properties.put("javax.persistence.jdbc.url",
					System.getProperty("cloudsql.url.dev"));
		}

		res.getWriter().println(entityManager == null ? "NULL" : entityManager.toString());
		res.getWriter().println(persistService == null ? "NULL" : persistService.toString());


		entityManager.getTransaction().begin();
		entityManager.persist(new Greeting("user", new Date(), "Hello!"));
		entityManager.persist(new Greeting("user", new Date(), "Hi!"));
		entityManager.getTransaction().commit();
/*
    // List all the rows.
    em = emf.createEntityManager();
    em.getTransaction().begin();
    List<Greeting> result = em
        .createQuery("FROM Greeting", Greeting.class)
        .getResultList();
    for (Greeting g : result) {
      res.getWriter().println(
          g.getId() + " " +
          g.getAuthor() + "(" + g.getDate() + "): " +
          g.getContent());
    }
    em.getTransaction().commit();
    em.close();
	   */
	}
}
