package med.voll.api.domain.Medico;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Page<Medico> findAllByAtivoTrue(Pageable paginacao);

    @Query("""
            SELECT m from Medico m
                        where m.ativo = true
                            and m.especialidade = :especialidade
                            and m.id not in (SELECT c.medico.id FROM Consulta c
                                                where c.data = :data )
                            order by rand()
                            limit 1
            """)
    Medico escolherMedicoAleatorioNaData(Especialidade especialidade, LocalDateTime data);

    @Query("""
            SELECT m.ativo FROM Medico m
            WHERE m.id = :id
            """)
    Boolean findAtivoById(Long id);
}
