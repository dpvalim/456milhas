package br.com.diogotour.milhas.domain;

import java.util.List;

public class Pedido {
    Pagamento pagamento;
    List<Passagem> passagens;
    StatusPedido status;
}
