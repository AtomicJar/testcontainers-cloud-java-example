package cloud.testcontainers.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.Info;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testcontainers.DockerClientFactory;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.images.builder.Transferable;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(TccTestWatcher.class)
public class TestcontainersCloudFirstTest {

    @Test
    public void createPostgreSQLContainer() throws SQLException {
        try (PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:14-alpine")
                .withCopyToContainer(Transferable.of(initsql), "/docker-entrypoint-initdb.d/init.sql")
                .waitingFor(Wait.defaultWaitStrategy())) {
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
        assertThat(serverVersion)
                .as("Docker Client is configured via the Testcontainers desktop app")
                .satisfiesAnyOf(
                        dockerString -> assertThat(dockerString).contains("Testcontainers Desktop"),
                        dockerString -> assertThat(dockerString).contains("testcontainerscloud")
                        );

        String runtimeName = "Testcontainers Cloud";
        if (!serverVersion.contains("testcontainerscloud")) {
            runtimeName = dockerInfo.getOperatingSystem();
        }
        if (serverVersion.contains("Testcontainers Desktop")) {
            runtimeName += " via Testcontainers Desktop app";
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