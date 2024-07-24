package com.fiap.postech.estacionamento.core.service;

import com.fiap.postech.estacionamento.commoms.exception.NotFoundException;
import com.fiap.postech.estacionamento.commoms.exception.UnprocessableEntityException;
import com.fiap.postech.estacionamento.commoms.mappers.ParkingMapper;
import com.fiap.postech.estacionamento.core.domain.Parking;
import com.fiap.postech.estacionamento.resources.repository.entities.ParkingEntity;
import com.fiap.postech.estacionamento.resources.repository.mongodb.ParkingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class ParkingService {
    @Autowired
    private ParkingRepository repository;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private ActuationAreaService actuationAreaService;

    @Autowired
    private ActuationAreaValueService actuationAreaValueService;

    @Autowired
    private UserService userService;

    @Autowired
    private PaymentModeService paymentModeService;

    @Autowired
    private ParkingMapper mapper;

    @Value("${id.payment.pix:1}")
    private Long idPixPayment;

    public Parking register(Parking parking) {
        parkingValidate(parking);

        ParkingEntity entity = repository.save(mapper.toEntity(parking));

        paymentService.createPayment(
                entity.getIdUser(),
                idPixPayment,
                entity.getId()
        );

        if (Objects.equals(parking.getIdPaymentMode(), idPixPayment)) {
            paymentService.liquidatePayment(
                    entity.getId(),
                    calcTime(entity.getInitialDate(), entity.getFinalDate()),
                    actuationAreaValueService.getActiveValueForActuationArea(entity.getIdActuationArea()),
                    userService.getUserById(entity.getIdUser()).getEmail()
            );
        }

        return mapper.toDomain(entity);
    }

    private void parkingValidate(Parking parking) {
        registerValidate(parking);

        repository.findByLicensePlateAndFinished(parking.getLicensePlate(), false)
                .ifPresent(it -> {
                    throw new UnprocessableEntityException("Veículo já estacionado, verifique os dados.");
                });

        if (parking.getFinalDate() != null &&
                !Objects.equals(parking.getIdPaymentMode(), idPixPayment)) {
            throw new UnprocessableEntityException("Período fixo de estacionamento só pode ser pago via PIX");
        }
    }

    private void registerValidate(Parking parking) {
        if (userService.getUserById(parking.getIdUser())
                .getVehicles()
                .stream()
                .noneMatch(vehicle -> vehicle.getLicensePlate().equals(parking.getLicensePlate()))) {
            throw new UnprocessableEntityException("Placa não cadastrada para o usuário.");
        }

        try {
            actuationAreaService.findById(parking.getIdActuationArea());
            paymentModeService.findById(parking.getIdPaymentMode());
        } catch (NotFoundException exception) {
            throw new UnprocessableEntityException(exception.getMessage());
        }
    }

    public List<Parking> getExpired(LocalDateTime dateLimit) {
        return repository
                .findByFinalDateBeforeAndFinished(dateLimit, false)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    public List<Parking> getFutureExpiration(LocalDateTime initialDate, LocalDateTime finalDate) {
        return repository
                .findByFinalDateBetweenAndFinished(initialDate, finalDate, false)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    public void addOneHourLimitPark(UUID id) {
        repository.findById(id).ifPresent(it -> {
            it.setFinalDate(it.getInitialDate().plusHours(1));

            repository.save(it);
        });
    }

    public Parking exitRegister(String licensePlate, LocalDateTime finalParking) {
        ParkingEntity parking = exitValidate(licensePlate);

        parking.setFinalDate(finalParking);

        if (!parking.getIdPaymentMode().equals(idPixPayment)) {
            paymentService.liquidatePayment(
                    parking.getId(),
                    calcTime(parking.getInitialDate(), parking.getFinalDate()),
                    actuationAreaValueService.getActiveValueForActuationArea(parking.getIdActuationArea()),
                    userService.getUserById(parking.getIdUser()).getEmail()
            );
        }

        return mapper.toDomain(repository.save(parking));
    }

    private ParkingEntity exitValidate(String licensePlate) {
        return repository.findByLicensePlateAndFinalDateIsNotNull(licensePlate).orElseThrow(() ->
                new UnprocessableEntityException(
                        "Registro de estacionamento não encontrado, valide se ele já foi pago ou finalizado.")
        );
    }

    private Integer calcTime(LocalDateTime initialDate, LocalDateTime finalDate) {
        return (int) Math.ceil(Duration.between(initialDate, finalDate).toMinutes() / 60.0);
    }
}
