package school.sptech;

public class Validador {

    public static boolean validarExpressao(String expressao) {
        if (expressao == null) {
        }

        Pilha<Character> pilha = new Pilha<>(expressao.length());

        for (int i = 0; i < expressao.length(); i++) {
            char c = expressao.charAt(i);

            if (c == '(' || c == '[' || c == '{') {
                pilha.push(c);
                continue;
            }

            if (c == ')' || c == ']' || c == '}') {
                if (pilha.isEmpty()) {
                    return false;
                }

                char topo = pilha.pop();

                if (!corresponde(topo, c)) {
                    return false;
                }
            }
        }

        return pilha.isEmpty();
    }

    private static boolean corresponde(char abertura, char fechamento) {
        return (abertura == '(' && fechamento == ')')
                || (abertura == '[' && fechamento == ']')
                || (abertura == '{' && fechamento == '}');
    }
}
