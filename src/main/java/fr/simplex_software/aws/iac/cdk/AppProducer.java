package fr.simplex_software.aws.iac.cdk;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Singleton;
import software.amazon.awscdk.App;
import software.amazon.awscdk.StackProps;

@ApplicationScoped
public class AppProducer {

    @Produces
    @Singleton
    App app() {
        return new App();
    }

    @Produces
    @Singleton
    StackProps stackProps() {
        return StackProps.builder().build();
    }
}
