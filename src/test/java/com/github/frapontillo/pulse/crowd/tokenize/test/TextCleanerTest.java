/*
 * Copyright 2015 Francesco Pontillo
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.frapontillo.pulse.crowd.tokenize.test;

import com.github.frapontillo.pulse.crowd.tokenize.TextCleaner;
import com.github.frapontillo.pulse.crowd.tokenize.TokenizerConfig;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertTrue;

/**
 * @author Francesco Pontillo
 */
public class TextCleanerTest {
    @Test public void testDefaults() {
        TextCleaner textCleaner = new TextCleaner(null);
        String string = "123 some random thing www.google.it";
        assertTrue(textCleaner.clean(string).equals(string));
    }

    @Test public void testMin1Chars() {
        TokenizerConfig config = new TokenizerConfig();
        config.setMinChars(1);
        TextCleaner textCleaner = new TextCleaner(config);
        assertTrue(textCleaner.clean("1 2 3 4 5 6").equals("1 2 3 4 5 6"));
    }

    @Test public void testMin4Chars() {
        TokenizerConfig config = new TokenizerConfig();
        config.setMinChars(4);
        TextCleaner textCleaner = new TextCleaner(config);
        assertTrue(textCleaner.clean("00 0000 one two three four five six")
                .equals(" 0000   three four five "));
    }

    @Test public void testMin9Chars() {
        TokenizerConfig config = new TokenizerConfig();
        config.setMinChars(9);
        TextCleaner textCleaner = new TextCleaner(config);
        assertTrue(textCleaner.clean("something somewhat").equals("something "));
    }

    @Test public void testHttpUrl() {
        TokenizerConfig config = new TokenizerConfig();
        config.setUrls(true);
        TextCleaner textCleaner = new TextCleaner(config);
        assertTrue(textCleaner.clean("this is http://google.com rockin hard")
                .equals("this is  rockin hard"));
    }

    @Test public void testHttpsUrl() {
        TokenizerConfig config = new TokenizerConfig();
        config.setUrls(true);
        TextCleaner textCleaner = new TextCleaner(config);
        assertTrue(textCleaner.clean("this is https://google.com rockin hard")
                .equals("this is  rockin hard"));
    }

    @Test public void testTdotCoUrl() {
        TokenizerConfig config = new TokenizerConfig();
        config.setUrls(true);
        TextCleaner textCleaner = new TextCleaner(config);
        assertTrue(textCleaner.clean("this is https://t.co/something rockin hard")
                .equals("this is  rockin hard"));
    }

    @Test public void testHashtags() {
        TokenizerConfig config = new TokenizerConfig();
        config.setHashtags(true);
        TextCleaner textCleaner = new TextCleaner(config);
        assertTrue(textCleaner.clean("It was almost like being on ecstasy, only that instead of " +
                "having pointless conversations and dancing like idiots...wait, it was exactly " +
                "like being on ecstasy! #sixseasonsandamovie #community")
                .equals("It was almost like being on ecstasy, only that instead of having " +
                        "pointless conversations and dancing like idiots...wait, it was exactly " +
                        "like being on ecstasy!  "));
    }

    @Test public void testMentions() {
        TokenizerConfig config = new TokenizerConfig();
        config.setMentions(true);
        TextCleaner textCleaner = new TextCleaner(config);
        assertTrue(textCleaner.clean("@frapontillo what the hell is going on here @pontifex?")
                .equals(" what the hell is going on here ?"));
    }

    @Test public void testNumbers() {
        TokenizerConfig config = new TokenizerConfig();
        config.setNumbers(true);
        TextCleaner textCleaner = new TextCleaner(config);
        assertTrue(textCleaner
                .clean("let's count: 1 sheep, 2 sheeps, 3.5 horses, -1337 lols, 69,87 gronks")
                .equals("let's count:  sheep,  sheeps,  horses,  lols, , gronks"));
    }

    @Test public void testCustomRegexes() {
        TokenizerConfig config = new TokenizerConfig();
        config.setRegexes(Arrays.asList("francesco", "@frapontillo"));
        TextCleaner textCleaner = new TextCleaner(config);
        assertTrue(textCleaner.clean("@frapontillo doesn't exist anymore, nor does francesco!")
                .equals(" doesn't exist anymore, nor does !"));
    }
}
