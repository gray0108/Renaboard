package com.gray.renaboard.tutorial.persistence;

import com.gray.renaboard.tutorial.domain.MemberVO;

public interface MemberDAO {
    public String getTime();
    public void insertMember(MemberVO memberVO);
    public MemberVO readMember(String userid) throws Exception;
    public MemberVO readWithPW(String userid, String userpw) throws Exception;

}
