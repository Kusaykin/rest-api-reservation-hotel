package com.example.angularwithspring.angularwithspring.config;

import com.example.angularwithspring.angularwithspring.converter.ReservationEntityToReservationResponseConverter;
import com.example.angularwithspring.angularwithspring.converter.ReservationRequestToReservationEntityConverter;
import com.example.angularwithspring.angularwithspring.converter.RoomEntityToReservableRoomResponseConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class ConversionConfig {

    private Set<Converter> getConverters() {
        Set<Converter> converters = new HashSet<Converter>();
        converters.add(new RoomEntityToReservableRoomResponseConverter());
        converters.add(new ReservationRequestToReservationEntityConverter());
        converters.add(new ReservationEntityToReservationResponseConverter());

        return converters;
    }
    @Bean
    public ConversionService conversionService() {
        ConversionServiceFactoryBean bean = new ConversionServiceFactoryBean();
        bean.setConverters(getConverters());
        bean.afterPropertiesSet();

        return bean.getObject();
    }
}
