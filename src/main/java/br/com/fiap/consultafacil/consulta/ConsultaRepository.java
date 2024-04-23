package br.com.fiap.consultafacil.consulta;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> { 
    Optional<Consulta> findById(Long id);
}
