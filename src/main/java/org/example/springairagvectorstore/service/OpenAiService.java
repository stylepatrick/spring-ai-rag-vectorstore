package org.example.springairagvectorstore.service;

import lombok.RequiredArgsConstructor;
import org.example.springairagvectorstore.model.Answer;
import org.example.springairagvectorstore.model.Question;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OpenAiService {

    private final ChatClient chatClient;
    private final VectorStore vectorStore;

    @Value("classpath:/string-templates/help-assistant-prompt.st")
    private Resource helpAssistantPrompt;

    public Answer buildAnswerFromVectorStore(Question question) {
        List<Document> documents = vectorStore.similaritySearch(SearchRequest
                // higher value for withTopK will load a higher sentiment from the vector store
                .query(question.text()).withTopK(4));
        List<String> vectorStoreContent = documents.stream().map(Document::getContent).toList();

        PromptTemplate promptTemplate = new PromptTemplate(helpAssistantPrompt);
        Prompt prompt = promptTemplate.create(Map.of(
                "question", question.text(),
                "documents", vectorStoreContent));

        ChatResponse response = chatClient.call(prompt);

        return new Answer(response.getResult().getOutput().getContent());
    }

}
