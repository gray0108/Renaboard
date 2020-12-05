package com.gray.renaboard.article.service;

import com.gray.renaboard.article.domain.ArticleVO;
import com.gray.renaboard.commons.paging.Criteria;

import java.util.List;

public interface ArticleService {
    public void create(ArticleVO articleVO) throws Exception;
    public ArticleVO read(Integer articleNo) throws Exception;
    public void update(ArticleVO articleVO) throws Exception;
    public void delele(Integer articleNo) throws Exception;
    public List<ArticleVO> listAll() throws Exception;

    /* Paging process */
    List<ArticleVO> listCriteria(Criteria criteria) throws Exception;
    int countArticles(Criteria criteria) throws Exception;
}
