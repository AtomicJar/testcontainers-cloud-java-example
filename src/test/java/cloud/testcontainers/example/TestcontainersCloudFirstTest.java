package cloud.testcontainers.example;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.Info;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testcontainers.DockerClientFactory;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.images.builder.Transferable;
import org.testcontainers.shaded.com.google.common.collect.Streams;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(TccTestWatcher.class)
public class TestcontainersCloudFirstTest {

    public static final String DOCKER_CLOUD_VERSION_LABEL = "cloud.docker.run.version";

    public static final String TESTCONTAINERS_DESKTOP_APP_NAME = "Testcontainers Desktop";

    public static final String TESTCONTAINERS_CLOUD_VERSION_NAME = "testcontainerscloud";

    @Test
    public void createPostgreSQLContainer() throws SQLException {
        try (PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:14-alpine")
                .withCopyToContainer(Transferable.of(initsql), "/docker-entrypoint-initdb.d/init.sql")) {
            postgreSQLContainer.start();
            Connection connection = DriverManager.getConnection(postgreSQLContainer.getJdbcUrl(), postgreSQLContainer.getUsername(), postgreSQLContainer.getPassword());
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) FROM guides");
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            resultSet.next();
            assertThat(resultSet.getInt(1)).isEqualTo(6);
        }
    }

    @Test
    public void testcontainersCloudDockerEngine() {
        DockerClient client = DockerClientFactory.instance().client();
        Info dockerInfo = client.infoCmd().exec();

        String serverVersion = dockerInfo.getServerVersion();
        String[] labels = dockerInfo.getLabels();

        List<String> info = Streams.concat(
                Stream.of(String.format("server.version=%s", serverVersion)),
                Arrays.stream(labels == null ? new String[]{} : labels)
        ).collect(Collectors.toList());

        assertThat(info)
                .as("Docker Client is configured via the Testcontainers desktop app")
                .anySatisfy(it -> assertThat(it).containsAnyOf(
                        TESTCONTAINERS_DESKTOP_APP_NAME,
                        TESTCONTAINERS_CLOUD_VERSION_NAME,
                        DOCKER_CLOUD_VERSION_LABEL
                ));

        logRuntimeDetails(serverVersion != null ? serverVersion : "", dockerInfo);
    }

    private static void logRuntimeDetails(String serverVersion, Info dockerInfo) {
        String runtimeName = "Testcontainers Cloud";
        boolean hasCloudLabel = Stream.of(
                dockerInfo.getLabels() != null
                ? dockerInfo.getLabels()
                : new String[]{}
        ).anyMatch(label -> label.contains(DOCKER_CLOUD_VERSION_LABEL));
        if (!serverVersion.contains(TESTCONTAINERS_CLOUD_VERSION_NAME) && !hasCloudLabel) {
            runtimeName = dockerInfo.getOperatingSystem();
        }
        if (serverVersion.contains(TESTCONTAINERS_DESKTOP_APP_NAME)) {
            runtimeName += " via Testcontainers Desktop";
        }
        System.out.println(PrettyStrings.getLogo(runtimeName));
    }

    private static final String initsql =
            "create table guides\n" +
                    "(\n" +
                    "    id         bigserial     not null,\n" +
                    "    title      varchar(1023)  not null,\n" +
                    "    url        varchar(1023) not null,\n" +
                    "    primary key (id)\n" +
                    ");\n" +
                    "\n" +
                    "insert into guides(title, url)\n" +
                    "values ('Getting started with Testcontainers', 'https://testcontainers.com/getting-started/'),\n" +
                    "       ('Getting started with Testcontainers for Java', 'https://testcontainers.com/guides/getting-started-with-testcontainers-for-java/'),\n" +
                    "       ('Getting started with Testcontainers for .NET', 'https://testcontainers.com/guides/getting-started-with-testcontainers-for-dotnet/'),\n" +
                    "       ('Getting started with Testcontainers for Node.js', 'https://testcontainers.com/guides/getting-started-with-testcontainers-for-nodejs/'),\n" +
                    "       ('Getting started with Testcontainers for Go', 'https://testcontainers.com/guides/getting-started-with-testcontainers-for-go/'),\n" +
                    "       ('Testcontainers container lifecycle management using JUnit 5', 'https://testcontainers.com/guides/testcontainers-container-lifecycle/')\n" +
                    ";";
}