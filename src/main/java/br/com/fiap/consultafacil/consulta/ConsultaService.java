package br.com.fiap.consultafacil.consulta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.consultafacil.user.User;

public class ConsultaService {
    
    @Autowired
    ConsultaRepository repository;

    public void catchConsulta(Long id, User myuser) {
        var consulta = repository.findById(id).orElseThrow(
            () -> new IllegalArgumentException("Consulta não encontrada")
        );

        if(consulta.getUser() != null)
            throw new IllegalArgumentException("Consulta já atribuída");

        consulta.setUser(myuser);
        repository.save(consulta);
    }

    public void dropConsulta(Long id, User myuser) {
        var consulta = repository.findById(id).orElseThrow(
            () -> new IllegalArgumentException("Consulta não encontrada")
        );

        if(consulta.getUser() != myuser)
            throw new IllegalArgumentException("Consulta atribuída a outro usuário");


        consulta.setUser(null);
        repository.save(consulta);
    }

    public void inc(Long id, User myuser) {
        var consulta = repository.findById(id).orElseThrow(
            () -> new IllegalArgumentException("tarefa não encontrada")
        );
        if (consulta.getSituacao() +10 > 100) return;

        consulta.setSituacao(consulta.getSituacao() + 10);
        repository.save(consulta);
    }

    public void dec(Long id, User myuser) {
        var consulta = repository.findById(id).orElseThrow(
            () -> new IllegalArgumentException("tarefa não encontrada")
        );

        if (consulta.getSituacao() - 10 < 0) return;

        consulta.setSituacao(consulta.getSituacao() - 10);
        repository.save(consulta);
    }

}
