package ua.in.szolotukhin.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ua.in.szolotukhin.mapstruct.dto.Car;
import ua.in.szolotukhin.mapstruct.dto.CarDto;

@Mapper
public interface CarMapper {
    @Mapping(source = "name", target = "name")
    CarDto carToCarDto(Car car);
}
