package com.org.peysen.bootluence.analyzer;

import com.org.peysen.bootluence.analyzer.filter.SynonyFilter;
import com.org.peysen.bootluence.service.ISynonyEngine;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.LowerCaseFilter;
import org.apache.lucene.analysis.StopFilter;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.core.StopAnalyzer;
import org.apache.lucene.analysis.standard.StandardFilter;
import org.apache.lucene.analysis.standard.StandardTokenizer;

/**
 * Author: peimengmeng
 * Date: 2022/1/13 11:01
 * Desc: 自定义停词分析器
 */
public class SynonyAnalyzer extends Analyzer {

    private ISynonyEngine synonyEngine;

    public SynonyAnalyzer(ISynonyEngine synonyEngine) {
        this.synonyEngine = synonyEngine;
    }

    @Override
    protected TokenStreamComponents createComponents(String fieldName) {
        final Tokenizer tokenizer = new StandardTokenizer();

        SynonyFilter synonyFilter = new SynonyFilter(new StopFilter(
                    new LowerCaseFilter(new StandardFilter(tokenizer)),
                    StopAnalyzer.ENGLISH_STOP_WORDS_SET),
                this.synonyEngine);

        return new TokenStreamComponents(tokenizer, synonyFilter);
    }
}
