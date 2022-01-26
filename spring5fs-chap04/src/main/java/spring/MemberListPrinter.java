package spring;

import java.util.Collection;

public class MemberListPrinter {
    final private MemberDao memberDao;
    final private MemberPrinter printer;

    public MemberListPrinter(MemberDao memberDao, MemberPrinter memberPrinter) {
        this.memberDao = memberDao;
        this.printer = memberPrinter;
    }

    public void printAll() {
        Collection<Member> members = memberDao.selectAll();
        members.forEach(printer::print);
    }
}
