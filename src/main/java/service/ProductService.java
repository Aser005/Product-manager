package service;

import dao.Productdao;
import exception.InvalidProductException;
import exception.ProductNotFoundException;
import model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductService {

;

    public ProductService() {
    }
   // public void validateProduct(Product product) throws InvalidProductException {
      //  if (product.getId() <= 0 ||
       //         product.getPrice() <= 0 ||
       //         product.getName() == null ||
       //         product.getDescription() == null) {
        //    throw new InvalidProductException("Invalid product fields");
      //  }
   // }

    public List<Product> getProducts(){
        Productdao productdao = new Productdao();
        return productdao.getAllProducts();
    }

    public Product getProduct(int id) throws ProductNotFoundException {
        Productdao productdao = new Productdao();
        List<Product> products = productdao.getAllProducts();
        for(int i=0;i<products.size();i++){
            if(products.get(i).getId()==id){
                return products.get(i);
            }
        }
        throw new ProductNotFoundException("Product not found");



    }







}
