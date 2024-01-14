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
import java.util.Optional;

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

        List<String> boardImageList = dto.getBoardImageList();
        List<Image> imageEntities = new ArrayList<>();

        for (String image : boardImageList){
            Image imageEntity = new Image(productNumber,image);
            imageEntities.add(imageEntity);
        }

        imageRepository.saveAll(imageEntities);
        return product;
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


    @Override
    //제품 수정하기
    public Optional<Product> modifyProduct(Long id, PostProductRequestDto dto){
        // 일단 id에 맞는 값들을 가져옴
        Optional<Product> entity = this.productRepository.findById(id);

        entity.ifPresent(t->{

            if(dto.getName() !=null){
                t.setName(dto.getName());
            }
            if(dto.getPrice() !=null){
                t.setPrice(dto.getPrice());
            }
            if(dto.getStock() !=null){
                t.setStock(dto.getStock());
            }
            if(dto.getDescription() !=null){
                t.setDescription(dto.getDescription());
            }
            if(dto.getMainImage() !=null){
                t.setMainImage(dto.getMainImage());
            }

            this.productRepository.save(t);
          }
        );

        // Get the existing images associated with the product
        List<Image> existingImages = this.imageRepository.findByProductId(id);

        // Get the list of new image URLs from the request
        List<String> newBoardImageList = dto.getBoardImageList();

        // Create a list to store the new Image entities
        List<Image> newImages = new ArrayList<>();

        // Update existing Image entities based on the new image URLs
        if (newBoardImageList != null) {
            for (int i = 0; i < Math.min(existingImages.size(), newBoardImageList.size()); i++) {
                Image existingImage = existingImages.get(i);
                String newImageUrl = newBoardImageList.get(i);
                existingImage.setImage(newImageUrl);
                this.imageRepository.save(existingImage);
            }
        }

        // Create and save new Image entities for additional images
        for (int i = existingImages.size(); i < newBoardImageList.size(); i++) {
            String newImageUrl = newBoardImageList.get(i);
            Image newImageEntity = new Image(id, newImageUrl);
            this.imageRepository.save(newImageEntity);
        }


        return entity;
    }


    //제품 삭제하기
    @Override
    public void deleteProduct(Long id){
        imageRepository.deleteByProductId(id);
        productRepository.deleteById(id);
    }
}
