package com.gonnnza.mlc_backend.Repository;

import com.gonnnza.mlc_backend.Model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepo
extends JpaRepository<Pedido, Long> {
}
