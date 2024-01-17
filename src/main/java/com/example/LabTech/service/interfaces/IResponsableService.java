package com.example.LabTech.service.interfaces;

import com.example.LabTech.entite.Responsable;
import java.util.List;
import java.util.Optional;

public interface IResponsableService {

    List<Responsable> getAllresponsable();

    Optional<Responsable> getResponsableById(long id);

    Responsable addResponsable(Responsable responsable);

    Responsable updateResponsable(Responsable responsable);

    void deleteResponsable(long id);
}