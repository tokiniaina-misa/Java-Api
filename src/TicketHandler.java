import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.OutputStream;
import java.io.IOException;
import java.util.List;

public class TicketHandler implements HttpHandler {
    private final TicketService service;

    public TicketHandler(TicketService service) {
        this.service = service;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String path = exchange.getRequestURI().getPath();
        int counterId = parseId(exchange.getRequestURI().getQuery());
        String htmlStart = "<div style='font-family:Segoe UI,sans-serif;padding:10px'>";
        String htmlEnd = "</div>";
        String response;

        try {
            response = switch (path) {
                case "/api/create-ticket" -> htmlStart +
                    "<h2>Nouveau Ticket</h2><hr>" +
                    "<p><strong>" + service.addTicket(counterId) + "</strong></p>" +
                    "<p>En attente : " + service.waitingCount(counterId) + "</p>" + htmlEnd;

                case "/api/serve-ticket" -> service.isEmpty(counterId) ? htmlStart +
                    "<h3>Aucun ticket à servir</h3><hr>" + htmlEnd :
                    htmlStart + "<h2>Ticket Servi</h2><hr><p><strong>" + service.serveNext(counterId) + "</strong></p>" + htmlEnd;

                case "/api/peek-ticket" -> service.isEmpty(counterId) ? htmlStart +
                    "<h3>File vide</h3><hr>" + htmlEnd :
                    htmlStart + "<h2>Ticket en tête</h2><hr><p><strong>" + service.peekNext(counterId) + "</strong></p>" + htmlEnd;

                case "/api/ticket-count" -> htmlStart +
                    "<h2>Tickets en attente</h2><hr>" +
                    "<p>Guichet <strong>" + counterId + "</strong> : " + service.waitingCount(counterId) + "</p>" + htmlEnd;

                case "/api/ticket-list" -> {
                    StringBuilder sb = new StringBuilder(htmlStart + "<h2>Liste des Tickets</h2><hr><ul>");
                    for (Ticket t : service.listTickets(counterId)) sb.append("<li>").append(t).append("</li>");
                    sb.append("</ul>").append(htmlEnd);
                    yield sb.toString();
                }

                default -> htmlStart + "<h3>Endpoint inconnu</h3>" + htmlEnd;
            };
        } catch (Exception e) {
            response = htmlStart + "<h3>Erreur interne</h3><hr><p>" + e.getMessage() + "</p>" + htmlEnd;
        }

        exchange.getResponseHeaders().add("Content-Type", "text/html; charset=utf-8");
        exchange.sendResponseHeaders(200, response.getBytes().length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(response.getBytes());
        }
    }

    private int parseId(String query) {
        try {
            return Integer.parseInt(query.split("=")[1]);
        } catch (Exception e) {
            return 1;
        }
    }
}
