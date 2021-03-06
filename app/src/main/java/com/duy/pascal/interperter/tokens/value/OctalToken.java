/*
 *  Copyright (c) 2017 Tran Le Duy
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.duy.pascal.interperter.tokens.value;

import android.support.annotation.NonNull;

import com.duy.pascal.interperter.linenumber.LineNumber;

/**
 * Created by Duy on 28-May-17.
 */

public class OctalToken extends ValueToken {
    private Long cacheValue = null;
    private String value;

    public OctalToken(LineNumber line, String value) {
        super(line);
        this.value = value;
        this.lineNumber.setLength(value.length());
    }

    @Override
    public Object getValue() {
        if (cacheValue != null) return cacheValue;
        cacheValue = Long.parseLong(value.substring(1, value.length()), 8);
        return cacheValue;
    }

    @Override
    public String toString() {
        return value;
    }

    @NonNull
    @Override
    public String toCode() {
        return value;
    }
}
