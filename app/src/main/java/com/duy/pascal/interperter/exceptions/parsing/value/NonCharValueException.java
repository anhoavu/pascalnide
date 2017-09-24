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

package com.duy.pascal.interperter.exceptions.parsing.value;

import com.duy.pascal.interperter.exceptions.parsing.ParsingException;

public final class NonCharValueException extends ParsingException {
   @NotNull
   private CharacterToken characterToken;

   @NotNull
   public final CharacterToken getCharacterToken() {
      return this.characterToken;
   }

   public final void setCharacterToken(@NotNull CharacterToken var1) {
      Intrinsics.checkParameterIsNotNull(var1, "<set-?>");
      this.characterToken = var1;
   }

   public NonCharValueException(@NotNull LineInfo line, @NotNull CharacterToken characterToken) {
      Intrinsics.checkParameterIsNotNull(line, "line");
      Intrinsics.checkParameterIsNotNull(characterToken, "characterToken");
      super(line);
      this.characterToken = characterToken;
      this.setLineInfo(line);
   }
}