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
import javax.jws.WebParam;

@Controller
@RequestMapping("/article")
public class ArticleController {
    private static final Logger logger = LoggerFactory.getLogger(ArticleController.class);
    private final ArticleService articleService;

    @Inject
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @RequestMapping(value = "write", method = RequestMethod.GET)
    public String writeGET() {
        logger.info("write GET...");

        return "/article/write";
    }

    @RequestMapping(value = "/write", method = RequestMethod.POST)
    public String writePOST(ArticleVO articleVO, RedirectAttributes redirectAttributes) throws Exception {
        logger.info("write POST...");
        logger.info(articleVO.toString());
        articleService.create(articleVO);
        redirectAttributes.addFlashAttribute("msg", "regSuccess");

        return "redirect:/article/list";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) throws Exception{
        logger.info("list...");
        model.addAttribute("articles", articleService.listAll());

        return "/article/list";
    }

    @RequestMapping(value = "/read", method = RequestMethod.GET)
    public String read(@RequestParam("articleNo") int articleNo, Model model) throws Exception {
        logger.info("read...");
        model.addAttribute("article", articleService.read(articleNo));

        return "/article/read";
    }

    @RequestMapping(value = "/modify", method = RequestMethod.GET)
    public String modifyGET(@RequestParam("articleNo") int articleNo, Model model) throws Exception {
        logger.info("modifyGet ...");
        model.addAttribute("article", articleService.read(articleNo));

        return "/article/modify";
    }

    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public String modifyPOST(ArticleVO articleVO, RedirectAttributes redirectAttributes) throws Exception {
        logger.info("modifyPOST ...");
        articleService.update(articleVO);
        redirectAttributes.addFlashAttribute("msg", "modSuccess");

        return "redirect:/article/list";
    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public String remove(@RequestParam("articleNo") int articleNo, RedirectAttributes redirectAttributes) throws Exception {
        logger.info("remove ...");
        articleService.delele(articleNo);
        redirectAttributes.addFlashAttribute("msg", "delSuccess");

        return "redirect:/article/list";
    }

    @RequestMapping(value = "/listCriteria", method = RequestMethod.GET)
    public String listCriteria(Model model, Criteria criteria) throws Exception {
        logger.info("listCriteria...");
        model.addAttribute("articles", articleService.listCriteria(criteria));
        return "/article/list_criteria";
    }

    @RequestMapping(value = "/listPaging", method = RequestMethod.GET)
    public String listPaging(Model model, Criteria criteria) throws Exception {
        logger.info("listPaging...");

        PageMaker pageMaker = new PageMaker();
        pageMaker.setCriteria(criteria);

        //전체 게시글의 갯수 구하기
        pageMaker.setTotalCount(articleService.countArticles(criteria));

        model.addAttribute("articles", articleService.listCriteria(criteria));
        model.addAttribute("pageMaker", pageMaker);

        return "/article/list_paging";
    }

    // 목록페이지 정보를 유지하는 CRUD
    @RequestMapping(value = "/readPaging", method = RequestMethod.GET)
    public String readPaging(@RequestParam("articleNo") int articleNo,
                             @ModelAttribute("criteria") Criteria criteria, Model model) throws Exception{
        model.addAttribute("article", articleService.read(articleNo));

        return "/article/read_paging";
    }

    @RequestMapping(value = "/modifyPaging", method = RequestMethod.GET)
    public String modifyGETPaging(@RequestParam("articleNo") int articleNo,
                                  @ModelAttribute("criteria") Criteria criteria, Model model) throws Exception{
        logger.info("modifyGetPaging...");
        model.addAttribute("article", articleService.read(articleNo));

        return "/article/modify_paging";
    }
    @RequestMapping(value = "/modifyPaging", method = RequestMethod.POST)
    public String modifyPOSTPaging(ArticleVO articleVO,
                                   Criteria criteria,
                                   RedirectAttributes redirectAttributes) throws Exception{
        logger.info("modifyPOSTPaging...");
        articleService.update(articleVO);
        redirectAttributes.addAttribute("page", criteria.getPage());
        redirectAttributes.addAttribute("perPageNum", criteria.getPerPageNum());
        redirectAttributes.addFlashAttribute("msg", "modSuccess");

        return "redirect:/article/listPaging";
    }

    @RequestMapping(value = "/removePaging", method = RequestMethod.POST)
    public String removePaging(@RequestParam("articleNo") int articleNo,
                               Criteria criteria,
                               RedirectAttributes redirectAttributes) throws Exception {
        logger.info("remove...");
        articleService.delele(articleNo);
        redirectAttributes.addAttribute("page", criteria.getPage());
        redirectAttributes.addAttribute("perPageNum", criteria.getPerPageNum());
        redirectAttributes.addFlashAttribute("msg", "delSuccess");

        return "redirect:/article/listPaging";
    }
}
