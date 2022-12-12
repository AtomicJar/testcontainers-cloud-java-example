package cloud.testcontainers.example;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.Info;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testcontainers.DockerClientFactory;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.utility.DockerImageName;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(TccTestWatcher.class)
public class TestcontainersCloudFirstTest {

    @Test
    public void canRunContainers() {
        try (KafkaContainer kafka = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:6.2.1"))) {
            kafka.start();
        }
    }

    @Test
    public void testcontainersCloudDockerEngine() {
        DockerClient client = DockerClientFactory.instance().client();
        Info dockerInfo = client.infoCmd().exec();

        assertThat(dockerInfo.getServerVersion())
                .as("Docker Client has to be connected to Testcontainers Cloud")
                .contains("testcontainerscloud");

        System.out.println(PrettyStrings.logo);
    }
}