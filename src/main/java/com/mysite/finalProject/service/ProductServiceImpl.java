package com.mysite.finalProject.service;

import com.mysite.finalProject.dto.PostProductRequestDto;
import com.mysite.finalProject.dto.ProductResponseDto;
import com.mysite.finalProject.model.Image;
import com.mysite.finalProject.model.Product;
import com.mysite.finalProject.model.User;
import com.mysite.finalProject.repository.ImageRepository;
import com.mysite.finalProject.repository.InterestRepository;
import com.mysite.finalProject.repository.ProductRepository;
import com.mysite.finalProject.repository.projection.ProductItem;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    private final InterestRepository interestRepository;



    //제품 저장하기
    @Override
    public Product saveProduct(PostProductRequestDto dto){
        //제품 추가 요청으로 받아온 dto를 repository를 통해 db에 저장한다.
        Product product = new Product(dto);
        product = productRepository.save(product);

        //제품 생성 시 자동으로 생성되는 id를 가져온다.
        Long productNumber = product.getId();

        //이미지 리스트는 빈 배열에 넣는다
        List<String> boardImageList = dto.getBoardImageList();
        List<Image> imageEntities = new ArrayList<>();
        //이미지 리스트의 크기만큼 반복문을 실횅한다.
        for (String image : boardImageList){
            //Image 객체에 제품 id와 이미지를 저장하고 배열에 추가한다.
            Image imageEntity = new Image(productNumber,image);
            imageEntities.add(imageEntity);
        }
        // 모든 이미지를 db에 저장한다.
        imageRepository.saveAll(imageEntities);
        return product;
    }



    @Override
    //모든 제품 조회하기(페이징 처리)
    public Page<ProductResponseDto> findAll(int page, int maxPageSize, User user){
        Pageable pageable = PageRequest.of(page, maxPageSize); //maxPageSize -> 한 페이지에 출력할 게시글 개수
        Page<Product> products = productRepository.findAll(pageable);

        System.out.println("제품테스트"+products);
        List<ProductResponseDto> result = new ArrayList<>();

        for(Product product: products){
            Boolean interest = interestRepository.existsByProductIdAndUserId(product.getId(),user.getId()); //이미 리뷰 작성했는지 체크
            System.out.println("관심 제품 추가 여부 "+interest);
            result.add(new ProductResponseDto().toDto(product,interest));
        }

        return new PageImpl<>(result, pageable, products.getTotalElements());
    }

    //모든 제품 조회하기(가격 높은 순 처리 + 페이징)
    @Override
    public Page<Product> findAllOrderByColumnDesc(int page, int maxPageSize){
        Pageable pageable = PageRequest.of(page, maxPageSize); //maxPageSize -> 한 페이지에 출력할 게시글 개수
        return productRepository.findAllByOrderByPriceDesc(pageable);
    }

    //모든 제품 조회하기(가격 낮은 순 처리 + 페이징)
    @Override
    public Page<Product> findAllOrderByColumnAsc(int page, int maxPageSize){
        Pageable pageable = PageRequest.of(page, maxPageSize); //maxPageSize -> 한 페이지에 출력할 게시글 개수
        return productRepository.findAllByOrderByPriceAsc(pageable);
    }

    //카테고리 별 제품 조회하기(페이징)
    @Override
    public Page<Product> findByCategory(int page, int maxPageSize, String category){
        Pageable pageable = PageRequest.of(page, maxPageSize); //maxPageSize -> 한 페이지에 출력할 게시글 개수
        return productRepository.findByCategory(pageable,category);
    }

    //카테고리 별 제품 조회하기(가격 낮은 순 처리 + 페이징)
    @Override
    public Page<Product> findByCategoryByOrderAsc(int page, int maxPageSize, String category){
        Pageable pageable = PageRequest.of(page, maxPageSize); //maxPageSize -> 한 페이지에 출력할 게시글 개수
        return productRepository.findByCategoryPriceAsc(category,pageable);
    }

    //카테고리 별 제품 조회하기(가격 높은 순 처리 + 페이징)
    @Override
    public Page<Product> findByCategoryByOrderDesc(int page, int maxPageSize, String category){
        Pageable pageable = PageRequest.of(page, maxPageSize); //maxPageSize -> 한 페이지에 출력할 게시글 개수
        return productRepository.findByCategoryPriceDesc(category,pageable);
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

        // 제품 id에 해당하는 이미지들 가져오기
        List<Image> existingImages = this.imageRepository.findByProductId(id);

        // 수정할 사이드 이미지들을 빈 리스트에 담음
        List<String> newBoardImageList = dto.getBoardImageList();

        //수정할 이미지 개수가 기존의 개수보다 작을 경우, 초과하는 이미지는 삭제한다.
        if (existingImages.size() > newBoardImageList.size()) {
            for (int i = newBoardImageList.size(); i < existingImages.size(); i++) {
                Image existingImage = existingImages.get(i);
                this.imageRepository.delete(existingImage);
            }
        }

        //수정할 이미지 리스트가 공백이 아닐 경우에 실행함
        if (newBoardImageList != null) {

            //기존 이미지 개수와 수정할 이미지 개수 중 작은 개수 만큼 반복문 실행
            for (int i = 0; i < Math.min(existingImages.size(), newBoardImageList.size()); i++) {
                Image existingImage = existingImages.get(i); // 기존 이미지 리스트 중 i번째 사진을 가져옴

                System.out.println("기존의 이미지 :" +  existingImage);

                String newImageUrl = newBoardImageList.get(i);// 수정할 이미지 리스트 중 i번째 사진을 가져옴

                System.out.println("수정할 이미지 :" + newImageUrl);


                existingImage.setImage(newImageUrl);//교환한다.

                System.out.println("최종 이미지 :" +existingImage);

                this.imageRepository.save(existingImage);
            }
        }

        // 만약 수정하는 이미지 개수가 기존 업로드 된 이미지 개수보다 많을 때
        for (int i = existingImages.size(); i < newBoardImageList.size(); i++) { //이미 생성된 이미지의 개수부터 새로운 이미지 리스트 개수까지 반복
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
