package com.gray.renaboard.article.controller;

import com.gray.renaboard.article.domain.ArticleVO;
import com.gray.renaboard.article.service.ArticleService;
import com.gray.renaboard.commons.paging.Criteria;
import com.gray.renaboard.commons.paging.PageMaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;

// This Controller is written until Paging process.
// So I add paging URL in these Mapping.

@Controller
@RequestMapping("/article/paging")
public class ArticlePagingController {
    private static final Logger logger = LoggerFactory.getLogger(ArticlePagingController.class);
    private  final ArticleService articleService;

    @Inject
    public ArticlePagingController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @RequestMapping(value = "/write", method = RequestMethod.GET)
    public String writeGET() {
        logger.info("paging writeGET() called...");
        return "article/paging/write";
    }

    @RequestMapping(value = "/write", method=RequestMethod.POST)
    public String writePOST(ArticleVO articleVO, RedirectAttributes redirectAttributes) throws Exception {
        logger.info("paging writePOST() called....");

        articleService.create(articleVO);
        redirectAttributes.addFlashAttribute("msg", "regSuccess");

        return "article/paging/list_paging";
    }

    @RequestMapping(value = "/list_paging", method = RequestMethod.GET)
    public String list(Model model, Criteria criteria) throws Exception {
        logger.info("paging list() called...");

        PageMaker pageMaker = new PageMaker();
        pageMaker.setCriteria(criteria);
        pageMaker.setTotalCount(articleService.countArticles(criteria));

        model.addAttribute("articles", articleService.listCriteria(criteria));
        model.addAttribute("pageMaker", pageMaker);

        return "article/paging/list_paging";
    }

    @RequestMapping(value = "/read_paging", method = RequestMethod.GET)
    public String read(@RequestParam("articleNo") int articleNo,
                       @ModelAttribute("criteria") Criteria criteria, Model model) throws Exception {
        logger.info("paging read() called ...");
        model.addAttribute("article", articleService.read(articleNo));

        return "article/paging/read_paging";
    }

    @RequestMapping(value = "/modify_paging", method = RequestMethod.GET)
    public String modifyGETPaging(@RequestParam("articleNo") int articleNo,
                                  @ModelAttribute("criteria") Criteria criteria, Model model) throws Exception{
        logger.info("modifyGetPaging...");
        model.addAttribute("article", articleService.read(articleNo));

        return "/article/paging/modify_paging";
    }
    @RequestMapping(value = "/modify_paging", method = RequestMethod.POST)
    public String modifyPOSTPaging(ArticleVO articleVO,
                                   Criteria criteria,
                                   RedirectAttributes redirectAttributes) throws Exception{
        logger.info("modifyPOSTPaging...");
        articleService.update(articleVO);
        redirectAttributes.addAttribute("page", criteria.getPage());
        redirectAttributes.addAttribute("perPageNum", criteria.getPerPageNum());
        redirectAttributes.addFlashAttribute("msg", "modSuccess");

        return "article/paging/list_paging";
    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public String removePaging(@RequestParam("articleNo") int articleNo,
                               Criteria criteria,
                               RedirectAttributes redirectAttributes) throws Exception {
        logger.info("remove...");
        articleService.delele(articleNo);
        redirectAttributes.addAttribute("page", criteria.getPage());
        redirectAttributes.addAttribute("perPageNum", criteria.getPerPageNum());
        redirectAttributes.addFlashAttribute("msg", "delSuccess");

        return "article/paging/list_paging";
    }
}
