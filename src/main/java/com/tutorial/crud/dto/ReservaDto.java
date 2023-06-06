import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.springframework.format.annotation.DateTimeFormat;

public class ReservaDto {

    @NotNull
    private Integer idUsuario;

    @NotNull
    private Integer idVuelo;

    @NotBlank
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "El formato de fecha debe ser 'yyyy-MM-dd'")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String fechaReserva;

    @NotBlank
    @Pattern(regexp = "\\d{2}:\\d{2}", message = "El formato de hora debe ser 'HH:mm'")
    @DateTimeFormat(pattern = "HH:mm")
    private String horaReserva;

    public ReservaDto() {
    }

    public ReservaDto(@NotNull Integer idUsuario, @NotNull Integer idVuelo, @NotBlank String fechaReserva, @NotBlank String horaReserva) {
        this.idUsuario = idUsuario;
        this.idVuelo = idVuelo;
        this.fechaReserva = fechaReserva;
        this.horaReserva = horaReserva;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdVuelo() {
        return idVuelo;
    }

    public void setIdVuelo(Integer idVuelo) {
        this.idVuelo = idVuelo;
    }

    public String getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(String fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public String getHoraReserva() {
        return horaReserva;
    }

    public void setHoraReserva(String horaReserva) {
        this.horaReserva = horaReserva;
    }
}
