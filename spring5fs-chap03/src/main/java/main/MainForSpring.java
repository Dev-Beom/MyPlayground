package main;

import config.AppCtx;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainForSpring {
    private static ApplicationContext context = null;

    public static void main(String[] args) throws IOException {

        /**
         * AnnotationConfigApplicationContext 를 사용해 스프링 컨테이너를 생성한다.
         * 스프링 컨테이너는 Assembler 와 동일하게 객체를 생성하고 의존 객체를 주입한다.
         * Assembler 는 직접 객체를 생성하는 반면에 AnnotationConfigApplicationContext는
         * 설정 파일(AppCtx 클래스)로부터 생성할 객체와 의존 주입 대상을 정한다.
         */    
        context = new AnnotationConfigApplicationContext(AppCtx.class);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            System.out.println("명령어를 입력하세요.");
            String command = br.readLine();
            if (command.equals("exit")) break;
            if (command.startsWith("new ")) {
                processNewCommand(command.split(" "));
                continue;
            } else if (command.startsWith("change")) {
                processChangeCommand(command.split(" "));
                continue;
            }
            printHelp();
        }
    }

    private static void processNewCommand(String[] args) {
        if (args.length != 5) {
            printHelp();
            return;
        }
        MemberRegisterService registerService = context.getBean("memberRegisterService", MemberRegisterService.class);
        RegisterRequest req = new RegisterRequest();
        req.setEmail(args[1]);
        req.setName(args[2]);
        req.setPassword(args[3]);
        req.setConfirmPassword(args[4]);

        if (!req.isPasswordEqualToConfirmPassword()) {
            System.out.println("암호와 확인이 일치하지 않습니다.\n");
            return;
        }
        try {
            registerService.regist(req);
            System.out.println("등록되었습니다.\n");
        } catch (DuplicateMemberException e) {
            System.out.println("이미 존재하는 이메일입니다.\n");
        }
    }

    private static void processChangeCommand(String[] args) {
        if (args.length != 4) {
            printHelp();
            return;
        }
        ChangePasswordService passwordService =
                context.getBean("changePasswordService", ChangePasswordService.class);
        try {
            passwordService.changePassword(args[1], args[2], args[3]);
            System.out.println("암호를 변경했습니다.\n");
        } catch (MemberNotFoundException e) {
            System.out.println("존재하지 않는 이메일입니다\n");
        } catch (WrongIdPasswordException e) {
            System.out.println("이메일과 암호가 일치하지 않습니다.");
        }
    }

    private static void printHelp() {
        String helpMsg = "\n잘못된 명령입니다. 아래 명령어 사용법을 확인하세요.\n" +
                "명령어 사용법:\n" +
                "new 이메일 이름 암호 암호확인\n" +
                "change 이메일 현재비밀번호 변경비밀번호\n";
        System.out.println(helpMsg);
    }
}
