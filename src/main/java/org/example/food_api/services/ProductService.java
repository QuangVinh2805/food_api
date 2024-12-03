package org.example.food_api.services;

import org.example.food_api.models.*;
import org.example.food_api.models.Product;
import org.example.food_api.repository.ProductDetailRepository;
import org.example.food_api.repository.ProductRepository;
import org.example.food_api.repository.UserRepository;
import org.example.food_api.request.ProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductDetailRepository productDetailRepository;

    public ResponseEntity<List<Product>> listAllProduct(){
        List<Product> listProduct= productRepository.findAll();
        if(listProduct.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Product>>(listProduct, HttpStatus.OK);
    }

    public ResponseEntity<List<ProductDetail>> listAllProductDetail(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ProductDetail> pagedResult = productDetailRepository.findAll(pageable);

        if (pagedResult.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(pagedResult.getContent(), HttpStatus.OK);
    }
    public ResponseEntity<Product> createProduct(ProductRequest productRequest) {
        // Kiểm tra null và tính hợp lệ của các trường trong ProductRequest
        if (productRequest.getProductName() == null || productRequest.getProductName().isEmpty()) {
            return new ResponseEntity("Product name cannot be null or empty", HttpStatus.BAD_REQUEST);
        }
        if (productRequest.getCategoryId() == null) {
            return new ResponseEntity("Category ID cannot be null", HttpStatus.BAD_REQUEST);
        }
        if (productRequest.getPrice() == null || productRequest.getPrice() <= 0) {
            return new ResponseEntity("Price must be a positive value", HttpStatus.BAD_REQUEST);
        }
        if (productRequest.getQuantity() == null || productRequest.getQuantity() < 0) {
            return new ResponseEntity("Quantity cannot be null or negative", HttpStatus.BAD_REQUEST);
        }
        if (productRequest.getImage() == null || productRequest.getImage().isEmpty()) {
            return new ResponseEntity("Image URL cannot be null or empty", HttpStatus.BAD_REQUEST);
        }

        // Kiểm tra sản phẩm đã tồn tại
        Product existingProduct = productRepository.findByProductName(productRequest.getProductName());
        if (existingProduct != null) {
            String message = "Product name already exists: ";
            System.out.println(message + existingProduct.getProductName());
            return new ResponseEntity(message, HttpStatus.CONFLICT);
        }

        // Tạo đối tượng Product từ ProductRequest
        Product product = new Product();
        product.setProductName(productRequest.getProductName());
        product.setImage(productRequest.getImage());

        // Tạo đối tượng ProductDetail từ ProductRequest
        ProductDetail productDetail = new ProductDetail();
        productDetail.setPrice(productRequest.getPrice());
        productDetail.setQuantity(productRequest.getQuantity());

        // Thiết lập Category cho ProductDetail
        Category category = new Category();
        category.setId(productRequest.getCategoryId());
        productDetail.setCategory(category);

        // Lưu sản phẩm và chi tiết sản phẩm vào cơ sở dữ liệu
        productRepository.save(product);
        productDetail.setProduct(product); // Liên kết ProductDetail với Product
        productDetailRepository.save(productDetail);

        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }




    public ResponseEntity<Product> updateProduct(ProductRequest productRequest) {
        Long productId = productRepository.findIdByProductId(productRequest.getId());
        if (productId == null) {
            String message = "product id not found";
            System.out.println(message + productId);
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }

        // Lấy sản phẩm hiện tại từ cơ sở dữ liệu
        Product existingProduct = productRepository.findById(productRequest.getId());
        ProductDetail productDetail = productDetailRepository.findByProductId(productId);

        if (existingProduct == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        // Cập nhật các trường không phải null
        if (productRequest.getProductName() != null) {
            existingProduct.setProductName(productRequest.getProductName());
        }
        if (productRequest.getCategoryId() != null) {
            // Tạo đối tượng Category mới từ categoryId
            Category category = new Category();
            category.setId(productRequest.getCategoryId());
            productDetail.setCategory(category);
        }
        if (productRequest.getPrice() != null) {
            productDetail.setPrice(productRequest.getPrice());
        }
        if (productRequest.getQuantity() != null){
            productDetail.setQuantity(productRequest.getQuantity());
        }
        if (productRequest.getImage() != null) {
            existingProduct.setImage(productRequest.getImage());
        }

        // Lưu sản phẩm đã được cập nhật vào cơ sở dữ liệu
        productRepository.save(existingProduct);
        productDetailRepository.save(productDetail);

        return new ResponseEntity<>(existingProduct, HttpStatus.OK);
    }




    public ResponseEntity<String> deleteProduct(Long id){
        Long productId = productRepository.findIdByProductId(id);
        if (productId == null){
            String message = "product id not found";
            System.out.println(message + productId);
            return new ResponseEntity(message, HttpStatus.FORBIDDEN);

        }
        ProductDetail productDetail = productDetailRepository.findByProductId(productId);
        productDetailRepository.delete(productDetail);
        productRepository.deleteById(productId);
        return new ResponseEntity(HttpStatus.OK);
    }

    public ResponseEntity<List<ProductDetail>> listProductDetailByHot(){
        return new ResponseEntity<>(productDetailRepository.findByIsHot(1L), HttpStatus.OK);
    }
}
