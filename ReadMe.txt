This is a Maven based framework

Pre Requisites
selenium jars to run locally (copy JAR folder to your C:\) - jars needs to be running to enable selenium tests to run!
selenium associated drivers for the browsers
cucumber pluggin for your your IDE

src/test/resources: contains your feature file of what you want to test, your scenario (s). 
					I would recomend having 1 feature file per page

src/test/java/stepdefs: this contains the glue copde that maps your feature file to your methods in the "webconnector class"
						Right click runner to excecute the test
						
			/testconfig: contains Key URL's of pages
			/OR.properties: contains xpaths or element ID's for assertions and references elements to click on			