package cloud.testcontainers.example;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.Info;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testcontainers.DockerClientFactory;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.images.builder.Transferable;
import org.testcontainers.utility.DockerImageName;

import java.util.Properties;

import static org.assertj.core.api.Assertions.anyOf;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(TccTestWatcher.class)
public class TestcontainersCloudFirstTest {

    @Test
    public void createPostgreSQLContainer() {
        try (PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:15-alpine")
                .withCopyToContainer(Transferable.of(initsql), "/docker-entrypoint-initdb.d/init.sql")) {
            postgreSQLContainer.start();
        }
    }

    @Test
    public void testcontainersCloudDockerEngine() {
        DockerClient client = DockerClientFactory.instance().client();
        Info dockerInfo = client.infoCmd().exec();

        String serverVersion = dockerInfo.getServerVersion();
        assertThat(serverVersion)
                .as("Docker Client is configured via the Testcontainers desktop app")
                .satisfiesAnyOf(
                        dockerString -> assertThat(dockerString).contains("Testcontainers Desktop"),
                        dockerString -> assertThat(dockerString).contains("testcontainerscloud")
                        );

        String runtimeName = serverVersion;
        if (runtimeName.contains("testcontainerscloud")) {
            runtimeName = "Testcontainers Cloud";
        }
        if (serverVersion.contains("Testcontainers Desktop")) {
            runtimeName += " via Testcontainers Desktop app";
        }
        System.out.println(PrettyStrings.getLogo(runtimeName));
    }

    private static final String initsql =
            "create table quotes\n" +
                "(\n" +
                "    id         bigserial     not null,\n" +
                "    quote       varchar(1023)  not null,\n" +
                "    rating      numeric(5, 2) not null,\n" +
                "    primary key (id)\n" +
            ");\n" +
            "\n" +
            "insert into quotes(quote, rating)\n" +
            "values ('Testcontainers is pretty amazing', 21.00),\n" +
            "       ('Integration tests are good too', 34.50)\n" +
            ";";
}