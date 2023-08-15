package DependencyInversionPrinciple;

interface DatabaseConnection {
    void connect();
    void disconnect();
    void executeQuery(String query);
}

class Database implements DatabaseConnection {
    @Override
    public void connect() {
        // DB 연결
    }

    @Override
    public void disconnect() {
        // DB 연결 해제
    }

    @Override
    public void executeQuery(String query) {
        // 쿼리 실행
    }
}

class Application {
    private final DatabaseConnection databaseConnection;
    public Application(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    public void processData() {
        databaseConnection.connect();
        databaseConnection.disconnect();
    }
}