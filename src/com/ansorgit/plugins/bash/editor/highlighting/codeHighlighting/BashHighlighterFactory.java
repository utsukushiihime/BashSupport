/*
 * Copyright 2009 Joachim Ansorg, mail@ansorg-it.com
 * File: BashHighlighterFactory.java, Class: BashHighlighterFactory
 * Last modified: 2010-01-25
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

package com.ansorgit.plugins.bash.editor.highlighting.codeHighlighting;

import com.ansorgit.plugins.bash.BashComponents;
import com.ansorgit.plugins.bash.lang.psi.api.BashFile;
import com.intellij.codeHighlighting.TextEditorHighlightingPass;
import com.intellij.codeHighlighting.TextEditorHighlightingPassFactory;
import com.intellij.codeHighlighting.TextEditorHighlightingPassRegistrar;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

/**
 * User: jansorg
 * Date: Jan 25, 2010
 * Time: 8:27:58 PM
 */
public class BashHighlighterFactory implements TextEditorHighlightingPassFactory {
    private TextEditorHighlightingPassRegistrar myRegistrar;

    public BashHighlighterFactory(final TextEditorHighlightingPassRegistrar passRegistrar) {
        myRegistrar = passRegistrar;
    }

    public TextEditorHighlightingPass createHighlightingPass(@NotNull PsiFile file, @NotNull Editor editor) {
        if (file instanceof BashFile) {
            return new BashEditorHighlighterPass(file.getProject(), (BashFile) file, editor);
        }

        return null;
    }

    public void projectOpened() {

    }

    public void projectClosed() {

    }

    @NonNls
    @NotNull
    public String getComponentName() {
        return BashComponents.HighlighterFactory;
    }

    public void initComponent() {
        myRegistrar.registerTextEditorHighlightingPass(this, null, null, true, -1);
    }

    public void disposeComponent() {

    }
}