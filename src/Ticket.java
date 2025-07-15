public class Ticket {
    private final int number;
    private final int counterId;

    public Ticket(int number, int counterId) {
        this.number = number;
        this.counterId = counterId;
    }

    public String toString() {
        return "Ticket Nº: " + number + "\nGuichet Nº: " + counterId;
    }
}
