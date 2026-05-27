public class ArvoreBinaria {
    private No raiz;

    public ArvoreBinaria() {
        this.raiz = null;
        System.out.println("Árvore criada com sucesso.");
    }

    // ── INSERÇÃO ──────────────────────────────
    public void inserir(Integer conteudo) {
        No novoNo = new No(conteudo);
        if (estaVazia()) {
            this.raiz = novoNo;
            System.out.println("Raiz criada com sucesso. Valor: " + novoNo.getConteudo());
        } else {
            inserirRecursivo(novoNo, this.raiz);
        }
    }

    private void inserirRecursivo(No novoNo, No aux) {
        if (aux.getConteudo() > novoNo.getConteudo()) {
            if (aux.getEsquerda() == null) {
                aux.setEsquerda(novoNo);
                System.out.println("Nó " + novoNo.getConteudo() + " inserido à esquerda de " + aux.getConteudo());
            } else {
                inserirRecursivo(novoNo, aux.getEsquerda());
            }
        } else if (aux.getConteudo() < novoNo.getConteudo()) {
            if (aux.getDireita() == null) {
                aux.setDireita(novoNo);
                System.out.println("Nó " + novoNo.getConteudo() + " inserido à direita de " + aux.getConteudo());
            } else {
                inserirRecursivo(novoNo, aux.getDireita());
            }
        } else {
            System.out.println("Não são permitidos nós repetidos: " + novoNo.getConteudo());
        }
    }

    // ── REMOÇÃO ───────────────────────────────
    /*
     * Cobre todos os cenários pedidos:
     *   1) Remoção do nó raiz (qualquer um dos sub-casos abaixo aplicados à raiz)
     *   2) Nó folha (sem filhos)      → simplesmente removido
     *   3) Nó com um único filho      → substituído pelo filho
     *   4) Nó com dois filhos         → substituído pelo sucessor in-order
     *                                   (menor da sub-árvore direita)
     */
    public void remover(Integer conteudo) {
        if (estaVazia()) {
            System.out.println("A árvore está vazia. Não há nós para remover.");
            return;
        }
        this.raiz = removerRecursivo(this.raiz, conteudo);
    }

    private No removerRecursivo(No atual, Integer conteudo) {

        // Valor não encontrado
        if (atual == null) {
            System.out.println("Valor " + conteudo + " não encontrado na árvore.");
            return null;
        }

        // Navega até o nó
        if (conteudo < atual.getConteudo()) {
            atual.setEsquerda(removerRecursivo(atual.getEsquerda(), conteudo));
            return atual;
        }
        if (conteudo > atual.getConteudo()) {
            atual.setDireita(removerRecursivo(atual.getDireita(), conteudo));
            return atual;
        }

        // ── Nó encontrado ──

        // CASO 2 – Nó folha (sem filhos)
        if (atual.getEsquerda() == null && atual.getDireita() == null) {
            System.out.println("[Caso: folha] Nó " + atual.getConteudo() + " removido.");
            return null;
        }

        // CASO 3a – Apenas filho direito
        if (atual.getEsquerda() == null) {
            System.out.println("[Caso: 1 filho] Nó " + atual.getConteudo()
                    + " removido → substituído por filho direito (" + atual.getDireita().getConteudo() + ").");
            return atual.getDireita();
        }

        // CASO 3b – Apenas filho esquerdo
        if (atual.getDireita() == null) {
            System.out.println("[Caso: 1 filho] Nó " + atual.getConteudo()
                    + " removido → substituído por filho esquerdo (" + atual.getEsquerda().getConteudo() + ").");
            return atual.getEsquerda();
        }

        // CASO 4 – Dois filhos
        // Substitui o valor pelo sucessor in-order (menor da sub-árvore direita)
        // e depois remove esse sucessor recursivamente.
        No sucessor = encontrarMenor(atual.getDireita());
        System.out.println("[Caso: 2 filhos] Nó " + atual.getConteudo()
                + " removido → valor substituído pelo sucessor in-order " + sucessor.getConteudo() + ".");
        atual.setConteudo(sucessor.getConteudo());
        atual.setDireita(removerRecursivo(atual.getDireita(), sucessor.getConteudo()));
        return atual;
    }

    /** Retorna o nó com o menor valor a partir de 'no' (vai sempre à esquerda). */
    private No encontrarMenor(No no) {
        while (no.getEsquerda() != null) {
            no = no.getEsquerda();
        }
        return no;
    }

    // ── DESENHO DA ÁRVORE ─────────────────────
    public void desenharArvore() {
        if (estaVazia()) {
            System.out.println("A árvore está vazia. Nada para desenhar.");
            return;
        }
        System.out.println("\n--- ESTRUTURA DA ÁRVORE ---");
        System.out.println(this.raiz.getConteudo() + " [raiz]");
        desenharFilhos(this.raiz, "");
        System.out.println("----------------------------");
    }

    private void desenharFilhos(No no, String prefixo) {
        boolean temEsq = no.getEsquerda() != null;
        boolean temDir = no.getDireita() != null;

        if (!temEsq && !temDir) return;

        if (temEsq) {
            String conector = temDir ? "├── (E) " : "└── (E) ";
            System.out.println(prefixo + conector + no.getEsquerda().getConteudo());
            String novoPrefixo = prefixo + (temDir ? "│   " : "    ");
            desenharFilhos(no.getEsquerda(), novoPrefixo);
        }

        if (temDir) {
            System.out.println(prefixo + "└── (D) " + no.getDireita().getConteudo());
            desenharFilhos(no.getDireita(), prefixo + "    ");
        }
    }

    // ── PERCURSOS ─────────────────────────────
    public void percurso(String percurso) {
        if (estaVazia()) {
            System.out.println("A árvore está vazia.");
            return;
        }
        switch (percurso) {
            case "Pre":
                System.out.print("Pré-ordem: ");
                preOrdem(this.raiz);
                System.out.println();
                break;
            case "Em":
                System.out.print("Em-ordem: ");
                emOrdem(this.raiz);
                System.out.println();
                break;
            case "Pos":
                System.out.print("Pós-ordem: ");
                posOrdem(this.raiz);
                System.out.println();
                break;
            default:
                System.out.println("Percurso inválido!");
        }
    }

    private void preOrdem(No no) {
        if (no == null) return;
        System.out.print(no.getConteudo() + " ");
        preOrdem(no.getEsquerda());
        preOrdem(no.getDireita());
    }

    private void emOrdem(No no) {
        if (no == null) return;
        emOrdem(no.getEsquerda());
        System.out.print(no.getConteudo() + " ");
        emOrdem(no.getDireita());
    }

    private void posOrdem(No no) {
        if (no == null) return;
        posOrdem(no.getEsquerda());
        posOrdem(no.getDireita());
        System.out.print(no.getConteudo() + " ");
    }

    // ── UTILITÁRIO ────────────────────────────
    private boolean estaVazia() {
        return this.raiz == null;
    }
}