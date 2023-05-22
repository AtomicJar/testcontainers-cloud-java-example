# testcontainers-cloud-java-example

The current repository helps you to verify that you configured your [Testcontainers Cloud][tcc] agent correctly in your local environment.
To bootstrap an actual project, please refer to the [Testcontainers Java Quickstart](https://github.com/testcontainers/testcontainers-java-spring-boot-quickstart).

## Clone the repository and run the first Testcontainer test suite

```
git clone https://github.com/AtomicJar/testcontainers-cloud-java-example
cd testcontainers-cloud-java-example
./mvnw test
```

## Verify the agent is running

✅ __Passive State__: Agent awaiting a Testcontainers test to be executed.

![agent-running](./docs/passive-connection.png)

✅ __Running State__: Agent connected to Testcontainers Cloud.

![agent-running](./docs/active-connection.png)

⚠️ __Stopped State__: Agent is stopped and will not accept connections.

Please, Start the agent to continue.

![agent-stopped](./docs/stopped.png)

To download the agent for local usage, check the [download page here][tcc-download].

## Run the test suite

`./mvnw test`

### Your environment is correctly configured if

Test output:

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


  Congratulations on running your first test on Testcontainers Cloud! 🎉
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

Agent status:

![agent-running](./docs/active-connection.png)

[tcc]: https://testcontainers.cloud/
[tcc-download]: https://app.testcontainers.cloud/start/download?mode=update
