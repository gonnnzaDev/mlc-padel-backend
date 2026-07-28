package com.gonnnza.mlc_backend.Repository;

import java.util.List;

import com.gonnnza.mlc_backend.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gonnnza.mlc_backend.Enum.EstadoEnum;
import com.gonnnza.mlc_backend.Model.PedidoTicket;

@Repository
public interface TicketRepo extends JpaRepository<PedidoTicket, Long> {
    List<PedidoTicket> findAllByEstado(EstadoEnum estado);
    PedidoTicket findByUsuario(Usuario usuario);

}
