package com.example.deliveryhamburgueriabk.model;

import com.example.deliveryhamburgueriabk.enums.FormaPagamento;
import com.example.deliveryhamburgueriabk.enums.StatusPedido;

import java.util.ArrayList;
import java.util.List;

public class Pedido {

    private int numeroPedido;
    private double valorTotal;
    private FormaPagamento formaPagamento;
    private StatusPedido statusPedido;

    private List<ItemPedido> itens = new ArrayList<>();

    public Pedido(int numeroPedido, FormaPagamento formaPagamento) {
        this.numeroPedido = numeroPedido;
        this.formaPagamento = formaPagamento;
        this.statusPedido = StatusPedido.PENDENTE;
    }

    public void adicionarItem(ItemPedido item) {
        itens.add(item);
        valorTotal += item.getSubtotal();
    }

    public int getNumeroPedido() {
        return numeroPedido;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public StatusPedido getStatusPedido() {
        return statusPedido;
    }

    public void setStatusPedido(StatusPedido statusPedido) {
        this.statusPedido = statusPedido;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }
}