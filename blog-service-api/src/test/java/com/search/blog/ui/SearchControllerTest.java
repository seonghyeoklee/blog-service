package com.search.blog.ui;

import com.search.blog.dto.SearchRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.client.MockMvcWebTestClient;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static com.search.utils.ApiDocumentation.getDocumentRequest;
import static com.search.utils.ApiDocumentation.getDocumentResponse;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.document;
import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.documentationConfiguration;

@Transactional
@ExtendWith(RestDocumentationExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SearchControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @BeforeEach
    void setUp(WebApplicationContext context, RestDocumentationContextProvider restDocumentation) {
        webTestClient = MockMvcWebTestClient.bindToApplicationContext(context)
                .configureClient()
                .filter(documentationConfiguration(restDocumentation))
                .build();
    }

    @Test
    @DisplayName("블로그 검색")
    void searchBlog() {
        SearchRequest request = new SearchRequest("https://brunch.co.kr/@tourism", "blog", "accuracy", 1, 10);

        webTestClient.get()
                .uri(uriBuilder -> uriBuilder.path("/v1/search/blog")
                        .queryParam("blogUrl", request.getBlogUrl())
                        .queryParam("search", request.getSearch())
                        .queryParam("sort", request.getSort())
                        .queryParam("page", request.getPage())
                        .queryParam("size", request.getSize())
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .consumeWith(document("search-blog",
                                getDocumentRequest(),
                                getDocumentResponse(),
                                requestParameters(
                                        parameterWithName("blogUrl").description("블로그 주소"),
                                        parameterWithName("search").description("검색어"),
                                        parameterWithName("sort").description("정렬"),
                                        parameterWithName("page").description("페이지"),
                                        parameterWithName("size").description("사이즈")
                                ),
                                responseFields(
                                        fieldWithPath("content[].title")
                                                .type(JsonFieldType.STRING)
                                                .description("블로그 글 제목"),
                                        fieldWithPath("content[].content")
                                                .type(JsonFieldType.STRING)
                                                .description("블로그 글 요약"),
                                        fieldWithPath("content[].blogname")
                                                .type(JsonFieldType.STRING)
                                                .description("블로그의 이름"),
                                        fieldWithPath("content[].url")
                                                .type(JsonFieldType.STRING)
                                                .description("블로그 글 URL"),
                                        fieldWithPath("content[].datetime")
                                                .type(JsonFieldType.STRING)
                                                .description("블로그 글 작성시간"),
                                        fieldWithPath("content[].thumbnail")
                                                .type(JsonFieldType.STRING)
                                                .description("미리보기 이미지 URL"),
                                        fieldWithPath("pageable.sort.empty")
                                                .type(JsonFieldType.BOOLEAN)
                                                .description("empty"),
                                        fieldWithPath("pageable.sort.unsorted")
                                                .type(JsonFieldType.BOOLEAN)
                                                .description("unsorted"),
                                        fieldWithPath("pageable.sort.sorted")
                                                .type(JsonFieldType.BOOLEAN)
                                                .description("sorted"),
                                        fieldWithPath("pageable.offset")
                                                .type(JsonFieldType.NUMBER)
                                                .description("offset"),
                                        fieldWithPath("pageable.pageSize")
                                                .type(JsonFieldType.NUMBER)
                                                .description("페이지 사이즈"),
                                        fieldWithPath("pageable.pageNumber")
                                                .type(JsonFieldType.NUMBER)
                                                .description("현재 페이지 번호"),
                                        fieldWithPath("pageable.paged")
                                                .type(JsonFieldType.BOOLEAN)
                                                .description("paged"),
                                        fieldWithPath("pageable.unpaged")
                                                .type(JsonFieldType.BOOLEAN)
                                                .description("unpaged"),
                                        fieldWithPath("totalPages")
                                                .type(JsonFieldType.NUMBER)
                                                .description("총 페이지"),
                                        fieldWithPath("totalElements")
                                                .type(JsonFieldType.NUMBER)
                                                .description("총 결과 카운트"),
                                        fieldWithPath("last")
                                                .type(JsonFieldType.BOOLEAN)
                                                .description("마지막 페이지 여부"),
                                        fieldWithPath("size")
                                                .type(JsonFieldType.NUMBER)
                                                .description("페이지 사이즈"),
                                        fieldWithPath("number")
                                                .type(JsonFieldType.NUMBER)
                                                .description("현재 페이지 번호"),
                                        fieldWithPath("sort.empty")
                                                .type(JsonFieldType.BOOLEAN)
                                                .description("sort.empty"),
                                        fieldWithPath("sort.unsorted")
                                                .type(JsonFieldType.BOOLEAN)
                                                .description("sort.unsorted"),
                                        fieldWithPath("sort.sorted")
                                                .type(JsonFieldType.BOOLEAN)
                                                .description("sort.sorted"),
                                        fieldWithPath("numberOfElements")
                                                .type(JsonFieldType.NUMBER)
                                                .description("numberOfElements"),
                                        fieldWithPath("first")
                                                .type(JsonFieldType.BOOLEAN)
                                                .description("첫 페이지 여부"),
                                        fieldWithPath("empty")
                                                .type(JsonFieldType.BOOLEAN)
                                                .description("empty")
                                )
                        )
                );
    }
}
