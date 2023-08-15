package OpenClosedPrinciple;

interface Sender {
    void send();
}

class EmailSender implements Sender {
    public void send() {
        // 결제 결과를 email 로 발송하는 코드
    }
}

class SmsSender implements Sender {
    public void send() {
        // 결제 결과를 sms 로 발송하는 코드
    }
}

class KakaoTalkSender implements Sender {
    public void send() {
        // 결제 결과를 카카오톡으로 발송하는 코드
    }
}

class PaymentResultSender {
    private final Sender sender;

    public PaymentResultSender(Sender sender) {
        this.sender = sender;
    }

    public void sendPaymentResult() {
        sender.send();
    }
}