package org.example.member;

import java.util.List;

public class MemberService {

    MemberRepository memberRepository;
    public MemberService() {
        memberRepository = new MemberRepository();
    }
    public String signUp(String userName, String password, String nickname) {
        return memberRepository.signUp(userName, password, nickname);
    }
    public void login() {

    }

    public void logout() {

    }

    public void withdrawal() {

    }
    public List<Member> findByAll() {
        return memberRepository.findByAll();
    }
    public boolean findBySameId(String checkName) {
        List<Member> memberList = memberRepository.findByAll();
        for (Member member : memberList) {
            if (member.getUserName().equals(checkName)) {
                return true;
            }
        }
        return false;
    }
}
