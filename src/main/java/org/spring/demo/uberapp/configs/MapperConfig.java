package org.spring.demo.uberapp.configs;

import org.locationtech.jts.geom.Point;
import org.modelmapper.ModelMapper;
import org.spring.demo.uberapp.dto.PointDto;
import org.spring.demo.uberapp.util.GeometryUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper =  new ModelMapper();

        modelMapper.typeMap(PointDto.class, Point.class).setConverter(
          converter -> {
              PointDto source = converter.getSource();
              return GeometryUtil.createPoint(source);
          }
        );

        modelMapper.typeMap(Point.class,PointDto.class).setConverter(context  -> {
            Point source = context.getSource();
            double coordinates[] = {
                    source.getX(),
                    source.getY()
            };
            return new PointDto(coordinates);
        });

        return modelMapper;
    }
}
