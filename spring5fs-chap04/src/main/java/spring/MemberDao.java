package spring;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MemberDao {
    private static long nextId = 0;

    private final Map<String, Member> database = new HashMap<>();

    public Member selectByEmail(String email) {
        return database.get(email);
    }

    public void insert(Member member) {
        member.setId(++nextId);
        database.put(member.getEmail(), member);
    }

    public void update(Member member) {
        database.put(member.getEmail(), member);
    }

    public Collection<Member> selectAll() {
        return database.values();
    }
}
