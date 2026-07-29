package com.gonnnza.mlc_backend.Repository;

import java.util.List;

import com.gonnnza.mlc_backend.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gonnnza.mlc_backend.Enum.EstadoEnum;
import com.gonnnza.mlc_backend.Model.Ticket;

@Repository
public interface TicketRepo extends JpaRepository<Ticket, Long> {
    List<Ticket> findAllByEstado(EstadoEnum estado);


}
