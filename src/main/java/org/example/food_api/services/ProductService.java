package org.example.food_api.services;

import org.example.food_api.models.Category;
import org.example.food_api.models.Product;
import org.example.food_api.models.ProductDetail;
import org.example.food_api.repository.CategoryRepository;
import org.example.food_api.repository.ProductDetailRepository;
import org.example.food_api.repository.ProductRepository;
import org.example.food_api.request.ProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ProductDetailRepository productDetailRepository;

    public ResponseEntity<List<Product>> listAllProduct() {
        List<Product> listProduct = productRepository.findAll();
        if (listProduct.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Product>>(listProduct, HttpStatus.OK);
    }

    public List<ProductDetail> findProductDetailByProductId(Long productId) {
        List<ProductDetail> productDetail = productDetailRepository.findProductDetailsByProductId(productId);
        if (productDetail == null) {
            ResponseEntity.notFound().build();
        }
        return productDetail;
    }

    public ResponseEntity<List<ProductDetail>> listAllProductDetail(int page, int size, Long status) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());

        Page<ProductDetail> pagedResult;

        if (status != null) {
            pagedResult = productDetailRepository.findByStatus(pageable, status);
        } else {
            pagedResult = productDetailRepository.findAll(pageable);
        }

        if (pagedResult.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(pagedResult.getContent(), HttpStatus.OK);
    }

    public ResponseEntity<Product> createProduct(ProductRequest productRequest) {
        // Thiết lập Category cho ProductDetail
        Category category = categoryRepository.findById(productRequest.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category không tồn tại"));

        // Kiểm tra sản phẩm đã tồn tại
        if (productRepository.findByProductName(productRequest.getProductName()) != null) {
            return new ResponseEntity("Tên sản phẩm đã tồn tại", HttpStatus.CONFLICT);
        }

        // Tạo đối tượng Product từ ProductRequest
        Product product = new Product();
        product.setStatus(1L);
        product.setProductName(productRequest.getProductName());
        product.setImage(productRequest.getImage());

        // Tạo đối tượng ProductDetail từ ProductRequest
        ProductDetail productDetail = new ProductDetail();
        productDetail.setPrice(productRequest.getPrice());
        productDetail.setQuantity(productRequest.getQuantity());
        productDetail.setCategory(category);
        productDetail.setStatus(1L); // Thiết lập giá trị cho trường status

        // Lưu sản phẩm và chi tiết sản phẩm vào cơ sở dữ liệu
        Product savedProduct = productRepository.save(product);
        productDetail.setProduct(savedProduct);
        productDetailRepository.save(productDetail);

        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    public ResponseEntity<Product> updateProduct(ProductRequest productRequest) {
        Long productId = productRepository.findIdByProductId(productRequest.getId());
        if (productId == null) {
            String message = "product id " + productId + " not found";
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
        if (productRequest.getStatus() != null) {
            existingProduct.setStatus(productRequest.getStatus());
        }
        if (productRequest.getCategoryId() != null) {
            Long categoryId = productRequest.getCategoryId();
            Category category = categoryRepository.findCategoryById(categoryId);
            if (category == null) {
                return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
            }
            productDetail.setCategory(category);
        }
        if (productRequest.getPrice() != null) {
            productDetail.setPrice(productRequest.getPrice());
        }
        if (productRequest.getQuantity() != null) {
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


    public ResponseEntity<String> deleteProduct(Long id) {
        Long productId = productRepository.findIdByProductId(id);
        if (productId == null) {
            String message = "product id not found";
            System.out.println(message + productId);
            return new ResponseEntity(message, HttpStatus.FORBIDDEN);

        }
        ProductDetail productDetail = productDetailRepository.findByProductId(productId);
        if (productDetail.getStatus() == 0L) {
            productDetail.setStatus(1L);
        } else {
            productDetail.setStatus(0L);
        }

        productDetailRepository.save(productDetail);
        Product product = productRepository.findByProductId(productId);
        if (product.getStatus() == 0L) {
            product.setStatus(1L);
        } else {
            product.setStatus(0L);
        }
        productRepository.save(product);
        return new ResponseEntity(HttpStatus.OK);
    }

    public ResponseEntity<List<ProductDetail>> listProductDetailByHot() {
        return new ResponseEntity<>(productDetailRepository.findByIsHot(1L), HttpStatus.OK);
    }
}
