package vostore.apptualidade.testeingles;

/**
 * Created by lucas on 30/10/2017.
 */
public class QuestionBank {

    // array of questions
    private String textQuestions [] = {
            "A América Latina pediu ao governo de Nicolás Maduro que aceite ajuda humanitária, a fim de \"descomprimir\" a crise que está por trás da migração em massa de venezuelanos pela região, em reunião em Quíto, no Equador.\n" +
                    "\n" +
                    "\n\nSegundo a Organização das Nações Unidas (ONU), cerca de 2,3 milhões de venezuelanos vivem no exterior, dos quais 1,6 milhão abandonaram o país desde 2015, com a piora da escassez de medicamentos e alimentos em meio à hiperinflação que corrói os salários.",
            "Há cerca de um ano e meio, o padre Jesus, da Paróquia de Pacaraima, em Roraima, servia cerca de 80 cafés da manhã por dia para venezuelanos que cruzavam a fronteira em busca de condições melhores de vida.Hoje, a paróquia se vê forçada a ofertar 1,7 mil refeições diárias – um café com leite e um pão-, com a intensificação da crise migratória.",
            "O ativista Martin Luther King foi assassinado em 1968 após ser um dos maiores símbolos do movimento negro e liderar grande parte da luta pelos direitos da população norte-americana. Um marcante episódio na luta pela igualdade racial que ocorreu em 1965 foi: ","Um atirador foi detido nesta quarta-feira (14 de fevereiro) após deixar mortos e feridos em uma escola em Parkland, na Flórida. O xerife do condado disse que 17 pessoas morreram. Um alarme de incêndio foi disparado por volta das 14h30, pouco antes do final das aulas, e os tiros começaram em seguida.\n\n O atirador detido era um: ","O presidente Michel Temer assinou, no começo da tarde desta sexta-feira (29 de dezembro), o decreto aumentando o salário-mínimo para 954 reais a partir do dia primeiro de janeiro de 2018. O valor é 17 reais acima do salário-mínimo atual.\n" +
            "\n" +
            "\n(EBC, 29.12.17. Disponível em: <https://goo.gl/7ZmkrT>. Adaptado)\n" +
            "\n" +
            "\n\nEntre as críticas feitas no mês de janeiro de 2018 ao valor do novo salário-mínimo, é correto identificar o fato de que"


    };

    // Array as respostas
    private String multipleChoice [][] = {{"DESVALORIZAÇÃO DA MOEDA PARA MOVIMENTAR O COMÉRCIO","DEPENDENCIA DA ECONOMIA VENEZUELANA AO PETRÓLEO","FALTA DE INVESTIMENTOS NA INDÚSTRIA LOCAL, FORÇANDO O GOVERNO A IMPORTAR PRODUTOS DE PRIMEIRA NECESSIDADE, COMO A PASTA DE DENTE","INSATISFAÇÃO POPULAR COM A ELEIÇÃO DO PRESIDENTE NICÓLAS MADURA APÓS A MORTE DE HUGO CHÁVEZ\n"},
            {"PERDA DO PODER DE COMPRA DA POPULAÇÃO MOTIVADA PELA HIPERINFLAÇÃO E A ESCASSEZ DE PRODUTOS","REAÇÃO DO GOVERNO PARA FREAR A CRISE,COM OS APAGÕES DIÁRIOS","FACILIDADE DE ACESSO À PAÍSES MAIS DESENVOLVIDOS DA AMÉRICA DO SUL","REVOLTA DOS VENEZUELANOS COM A POLÍTICA FISCAL"},{"o Dia da Batalha Negra, em que os ativistas travaram guerra contra as autoridades brancas da região do Alabama.","a Marcha das Mulheres Negras pelo Direito ao Trabalho, já que na época, quando havia vagas de emprego para negros, eram direcionadas apenas aos homens.","o Domingo Sangrento, quando manifestantes negros foram atacados ao reivindicar o direito ao voto no estado do Alabama.","o Grito pela Igualdade, quando homens e mulheres foram às ruas manifestar sua indignação pela falta de trabalho, educação e serviços básicos à população negra."},{"imigrante latino originário do México que vivia em situação de pobreza.","negro órfão que morava em um bairro periférico próximo à escola.","muçulmano de origem afegã com vínculos com o Estado Islâmico.","ex-aluno da escola que havia sido expulso por motivos disciplinares."},{"o governo suspendeu o aumento, pois não seria capaz de custear o seu pagamento.","o valor nominal do salário-mínimo hoje é menor do que era antes da crise.","houve estagnação do salário-mínimo devido à recessão e à deflação.","o reajuste do salário-mínimo ficou abaixo da inflação do ano anterior."}
    };

    // array of correct answers - in the same order as array of questions
    private String mCorrectAnswers[] = {"DEPENDENCIA DA ECONOMIA VENEZUELANA AO PETRÓLEO","PERDA DO PODER DE COMPRA DA POPULAÇÃO MOTIVADA PELA HIPERINFLAÇÃO E A ESCASSEZ DE PRODUTOS","o Domingo Sangrento, quando manifestantes negros foram atacados ao reivindicar o direito ao voto no estado do Alabama.","ex-aluno da escola que havia sido expulso por motivos disciplinares.","o reajuste do salário-mínimo ficou abaixo da inflação do ano anterior."};

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