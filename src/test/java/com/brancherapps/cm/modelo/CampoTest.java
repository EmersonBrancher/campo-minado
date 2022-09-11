package com.brancherapps.cm.modelo;


import com.brancherapps.cm.excecao.ExplosaoException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CampoTest {

    private Campo campo = new Campo(3, 3);

    @BeforeEach
    void iniciarCampo() {
        Campo campo = new Campo(3, 3);
    }

    @Test
    void testeVizinhoDistancia1Esquerda() {
        Campo vizinho = new Campo(3, 2);
        boolean resultado = campo.adicionarVizinho(vizinho);
        Assertions.assertTrue(resultado);
    }

    @Test
    void testeVizinhoDistancia1Direita() {
        Campo vizinho = new Campo(3, 4);
        boolean resultado = campo.adicionarVizinho(vizinho);
        Assertions.assertTrue(resultado);
    }

    @Test
    void testeVizinhoDistancia1Emcima() {
        Campo vizinho = new Campo(2, 3);
        boolean resultado = campo.adicionarVizinho(vizinho);
        Assertions.assertTrue(resultado);
    }

    @Test
    void testeVizinhoDistancia1Embaixo() {
        Campo vizinho = new Campo(4, 3);
        boolean resultado = campo.adicionarVizinho(vizinho);
        Assertions.assertTrue(resultado);
    }

    @Test
    void testeVizinhoDistancia2EmcimaEsquerda() {
        Campo vizinho = new Campo(2, 2);
        boolean resultado = campo.adicionarVizinho(vizinho);
        Assertions.assertTrue(resultado);
    }

    @Test
    void testeVizinhoDistancia2EmcimaDireita() {
        Campo vizinho = new Campo(2, 4);
        boolean resultado = campo.adicionarVizinho(vizinho);
        Assertions.assertTrue(resultado);
    }

    @Test
    void testeVizinhoDistancia2EmBaixoEsquerda() {
        Campo vizinho = new Campo(2, 4);
        boolean resultado = campo.adicionarVizinho(vizinho);
        Assertions.assertTrue(resultado);
    }

    @Test
    void testeVizinhoDistancia2EmBaixoDireita() {
        Campo vizinho = new Campo(4, 4);
        boolean resultado = campo.adicionarVizinho(vizinho);
        Assertions.assertTrue(resultado);
    }

    @Test
    void testeNaoVizinho() {
        Campo vizinho = new Campo(1, 1);
        boolean resultado = campo.adicionarVizinho(vizinho);
        Assertions.assertFalse(resultado);
    }

    @Test
    void testeValorPadraoAtributoMarcado() {
        Assertions.assertFalse(campo.isMarcado());
    }

    @Test
    void testeAlternarMarcacao() {
        campo.alternarMarcacao();
        Assertions.assertTrue(campo.isMarcado());
    }

    @Test
    void testeAlternarMarcacaoDuasChamadas() {
        campo.alternarMarcacao();
        campo.alternarMarcacao();
        Assertions.assertFalse(campo.isMarcado());
    }

    @Test
    void testeAbrirNaoMinadoNaoMarcado() {
        Assertions.assertTrue(campo.abrir());
    }

    @Test
    void testeAbrirNaoMinadoMarcado() {
        campo.alternarMarcacao();
        Assertions.assertFalse(campo.abrir());
    }

    @Test
    void testeAbrirMinadoMarcado() {
        campo.alternarMarcacao();
        campo.minar();
        Assertions.assertFalse(campo.abrir());
    }

    @Test
    void testeAbrirMinadoNaoMarcado() {
        campo.minar();

        Assertions.assertThrows(ExplosaoException.class, () -> {
            campo.abrir();
        });
    }

    @Test
    void testeAbrurComVizinhos1() {
        Campo campo11 = new Campo(1, 1);
        Campo campo22 = new Campo(2, 2);

        campo22.adicionarVizinho(campo11);

        campo.adicionarVizinho(campo22);

        campo.abrir();

        Assertions.assertTrue(campo22.isAberto() && campo11.isAberto());
    }

    @Test
    void testeAbrurComVizinhos2() {
        Campo campo11 = new Campo(1, 1);
        Campo campo12 = new Campo(1, 2);
        campo12.minar();

        Campo campo22 = new Campo(2, 2);
        campo22.adicionarVizinho(campo11);
        campo22.adicionarVizinho(campo12);

        campo.adicionarVizinho(campo22);
        campo.abrir();

        Assertions.assertTrue(campo22.isAberto() && campo11.isFechado());
    }

    @Test
    void testeGetLinhaGetColuna() {
        Assertions.assertEquals(3, campo.getLinha());
        Assertions.assertEquals(3, campo.getColuna());

    }

    @Test
    void testeReiniciado() {
        campo.abrir();
        campo.minar();
        campo.marcar();
        campo.reiniciar();
        Assertions.assertFalse(campo.isAberto());
        Assertions.assertFalse(campo.isMinado());
        Assertions.assertFalse(campo.isMarcado());
    }

    @Test
    void testeToStringMarcado() {
        campo.marcar();
        Assertions.assertEquals("x", campo.toString());
    }

    @Test
    void testeToStringAbertoEMinado() {
        campo.abrir();
        campo.minar();
        Assertions.assertEquals("*", campo.toString());
    }

    @Test
    void testeToStringAbertoComMinasNaVizinhanca() {
        campo.abrir();
        Campo campo44 = new Campo(4, 4);
        campo44.minar();
        campo.adicionarVizinho(campo44);
        Assertions.assertEquals("1", campo.toString());
    }

    @Test
    void testeToStringAberto() {
        campo.abrir();
        Assertions.assertEquals(" ", campo.toString());
    }

    @Test
    void testeToStringSemInteracao() {
        Assertions.assertEquals("?", campo.toString());
    }

    @Test
    void testeObjetivoAlcancadoDesvendado() {
        campo.abrir();
        Assertions.assertTrue(campo.objetivoAlcancado());
    }

    @Test
    void testeObjetivoAlcancadoProtegido() {
        campo.minar();
        campo.marcar();
        Assertions.assertTrue(campo.objetivoAlcancado());
    }

}
