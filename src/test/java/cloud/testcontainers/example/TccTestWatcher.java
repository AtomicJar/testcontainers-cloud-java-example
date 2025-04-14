package cloud.testcontainers.example;

import org.junit.jupiter.api.extension.*;
import org.testcontainers.DockerClientFactory;
import org.testcontainers.dockerclient.TestcontainersHostPropertyClientProviderStrategy;

public class TccTestWatcher implements TestWatcher {

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        TestWatcher.super.testFailed(context, cause);

        boolean isTCD = DockerClientFactory.instance().isUsing(TestcontainersHostPropertyClientProviderStrategy.class);

        System.out.println();
        System.out.println(PrettyStrings.ohNo);
        System.out.println();
        if (isTCD) {
            System.out.println("Is your local Docker running? Please start it or configure Testcontainers Desktop to use Testcontainers Cloud as a runtime!");
        } else {
            System.out.println("It seems you are not running Testcontainers Desktop nor the CI agent. Have you started it?");
        }
        System.out.println();
    }
}
