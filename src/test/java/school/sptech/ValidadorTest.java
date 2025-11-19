package school.sptech;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import static org.junit.jupiter.api.Assertions.*;

class ValidadorTest {

    @Nested
    @DisplayName("Casos Válidos")
    class CasosValidos {

        @Test
        @DisplayName("Deve validar expressão vazia")
        void deveValidarExpressaoVazia() {
            assertTrue(Validador.validarExpressao(""));
        }

        @Test
        @DisplayName("Deve validar parênteses simples")
        void deveValidarParentesesSimples() {
            assertTrue(Validador.validarExpressao("()"));
        }

        @Test
        @DisplayName("Deve validar colchetes simples")
        void deveValidarColchetesSimples() {
            assertTrue(Validador.validarExpressao("[]"));
        }

        @Test
        @DisplayName("Deve validar chaves simples")
        void deveValidarChavesSimples() {
            assertTrue(Validador.validarExpressao("{}"));
        }

        @Test
        @DisplayName("Deve validar múltiplos pares sequenciais")
        void deveValidarMultiplosPares() {
            assertTrue(Validador.validarExpressao("()[]{}"));
        }

        @Test
        @DisplayName("Deve validar aninhamento correto")
        void deveValidarAninhamento() {
            assertTrue(Validador.validarExpressao("({[]})"));
        }

        @Test
        @DisplayName("Deve validar aninhamento profundo")
        void deveValidarAninhamentoProfundo() {
            assertTrue(Validador.validarExpressao("(((())))"));
            assertTrue(Validador.validarExpressao("[[[[]]]]"));
            assertTrue(Validador.validarExpressao("{{{{}}}}"));
        }

        @Test
        @DisplayName("Deve validar expressão matemática simples")
        void deveValidarExpressaoMatematica() {
            assertTrue(Validador.validarExpressao("(a + b)"));
        }

        @Test
        @DisplayName("Deve validar expressão matemática complexa")
        void deveValidarExpressaoCompleta() {
            assertTrue(Validador.validarExpressao("(a + b) * [c - {d / e}]"));
        }

        @Test
        @DisplayName("Deve validar múltiplos níveis misturados")
        void deveValidarMultiplosNiveisMisturados() {
            assertTrue(Validador.validarExpressao("({[()]})"));
            assertTrue(Validador.validarExpressao("[{()}]"));
            assertTrue(Validador.validarExpressao("{[()]}"));
        }

        @Test
        @DisplayName("Deve ignorar caracteres não-bracket")
        void deveIgnorarCaracteresComuns() {
            assertTrue(Validador.validarExpressao("abc123"));
            assertTrue(Validador.validarExpressao("if (x > 0) { return true; }"));
        }

        @Test
        @DisplayName("Deve validar expressão com espaços")
        void deveValidarComEspacos() {
            assertTrue(Validador.validarExpressao("( [ ] )"));
            assertTrue(Validador.validarExpressao("  (  )  "));
        }
    }

    @Nested
    @DisplayName("Casos Inválidos - Ordem Errada")
    class CasosInvalidosOrdem {

        @Test
        @DisplayName("Deve rejeitar ordem errada simples")
        void deveRejeitarOrdemErrada() {
            assertFalse(Validador.validarExpressao("(]"));
            assertFalse(Validador.validarExpressao("[}"));
            assertFalse(Validador.validarExpressao("{)"));
        }

        @Test
        @DisplayName("Deve rejeitar fechamento antes de abertura")
        void deveRejeitarFechamentoAntes() {
            assertFalse(Validador.validarExpressao(")("));
            assertFalse(Validador.validarExpressao("]["));
            assertFalse(Validador.validarExpressao("}{"));
        }

        @Test
        @DisplayName("Deve rejeitar cruzamento de brackets")
        void deveRejeitarCruzamento() {
            assertFalse(Validador.validarExpressao("([)]"));
            assertFalse(Validador.validarExpressao("({[}])"));
            assertFalse(Validador.validarExpressao("[(])"));
        }

        @Test
        @DisplayName("Deve rejeitar ordem incorreta em expressão")
        void deveRejeitarOrdemIncorretaEmExpressao() {
            assertFalse(Validador.validarExpressao("(a + b] * c"));
        }
    }

    @Nested
    @DisplayName("Casos Inválidos - Desbalanceamento")
    class CasosInvalidosDesbalanceamento {

        @Test
        @DisplayName("Deve rejeitar apenas abertura")
        void deveRejeitarApenasAbertura() {
            assertFalse(Validador.validarExpressao("("));
            assertFalse(Validador.validarExpressao("["));
            assertFalse(Validador.validarExpressao("{"));
        }

        @Test
        @DisplayName("Deve rejeitar apenas fechamento")
        void deveRejeitarApenasFechamento() {
            assertFalse(Validador.validarExpressao(")"));
            assertFalse(Validador.validarExpressao("]"));
            assertFalse(Validador.validarExpressao("}"));
        }

        @Test
        @DisplayName("Deve rejeitar mais aberturas que fechamentos")
        void deveRejeitarMaisAberturas() {
            assertFalse(Validador.validarExpressao("(()"));
            assertFalse(Validador.validarExpressao("[[[]"));
            assertFalse(Validador.validarExpressao("{{{}}"));
        }

        @Test
        @DisplayName("Deve rejeitar mais fechamentos que aberturas")
        void deveRejeitarMaisFechamentos() {
            assertFalse(Validador.validarExpressao("())"));
            assertFalse(Validador.validarExpressao("[]]"));
            assertFalse(Validador.validarExpressao("{}}}"));
        }

        @Test
        @DisplayName("Deve rejeitar múltiplas aberturas sem fechamento")
        void deveRejeitarMultiplasAberturasNaoFechadas() {
            assertFalse(Validador.validarExpressao("((([[[{{{"));
        }

        @Test
        @DisplayName("Deve rejeitar múltiplos fechamentos sem abertura")
        void deveRejeitarMultiplosFechamentosSemAbertura() {
            assertFalse(Validador.validarExpressao("}}}]]])"));
        }
    }

    @Nested
    @DisplayName("Casos Especiais")
    class CasosEspeciais {

        @Test
        @DisplayName("Deve validar string com apenas letras")
        void deveValidarApenasLetras() {
            assertTrue(Validador.validarExpressao("abcdefg"));
        }

        @Test
        @DisplayName("Deve validar string com apenas números")
        void deveValidarApenasNumeros() {
            assertTrue(Validador.validarExpressao("1234567"));
        }

        @Test
        @DisplayName("Deve validar expressão de código real")
        void deveValidarCodigoReal() {
            assertTrue(Validador.validarExpressao("if (x > 0 && y < 10) { z = array[index]; }"));
        }

        @Test
        @DisplayName("Deve rejeitar código com erro")
        void deveRejeitarCodigoComErro() {
            assertFalse(Validador.validarExpressao("if (x > 0 { z = array[index]; }"));
        }

        @Test
        @DisplayName("Deve validar JSON válido simplificado")
        void deveValidarJSONSimplificado() {
            assertTrue(Validador.validarExpressao("{\"name\": \"value\", \"array\": [1, 2, 3]}"));
        }

        @Test
        @DisplayName("Deve rejeitar JSON inválido")
        void deveRejeitarJSONInvalido() {
            assertFalse(Validador.validarExpressao("{\"name\": \"value\", \"array\": [1, 2, 3}"));
        }

        @Test
        @DisplayName("Deve validar um único par no meio de texto")
        void deveValidarParNoMeioDeTexto() {
            assertTrue(Validador.validarExpressao("texto (importante) mais texto"));
        }

        @Test
        @DisplayName("Deve validar alternância de tipos")
        void deveValidarAlternancia() {
            assertTrue(Validador.validarExpressao("()[]{}()[]{}"));
        }
    }

    @Nested
    @DisplayName("Casos Extremos")
    class CasosExtremos {

        @Test
        @DisplayName("Deve validar expressão muito longa válida")
        void deveValidarExpressaoLonga() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 100; i++) {
                sb.append("(");
            }
            for (int i = 0; i < 100; i++) {
                sb.append(")");
            }
            assertTrue(Validador.validarExpressao(sb.toString()));
        }

        @Test
        @DisplayName("Deve rejeitar expressão longa com um erro no final")
        void deveRejeitarExpressaoLongaComErro() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 100; i++) {
                sb.append("(");
            }
            for (int i = 0; i < 99; i++) {
                sb.append(")");
            }
            assertFalse(Validador.validarExpressao(sb.toString()));
        }

        @Test
        @DisplayName("Deve validar padrão complexo válido")
        void deveValidarPadraoComplexo() {
            assertTrue(Validador.validarExpressao("({[()]})[{()}]({[]})"));
        }

        @Test
        @DisplayName("Deve rejeitar erro sutil no meio")
        void deveRejeitarErroSutil() {
            assertFalse(Validador.validarExpressao("({[()]})[{(})]({[]})"));
        }
    }

    @Nested
    @DisplayName("Testes de Robustez")
    class TestesRobustez {

        @Test
        @DisplayName("Deve lidar com caracteres especiais")
        void deveLidarComCaracteresEspeciais() {
            assertTrue(Validador.validarExpressao("(a + b) * @#$% [c]"));
        }

        @Test
        @DisplayName("Deve validar com quebras de linha")
        void deveValidarComQuebrasDeLinha() {
            assertTrue(Validador.validarExpressao("(\n[\n]\n)"));
        }

        @Test
        @DisplayName("Deve validar com tabs")
        void deveValidarComTabs() {
            assertTrue(Validador.validarExpressao("(\t[\t]\t)"));
        }

        @Test
        @DisplayName("Deve validar expressão matemática completa")
        void deveValidarExpressaoMatematicaCompleta() {
            String expr = "((a + b) * (c - d)) / [(e + f) - {g * h}]";
            assertTrue(Validador.validarExpressao(expr));
        }
    }
}