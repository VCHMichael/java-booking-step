package app.flight;

public class NoEntityException extends Exception {
    private final int id;

    public NoEntityException(int id) {
        this.id = id;
    }
    @Override
    public String toString() {
        return "Рейс с id: " + id + " не существует. ";
    }
}
