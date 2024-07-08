package com.bankypokem.soap;


import com.bankypokem.soap.SpringContext;
import com.bankypokem.soap.repository.RequestDataRepository;
import com.bankypokem.soap.service.PokeApiService;
import org.mockito.Mockito;
import org.springframework.context.ApplicationContext;


public class SpringContextTestUtils {

    private static ApplicationContext context;

    public static void setupSpringContext(RequestDataRepository mockRequestDataRepository){
        context= Mockito.mock(ApplicationContext.class);
        Mockito.when(context.getBean(RequestDataRepository.class)).thenReturn(mockRequestDataRepository);
        SpringContext springContext= new SpringContext();
        springContext.setApplicationContext(context);
    }

    public static void setupSpringContext(PokeApiService mockPokeApiService, RequestDataRepository mockRequestDataRepository){
        context= Mockito.mock(ApplicationContext.class);
        Mockito.when(context.getBean(RequestDataRepository.class)).thenReturn(mockRequestDataRepository);
        Mockito.when(context.getBean(PokeApiService.class)).thenReturn(mockPokeApiService);
        SpringContext springContext= new SpringContext();
        springContext.setApplicationContext(context);
    }
}
