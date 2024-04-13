package br.com.fiap.consultafacil.consulta;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Consulta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "{consulta.tipoconsulta.notblank}")
    private String tipoconsulta; // Equivalente ao Titulo

    @Size(min = 10, max = 255, message = "{consulta.motivo.size}")
    private String motivo; // Equivalente a Descricao

    @Positive(message = "{consulta.valorconsulta.positive}" )
    private int valorconsulta; // Equivalente ao Score  

    @Min(0) @Max(100)
    private int situacao; // equivalente ao Status

}
