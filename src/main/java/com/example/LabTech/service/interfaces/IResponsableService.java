package com.example.LabTech.service.interfaces;

import com.example.LabTech.DTO.ResponsableDto;
import com.example.LabTech.entite.Responsable;
import java.util.List;
import java.util.Optional;

public interface IResponsableService {

    List<ResponsableDto> getAllresponsable();

    Optional<ResponsableDto> getResponsableById(long id);

    ResponsableDto addResponsable(ResponsableDto responsable);

    ResponsableDto updateResponsable(ResponsableDto responsable);

    void deleteResponsable(long id);
}