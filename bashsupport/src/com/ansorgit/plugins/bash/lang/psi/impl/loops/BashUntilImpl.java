/*
 * Copyright 2010 Joachim Ansorg, mail@ansorg-it.com
 * File: BashUntilImpl.java, Class: BashUntilImpl
 * Last modified: 2010-06-30
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ansorgit.plugins.bash.lang.psi.impl.loops;

import com.ansorgit.plugins.bash.lang.lexer.BashTokenTypes;
import com.ansorgit.plugins.bash.lang.psi.api.loops.BashUntil;
import com.ansorgit.plugins.bash.lang.psi.impl.BashKeywordDefaultImpl;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;

/**
 * Date: 06.05.2009
 * Time: 12:50:57
 *
 * @author Joachim Ansorg
 */
public class BashUntilImpl extends BashKeywordDefaultImpl implements BashUntil {
    public BashUntilImpl(final ASTNode astNode) {
        super(astNode, "bash until command");
    }

    public PsiElement keywordElement() {
        return findChildByType(BashTokenTypes.UNTIL_KEYWORD);
    }
}