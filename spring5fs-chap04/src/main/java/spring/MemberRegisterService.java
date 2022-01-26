package spring;

import org.springframework.beans.factory.annotation.Autowired;

public class MemberRegisterService {
    @Autowired
    private MemberDao memberDao;

    public MemberRegisterService() {
    }

    public MemberRegisterService(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    public Long regist(RegisterRequest req) {
        Member member = memberDao.selectByEmail(req.getEmail());
        if (member != null) {
            throw new DuplicateMemberException("dup email" + req.getEmail());
        }
        Member newMember = RegisterRequest.builder(req);
        memberDao.insert(newMember);
        return newMember.getId();
    }
}
