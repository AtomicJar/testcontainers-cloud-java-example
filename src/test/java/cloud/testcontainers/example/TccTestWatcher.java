package cloud.testcontainers.example;

import org.junit.jupiter.api.extension.*;

public class TccTestWatcher implements TestWatcher {

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        TestWatcher.super.testFailed(context, cause);
        System.out.println();
        System.out.println(PrettyStrings.ohNo);
        System.out.println();
        System.out.println("It seems you are not running Testcontainers Desktop nor the CI agent. Have you started it?");
        System.out.println();
    }
}
