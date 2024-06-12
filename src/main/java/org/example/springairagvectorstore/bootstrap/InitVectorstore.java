package org.example.springairagvectorstore.bootstrap;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.springairagvectorstore.config.VectorstoreProperties;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.ai.transformer.splitter.TextSplitter;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@AllArgsConstructor
public class InitVectorstore implements CommandLineRunner {

    private final VectorStore vectorStore;
    private final VectorstoreProperties vectorstoreProperties;

    @Override
    public void run(String... args) {

        if (vectorStore.similaritySearch("Java").isEmpty()) {
            log.info("Loading documents into vector store");

            vectorstoreProperties.getDocumentsToLoad().forEach(document -> {
                log.info("Loading document: {}", document.getFilename());

                TikaDocumentReader documentReader = new TikaDocumentReader(document);
                List<Document> documents = documentReader.get();

                TextSplitter textSplitter = new TokenTextSplitter();

                List<Document> splitDocuments = textSplitter.apply(documents);

                vectorStore.add(splitDocuments);
            });

            log.info("Vector store loaded");

        }

    }
}
