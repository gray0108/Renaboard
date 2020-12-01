package com.gray.renaboard.tutorial;

import com.gray.renaboard.tutorial.persistence.MemberDAO;
import com.gray.renaboard.tutorial.domain.MemberVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring-config/applicationContext.xml"})
public class MemberDAOTest {

    @Inject
    private MemberDAO memberDAO;

    //current time test
    @Test
    public void testTime() throws Exception {
        System.out.println(memberDAO.getTime());
    }

    //Sign up test
    @Test
    public void testInsertMember() throws Exception{
        MemberVO memberVO = new MemberVO();
        memberVO.setUserid("user00");
        memberVO.setUserpw("user00");
        memberVO.setUsername("user00");
        memberVO.setEmail("user00@mail.com");

        memberDAO.insertMember(memberVO);
    }

    //Find member test
    @Test
    public void testReadMember() throws Exception {
        memberDAO.readMember("user00");
    }

    //Find member test2 ID + PASSWORD
    @Test
    public void testReadWithPS() throws Exception{
        memberDAO.readWithPW("user00","user00");
    }
}
