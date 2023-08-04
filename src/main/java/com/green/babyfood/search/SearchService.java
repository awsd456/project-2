package com.green.babyfood.search;

import com.green.babyfood.search.EnToKo.EnToKo;
import com.green.babyfood.search.model.SearchSelDto;
import com.green.babyfood.search.model.SearchtSelVo;
import kr.co.shineware.nlp.komoran.constant.DEFAULT_MODEL;
import kr.co.shineware.nlp.komoran.core.Komoran;
import kr.co.shineware.nlp.komoran.model.KomoranResult;
import kr.co.shineware.nlp.komoran.model.Token;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class SearchService {
    private final SearchMapper mapper;
    List<SearchtSelVo> selproduct(String product, int page, int row){
        SearchSelDto dto = new SearchSelDto();
        dto.setPage(page);
        dto.setRow(row);

        int startIdx = (dto.getPage() - 1) * dto.getRow();
        dto.setStartIdx(startIdx);

        String msg = "";
        boolean isEnglish = true;

        Pattern p = Pattern.compile("[a-zA-Z0-9]");
        String typoText = product;
        Matcher m = p.matcher(typoText);
        isEnglish = m.find();
        if(isEnglish) {
            msg = EnToKo.engToKor(typoText);
        } else {
            msg = typoText;
        }

        Komoran komoran = new Komoran(DEFAULT_MODEL.FULL);
        KomoranResult analyzeResultList = komoran.analyze(msg);
        List<Token> tokenList = analyzeResultList.getTokenList();

        StringBuffer sb = new StringBuffer();


        if (tokenList.size()!=1) {
            for (int i = 0; i < tokenList.size() - 1; i++) {
                sb.append(tokenList.get(i).getMorph() + "|");
            }
        }
        StringBuffer append = sb.append(tokenList.get(tokenList.size() - 1).getMorph());
        String str = String.valueOf(append);

        log.info("str: {}", str);
        dto.setMsg(str);
        List<SearchtSelVo> productDtos = mapper.selproduct(dto);

        return productDtos;
    }

    public List<SearchtSelVo>selfilter(String product,int page, int row, int sorter,
                                       String egg, String milk, String buckwheat,String peanut, String soybean,String wheat
            ,String pine_nut,String walnut,String crab,String shrimp,String squid,String mackerel,String shellfish,String peach
            ,String tomato,String chicken,String pork,String beef,String sulfur_dioxide,String fish){


        
        StringBuffer allergy = new StringBuffer();

        allergy.append(egg+",").append(milk+",").append(buckwheat).append(",").append(peanut+",").append(soybean + ",")
                .append(wheat+",").append(pine_nut+",").append(walnut+",").append(crab+",").append(shrimp+",").append(squid+",")
                .append(mackerel+",").append(shellfish+",").append(peach+",").append(tomato+",").append(chicken+",").append(pork+",")
                .append(beef+",").append(sulfur_dioxide+",").append(fish+",");
        String strallergy = String.valueOf(allergy);
        String[] split = strallergy.split(",");
        String plus="";
        for (String s : split) {
            if(!s.equals("null")){
                plus+=s+",";
            }
        }
        String subAllergy = plus.substring(0, plus.length()-1);

        SearchSelDto dto = new SearchSelDto();
        dto.setPage(page);
        dto.setRow(row);
        dto.setAllergy(subAllergy);
        dto.setSorter(sorter);

        int startIdx = (dto.getPage() - 1) * dto.getRow();
        dto.setStartIdx(startIdx);

        String msg = "";
        boolean isEnglish = true;

        Pattern p = Pattern.compile("[a-zA-Z0-9]");
        String typoText = product;
        Matcher m = p.matcher(typoText);
        isEnglish = m.find();
        if(isEnglish) {
            msg = EnToKo.engToKor(typoText);
        } else {
            msg = typoText;
        }

        Komoran komoran = new Komoran(DEFAULT_MODEL.FULL);
        KomoranResult analyzeResultList = komoran.analyze(msg);

        List<Token> tokenList = analyzeResultList.getTokenList();

        StringBuffer sb = new StringBuffer();


        if (tokenList.size()!=1) {
            for (int i = 0; i < tokenList.size() - 1; i++) {
                sb.append(tokenList.get(i).getMorph() + "|");
            }
        }
        StringBuffer append = sb.append(tokenList.get(tokenList.size() - 1).getMorph());
        String str = String.valueOf(append);

        log.info("str: {}", str);
        dto.setMsg(str);
        List<SearchtSelVo> productDtos = mapper.selfilter(dto);

        return productDtos;

    }
}
