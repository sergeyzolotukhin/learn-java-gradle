package ua.in.sz.junit;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.util.List;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class MultiMockCallTest {

    @Mock
    private List mockedList;

    @Test
    void multiWhen() {
        Mockito.when(mockedList.get(0)).thenReturn("test 1");
        Mockito.when(mockedList.get(0)).thenReturn("test 2");
        Mockito.when(mockedList.get(0)).thenReturn("test 3");

        log.info("get by index: [{}]", mockedList.get(0));
        log.info("get by index: [{}]", mockedList.get(0));
        log.info("get by index: [{}]", mockedList.get(0));
        log.info("get by index: [{}]", mockedList.get(0));
    }

    @Test
    void multiWhen_2() {
        Mockito.when(mockedList.get(0)).thenReturn("test 1","test 2", "test 3");

        log.info("get by index: [{}]", mockedList.get(0));
        log.info("get by index: [{}]", mockedList.get(0));
        log.info("get by index: [{}]", mockedList.get(0));
        log.info("get by index: [{}]", mockedList.get(0));
    }

    @Test
    void multiWhen_3() {
        Mockito.when(mockedList.get(0)).thenReturn("test 1")
                .thenReturn("test 2")
                .thenReturn( "test 3");

        log.info("get by index: [{}]", mockedList.get(0));
        log.info("get by index: [{}]", mockedList.get(0));
        log.info("get by index: [{}]", mockedList.get(0));
        log.info("get by index: [{}]", mockedList.get(0));
    }

    @Test
    void multiWhen_4() {
        Mockito.when(mockedList.get(Mockito.anyInt())).then(invocation -> "value-" + invocation.getArgument(0));

        log.info("get by index: [{}]", mockedList.get(0));
        log.info("get by index: [{}]", mockedList.get(1));
        log.info("get by index: [{}]", mockedList.get(2));
        log.info("get by index: [{}]", mockedList.get(3));
    }

    @Test
    void multiWhen_5() {
        Mockito.when(mockedList.indexOf("111")).thenReturn(5);
        Mockito.when(mockedList.indexOf("222")).thenReturn(10);
        Mockito.when(mockedList.indexOf("333")).thenReturn(15);

        log.info("get by index: [{}]", mockedList.indexOf("222"));
        log.info("get by index: [{}]", mockedList.indexOf("111"));
        log.info("get by index: [{}]", mockedList.indexOf("333"));
        log.info("get by index: [{}]", mockedList.indexOf("444"));
    }
}