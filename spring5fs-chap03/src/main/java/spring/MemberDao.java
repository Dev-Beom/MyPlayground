package spring;

import java.util.HashMap;
import java.util.Map;

public class MemberDao {
    private static long nextId = 0;

    private Map<String, Member> database = new HashMap<>();

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
}
