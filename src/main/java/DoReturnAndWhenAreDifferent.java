import forTest.DoReturnAndWhen;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.doReturn;
public class DoReturnAndWhenAreDifferent {
}



@RunWith(MockitoJUnitRunner.class)
public class DoReturnAndWhenTest {

    @Spy
    DoReturnAndWhen doReturnAndWhen;

    /*
when(...) thenReturn(...) makes a real method call just before it is mocked. \

So if the called method throws an Exception you have to deal with it / mock it etc.
Of course you still get your result (what you define in thenReturn(...) )

doReturn(...) when(...) does not call the method at all.

I am not sure whether this behaviour is specific for Spied objects..
At the very this moment i have sth similar to be tested:*/

    @Test
    public void print() throws Exception {
//        when(doReturnAndWhen.print()).thenReturn(2);
        doReturn(2).when(doReturnAndWhen).print();
        doReturnAndWhen.print();
    }

}