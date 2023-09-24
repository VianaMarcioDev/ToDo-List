package com.marcioviana.todolist;

import com.marcioviana.todolist.entity.ToDo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT )
@AutoConfigureWebTestClient
class ToDoListApplicationTests {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testCreateToDoSuccess() {
        var toDo = new ToDo("fazer compras","ir ao supermercado para fazer as compras do mês", false, 3 );

        webTestClient
                .post()
                .uri("/todos")
                .bodyValue(toDo)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$").isArray()
                .jsonPath("$.length()").isEqualTo(1)
                .jsonPath("$[0].nome").isEqualTo(toDo.getNome())
                .jsonPath("$[0].descricao").isEqualTo(toDo.getDescricao())
                .jsonPath("$[0].realizado").isEqualTo(toDo.isRealizado())
                .jsonPath("$[0].prioridade").isEqualTo(toDo.getPrioridade());
    }

    @Test
    void testCreateToDoFailure() {
        webTestClient
                .post()
                .uri("/todos")
                .bodyValue(
                        new ToDo("", "", false, 0))
                .exchange()
                .expectStatus().isBadRequest();
    }

    public WebTestClient getWebTestClient() {
        return webTestClient;
    }

    public void setWebTestClient(WebTestClient webTestClient) {
        this.webTestClient = webTestClient;
    }
}
