# testcontainers-cloud-java-example

This is an example repository with a simple test confirming proper connection from Testcontainers Desktop (or the CI agent) to your [Testcontainers Cloud](https://app.testcontainers.cloud) account.
For details on how to bootstrap Testcontainers in an actual project, please refer to the [Testcontainers Java Quickstart](https://github.com/testcontainers/testcontainers-java-spring-boot-quickstart).

## Clone the repository and run the first Testcontainers test suite

```shell
git clone https://github.com/AtomicJar/testcontainers-cloud-java-example
cd testcontainers-cloud-java-example
make test
```

The `Make` command will run the test suite using `./mvnw test`.


## Confirm your environment is configured correctly

The test output should show the Testcontainers logo and which container runtime was used:  

```shell

████████╗███████╗███████╗████████╗ ██████╗ ██████╗ ███╗   ██╗████████╗ █████╗ ██╗███╗   ██╗███████╗██████╗ ███████╗ 
╚══██╔══╝██╔════╝██╔════╝╚══██╔══╝██╔════╝██╔═══██╗████╗  ██║╚══██╔══╝██╔══██╗██║████╗  ██║██╔════╝██╔══██╗██╔════╝ 
   ██║   █████╗  ███████╗   ██║   ██║     ██║   ██║██╔██╗ ██║   ██║   ███████║██║██╔██╗ ██║█████╗  ██████╔╝███████╗ 
   ██║   ██╔══╝  ╚════██║   ██║   ██║     ██║   ██║██║╚██╗██║   ██║   ██╔══██║██║██║╚██╗██║██╔══╝  ██╔══██╗╚════██║ 
   ██║   ███████╗███████║   ██║   ╚██████╗╚██████╔╝██║ ╚████║   ██║   ██║  ██║██║██║ ╚████║███████╗██║  ██║███████║ 
   ╚═╝   ╚══════╝╚══════╝   ╚═╝    ╚═════╝ ╚═════╝ ╚═╝  ╚═══╝   ╚═╝   ╚═╝  ╚═╝╚═╝╚═╝  ╚═══╝╚══════╝╚═╝  ╚═╝╚══════╝ 
  
  
  Congratulations on running your first test! 🎉
  Runtime used: 
      Testcontainers Cloud
 
  You can now return to the website to complete your onboarding.

[INFO] Tests run: 2, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 19.278 s - in cloud.testcontainers.example.TestcontainersCloudFirstTest
[INFO]
[INFO] Results:
[INFO]
[INFO] Tests run: 2, Failures: 0, Errors: 0, Skipped: 0
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  34.316 s
[INFO] Finished at: 2023-05-16T18:19:21+02:00
[INFO] ------------------------------------------------------------------------
```

## (optional) Use Testcontainers Desktop to easily debug the database

[Testcontainers Desktop](https://testcontainers.com/desktop/) helps developers with common tasks such as debugging your Testcontainers-powered dependencies. Let's practice!

The tests in this project create a PostgreSQL database and populate it with sample data. You can [set a fixed port](https://newsletter.testcontainers.com/announcements/set-fixed-ports-to-easily-debug-development-services) for the `postgres` service, then [freeze containers shutdown](https://newsletter.testcontainers.com/announcements/freeze-containers-to-prevent-their-shutdown-while-you-debug) to easily connect to the database from your IDE after your tests run.

See if you can inspect the database. Username: `test`. Password: `test`.
 