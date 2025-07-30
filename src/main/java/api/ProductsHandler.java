package api;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import exception.ProductNotFoundException;
import model.Product;
import service.ProductService;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;


public class ProductsHandler implements HttpHandler {




    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String response = "";
        int statusCode = 200;
        System.out.println("Method is :"+exchange.getRequestMethod());
        String path = exchange.getRequestURI().getPath();
        System.out.println("path: " + path);
        if("GET".equals(exchange.getRequestMethod())){

            if(path.equals("/products")){//return whole list
                ProductService productService = new ProductService();
                List<Product> products = productService.getProducts();

                StringBuilder sb = new StringBuilder("[");
                for (int i = 0; i < products.size(); i++) {
                    Product p = products.get(i);
                    sb.append("{")
                            .append("\"id\":").append(p.getId()).append(",")
                            .append("\"name\":\"").append(p.getName()).append("\",")
                            .append("\"price\":").append(p.getPrice()).append(",")
                            .append("\"description\":\"").append(p.getDescription()).append("\"")
                            .append("}");
                    if (i < products.size() - 1) sb.append(",");
                }
                sb.append("]");
                response = sb.toString();

            }
            if(path.startsWith("/products/")) {
                String[] parts = path.split("/");

                if (parts.length == 3) {
                    try {
                        int productId = Integer.parseInt(parts[2]);
                        ProductService productService =new ProductService();
                        Product product = productService.getProduct(productId);

                        // Manually build JSON response
                        response = "{"
                                + "\"id\":" + product.getId() + ","
                                + "\"name\":\"" + product.getName() + "\","
                                + "\"price\":" + product.getPrice() + ","
                                + "\"description\":\"" + product.getDescription() + "\""
                                + "}";
                        statusCode = 200;

                    } catch (NumberFormatException e) {
                        response = "Invalid product ID format";
                        statusCode = 400;
                    } catch (ProductNotFoundException e) {
                        response = e.getMessage();
                        statusCode = 404;
                    }
                } else {
                    response = "Invalid product path";
                    statusCode = 400;
                }
            }


            exchange.sendResponseHeaders(statusCode, response.getBytes().length);
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();


        }


    }
}
