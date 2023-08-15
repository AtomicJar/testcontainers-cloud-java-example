# testcontainers-cloud-java-example

The current repository helps you to verify that you configured your [Testcontainers Desktop](https://testcontainers.com/desktop) or Testcontainers Cloud agent correctly in your local environment.
To bootstrap an actual project, please refer to the [Testcontainers Java Quickstart](https://github.com/testcontainers/testcontainers-java-spring-boot-quickstart).

## Clone the repository and run the first Testcontainers test suite

```
git clone https://github.com/AtomicJar/testcontainers-cloud-java-example
cd testcontainers-cloud-java-example
./mvnw test
```

## Run the test suite

`./mvnw test`

### Your environment is correctly configured if

Test output shows the AtomicJar logo and details of the Docker environment used:  

```shell
                                         /
                                       /////////
                                    ///////////////
                                   /////////////////
                                      /////////////
                                     %%   ////   %
                                     %%    //   %%
                                   %%      //      %
                                 %%        ////      %
                                 %     /////////     %
                                  % /////////////// %%
                                    %%%%%%%%%%%%%%%

    /%%%%%%    /%%                             /%%              /%%%%%
   /%%__  %%  | %%                            |__/             |__  %%
  | %%  \ %% /%%%%%%    /%%%%%%  /%%%%%%/%%%%  /%%  /%%%%%%%      | %%  /%%%%%%   /%%%%%%
  | %%%%%%%%|_  %%_/   /%%__  %%| %%_  %%_  %%| %% /%%_____/      | %% |____  %% /%%__  %%
  | %%__  %%  | %%    | %%  \ %%| %% \ %% \ %%| %%| %%       /%%  | %%  /%%%%%%%| %%  \__/
  | %%  | %%  | %% /%%| %%  | %%| %% | %% | %%| %%| %%      | %%  | %% /%%__  %%| %%
  | %%  | %%  |  %%%%/|  %%%%%%/| %% | %% | %%| %%|  %%%%%%%|  %%%%%%/|  %%%%%%%| %%
  |__/  |__/   \___/   \______/ |__/ |__/ |__/|__/ \_______/ \______/  \_______/|__/


  
  Congratulations on running your first test! 🎉
  Environment used: 
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

Congratulations, you can return to the web application to continue your onboarding: 
[app.testcontainers.cloud](https://app.testcontainers.cloud)

# Optional next steps

[Testcontainers Desktop app](https://testcontainers.com/desktop/) helps with common tasks like debugging when working with Testcontainers setups. 
The tests in this project create a PostgreSQL database and populate it with sample data. You can configure [fixed ports](https://newsletter.testcontainers.com/announcements/set-fixed-ports-to-easily-debug-development-services) and use [freeze containers](https://newsletter.testcontainers.com/announcements/freeze-containers-to-prevent-their-shutdown-while-you-debug) features of Testcontainers desktop app to try connecting your IDE to the database like you would when debugging tests.


