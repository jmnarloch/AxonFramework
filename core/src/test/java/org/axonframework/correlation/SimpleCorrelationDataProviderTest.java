/*
 * Copyright (c) 2010-2014. Axon Framework
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

package org.axonframework.correlation;

import org.axonframework.domain.GenericMessage;
import org.axonframework.domain.Message;
import org.junit.*;

import java.util.HashMap;
import java.util.Map;

import static java.util.Collections.singletonMap;
import static org.junit.Assert.*;

public class SimpleCorrelationDataProviderTest {

    @Test
    public void testResolveCorrelationData() throws Exception {
        Map<String, Object> metaData = new HashMap<String, Object>();
        metaData.put("key1", "value1");
        metaData.put("key2", "value2");
        metaData.put("key3", "value3");
        Message message = new GenericMessage<String>("payload", metaData);

        assertEquals(singletonMap("key1", "value1"),
                     new SimpleCorrelationDataProvider("key1").correlationDataFor(message));

        final Map<String, ?> actual2 = new SimpleCorrelationDataProvider("key1", "key2", "noExist", null)
                .correlationDataFor(message);
        assertEquals("value1", actual2.get("key1"));
        assertEquals("value2", actual2.get("key2"));
    }
}