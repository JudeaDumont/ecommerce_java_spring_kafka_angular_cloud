package com.alibou.ecommerce.product;

import com.alibou.ecommerce.exception.ProductPurchaseException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;
    private final ProductMapper mapper;


    public Integer createProduct(ProductRequest request) {
        var product = mapper.toProduct(request);
        return repository.save(product).getId();
    }

    public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> request) {
        List<Integer> purchasedProductIds = request.stream().map(ProductPurchaseRequest::productId).toList();

        var products = repository.findAllByIdInOrderById(purchasedProductIds);

        if(purchasedProductIds.size() != products.size()){
            throw new ProductPurchaseException("One or more products does not exist");
        }

        var storedRequest = request.stream().sorted(Comparator.comparing(ProductPurchaseRequest::productId))
                .toList();

        var purchasedProducts = new ArrayList<ProductPurchaseResponse>();
        for (int i = 0; i < products.size(); i++) {
            var product = products.get(i);
            var purchasedProduct = storedRequest.get(i);
            if(product.getAvailableQuantity() < purchasedProduct.quantity()){
                throw new ProductPurchaseException("One or more products do not have enough available quantity");
            }
            var newAvailableQuantity = product.getAvailableQuantity() - purchasedProduct.quantity();
            product.setAvailableQuantity(newAvailableQuantity);
            repository.save(product);
            purchasedProducts.add(mapper.toProductPurchaseResponse(product, purchasedProduct.quantity()));
        }

        return purchasedProducts;
    }

    public List<ProductResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toProductResponse)
                .collect(Collectors.toList());
    }

    public ProductResponse findById(Integer productId) {
        return repository.findById(productId)
                .map(mapper::toProductResponse)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Product with id " + productId + " not found"));
    }
}
