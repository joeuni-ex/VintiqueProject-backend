package com.mysite.finalProject.service;

import com.mysite.finalProject.dto.PostProductRequestDto;
import com.mysite.finalProject.model.Image;
import com.mysite.finalProject.model.Product;
import com.mysite.finalProject.repository.ImageRepository;
import com.mysite.finalProject.repository.ProductRepository;
import com.mysite.finalProject.repository.projection.ProductItem;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ImageRepository imageRepository;



    //제품 저장하기
    @Override
    public Product saveProduct(PostProductRequestDto dto){

        Product product = new Product(dto);
        product = productRepository.save(product);

        Long productNumber = product.getId();
        //System.out.println(product.getName());
        List<String> boardImageList = dto.getBoardImageList();
        List<Image> imageEntities = new ArrayList<>();

        for (String image : boardImageList){
            Image imageEntity = new Image(productNumber,image);
            imageEntities.add(imageEntity);
        }

        imageRepository.saveAll(imageEntities);
        return product;
    }

    //제품 삭제하기
    @Override
    public void deleteProduct(Long id){
        imageRepository.deleteByProductId(id);
        productRepository.deleteById(id);
    }

    //모든 제품 조회하기
    @Override
    public List<Product> findAllProducts(){
        return productRepository.findAll();
    }


    @Override
    //제품 상세 조회하기
    public List<ProductItem> findByIdProduct(Long id){

        return productRepository.findByIdImageOfProduct(id);
    }


}
