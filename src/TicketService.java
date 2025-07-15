import java.util.*;

public class TicketService {
    private final Map<Integer, TicketQueue<Ticket>> counters = new HashMap<>();
    private final Map<Integer, Integer> counterNumbers = new HashMap<>();

    public TicketService(int[] counterIds) {
        for (int id : counterIds) {
            counters.put(id, new TicketQueue<>());
            counterNumbers.put(id, 1);
        }
    }

    public Ticket addTicket(int counterId) {
        int ticketNum = counterNumbers.get(counterId);
        Ticket ticket = new Ticket(ticketNum, counterId);
        counters.get(counterId).enqueue(ticket);
        counterNumbers.put(counterId, ticketNum + 1);
        return ticket;
    }

    public Ticket serveNext(int counterId) {
        return counters.get(counterId).dequeue();
    }

    public Ticket peekNext(int counterId) {
        return counters.get(counterId).peek();
    }

    public int waitingCount(int counterId) {
        return counters.get(counterId).size();
    }

    public boolean isEmpty(int counterId) {
        return counters.get(counterId).isEmpty();
    }

    public List<Ticket> listTickets(int counterId) {
        return counters.get(counterId).getAll();
    }
}
