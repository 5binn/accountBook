package org.example.member;

import java.util.List;

public class MemberService {

    private MemberRepository memberRepository;

    public MemberService() {
        memberRepository = new MemberRepository();
    }

    public String signUp(String userName, String password, String nickname) {
        return memberRepository.signUp(userName, password, nickname);
    }

    public void login(Member member) {
        memberRepository.login(member);
    }

    public void logout() {
        memberRepository.logout();
    }

    public void withdrawal() {
        memberRepository.withdrawal();
    }

    public Member findBySameId(String checkName) {
        List<Member> memberList = memberRepository.findByAll();
        for (Member member : memberList) {
            if (member.getUserName().equals(checkName)) {
                return member;
            }
        }
        return null;
    }

    public List<Member> findByAll() {
        return memberRepository.findByAll();
    }


}
