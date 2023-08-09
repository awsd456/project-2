package com.green.babyfood.mypage;

import com.green.babyfood.mypage.model.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name = "마이페이지 , 주문내역")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mypage")
public class MypageController {

    private final MypageService service;

    @GetMapping("/orderlist")
    @Operation(summary = "주문내역조회",description = ""+
            "iuser: 유저PK <br>"+
            "month: 조회하고싶은 기간(개월) <br>")
    OrderlistSelDto[] getOrderlistMonths(OrderlistMonthsSelDto dto){
        return service.Orderlist(dto);
    }

    @GetMapping("/orderlist/detail")
    @Operation(summary = "상세주문내역",description = "")
    OrderlistSelUserDto getOrderlistDetail(Long orderId){
        return service.OrderlistDetail(orderId);
    }

    @DeleteMapping("/orderlist")
    @Operation(summary = "주문내역삭제",description = ""+
            "orderId: 주문내역PK <br>")
    public int delorderlist(Long orderId){
        return service.delorder(orderId);
    }

    @GetMapping("/profile")
    @Operation(summary = "내 정보조회",description = ""+
            "image: 프로필이미지<br>"+
            "address: 주소 <br>")
    ProfileSelDto getprofile(Long iuser){
        return service.profile(iuser);
    }

    @PatchMapping("/profile")
    @Operation(summary = "내정보(유저 정보) 수정" , description = "")
    int patchprofile(@RequestBody ProfileUpdDto dto){
        return service.UpdProfileDto(dto);
    }
    @PostMapping("/profile/nickname")
    @Operation(summary = "닉네임 중복체크" ,
            description = "return : 1이면 중복인것")
    int getNickNamecheck(@RequestParam String nickname){
        return service.nicknmcheck(nickname);
    }

    @DeleteMapping("/profile")
    @Operation(summary = "회원탈퇴")
    int delprofile(Long iuser){
        return service.delUser(iuser);
    }


}
