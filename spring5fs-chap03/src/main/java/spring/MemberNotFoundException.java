package spring;

public class MemberNotFoundException extends RuntimeException{
    public MemberNotFoundException() {
        super("일치하는 사용자가 없습니다.");
    }
}
