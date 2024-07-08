package com.bankypokem.soap.handler;

import com.bankypokem.soap.SpringContextTestUtils;
import com.bankypokem.soap.model.RequestData;
import com.bankypokem.soap.repository.RequestDataRepository;
import com.bankypokem.soap.util.Constants;
import com.sun.net.httpserver.HttpExchange;
import org.junit.jupiter.api.Test;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class PokemonSOAPHandlerTest {

    @Test
    void testHandleMessageResponse() throws IOException, SOAPException {

        SOAPMessageContext mockContext = mock(SOAPMessageContext.class);
        SOAPMessage mockSoapMessage= mock(SOAPMessage.class);
        HttpExchange mockExchange= mock(HttpExchange.class);
        RequestDataRepository mockRequestDataRepository= mock(RequestDataRepository.class);
        SOAPBody mockSoapBody= mock(SOAPBody.class);

        String message="<S:Envelope xmlns:S=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                "   <SOAP-ENV:Header/>\n" +
                "   <S:Body>\n" +
                "      <ns2:abilitiesResponse xmlns:ns2=\"http://service.soap.bankypokem.com/\">\n" +
                "         <return>[{\"ability\":{\"name\":\"overgrow\",\"url\":\"https://pokeapi.co/api/v2/ability/65/\"},\"is_hidden\":false,\"slot\":1},{\"ability\":{\"name\":\"chlorophyll\",\"url\":\"https://pokeapi.co/api/v2/ability/34/\"},\"is_hidden\":true,\"slot\":3}]</return>\n" +
                "      </ns2:abilitiesResponse>\n" +
                "   </S:Body>\n" +
                "</S:Envelope>";

        when(mockContext.get(Constants.HTTP_EXCHANGE)).thenReturn(mockExchange);
        when(mockContext.getMessage()).thenReturn(mockSoapMessage);
        when(mockSoapMessage.getSOAPBody()).thenReturn(mockSoapBody);
        when(mockSoapBody.getTextContent()).thenReturn(message);
        when(mockContext.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY)).thenReturn(true);

        SpringContextTestUtils.setupSpringContext(mockRequestDataRepository);

        RequestData mockRequestData= mock(RequestData.class);
        when(mockRequestData.getSoapRequest()).thenReturn("");
        when(mockRequestData.getSoapResponse()).thenReturn("");

        PokemonSOAPHandler handler= new PokemonSOAPHandler();
        handler.setRequestData(mockRequestData);
        boolean result= handler.handleMessage(mockContext);



        verify(mockContext, times(1)).get(Constants.HTTP_EXCHANGE);
        verify(mockContext, times(1)).getMessage();
        verify(mockRequestData, times(1)).setSoapResponse(anyString());
        verify(mockRequestDataRepository, times(1)).save(any(RequestData.class));

        assertTrue(result, "The handle message method should return true");
    }

    @Test
    void testHandleMessageRequest() throws IOException, SOAPException {

        SOAPMessageContext mockContext = mock(SOAPMessageContext.class);
        SOAPMessage mockSoapMessage= mock(SOAPMessage.class);
        HttpExchange mockExchange= mock(HttpExchange.class);
        RequestDataRepository mockRequestDataRepository= mock(RequestDataRepository.class);
        SOAPBody mockSoapBody= mock(SOAPBody.class);

        String message="<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://service.soap.bankypokem.com/\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <ser:abilities>\n" +
                "         <arg0>pikachu</arg0>\n" +
                "      </ser:abilities>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";

        when(mockContext.get(Constants.HTTP_EXCHANGE)).thenReturn(mockExchange);
        when(mockContext.getMessage()).thenReturn(mockSoapMessage);
        when(mockSoapMessage.getSOAPBody()).thenReturn(mockSoapBody);
        when(mockSoapBody.getTextContent()).thenReturn(message);
        when(mockContext.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY)).thenReturn(false);

        SpringContextTestUtils.setupSpringContext(mockRequestDataRepository);
        PokemonSOAPHandler handler= new PokemonSOAPHandler();
        RequestData mockRequestData= mock(RequestData.class);
        handler.setRequestData(mockRequestData);
        boolean result= handler.handleMessage(mockContext);

        verify(mockContext, times(1)).get(Constants.HTTP_EXCHANGE);
        verify(mockContext, times(1)).getMessage();
        verify(mockRequestData, times((1))).setSoapRequest(anyString());
        verify(mockRequestData, times((1))).setRequestDate(any());

        assertTrue(result, "The handle message method should return true");
    }
}