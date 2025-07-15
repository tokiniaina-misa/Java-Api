import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws Exception {
        TicketService service = new TicketService(new int[]{1, 2, 3});
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        server.createContext("/api/create-ticket", new TicketHandler(service));
        server.createContext("/api/peek-ticket", new TicketHandler(service));
        server.createContext("/api/serve-ticket", new TicketHandler(service));
        server.createContext("/api/ticket-count", new TicketHandler(service));
        server.createContext("/api/ticket-list", new TicketHandler(service));

        server.createContext("/", exchange -> {
            byte[] html = Files.readAllBytes(Path.of("front/index.html"));
            exchange.sendResponseHeaders(200, html.length);
            exchange.getResponseBody().write(html);
            exchange.getResponseBody().close();
        });

        server.setExecutor(null);
        System.out.println("Serveur sur http://localhost:8080");
        server.start();
    }
}
