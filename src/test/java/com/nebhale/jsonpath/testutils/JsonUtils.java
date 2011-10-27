/*
 * Copyright 2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.nebhale.jsonpath.testutils;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

public final class JsonUtils {

    public static final String STRING_INVALID;

    public static final String STRING_VALID;

    public static final JsonNode NODE;

    static {
        try {
            STRING_INVALID = readFile("src/test/resources/invalid.json");
            STRING_VALID = readFile("src/test/resources/valid.json");
            NODE = new ObjectMapper().readTree(STRING_VALID);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private static String readFile(String file) throws IOException {
        StringBuilder sb = new StringBuilder();

        Reader in = null;
        try {
            in = new FileReader(file);

            char[] buffer = new char[8192];
            int length;
            while ((length = in.read(buffer)) != -1) {
                sb.append(buffer, 0, length);
            }
        } finally {
            if (in != null) {
                in.close();
            }
        }

        return sb.toString();
    }

    private JsonUtils() {
    }

}