package org.example.springairagvectorstore.resource;

import lombok.AllArgsConstructor;
import org.example.springairagvectorstore.model.Answer;
import org.example.springairagvectorstore.model.Question;
import org.example.springairagvectorstore.service.OpenAiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class WebApi {

    private OpenAiService openAiService;

    @PostMapping(value = "api/question")
    public ResponseEntity<Answer> question(
            @RequestBody Question question
    ) {
        return ResponseEntity.ok(openAiService.buildAnswerFromVectorStore(question));
    }

}
