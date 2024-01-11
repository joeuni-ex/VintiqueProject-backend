package com.mysite.finalProject.Service;

import com.mysite.finalProject.Dto.PostProductRequestDto;
import com.mysite.finalProject.model.Image;
import com.mysite.finalProject.model.Product;
import com.mysite.finalProject.repository.ImageRepository;
import com.mysite.finalProject.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ImageRepository imageRepository;

    //제품 저장하기
    @Override
    public Product saveProduct(PostProductRequestDto dto){

        Product product = new Product(dto);

        Long productNumber = product.getId();
        //System.out.println(product.getName());
        List<String> boardImageList = dto.getBoardImageList();
        List<Image> imageEntities = new ArrayList<>();

        for (String image : boardImageList){
            Image imageEntity = new Image(productNumber,image);
            imageEntities.add(imageEntity);
        }

        imageRepository.saveAll(imageEntities);
        return productRepository.save(product);
    }

    //제품 삭제하기
    @Override
    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }

    //모든 제품 조회하기
    @Override
    public List<Product> findAllProducts(){
        return productRepository.findAll();
    }

}
