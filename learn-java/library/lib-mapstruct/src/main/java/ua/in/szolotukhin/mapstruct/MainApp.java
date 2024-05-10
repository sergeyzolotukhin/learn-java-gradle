package ua.in.szolotukhin.mapstruct;

import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import ua.in.szolotukhin.mapstruct.dto.Car;
import ua.in.szolotukhin.mapstruct.dto.CarDto;

@Slf4j
public class MainApp {
    public static void main(String[] args) {
        Car car = new Car( "Morris", 5);

        final CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);
        CarDto carDto = INSTANCE.carToCarDto( car );

        log.info("Dto: {}", carDto);
    }
}
