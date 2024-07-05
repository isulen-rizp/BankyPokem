package com.bankypokem.soap.handler;

import com.bankypokem.soap.SpringContext;
import com.bankypokem.soap.model.RequestData;
import com.bankypokem.soap.repository.RequestDataRepository;
import com.bankypokem.soap.util.Constants;
import com.sun.net.httpserver.HttpExchange;
import lombok.extern.log4j.Log4j2;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Set;

@Log4j2
public class PokemonSOAPHandler implements SOAPHandler<SOAPMessageContext> {

    private RequestDataRepository requestDataRepository;
    private RequestData requestData;

    public PokemonSOAPHandler(){
        requestDataRepository= SpringContext.getBean(RequestDataRepository.class);
        requestData= new RequestData();
    }

    @Override
    public Set<QName> getHeaders() {
        return null;
    }

    @Override
    public boolean handleMessage(SOAPMessageContext context) {
        HttpExchange server = (HttpExchange)context.get(Constants.HTTP_EXCHANGE);

        try{
            SOAPMessage soapMessage= context.getMessage();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            soapMessage.writeTo(out);
            String message = new String(out.toByteArray(), Constants.UTF_8);
            boolean isOutbound= (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);

            if (isOutbound) {
                log.info("SOAP response body content:" + soapMessage.getSOAPBody().getTextContent());
                requestData.setSoapResponse(message);
            }
            else{
                message= message.replaceAll("(\\r|\\n)", "");
                log.info("SOAP request:" + message);
                requestData.setSoapRequest(message);
                requestData.setMethodExecuted(getMethodName(soapMessage));
                requestData.setRequestDate(LocalDateTime.now());
            }

            if(requestData.getSoapRequest()!= null && requestData.getSoapResponse()!=null){
                requestData.setIpOrigin(server.getRemoteAddress().getHostString());
                requestData.setTotalTimeMs(requestData.getRequestDate().until(LocalDateTime.now(), ChronoUnit.MILLIS));
                requestDataRepository.save(requestData);
                log.info(requestDataRepository.findAll());

                requestData= new RequestData();
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }

        return true;
    }

    private String getMethodName(SOAPMessage soapMessage){
        try {
            if (soapMessage == null) {
                return null;
            }
            SOAPBody body = soapMessage.getSOAPBody();
            if (body == null) {
                return null;
            }
            org.w3c.dom.Node nd = body.getFirstChild();
            while (nd != null && !(nd instanceof org.w3c.dom.Element)) {
                nd = nd.getNextSibling();
            }
            if (nd != null) {
                return nd.getLocalName();
            }
        } catch (SOAPException e) {
            //ignore, nothing we can do
        }
        return null;
    }

    @Override
    public boolean handleFault(SOAPMessageContext context) {
        return false;
    }

    @Override
    public void close(MessageContext context) {

    }
}
