package com.org.peysen.bootluence.service.impl;

import com.org.peysen.bootluence.service.ILuceneAnalyzerService;
import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Author: peimengmeng
 * Date: 2022/1/13 10:17
 * Desc:
 */
@Service
public class LuceneAnalyzerServiceImpl implements ILuceneAnalyzerService {
    @Autowired
    private List<Analyzer> analyzerList;

    @Override
    public void analyze(String content) {
        if (StringUtils.isBlank(content) || CollectionUtils.isEmpty(analyzerList)){
            return;
        }

        analyzerList.stream().forEach(analyzer -> {
            TokenStream tokenStream = analyzer.tokenStream("contents", content);

            displayTokensWithFullDetails(tokenStream);
        });
    }


    private void displayTokens(TokenStream tokenStream)  {
        try {
            tokenStream.reset();
            CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);

            while (tokenStream.incrementToken()){
                System.out.println("[" + String.valueOf(charTermAttribute.buffer()) + "]");
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            System.out.println("-------------------------");
            System.out.println();
        }
    }

    private void displayTokensWithFullDetails(TokenStream tokenStream)  {
        try {
            tokenStream.reset();

            // char数组在转为String时，会多出很多空格，建议使用TermToBytesRefAttribute
            CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);
            TermToBytesRefAttribute termToBytesRefAttribute = tokenStream.addAttribute(TermToBytesRefAttribute.class);

            PositionIncrementAttribute positionIncrementAttribute = tokenStream.addAttribute(PositionIncrementAttribute.class);
            OffsetAttribute offsetAttribute = tokenStream.addAttribute(OffsetAttribute.class);
            TypeAttribute typeAttribute = tokenStream.addAttribute(TypeAttribute.class);

            int position = 0;
            while (tokenStream.incrementToken()){

                int increment = positionIncrementAttribute.getPositionIncrement();
                if (increment > 0){
                    position = position + increment;
                    System.out.println();
                    System.out.print(position + ": ");
                }


                System.out.println();

                System.out.println("[" +
                        termToBytesRefAttribute.getBytesRef().utf8ToString() + ":" +
                        offsetAttribute.startOffset() + "-> " +
                        offsetAttribute.endOffset() + ": " +
                        typeAttribute.type()  + "]");
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            System.out.println("-------------------------");
            System.out.println();
        }
    }
}
