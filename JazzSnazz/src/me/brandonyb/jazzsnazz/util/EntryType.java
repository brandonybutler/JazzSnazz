/*
 * Copyright 2019 brandon.
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
package me.brandonyb.jazzsnazz.util;

/**
 * <p>Specifies the level of severity for a log entry.</p>
 * @author brandon
 */
public enum EntryType {
    /**
     * <p>Indicates that the message is severe.</p>
     */
    CRITICAL,
    /**
     * <p>Indicates that a non-fatal error has occurred.</p>
     */
    ERROR,
    /**
     * <p>Indicates a warning which is purely advisory.</p>
     */
    WARNING,
    /**
     * <p>Indicates an informational message where no further
     * action is required.</p>
     */
    INFORMATION,
    /**
     * <p>Indicates that something has succeeded.</p>
     */
    SUCCESS;
}
