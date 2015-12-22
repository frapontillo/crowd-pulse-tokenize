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

package com.github.frapontillo.pulse.crowd.tokenize;

import com.github.frapontillo.pulse.crowd.data.entity.Message;
import com.github.frapontillo.pulse.spi.IPluginConfig;
import com.github.frapontillo.pulse.spi.PluginConfigHelper;
import com.google.gson.JsonElement;

import java.util.List;

/**
 * The options from this class can be set to configure the tokenizer pre-processing phase, with
 * regards to how to clean the {@link Message} text before tokenizing it.
 *
 * @author Francesco Pontillo
 */
public class TokenizerConfig implements IPluginConfig<TokenizerConfig> {
    private Integer minChars;
    private boolean urls;
    private boolean hashtags;
    private boolean mentions;
    private boolean numbers;
    private List<String> regexes;

    @Override public TokenizerConfig buildFromJsonElement(JsonElement json) {
        return PluginConfigHelper.buildFromJson(json, TokenizerConfig.class);
    }

    public Integer getMinChars() {
        return minChars;
    }

    public void setMinChars(Integer minChars) {
        this.minChars = minChars;
    }

    public boolean isUrls() {
        return urls;
    }

    public void setUrls(boolean urls) {
        this.urls = urls;
    }

    public boolean isHashtags() {
        return hashtags;
    }

    public void setHashtags(boolean hashtags) {
        this.hashtags = hashtags;
    }

    public boolean isMentions() {
        return mentions;
    }

    public void setMentions(boolean mentions) {
        this.mentions = mentions;
    }

    public boolean isNumbers() {
        return numbers;
    }

    public void setNumbers(boolean numbers) {
        this.numbers = numbers;
    }

    public List<String> getRegexes() {
        return regexes;
    }

    public void setRegexes(List<String> regexes) {
        this.regexes = regexes;
    }

}
