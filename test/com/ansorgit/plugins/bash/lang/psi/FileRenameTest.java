package com.ansorgit.plugins.bash.lang.psi;

import com.ansorgit.plugins.bash.BashTestUtils;
import com.ansorgit.plugins.bash.lang.psi.api.BashFile;
import com.ansorgit.plugins.bash.lang.psi.api.BashFileReference;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiReference;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.testFramework.fixtures.LightCodeInsightFixtureTestCase;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author jansorg
 */
public class FileRenameTest extends LightCodeInsightFixtureTestCase {
    @Override
    protected String getBasePath() {
        return "/editor/refactoring/RenameTestCase/";
    }

    @Override
    protected String getTestDataPath() {
        return BashTestUtils.getBasePath() + getBasePath();
    }

    /**
     * Tests the basic rename feature for references pointing to files.
     *
     * @throws Exception
     */
    @Test
    public void testBasicFileRename() throws Exception {
        doRename(false);
    }

    /**
     * Tests the basic rename feature for references pointing to files.
     *
     * @throws Exception
     */
    @Test
    public void testBasicFileRenameWithHandler() throws Exception {
        doRename(true);
    }

    private void doRename(boolean renameWithHandler) {
        myFixture.setTestDataPath(getTestDataPath() + "basicFileRename");
        myFixture.configureByFiles("source.bash", "source2.bash", "target.bash");

        //Assert.assertFalse("caret element must not be a file", myFixture.getElementAtCaret() instanceof PsiFile);

        if (renameWithHandler) {
            myFixture.renameElementAtCaretUsingHandler("target_renamed.bash");
        } else {
            myFixture.renameElementAtCaret("target_renamed.bash");
        }

        myFixture.checkResultByFile("source.bash", "source_after.bash", false);
        myFixture.checkResultByFile("source2.bash", "source2_after.bash", false);
        myFixture.checkResultByFile("target_renamed.bash", "target_after.bash", false);

        PsiElement psiElement = PsiTreeUtil.getParentOfType(myFixture.getFile().findElementAt(myFixture.getCaretOffset()), BashFileReference.class);
        Assert.assertNotNull("file reference is null", psiElement);
        Assert.assertEquals("Filename wasn't changed", "target_renamed.bash", psiElement.getText());

        PsiReference psiReference = psiElement.getReference();
        Assert.assertNotNull("target file reference wasn't found", psiReference);
        Assert.assertEquals("target_renamed.bash", psiReference.getCanonicalText());

        PsiElement targetFile = psiReference.resolve();
        Assert.assertNotNull("target file resolve result wasn't found", targetFile);
        Assert.assertTrue("target is not a psi file", targetFile instanceof BashFile);
    }
}
