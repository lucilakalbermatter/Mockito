
import main.java.CalculatorService;
import main.java.MathApplication;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.internal.stubbing.answers.AnswersWithDelay;
import org.mockito.internal.stubbing.answers.Returns;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MathApplicationTest {

    @InjectMocks
    MathApplication mathApplication = new MathApplication();

    @Mock
    private CalculatorService calculatorService;


    @Captor
    ArgumentCaptor<Double> captor;

    @Test
    @DisplayName("Use of When()  and thenReturn()")
    public void testMathApplicationUsingWhen() {
        //add the expectations of calc service calls
        Mockito.when(calculatorService.divide(20.0,2.0)).thenReturn(10.0);
        Mockito.when(calculatorService.add(20.0,2.0)).thenReturn(22.0);
        Mockito.when(calculatorService.subtract(20.0,2.0)).thenReturn(18.0);
        Mockito.when(calculatorService.multiply(20.0,2.0)).thenReturn(40.0);

        Assertions.assertEquals(mathApplication.divide(20.0,2.0),10.0);
        Assertions.assertEquals(mathApplication.add(20.0,2.0),22.0);
        Assertions.assertEquals(mathApplication.subtract(20.0,2.0),18.0);
        Assertions.assertEquals(mathApplication.multiply(20.0,2.0),40.0);

    }

    @Test
    @DisplayName("Verify method calls")
    public void testMathApplicationUsingVerify() {

        mathApplication.divide(20.0,2.0);
        mathApplication.add(20.0,2.0);
        mathApplication.subtract(20.0,2.0);
        mathApplication.multiply(20.0,2.0);

        //verify calc service calls made for the above calls
        verify(calculatorService).divide(20.0,2.0);
        verify(calculatorService).add(20.0,2.0);
        verify(calculatorService).subtract(20.0,2.0);
        verify(calculatorService).multiply(20.0,2.0);

    }

    @Test
    @DisplayName("No' of method invocations/calls")
    public void testVerifyNoOfMethodCalls() {

        //3 times add
        mathApplication.add(20.0,2.0);
        mathApplication.add(20.0,2.0);
        mathApplication.add(20.0,2.0);

        //2 times subtract
        mathApplication.subtract(20.0,2.0);
        mathApplication.subtract(20.0,2.0);

        //1 time divide
        mathApplication.divide(20.0,2.0);

        //multiply : never


        //verify calc service calls made for the above calls
        verify(calculatorService, times(3)).add(20.0,2.0);
        verify(calculatorService, times(2)).subtract(20.0,2.0);
        verify(calculatorService, times(1)).divide(20.0,2.0);
        verify(calculatorService, times(0)).multiply(20.0,2.0);

    }

    @Test
    @DisplayName("No' of method invocations/calls - 2")
    public void testVerifyNoOfMethodCalls2() {

        //at most 3 times : add
        mathApplication.add(20.0,2.0);
        mathApplication.add(20.0,2.0);

        //at least 2 times: subtract
        mathApplication.subtract(20.0,2.0);
        mathApplication.subtract(20.0,2.0);
        mathApplication.subtract(20.0,2.0);
        mathApplication.subtract(20.0,2.0);

        //at least once : divide
        mathApplication.divide(20.0,2.0);
        mathApplication.divide(20.0,2.0);

        //multiply never

        //verify calc service calls made for the above calls
        verify(calculatorService, atMost(3)).add(20.0,2.0);
        verify(calculatorService, atLeast(2)).subtract(20.0,2.0);
        verify(calculatorService, atLeast(1)).divide(20.0,2.0);
        verify(calculatorService, never()).multiply(20.0,2.0);

    }

    @Test
    @DisplayName("Exception Handling")
    public void verify_ExceptionHandling() {

        //add the expected exceptions thrown for the below method calls

    }


    @Test
    @DisplayName("Verify Method call sequence/order")
    public void verify_OrderOfMethodExecution() {

        //TODO

        //Verify Method call execution order
        //TODO
    }

    @Test
    @DisplayName("Verify TimeOut")
    public void verify_TimeOut() {

        //TODO

        //Verify timeout above calls
        verify(calculatorService, timeout(1500).times(1)).add(20.0,2.0);
        verify(calculatorService, timeout(1500).times(1)).subtract(20.0,2.0);
    }


    @Test
    @DisplayName("Verify with Argument Capture")
    public void verify_ArgumentCaptureForAdd() {

        //TODO
    }

    @Test
    @DisplayName("Verify with Argument Capture For Subtract")
    public void verify_ArgumentCaptureForSubtract() {

        //TODO
    }

    @Test
    @DisplayName("Verify with Dynamic Responses")
    public void verify_dynamicResponses() {

        //Implement Answer
        Answer answer = new Answer() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                String methodName = invocationOnMock.getMethod().getName();
                switch (methodName) {
                    case "add":
                        return 500.0;
                    case "subtract":
                        return 200.0;
                    case "multiply":
                        return 100.0;
                    case "divide":
                        return 25.0;
                }
                return 0;
            }
        };
        //TODO

    }
}