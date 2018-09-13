package vostore.apptualidade.testeingles;

/**
 * Created by lucas on 30/10/2017.
 */
public class QuestionBank {

    // array of questions
    private String textQuestions [] = {
            "A vereadora do Rio de Janeiro Marielle Franco, assassinada em março deste ano, tinha projetos de lei que visavam proteger:","O juiz federal Leonardo da Costa Couceiro, da 4a Vara Federal de Niterói (RJ), suspendeu nesta segunda-feira, 8 de janeiro, a nomeação e a cerimônia de posse da deputada Cristiane Brasil (PTB-RJ) como nova ministra do Trabalho do governo Michel Temer. O juiz disse que a escolha da parlamentar por Michel Temer desrespeita a moralidade administrativa.\n" +
            "\n" +
            "\n\n(IstoÉ, 08.01.2018. Disponível em: <https:goo.gl/yGYRpb>. Adaptado)\n" +
            "\n" +
            "A decisão do juiz se deve ao fato de que a deputada","Um atirador foi detido nesta quarta-feira (14 de fevereiro) após deixar mortos e feridos em uma escola em Parkland, na Flórida. O xerife do condado disse que 17 pessoas morreram. Um alarme de incêndio foi disparado por volta das 14h30, pouco antes do final das aulas, e os tiros começaram em seguida.\n\n O atirador detido era um: ","O presidente Michel Temer assinou, no começo da tarde desta sexta-feira (29 de dezembro), o decreto aumentando o salário-mínimo para 954 reais a partir do dia primeiro de janeiro de 2018. O valor é 17 reais acima do salário-mínimo atual.\n" +
            "\n" +
            "\n(EBC, 29.12.17. Disponível em: <https://goo.gl/7ZmkrT>. Adaptado)\n" +
            "\n" +
            "\n\nEntre as críticas feitas no mês de janeiro de 2018 ao valor do novo salário-mínimo, é correto identificar o fato de que"


    };

    // Array as respostas
    private String multipleChoice [][] = {
            {"Causas animais e proteção da flora brasileira","Idosos, aposentadoria e INSS","Homens e mulheres contra o assédio, criação de novas  creches, entre outros aspectos sociais","A manutenção constante das rodovias e\n" +
                    "principais avenidas do Rio"},{"foi denunciada por lavagem de dinheiro.","é suspeita de envolvimento com caixa 2.","tem um histórico de condenações por corrupção.","foi condenada pela Justiça trabalhista."},{"imigrante latino originário do México que vivia em situação de pobreza.","negro órfão que morava em um bairro periférico próximo à escola.","muçulmano de origem afegã com vínculos com o Estado Islâmico.","ex-aluno da escola que havia sido expulso por motivos disciplinares."},{"o governo suspendeu o aumento, pois não seria capaz de custear o seu pagamento.","o valor nominal do salário-mínimo hoje é menor do que era antes da crise.","houve estagnação do salário-mínimo devido à recessão e à deflação.","o reajuste do salário-mínimo ficou abaixo da inflação do ano anterior."}
    };

    // array of correct answers - in the same order as array of questions
    private String mCorrectAnswers[] = {"Homens e mulheres contra o assédio, criação de novas  creches, entre outros aspectos sociais","foi condenada pela Justiça trabalhista.","ex-aluno da escola que havia sido expulso por motivos disciplinares.","o reajuste do salário-mínimo ficou abaixo da inflação do ano anterior."};

    // method returns number of questions
    public int getLength(){
        return textQuestions.length;
    }

    // method returns question from array textQuestions[] based on array index
    public String getQuestion(int a) {
        String question = textQuestions[a];
        return question;
    }

    // method return a single multiple choice item for question based on array index,
    // based on number of multiple choice item in the list - 1, 2, 3 or 4 as an argument
    public String getChoice(int index, int num) {
        String choice0 = multipleChoice[index][num-1];
        return choice0;
    }

    //  method returns correct answer for the question based on array index
    public String getCorrectAnswer(int a) {
        String answer = mCorrectAnswers[a];
        return answer;
    }
}