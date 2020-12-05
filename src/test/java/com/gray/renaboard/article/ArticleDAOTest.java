package com.gray.renaboard.article;

import com.gray.renaboard.article.domain.ArticleVO;
import com.gray.renaboard.article.persistence.ArticleDAO;
import com.gray.renaboard.commons.paging.Criteria;
import com.sun.jndi.toolkit.url.Uri;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.inject.Inject;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring-config/applicationContext.xml"})
public class ArticleDAOTest {
    private static final Logger logger = LoggerFactory.getLogger(ArticleDAOTest.class);

    @Inject
    private ArticleDAO articleDAO;

    @Test
    public void testCreate() throws Exception{
        ArticleVO articleVO = new ArticleVO();
        articleVO.setTitle("새로운 글 작성 테스트 제목");
        articleVO.setContent("새로운 글 작성 테스트 내용");
        articleVO.setWriter("새로운 글 작성자");
        articleDAO.create(articleVO);

    }

    @Test
    public void testRead() throws Exception {
        logger.info(articleDAO.read(1).toString());
    }

    @Test
    public void testUpdate() throws Exception {
        ArticleVO article = new ArticleVO();
        article.setArticleNo(1);
        article.setTitle("글 수정 테스트 제목");
        article.setContent("글 수정 테스트 내용");
        articleDAO.update(article);
    }

    @Test
    public void testDelete() throws Exception {
        articleDAO.delete(1);
    }


    @Test/* 페이징 처리 확인을 위해 테이블에 데이터 삽입하기 */
    public void createDummy() throws Exception{
        for (int i =1; i<= 400; i++) {
            ArticleVO articleVO = new ArticleVO();
            articleVO.setTitle("무케의 모험 "+i+"편");
            articleVO.setContent(i+" 권에 대한 내용입니다.");
            articleVO.setWriter("여우작가0"+(i%10));

            articleDAO.create(articleVO);
        }
    }

    @Test
    public void testListPaging() throws Exception {
        int page = 3;
        List<ArticleVO> articles = articleDAO.listPaging(page);

        for (ArticleVO article : articles) {
            logger.info(article.getArticleNo()+":"+article.getTitle());
        }
    }

    @Test
    public void testListCriteria() throws Exception {
        Criteria criteria = new Criteria();
        criteria.setPage(4);
        criteria.setPerPageNum(20);

        List<ArticleVO> articles = articleDAO.listCriteria(criteria);
        for (ArticleVO article : articles) {
            logger.info(article.getArticleNo() + " : " + article.getTitle());
        }
    }

    //Paging Upgrade
    @Test
    public void testURI() throws Exception {
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .path("/article/read")
                .queryParam("articleNo", 12)
                .queryParam("perPageNum", 20)
                .build();

        logger.info("/article/read?articleNo=12&perPageNum=20");
        logger.info(uriComponents.toString());
    }

    @Test
    public void testURI2() throws Exception {
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .path("/{module}/{page}")
                .queryParam("articleNo", 12)
                .queryParam("perPageNum", 20)
                .build()
                .expand("article","read")
                .encode();

        logger.info("/article/read?articleNo=12&perPageNum=20");
        logger.info(uriComponents.toString());
    }
}
