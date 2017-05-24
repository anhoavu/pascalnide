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

package com.duy.pascal.frontend.code_editor

interface EditorListener {
    fun saveAs()

    fun doFindAndReplace(from: String, to: String, regex: Boolean, matchCase: Boolean)

    fun doFind(find: String, regex: Boolean, wordOnly: Boolean, matchCase: Boolean)

    fun saveFile()

    fun goToLine(line: Int)

    /**
     * beautiful code
     */
    fun formatCode()

    fun undo()

    fun redo()

    fun paste()

    fun copyAll()

    /**
     * @return current content of editor
     */
    val code: String

    fun insert(text: CharSequence)

}