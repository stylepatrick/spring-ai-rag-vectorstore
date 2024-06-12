# spring-ai-rag-vectorstore
 Spring AI RAG vector store sentiment search on custom data loaded by tiko with a REST API.
 
 Data are loaded to the vector store over a bootstrap init methode.
 The example loades some articles from Stackoverflow with the help of tiko and push the embeddings to the vector store database.
 
 With the help of a prompt template (.st) the message is generated and sent to OpenAI on the vector store data source.  

 The project can be used to create embeddings from your custom data and then do a semantic search on it.
 
