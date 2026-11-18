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
    String uri = exchange.getRequestURI().getPath();

    Path file;

    if (uri.equals("/")) {
        file = Path.of("front/index.html");
    } else {
        file = Path.of("front").resolve(uri.substring(1)).normalize();
    }

    if (!file.startsWith(Path.of("front")) || !Files.exists(file) || Files.isDirectory(file)) {
        exchange.sendResponseHeaders(404, -1);
        exchange.close();
        return;
    }

    String contentType = Files.probeContentType(file);

    if (contentType == null) {
        contentType = "application/octet-stream";
    }

    exchange.getResponseHeaders().set("Content-Type", contentType + "; charset=UTF-8");

    byte[] content = Files.readAllBytes(file);

    exchange.sendResponseHeaders(200, content.length);
    exchange.getResponseBody().write(content);
    exchange.close();
});

        server.setExecutor(null);
        System.out.println("Serveur sur http://localhost:8080");
        server.start();
    }
}
