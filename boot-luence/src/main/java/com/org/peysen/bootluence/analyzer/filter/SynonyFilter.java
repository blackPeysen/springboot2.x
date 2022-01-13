package com.org.peysen.bootluence.analyzer.filter;

import com.org.peysen.bootluence.service.ISynonyEngine;
import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;
import org.apache.lucene.util.AttributeSource;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.List;
import java.util.Stack;

/**
 * Author: peimengmeng
 * Date: 2022/1/13 11:34
 * Desc:
 */
public class SynonyFilter extends TokenFilter {
    public static final String TOKEN_TYPE_SYNONY = "synony";

    private Stack<String> synonyStack;
    private ISynonyEngine synonyEngine;
    private AttributeSource.State current;

    private CharTermAttribute termAttribute;
    private PositionIncrementAttribute positionIncrementAttribute;

    /**
     * Construct a token stream filtering the given input.
     *
     * @param input
     */
    public SynonyFilter(TokenStream input, ISynonyEngine synonyEngine) {
        super(input);
        synonyStack = new Stack<>();
        this.synonyEngine = synonyEngine;

        this.termAttribute = addAttribute(CharTermAttribute.class);
        this.positionIncrementAttribute = addAttribute(PositionIncrementAttribute.class);
    }


    @Override
    public final boolean incrementToken() throws IOException {
        if (synonyStack.size() > 0){
            String syn = synonyStack.pop();
            restoreState(current);
            termAttribute.append(syn);
            positionIncrementAttribute.setPositionIncrement(0);

            return true;
        }

        if (!input.incrementToken()){
            return false;
        }

        if (addAliasesToStack()){
            current = captureState();
        }

        return true;
    }

    private boolean addAliasesToStack() throws IOException {
        String syn = String.valueOf(termAttribute.buffer());
        List<String> synonyWords = synonyEngine.getSynonyWords(syn);

        if (CollectionUtils.isEmpty(synonyWords)){
            return false;
        }

        synonyWords.stream().forEach(synony -> synonyStack.push(synony));

        return true;
    }
}
