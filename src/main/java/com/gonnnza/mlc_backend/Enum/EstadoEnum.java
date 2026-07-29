package com.gonnnza.mlc_backend.Enum;


/*
 *
 *  Pendiente -> Estado antes de pagar
 *
 *  Rechazado -> Estado despues de pagar rechazado
 *  Pagado -> Estado despues de pagar aceptado
 *
 *  Cancelado -> Vendedor decide cancelar un pedido
 *  Realizado -> Estado final si todo sale bien
 * */


public enum EstadoEnum {
    CANCELADO, PENDIENTE, RECHAZADO, PAGADO, REALIZADO
}
