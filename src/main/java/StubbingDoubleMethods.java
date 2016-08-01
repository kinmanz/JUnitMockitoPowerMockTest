/**
 * Created by Sergey.Luchko on 01.08.2016.
 */
public class StubbingDoubleMethods {
}


//Something like SomeObj.meth1(...).meth(...)

//See
//http://stackoverflow.com/questions/38697409/how-to-mock-someobj-meth1-meth2

/*
    Your problem is that the first when has already run when you start stubbing the second mock. In Mockito, you can only stub one method at a time. What you could do is assign the inner mock to a local variable like this.

        Number innerMock = when(mock(Number.class).longValue()).thenReturn(200L).getMock();
        when(statusNode.get("ratePlanId")).thenReturn(innerMock);
        This will work. But, mocking types that you don't own is a bit of a testing anti-pattern.
         If you can test this without mocking the Number class, that would be better.

        when(statusNode.get("ratePlanId")).thenReturn(Long.valueOf(200L));

        */


/*It's a problem because of the way Mockito is designed. When you call when, it switches into a kind of
"stubbing mode" where calling a method on a mock sets it up for stubbing.
Once you're in "stubbing mode", Mockito doesn't let you call when again until you've finished the stubbing.*/

/*

        Map<String, Object> statusNode = mock(Map.class, RETURNS_DEEP_STUBS);
        And then mock subcalls:

        when(statusNode.get("subscriberStatusId").longValue()).thenReturn(100L);
        You might need casting:

        when(((Number) statusNode.get("subscriberStatusId")).longValue()).thenReturn(100L);

*/


