package org.example.springairagvectorstore.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.util.List;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "documents")
public class VectorstoreProperties {

    private List<Resource> documentsToLoad;

}
