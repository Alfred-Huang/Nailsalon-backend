package com.nailsalon.nailsalonbackend.controller;

import com.nailsalon.nailsalonbackend.pojo.Product;
import com.nailsalon.nailsalonbackend.service.product.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/product")
public class ProductController {

    final
    ProductServiceImpl productService;

    public ProductController(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @PostMapping("/addProduct")
    public void addProduct(@RequestBody Map<String, List<Product>> map) throws IOException {
        productService.addProduct(map.get("productList"));
    }

    @PostMapping("updateProduct")
    public void updateProduct(@RequestBody Map<String, Product> map) throws IOException {
        System.out.println(map.get("data"));
        productService.updateProduct(map.get("data"));
    }

    @PostMapping("/getProduct")
    public List<Map<String, Object>> getProduct(){
        List<Map<String, Object>> result = new ArrayList<>();
        try {
            result = productService.getProduct();
        } catch (IOException e) {
            e.printStackTrace();
            return  result;
        }
        return result;
    }

    @PostMapping("/deleteProduct")
    public void deleteProduct(@RequestBody Map<String, String> targetId){
        try {
           productService.deleteProduct(targetId.get("targetId"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/getBrand/{brand}")
    public List<Map<String, Object>> getTargetBrand(@PathVariable String brand){
        List<Map<String, Object>> result = new ArrayList<>();
        try {
            result = productService.getProductByBrand(brand);
        } catch (IOException e) {
            e.printStackTrace();
            return  result;
        }
        return result;
    }

    @GetMapping("/getQuantity/{quantity}")
    public List<Map<String, Object>> getTargetQuantity(@PathVariable String quantity){
        List<Map<String, Object>> result = new ArrayList<>();
        try {
            result = productService.getProductByQuantity(quantity);
        } catch (IOException e) {
            e.printStackTrace();
            return  result;
        }
        return result;
    }

    @GetMapping("/getType/{type}")
    public List<Map<String, Object>> getTargetType(@PathVariable String type){
        List<Map<String, Object>> result = new ArrayList<>();
        try {
            result = productService.getProductByType(type);
        } catch (IOException e) {
            e.printStackTrace();
            return  result;
        }
        return result;
    }

    @PostMapping("/getBrandTag")
    public List<String> getBrandTag(){
        List<String> result = new ArrayList<>();
        try {
            result = productService.getBrandTag();
        } catch (IOException e) {
            e.printStackTrace();
            return  result;
        }
        return result;
    }

    @PostMapping("/getProductByTags")
    public List<Map<String,Object>> getProductByTags(@RequestBody Map<String, List<String>> tagMap){
        List<Map<String,Object>> result = new ArrayList<>();
        try {
            result = productService.getProductByTag(tagMap.get("tagList"));
        } catch (IOException e) {
            e.printStackTrace();
            return  result;
        }
        return result;
    }

}
