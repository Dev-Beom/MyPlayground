package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.ChangePasswordService;
import spring.MemberDao;
import spring.MemberRegisterService;

@Configuration  // 스프링 설정 클래스를 의미한다. 이 어노테이션을 붙여야 스프링 설정 클래스로 사용할 수 있다.
public class AppCtx {
    /**
     * 어노테이션은 해당 메서드가 생성한 객체를 스프링 빈이라고 설정한다.
     * 아래 코드의 경우엔 세 개의 메서드에 @Bean 어노테이션을 붙였는데 각각의 메서드마다 한개의 빈 객체를 생성한다.
     * 이 때 메서드 이름은 빈 객체의 이름으로 사용한다.
     * 예를 들어 memberDao() 메서드를 이용해서 생성한 빈 객체는 "memberDao"라는 이름으로 스프링에 등록된다.
     */
    @Bean
    public MemberDao memberDao() {
        return new MemberDao();
    }

    /**
     * MemberRegisterService 생성자를 호출할 때 memberDao() 메서드를 호출한다.
     * 즉 memberDao()가 생성한 객체를 MemberRegisterService 생성자를 통해 주입한다.
     */
    @Bean
    public MemberRegisterService memberRegisterService() {
        return new MemberRegisterService(memberDao());
    }

    /**
     * ChangePasswordService 타입의 빈을 설정한다. 이 메서드는 세터(Setter: setMemberDao() 메서드)를
     * 이용해서 의존 객체를 주입한다.
     */
    @Bean
    public ChangePasswordService changePasswordService() {
        ChangePasswordService passwordService = new ChangePasswordService();
        passwordService.setMemberDao(memberDao());
        return passwordService;
    }
}
