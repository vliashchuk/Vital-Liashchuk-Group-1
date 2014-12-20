package org.shop.context;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({FactoryConfiguration.class, RepositoryConfiguration.class, ServiceConfiguration.class, InitializerConfiguration.class})
public class ApplicationConfiguration {

}
