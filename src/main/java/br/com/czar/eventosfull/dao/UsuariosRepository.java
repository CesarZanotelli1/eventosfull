package br.com.czar.eventosfull.dao;

import br.com.czar.eventosfull.models.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuariosRepository extends JpaRepository<Usuarios, Integer> {
    @Query("from Usuarios t where t.email = :email and t.senha = :senha")
    Usuarios findByEmailandSenha(String email, String senha);
}
