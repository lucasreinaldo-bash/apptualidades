package vostore.apptualidade.Simulado;

/**
 * Created by lucas on 30/10/2017.
 */
public class QuestionBank {

    // array of questions
    private String textQuestions [] = {
            "O projeto de lei 6.299/2002 apresenta diversas mudanças relacionadas ao uso, controle e registro dos agrotóxicos no Brasil.Uma das alterações mais preocupantes relacionada aos riscos e possíveis problemas de saúde da população é: ",
            "O envelhecimento populacional pode ser explicado como decorrência de dois fatores:","Há cerca de um ano e meio, o padre Jesus, da Paróquia de Pacaraima, em Roraima, servia cerca de 80 cafés da manhã por dia para venezuelanos que cruzavam a fronteira em busca de condições melhores de vida. Hoje, a paróquia se vê forçada a ofertar 1,7 mil refeições diárias – um café com leite e um pão –, com a intensificação da crise migratória.",
            "O ativista Martin Luther King foi assassinado em 1968 após ser um dos maiores símbolos do movimento negro e liderar grande parte da luta pelos direitos da população norte-americana. Um marcante episódio na luta pela igualdade racial que ocorreu em 1965 foi: ",
            ".","A greve dos caminhoneiros, que aconteceu em maio deste ano, paralisou o país por cerca de 10 dias no Brasil. Os protestantes reivindicavam algumas questões, como:"

    };

    // Array as respostas
    private String multipleChoice [][] = {{"A LIBERAÇÃO INDISCRIMINADA DO PESTICIDA “LINDANO”, QUE TEM POTENCIAL CANCERÍGENO.","A PROIBIÇÃO DO PLANTIO DE MILHO GENETICAMENTE\n" +
            "MODIFICADO, QUE ACARRETA EM GRANDES PROBLEMAS DE SAÚDE.","A RENÚNCIA DA APROVAÇÃO DAS PASTAS DA SAÚDE E DO MEIO AMBIENTE, DEIXANDO O CONTROLE DOS AGROTÓXICOS APENAS NAS MÃOS DO MINISTÉRIO DA AGRICULTURA.","A DIMINUIÇÃO DO LIMITE MÁXIMO DE RESÍDUOS (LMR)\n" +
            "DE AGROTÓXICO - DEFINIDO PELA ANVISA –\n" +
            "EM TODOS OS PRODUTOS."},

            {"A MELHORIA DAS CONDIÇÕES DE SAÚDE E A QUEDA DA TAXA DE FECUNDIDADE","A MELHORIA DAS CONDIÇÕES DE SAÚDE E A UNIÃO FAMILIAR.","O APRIMORAMENTO INTELECTUAL E O INVESTIMENTO EM ATIVIDADES FÍSICAS QUANDO JOVENS.","A UNIÃO FAMILIAR E OS CUIDADOS COM OS MAIS VELHOS."},
            {"PERDA DO PODER DE COMPRA DA POPULAÇÃO MOTIVADA PELA HIPERINFLAÇÃO E A ESCASSEZ DE PRODUTOS","REAÇÃO DO GOVERNO PARA FREAR A CRISE COMO OS APAGÕES DIÁRIOS","FACILIDADE DE ACESSO À PAÍSES MAIS DESENVOLVIDOS DA AMÉRICA DO SUL","REVOLTA DOS VENEZUELANOS COM A POLÍTICA FISCAL"},
            {"O DIA DA BATALHA NEGRA, EM QUE OS ATIVISTAS TRAVARAM GUERRA CONTRA AS AUTORIDADES BRANCAS DA REGIÃO DO ALABAMA.","A MARCHA DAS MULHERES NEGRAS PELO DIREITO AO TRABALHO, JÁ QUE NA ÉPOCA, QUANDO HAVIA VAGAS DE EMPREGO PARA NEGROS, ERAM DIRECIONADAS APENAS AOS HOMENS.","O DOMINGO SANGRENTO, QUANDO MANIFESTANTES NEGROS FORAM ATACADOS AO REIVINDICAR O DIREITO AO VOTO NO ESTADO DO ALABAMA.","O GRITO PELA IGUALDADE, QUANDO HOMENS E MULHERES FORAM ÀS RUAS MANIFESTAR SUA INDIGNAÇÃO PELA FALTA DE TRABALHO, EDUCAÇÃO E SERVIÇOS BÁSICOS À POPULAÇÃO NEGRA."},
            {"PELO AUMENTO DE AMBIENTES FAVORÁVEIS À CONTAMINAÇÃO, COMO LOCAIS ÚMIDOS, ÁGUA PARADA EM LATAS, PNEUS E VASOS, PERMITINDO A PROLIFERAÇÃO DO MOSQUITO TRANSMISSOR ANOPHELES EM ÁREAS URBANAS.","AO MAIOR CONTATO DOS HUMANOS COM MACACOS EM PARQUES, MATAS E ZOOLÓGICOS, UMA VEZ QUE ELES SÃO OS PRINCIPAIS TRANSMISSORES DO VÍRUS E RESPONSÁVEIS PELA CONTAMINAÇÃO.","DEVIDO À EVOLUÇÃO DO VÍRUS, QUE PASSOU A SE ESPALHAR RAPIDAMENTE. EM ORGANISMOS DE BAIXA RESISTÊNCIA, O CONTÁGIO PODE ACONTECER POR MEIO DO AR, CONTATO COM ALGUÉM CONTAMINADO, ENTRE OUTROS FATORES.","PELA PROLIFERAÇÃO DO VÍRUS TAMBÉM EM ÁREAS URBANAS. ANTES, A CONTAMINAÇÃO ERA MAJORITARIAMENTE EM ÁREAS SILVESTRES, PRÓXIMAS A MATAS E FLORESTAS."},
            {"A e B","B e D","C e A","C e D"}
    };

    // array of correct answers - in the same order as array of questions
    private String mCorrectAnswers[] = {"A RENÚNCIA DA APROVAÇÃO DAS PASTAS DA SAÚDE E DO MEIO AMBIENTE, DEIXANDO O CONTROLE DOS AGROTÓXICOS APENAS NAS MÃOS DO MINISTÉRIO DA AGRICULTURA.","A MELHORIA DAS CONDIÇÕES DE SAÚDE E A QUEDA DA TAXA DE FECUNDIDADE","PERDA DO PODER DE COMPRA DA POPULAÇÃO MOTIVADA PELA HIPERINFLAÇÃO E A ESCASSEZ DE PRODUTOS","O DOMINGO SANGRENTO, QUANDO MANIFESTANTES NEGROS FORAM ATACADOS AO REIVINDICAR O DIREITO AO VOTO NO ESTADO DO ALABAMA.","PELA PROLIFERAÇÃO DO VÍRUS TAMBÉM EM ÁREAS URBANAS. ANTES, A CONTAMINAÇÃO ERA MAJORITARIAMENTE EM ÁREAS SILVESTRES, PRÓXIMAS A MATAS E FLORESTAS.","B e D"};

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