package com.gray.renaboard.article;

import com.gray.renaboard.article.domain.ArticleVO;
import com.gray.renaboard.article.persistence.ArticleDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;

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
}
