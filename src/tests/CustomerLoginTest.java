package tests;

import customer.CustomerLogin;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;



class CustomerLoginTest {

    private final static InputStream systemIn = System.in;
    private final static PrintStream systemOut = System.out;
    private ByteArrayInputStream typeIn;
    private static ByteArrayOutputStream typeOut;

    @BeforeEach
    void setUp() throws Exception {
        typeOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(typeOut));
    }
    @AfterEach
    void tearDown() {
        System.setIn(systemIn);
        System.setOut(systemOut);
    }


    @Test
    void shouldSuccessfullyLogIn(){
        String simulatedUserInput = "20343" + System.getProperty("line.separator") +
                "8745" + System.getProperty("line.separator");
        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));

        assertTrue(CustomerLogin.logIn());
    }

    @Test
    void shouldNotSuccessfullyLogInFalseId(){
        String simulatedUserInput = "20143" + System.getProperty("line.separator") +
                "8745" + System.getProperty("line.separator");
        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));

        assertFalse(CustomerLogin.logIn());
    }

    @Test
    void shouldNotSuccessfullyLogInFalsePincode(){
        String simulatedUserInput = "20343" + System.getProperty("line.separator") +
                "0000" + System.getProperty("line.separator");
        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));

        assertFalse(CustomerLogin.logIn());
    }

}