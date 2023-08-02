package com.green.babyfood.main;

import com.green.babyfood.main.model.MainSelVo;
import com.green.babyfood.main.model.MainSelVoMaxPaige;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MainService  {

    private final MainMapper mapper;


    public MainSelVoMaxPaige mainSelView(int paige, int row){
       int startIdx=(paige-1)*row;
        List<MainSelVo> mainSelVos = mapper.mainSelView(startIdx, row);

        int maxPaige1 = mapper.maxPaige();
        int maxPaige2= (int) Math.ceil((double)maxPaige1/row);
        MainSelVoMaxPaige mainSelVoMaxPaige=new MainSelVoMaxPaige();
        mainSelVoMaxPaige.setMaxPaige(maxPaige2);
        mainSelVoMaxPaige.setList(mainSelVos);

        return mainSelVoMaxPaige;
    }

    public List<MainSelVo> random(){
        return mapper.random();
    }

    public List<MainSelVo> bestSell(){
        return mapper.bestSell();
    }
}
