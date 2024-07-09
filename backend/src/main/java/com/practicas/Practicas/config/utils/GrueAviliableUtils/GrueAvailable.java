package com.practicas.Practicas.config.utils.GrueAviliableUtils;

import com.practicas.Practicas.model.Grue;
import com.practicas.Practicas.model.dto.RentsActives;
import com.practicas.Practicas.service.IGrueService;
import com.practicas.Practicas.service.IRentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class GrueAvailable {

    @Autowired
    IGrueService grueService;
    @Autowired
    IRentsService rentsService;
    private static final Logger logger = Logger.getLogger(GrueAvailable.class.getName());
    @Scheduled(fixedRate = 3600000)
    public void checkGrueAvailability() {
        logger.log(Level.INFO, "Iniciando la revisión de disponibilidad de grúas");

        LocalDateTime now = LocalDateTime.now();
        List<RentsActives> activeRents = rentsService.findRentsActives();

        for (RentsActives rent : activeRents) {
            LocalDateTime endDateTime = rent.getEndDate().atStartOfDay();
            if (now.isAfter(endDateTime)) {
                Grue grue = grueService.findBy(rent.getIdGrue());
                if (grue != null && !grue.isAvailable()) {
                    grue.setAvailable(true);
                    logger.log(Level.INFO, "Se ha terminado el alquiler de la grúa: " + grue.getId());
                    grueService.edit(grue);
                }
            }
        }

        logger.log(Level.INFO, "Revisión de disponibilidad de grúas completada");
    }
}


