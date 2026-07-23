package com.gonnnza.mlc_backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gonnnza.mlc_backend.Model.Pedido;

@Repository
public interface PedidoRepo extends JpaRepository<Long, Pedido> {

}