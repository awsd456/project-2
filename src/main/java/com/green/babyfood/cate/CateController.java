package com.green.babyfood.cate;

import com.green.babyfood.cate.model.CateSelLevelDto;
import com.green.babyfood.cate.model.CateSelLevelVo;
import com.green.babyfood.cate.model.CateView;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cate")
@RequiredArgsConstructor
public class CateController {

    private final CateService service;


    @GetMapping("/list")
    @Operation(summary = "카테고리 클릭시 해당품목만 보여주는 메소드",description = ""+
    "productId : 해당상품의 고유번호<br>"+
    "thumbnail : 해당상품의 썸네일<br>"+
    "title : 해당상품의 제목<br>"+
    "name : 해당상품의 이름<br>"+
    "price : 해당상품의 가격<br>"+
    "quantity : 해당상품의 재고량<br>"+
    "volumn : 해당상품의 판매량")
    public CateSelLevelVo getLevel(@RequestBody CateSelLevelDto dto){
        return service.cateSelLevel(dto);
    }


    @GetMapping("/all")
    @Operation(summary = "카테고리 목록")
    public List getAll(){
        return service.selCateList();
    }
}
