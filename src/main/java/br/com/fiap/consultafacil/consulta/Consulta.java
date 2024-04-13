package br.com.fiap.consultafacil.consulta;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    private String tipoconsulta; // Equivalente ao Titulo
    private String motivo; // Equivalente a Descricao
    private Double valorconsulta; // Equivalente ao Score  
    private int situacao; // equivalente ao Status

}
