import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Sergey.Luchko on 30.07.2016.
 */
public class ClassicSampleOfMiockito {
}

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AttachmentInputViewParserBeanTest {

    @Spy
    private AttachmentInputViewParserBean attachInpViewParserBean;

    @Mock
    AttachmentCreateView createView;

    @Mock
    AttachmentUpdateView updateView;

    @Mock
    AttachmentRefView refView;

    @Test
    public void parseCreateView() throws Exception {
        when(createView.isContentIdSet()).thenReturn(true);
        when(createView.getContentId()).thenReturn(100L);

        when(createView.getName()).thenReturn("TestName");

        when(createView.isDescriptionSet()).thenReturn(true);
        when(createView.getDescription()).thenReturn("TestDescription");

        AttachmentCreateParams result = attachInpViewParserBean.parseCreateView(createView);

        assertEquals("Should be equals", result.getName(), "TestName");
        assertEquals("Should be equals", result.getDescription(), "TestDescription");
        assertEquals("Should be equals", (long) result.getTempFileId(), 100L);

        verify(createView).getName();
        verify(createView).getDescription();
        verify(createView).getContentId();
    }

    @Test
    public void parseUpdateView() throws Exception {
        when(updateView.isNameSet()).thenReturn(true);
        when(updateView.getName()).thenReturn("TestName");

        when(updateView.isDescriptionSet()).thenReturn(true);
        when(updateView.getDescription()).thenReturn("TestDescription");

        AttachmentUpdateParams attachUpdParams = attachInpViewParserBean.parseUpdateView(updateView);

        assertEquals("Should be equals", attachUpdParams.getName(), "TestName");
        assertEquals("Should be equals", attachUpdParams.getDescription(), "TestDescription");

        verify(updateView).getName();
        verify(updateView).getDescription();
    }

    @Test
    public void parseRefView() throws Exception {
        when(refView.getAttachmentId()).thenReturn(100L);

        long attachId = attachInpViewParserBean.parseRefView(refView);

        assertEquals("Should be equals", attachId, 100L);
        verify(refView).getAttachmentId();
    }

}