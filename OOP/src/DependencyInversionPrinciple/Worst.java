package DependencyInversionPrinciple;
//
//class Database {
//    public void connect() {
//        // 데이터베이스 연결
//    }
//    public void disconnect() {
//        // 데이터 베이스 연결 해제
//    }
//    public void executeQuery(String query) {
//        // 쿼리 실행
//    }
//}
//
//class Application {
//    private final Database database;
//
//    public Application() {
//        this.database = new Database();
//    }
//
//    public void processData() {
//        database.connect();
//        // 데이터 처리 로직
//        database.disconnect();
//    }
//}
