package com.CtrlCorredorV1.DTOs;

import java.time.LocalTime;
import static com.CtrlCorredorV1.Tools.Helper.formato;

public class PerformanceDTO {

    public String evento1;
    public String evento2;
    public String melhoraPerformance;

    public PerformanceDTO(String evento1, String evento2, int melhoraPerformance) {
        this.evento1 = evento1;
        this.evento2 = evento2;
        this.melhoraPerformance = LocalTime.ofSecondOfDay(melhoraPerformance).format(formato);
    }
}
