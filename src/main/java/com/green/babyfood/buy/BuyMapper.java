package com.green.babyfood.buy;

import com.green.babyfood.buy.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BuyMapper {
    int InsBuy(BuyInsDto dto);
    int InsBuyDetail(BuyDetailInsDto dto);

    int delOrderbasket(Long cartId);
    int updProduct(BuyUpdDto dto);
    int userpoint(BuyPointUpdDto dto);

    List<BuySelOrderDto> selorderproduct(int orderId);
    BuySelUserDto selorder(int orderId);


}
